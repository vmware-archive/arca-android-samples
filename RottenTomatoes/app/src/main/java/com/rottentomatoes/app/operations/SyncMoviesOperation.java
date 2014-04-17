package com.rottentomatoes.app.operations;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.rottentomatoes.app.providers.RottenTomatoesContentProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.pivotal.arca.service.Operation;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.Task;

public class SyncMoviesOperation extends Operation {

	public SyncMoviesOperation(final Uri uri) {
		super(uri);
	}

	public SyncMoviesOperation(final Parcel in) {
		super(in);
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		super.writeToParcel(dest, flags);
	}
	
	@Override
	public Set<Task<?>> onCreateTasks() {
		final Set<Task<?>> set = new HashSet<Task<?>>();
		final String type = getUri().getLastPathSegment();
		set.add(new MovieListTask(type));
		return set;
	}

	@Override
	public void onSuccess(final Context context, final List<Task<?>> completed) {
		final ContentResolver resolver = context.getContentResolver();
		resolver.notifyChange(RottenTomatoesContentProvider.Uris.MOVIES_URI, null, false);
		resolver.notifyChange(RottenTomatoesContentProvider.Uris.MOVIE_TYPES_URI, null, false);
	}

	@Override
	public void onFailure(final Context context, final ServiceError error) {

	}

	public static final Parcelable.Creator<SyncMoviesOperation> CREATOR = new Parcelable.Creator<SyncMoviesOperation>() {
		@Override
		public SyncMoviesOperation createFromParcel(final Parcel in) {
			return new SyncMoviesOperation(in);
		}

		@Override
		public SyncMoviesOperation[] newArray(final int size) {
			return new SyncMoviesOperation[size];
		}
	};
}
