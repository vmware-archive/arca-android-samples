package com.rottentomatoes.app.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;

import com.rottentomatoes.app.operations.SyncMoviesOperation;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider;

import io.pivotal.arca.service.OperationService;

public class RottenTomatoesSyncAdapter extends AbstractThreadedSyncAdapter {

	private static final long MANUAL_SYNC_INTERVAL = 5 * 1000;

    private long mLastSyncTime = 0;

    public RottenTomatoesSyncAdapter(final Context context, final boolean autoInitialize) {
		super(context, autoInitialize);
	}

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        if (System.currentTimeMillis() > (mLastSyncTime + MANUAL_SYNC_INTERVAL)) {
            OperationService.start(getContext(), new SyncMoviesOperation(createMoviesUri("box_office")));
            OperationService.start(getContext(), new SyncMoviesOperation(createMoviesUri("in_theaters")));
            OperationService.start(getContext(), new SyncMoviesOperation(createMoviesUri("opening")));
            OperationService.start(getContext(), new SyncMoviesOperation(createMoviesUri("upcoming")));
            mLastSyncTime = System.currentTimeMillis();
        }
    }

	private static Uri createMoviesUri(final String type) {
		return Uri.withAppendedPath(RottenTomatoesContentProvider.Uris.MOVIES_URI, type);
	}
}
