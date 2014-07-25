package com.appnet.app.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appnet.app.R;
import com.appnet.app.providers.AppNetContentProvider;
import com.appnet.app.providers.AppNetContentProvider.PostTable;

import java.util.Arrays;
import java.util.Collection;

import io.pivotal.arca.adapters.Binding;
import io.pivotal.arca.adapters.SupportItemAdapter;
import io.pivotal.arca.dispatcher.Query;
import io.pivotal.arca.fragments.ArcaItemSupportFragment;

public class PostFragment extends ArcaItemSupportFragment {
	
	private static final Collection<Binding> BINDINGS = Arrays.asList(new Binding[] { 
		new Binding(R.id.post_text, PostTable.Columns.TEXT),
	});

	private String mId;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_post, container, false);
	}
	
	@Override
	public CursorAdapter onCreateAdapter(final View view, final Bundle savedInstanceState) {
		return new SupportItemAdapter(getActivity(), BINDINGS);
	}

	public void setId(final String id) {
        if (id != null) {
            mId = id;
            reload();
        }
	}
	
	private void reload() {
		final Uri uri = AppNetContentProvider.Uris.POSTS_URI;

        final Query query = new Query(uri);
        query.setWhere(PostTable.Columns.ID + "=?", mId);

        execute(query);
	}
	
}
