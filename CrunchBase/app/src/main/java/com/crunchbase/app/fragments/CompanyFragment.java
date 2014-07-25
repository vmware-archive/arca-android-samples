package com.crunchbase.app.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crunchbase.app.R;
import com.crunchbase.app.providers.CrunchBaseContentProvider;
import com.crunchbase.app.providers.CrunchBaseContentProvider.CompanyTable;
import com.xtremelabs.imageutils.ImageLoader;

import java.util.Arrays;
import java.util.Collection;

import io.pivotal.arca.adapters.Binding;
import io.pivotal.arca.adapters.SupportItemAdapter;
import io.pivotal.arca.adapters.ViewBinder;
import io.pivotal.arca.dispatcher.Query;
import io.pivotal.arca.fragments.ArcaItemSupportFragment;

public class CompanyFragment extends ArcaItemSupportFragment implements ViewBinder {
	
	private static final Collection<Binding> BINDINGS = Arrays.asList(new Binding[] { 
		new Binding(R.id.company_name, CompanyTable.Columns.NAME),
		new Binding(R.id.company_category_code, CompanyTable.Columns.CATEGORY_CODE),
		new Binding(R.id.company_description, CompanyTable.Columns.DESCRIPTION),
		new Binding(R.id.company_overview, CompanyTable.Columns.OVERVIEW),
		new Binding(R.id.company_image, CompanyTable.Columns.IMAGE_URL),
	});

	private String mId;
	private ImageLoader mImageLoader;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_company, container, false);
	}
	
	@Override
	public CursorAdapter onCreateAdapter(final View view, final Bundle savedInstanceState) {
		final SupportItemAdapter adapter = new SupportItemAdapter(getActivity(), BINDINGS);
		adapter.setViewBinder(this);
		return adapter;
	}
	
	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mImageLoader = ImageLoader.buildImageLoaderForSupportFragment(this);
	}
	
	@Override
	public void onDestroyView() {
		mImageLoader.destroy();
		super.onDestroyView();
	}
	
	public void setId(final String id) {
        if (id != null) {
            mId = id;
            reload();
        }
	}

	private void reload() {
		final Uri uri = CrunchBaseContentProvider.Uris.COMPANIES_URI;
        final Query query = new Query(uri);
        query.setWhere(CompanyTable.Columns.NAME + "=?", mId);
        execute(query);
	}

	@Override
	public boolean setViewValue(final View view, final Cursor cursor, final Binding binding) {
		switch (view.getId()) {
		case R.id.company_image:
		    final String url = cursor.getString(binding.getColumnIndex());
		    mImageLoader.loadImage((ImageView) view, url);
		    return true;

		default:
		    return false;
		}
	}
	
}
