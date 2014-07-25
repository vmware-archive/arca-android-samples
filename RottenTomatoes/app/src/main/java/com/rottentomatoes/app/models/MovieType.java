package com.rottentomatoes.app.models;

import android.content.ContentValues;

import com.rottentomatoes.app.providers.RottenTomatoesContentProvider.MovieTypeTable.Columns;

import java.util.List;

public class MovieType {

	public static ContentValues[] getContentValues(final List<Movie> list, final String type) {
		final ContentValues[] values = new ContentValues[list.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = getContentValues(list.get(i), type);
		}
		return values;
	}
	
	public static ContentValues getContentValues(final Movie item, final String type) {
		final ContentValues value = new ContentValues();
		value.put(Columns.MOVIE_ID, item.getId());
		value.put(Columns.TYPE, type);
		return value;
	}
	
}
