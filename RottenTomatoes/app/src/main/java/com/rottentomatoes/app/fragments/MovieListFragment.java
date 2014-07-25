package com.rottentomatoes.app.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.rottentomatoes.app.R;
import com.rottentomatoes.app.activities.MovieActivity;
import com.rottentomatoes.app.adapters.AnimationCursorAdapter;
import com.rottentomatoes.app.animators.ViewAnimator.DefaultViewAnimator;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider.MovieTypeView;
import com.xtremelabs.imageutils.ImageLoader;

import java.util.Arrays;
import java.util.Collection;

import io.pivotal.arca.adapters.Binding;
import io.pivotal.arca.adapters.ViewBinder;
import io.pivotal.arca.dispatcher.Error;
import io.pivotal.arca.dispatcher.Query;
import io.pivotal.arca.dispatcher.QueryResult;
import io.pivotal.arca.fragments.ArcaAdapterSupportFragment;

public class MovieListFragment extends ArcaAdapterSupportFragment implements OnItemClickListener, ViewBinder {
	
	private static final Collection<Binding> BINDINGS = Arrays.asList(new Binding[] { 
		new Binding(R.id.list_item_movie_title, MovieTypeView.Columns.TITLE),
		new Binding(R.id.list_item_movie_image, MovieTypeView.Columns.IMAGE_URL),
	});

	private String mType;
	private ImageLoader mImageLoader;
	
	@Override
	public int getAdapterViewId() {
		return R.id.movie_list;
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_movie_list, container, false);
	}
	
	@Override
	public CursorAdapter onCreateAdapter(final AdapterView<CursorAdapter> adapterView, final Bundle savedInstanceState) {
		final AnimationCursorAdapter adapter = new AnimationCursorAdapter(getActivity(), R.layout.list_item_movie, BINDINGS);
		adapter.setViewAnimator(new DefaultViewAnimator());
		adapter.setViewBinder(this);
		return adapter;
	}
	
	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getAdapterView().setOnItemClickListener(this);
		mImageLoader = ImageLoader.buildImageLoaderForSupportFragment(this);
	}
	
	@Override
	public void onDestroyView() {
		mImageLoader.destroy();
		super.onDestroyView();
	}
	
	public void setType(final String type) {
		mType = type;
		refreshList();
	}

	private void refreshList() {
		getAdapterView().setSelection(0);
		refresh();
	}
	
	private void refresh() {
        if (mType != null) {
            showLoading();

            final Uri uri = RottenTomatoesContentProvider.Uris.MOVIE_CATEGORIES_URI;

            final Query request = new Query(uri, 1000);
            request.setWhere(MovieTypeView.Columns.TYPE + "=?", mType);
            request.setSortOrder(MovieTypeView.Columns.TITLE + " asc");
            execute(request);
        }
	}

	@Override
	public void onContentError(final Error error) {
		Toast.makeText(getActivity(), "ERROR: " + error.getMessage(), Toast.LENGTH_SHORT).show();
		hideLoading();
	}
	
	@Override
	public void onContentChanged(final QueryResult result) {
		final CursorAdapter adapter = getCursorAdapter();
		if (adapter.getCount() > 0) {
			showResults();
		} else if (!result.isSyncing()) {
			hideLoading();
		}
	}
	
	private void showResults() {
		getView().findViewById(R.id.movie_list).setVisibility(View.VISIBLE);
		getView().findViewById(R.id.loading).setVisibility(View.INVISIBLE);
	}
	
	private void showLoading() {
		getView().findViewById(R.id.movie_list).setVisibility(View.INVISIBLE);
		getView().findViewById(R.id.loading).setVisibility(View.VISIBLE);
	}
	
	private void hideLoading() {
		getView().findViewById(R.id.loading).setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
		final Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
		final String itemId = cursor.getString(cursor.getColumnIndex(MovieTypeView.Columns.ID));
		MovieActivity.newInstance(getActivity(), itemId);
	}

	@Override
	public boolean setViewValue(final View view, final Cursor cursor, final Binding binding) {
		switch (view.getId()) {
		case R.id.list_item_movie_image:
		    final String url = cursor.getString(binding.getColumnIndex());
		    mImageLoader.loadImage((ImageView) view, url);
		    return true;

		default:
		    return false;
		}
	}
}
