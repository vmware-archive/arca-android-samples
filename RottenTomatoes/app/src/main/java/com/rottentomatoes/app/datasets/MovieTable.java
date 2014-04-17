package com.rottentomatoes.app.datasets;

import android.database.Cursor;
import android.net.Uri;

import io.pivotal.arca.provider.Column;
import io.pivotal.arca.provider.Column.Type;
import io.pivotal.arca.provider.SQLiteTable;
import io.pivotal.arca.provider.Unique;
import io.pivotal.arca.provider.Unique.OnConflict;
import io.pivotal.arca.utils.ArrayUtils;
import io.pivotal.arca.utils.StringUtils;

public class MovieTable extends SQLiteTable {
	
	public static interface ColumnNames {
		public static final String ID = "id";
		public static final String TITLE = "title";
		public static final String YEAR = "year";
		public static final String MPAA_RATING = "mpaa_rating";
		public static final String RUNTIME = "runtime";
		public static final String CRITICS_CONSENSUS = "critics_consensus";
		public static final String SYNOPSIS = "synopsis";
		public static final String IMAGE_URL = "image_url";
	}
	
	public static interface Columns extends SQLiteTable.Columns {
		@Unique(OnConflict.REPLACE)
		public static final Column ID = Type.TEXT.newColumn(ColumnNames.ID);
		public static final Column TITLE = Type.TEXT.newColumn(ColumnNames.TITLE);
		public static final Column YEAR = Type.TEXT.newColumn(ColumnNames.YEAR);
		public static final Column MPAA_RATING = Type.TEXT.newColumn(ColumnNames.MPAA_RATING);
		public static final Column RUNTIME = Type.TEXT.newColumn(ColumnNames.RUNTIME);
		public static final Column CRITICS_CONSENSUS = Type.TEXT.newColumn(ColumnNames.CRITICS_CONSENSUS);
		public static final Column SYNOPSIS = Type.TEXT.newColumn(ColumnNames.SYNOPSIS);
		public static final Column IMAGE_URL = Type.TEXT.newColumn(ColumnNames.IMAGE_URL);
	}
	
	@Override
	public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
		if (uri.getPathSegments().size() > 1) { 
			final String selectionWithId = StringUtils.append(selection, Columns.ID + "=?", " AND ");
			final String[] selectionArgsWithId = ArrayUtils.append(selectionArgs, new String[] { uri.getLastPathSegment() });
			return super.query(uri, projection, selectionWithId, selectionArgsWithId, sortOrder);
		} else {
			return super.query(uri, projection, selection, selectionArgs, sortOrder);
		}
	}
}
