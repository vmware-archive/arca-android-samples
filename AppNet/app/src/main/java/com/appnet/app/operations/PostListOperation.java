package com.appnet.app.operations;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.appnet.app.application.AppNetRequests;
import com.appnet.app.models.PostsResponse;

import io.pivotal.arca.dispatcher.ErrorBroadcaster;
import io.pivotal.arca.provider.DataUtils;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.SimpleOperation;

public class PostListOperation extends SimpleOperation {


	public PostListOperation(final Uri uri) {
		super(uri);
	}

	public PostListOperation(final Parcel in) {
		super(in);
	}

    @Override
    public ContentValues[] onExecute(final Context context) throws Exception {
        final PostsResponse response = AppNetRequests.getPostList(100);
        return DataUtils.getContentValues(response.getData());
    }

	@Override
	public void onFailure(final Context context, final ServiceError error) {
		ErrorBroadcaster.broadcast(context, getUri(), error.getCode(), error.getMessage());
	}
	
	public static final Parcelable.Creator<PostListOperation> CREATOR = new Parcelable.Creator<PostListOperation>() {
		@Override
		public PostListOperation createFromParcel(final Parcel in) {
			return new PostListOperation(in);
		}

		@Override
		public PostListOperation[] newArray(final int size) {
			return new PostListOperation[size];
		}
	};

}
