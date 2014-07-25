package com.crunchbase.app.operations;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import io.pivotal.arca.dispatcher.ErrorBroadcaster;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.SimpleOperation;

public class CompanyOperation extends SimpleOperation {

	public CompanyOperation(final Uri uri) {
		super(uri);
	}

	public CompanyOperation(final Parcel in) {
		super(in);
	}

    @Override
    public ContentValues[] onExecute(final Context context) throws Exception {
        throw new Exception("Override this method to return a json string for a Company.");
    }

	@Override
	public void onFailure(final Context context, final ServiceError error) {
		ErrorBroadcaster.broadcast(context, getUri(), error.getCode(), error.getMessage());
	}
	
	public static final Parcelable.Creator<CompanyOperation> CREATOR = new Parcelable.Creator<CompanyOperation>() {
		@Override
		public CompanyOperation createFromParcel(final Parcel in) {
			return new CompanyOperation(in);
		}

		@Override
		public CompanyOperation[] newArray(final int size) {
			return new CompanyOperation[size];
		}
	};

}
