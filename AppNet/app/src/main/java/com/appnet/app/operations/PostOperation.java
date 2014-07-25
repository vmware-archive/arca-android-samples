package com.appnet.app.operations;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import io.pivotal.arca.dispatcher.ErrorBroadcaster;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.SimpleOperation;

public class PostOperation extends SimpleOperation {

	public PostOperation(final Uri uri) {
		super(uri);
	}

	public PostOperation(final Parcel in) {
		super(in);
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		super.writeToParcel(dest, flags);
	}

    @Override
    public ContentValues[] onExecute(final Context context) throws Exception {
        throw new Exception("Override this method to return a json string for a Post.");
    }

	@Override
	public void onFailure(final Context context, final ServiceError error) {
		ErrorBroadcaster.broadcast(context, getUri(), error.getCode(), error.getMessage());
	}
	
	public static final Parcelable.Creator<PostOperation> CREATOR = new Parcelable.Creator<PostOperation>() {
		@Override
		public PostOperation createFromParcel(final Parcel in) {
			return new PostOperation(in);
		}

		@Override
		public PostOperation[] newArray(final int size) {
			return new PostOperation[size];
		}
	};

}
