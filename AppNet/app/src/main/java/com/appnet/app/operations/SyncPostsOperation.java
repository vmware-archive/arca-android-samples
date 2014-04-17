package com.appnet.app.operations;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.pivotal.arca.service.Operation;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.Task;

public class SyncPostsOperation extends Operation {

	public SyncPostsOperation(final Uri uri) {
		super(uri);
	}

	public SyncPostsOperation(final Parcel in) {
		super(in);
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		super.writeToParcel(dest, flags);
	}
	
	@Override
	public Set<Task<?>> onCreateTasks() {
		final Set<Task<?>> set = new HashSet<Task<?>>();
		set.add(new PostListTask());
		return set;
	}

	@Override
	public void onSuccess(final Context context, final List<Task<?>> completed) {
		final ContentResolver resolver = context.getContentResolver();
		resolver.notifyChange(getUri(), null, false);
	}

	@Override
	public void onFailure(final Context context, final ServiceError error) {
	}

	public static final Parcelable.Creator<SyncPostsOperation> CREATOR = new Parcelable.Creator<SyncPostsOperation>() {
		@Override
		public SyncPostsOperation createFromParcel(final Parcel in) {
			return new SyncPostsOperation(in);
		}

		@Override
		public SyncPostsOperation[] newArray(final int size) {
			return new SyncPostsOperation[size];
		}
	};
}
