package com.appnet.app.providers;

import android.net.Uri;

import io.pivotal.arca.provider.Column;
import io.pivotal.arca.provider.DatabaseProvider;
import io.pivotal.arca.provider.SQLiteTable;
import io.pivotal.arca.provider.Unique;

public class AppNetContentProvider extends DatabaseProvider {

	public static final String AUTHORITY = "com.appnet.app.providers.AppNetContentProvider";
	
	public static class Uris {
		public static final Uri POSTS_URI = Uri.parse("content://" + AUTHORITY + "/" + Paths.POSTS);
	}
	
	protected static class Paths {
		public static final String POSTS = "posts";
	}
	
	@Override
	public boolean onCreate() {
        registerDataset(AUTHORITY, Paths.POSTS, PostTable.class);
		registerDataset(AUTHORITY, Paths.POSTS + "/*", PostTable.class);
		return true;
	}

    public static class PostTable extends SQLiteTable {
        public static interface Columns extends SQLiteTable.Columns {
            @Unique(Unique.OnConflict.REPLACE)
            @Column(Column.Type.TEXT)
            public static final String ID = "id";

            @Column(Column.Type.TEXT)
            public static final String CREATED_AT = "created_at";

            @Column(Column.Type.TEXT)
            public static final String TEXT = "text";

            @Column(Column.Type.TEXT)
            public static final String NUM_STARS = "num_stars";

            @Column(Column.Type.TEXT)
            public static final String NUM_REPOSTS = "num_reposts";

            @Column(Column.Type.TEXT)
            public static final String NUM_REPLIES = "num_replies";

            @Column(Column.Type.TEXT)
            public static final String IMAGE_URL = "image_url";
        }
    }
}