package com.rottentomatoes.app.operations;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import io.pivotal.arca.dispatcher.ErrorBroadcaster;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.SimpleOperation;

public class MovieOperation extends SimpleOperation {

	public MovieOperation(final Uri uri) {
		super(uri);
	}

	public MovieOperation(final Parcel in) {
		super(in);
	}

    @Override
    public ContentValues[] onExecute(Context context) throws Exception {
        throw new Exception("Override this method to return a json string for a Movie.");
    }

	@Override
	public void onFailure(final Context context, final ServiceError error) {
		ErrorBroadcaster.broadcast(context, getUri(), error.getCode(), error.getMessage());
	}
	
	public static final Parcelable.Creator<MovieOperation> CREATOR = new Parcelable.Creator<MovieOperation>() {
		@Override
		public MovieOperation createFromParcel(final Parcel in) {
			return new MovieOperation(in);
		}

		@Override
		public MovieOperation[] newArray(final int size) {
			return new MovieOperation[size];
		}
	};

}
