package com.rottentomatoes.app.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rottentomatoes.app.R;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider.MovieTable;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider.MovieTable.Columns;
import com.xtremelabs.imageutils.ImageLoader;

import java.util.Arrays;
import java.util.Collection;

import io.pivotal.arca.adapters.Binding;
import io.pivotal.arca.adapters.SupportItemAdapter;
import io.pivotal.arca.adapters.ViewBinder;
import io.pivotal.arca.dispatcher.Query;
import io.pivotal.arca.fragments.ArcaItemSupportFragment;

public class MovieFragment extends ArcaItemSupportFragment implements ViewBinder {
	
	private static final Collection<Binding> BINDINGS = Arrays.asList(new Binding[] { 
		new Binding(R.id.movie_title, MovieTable.Columns.TITLE),
		new Binding(R.id.movie_year, MovieTable.Columns.YEAR),
		new Binding(R.id.movie_mpaa_rating, MovieTable.Columns.MPAA_RATING),
		new Binding(R.id.movie_runtime, MovieTable.Columns.RUNTIME),
		new Binding(R.id.movie_critics_consensus, MovieTable.Columns.CRITICS_CONSENSUS),
		new Binding(R.id.movie_synopsis, MovieTable.Columns.SYNOPSIS),
		new Binding(R.id.movie_image, MovieTable.Columns.IMAGE_URL),
	});

	private String mId;
	private ImageLoader mImageLoader;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_movie, container, false);
		mImageLoader = ImageLoader.buildImageLoaderForSupportFragment(this);
		return view;
	}
	
	@Override
	public CursorAdapter onCreateAdapter(final View view, final Bundle savedInstanceState) {
		final SupportItemAdapter adapter = new SupportItemAdapter(getActivity(), BINDINGS);
		adapter.setViewBinder(this);
		return adapter;
	}

    private void reload() {
		final Uri uri = RottenTomatoesContentProvider.Uris.MOVIES_URI;

        final Query query = new Query(uri);
        query.setWhere(Columns.ID + "=?", mId);
		
		execute(query);
	}
	
	public void setId(final String id) {
        if (id != null) {
            mId = id;
            reload();
        }
	}
	
	@Override
	public void onDestroyView() {
		mImageLoader.destroy();
		super.onDestroyView();
	}
	
	@Override
	public boolean setViewValue(final View view, final Cursor cursor, final Binding binding) {
		switch (view.getId()) {
		case R.id.movie_image:
		    final String url = cursor.getString(binding.getColumnIndex());
		    mImageLoader.loadImage((ImageView) view, url);
		    return true;

		default:
		    return false;
		}
	}
	
}
