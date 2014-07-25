package com.crunchbase.app.operations;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.crunchbase.app.application.CrunchBaseRequests;
import com.crunchbase.app.models.Company;
import com.crunchbase.app.models.SearchResponse;
import com.crunchbase.app.providers.CrunchBaseContentProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.pivotal.arca.dispatcher.ErrorBroadcaster;
import io.pivotal.arca.provider.DataUtils;
import io.pivotal.arca.service.Operation;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.Task;
import io.pivotal.arca.threading.Identifier;

public class CompanyListOperation extends Operation {

	public CompanyListOperation(final Uri uri) {
		super(uri);
	}

	public CompanyListOperation(final Parcel in) {
		super(in);
	}

	@Override
	public Set<Task<?>> onCreateTasks() {
		final Set<Task<?>> set = new HashSet<Task<?>>();
		set.add(new CompanyListTask(1));
		return set;
	}

	@Override
	public void onSuccess(final Context context, final List<Task<?>> completed) {
		final ContentResolver resolver = context.getContentResolver();
		resolver.notifyChange(getUri(), null);
	}

	@Override
	public void onFailure(final Context context, final ServiceError error) {
		ErrorBroadcaster.broadcast(context, getUri(), error.getCode(), error.getMessage());
	}
	
	public static final Parcelable.Creator<CompanyListOperation> CREATOR = new Parcelable.Creator<CompanyListOperation>() {
		@Override
		public CompanyListOperation createFromParcel(final Parcel in) {
			return new CompanyListOperation(in);
		}

		@Override
		public CompanyListOperation[] newArray(final int size) {
			return new CompanyListOperation[size];
		}
	};

    public static class CompanyListTask extends Task<List<Company>> {

        private final int mPage;

        public CompanyListTask(final int page) {
            mPage = page;
        }

        @Override
        public Identifier<?> onCreateIdentifier() {
            return new Identifier<String>("company_list:" + mPage);
        }

        @Override
        public List<Company> onExecuteNetworking(final Context context) throws Exception {
            final SearchResponse response = CrunchBaseRequests.getSearchResults("toronto", mPage);
            final int page = response.getNextPage();
            if (page > 0) {
                addDependency(new CompanyListTask(page));
            }
            return response.getResults();
        }

        @Override
        public void onExecuteProcessing(final Context context, final List<Company> data) throws Exception {
            final ContentValues[] values = DataUtils.getContentValues(data);
            final ContentResolver resolver = context.getContentResolver();
            resolver.bulkInsert(CrunchBaseContentProvider.Uris.COMPANIES_URI, values);
            resolver.notifyChange(CrunchBaseContentProvider.Uris.COMPANIES_URI, null);
        }
    }

}
