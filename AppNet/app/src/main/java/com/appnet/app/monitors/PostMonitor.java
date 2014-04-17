package com.appnet.app.monitors;

import android.content.Context;
import android.net.Uri;

import com.appnet.app.operations.PostOperation;
import io.pivotal.arca.dispatcher.Query;
import io.pivotal.arca.dispatcher.QueryResult;
import io.pivotal.arca.monitor.RequestMonitor.AbstractRequestMonitor;
import io.pivotal.arca.service.OperationService;

public class PostMonitor extends AbstractRequestMonitor {

	@Override
	public int onPostExecute(final Context context, final Query request, final QueryResult result) {
		final int count = result.getResult().getCount();
		if (count == 0 && syncDataFromNetwork(context, request.getUri())) {
			return Flags.DATA_SYNCING; 
		} else {
			return 0;
		}
	}
	
	public boolean syncDataFromNetwork(final Context context, final Uri uri) {
		final boolean isSyncing = OperationService.start(context, new PostOperation(uri));
		return isSyncing;
	}
}
