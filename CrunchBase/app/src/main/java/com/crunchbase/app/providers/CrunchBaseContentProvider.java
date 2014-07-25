package com.crunchbase.app.providers;

import android.net.Uri;

import io.pivotal.arca.provider.Column;
import io.pivotal.arca.provider.DatabaseProvider;
import io.pivotal.arca.provider.SQLiteTable;
import io.pivotal.arca.provider.Unique;

public class CrunchBaseContentProvider extends DatabaseProvider {

	public static final String AUTHORITY = "com.crunchbase.app.providers.CrunchBaseContentProvider";
	
	public static class Uris {
		public static final Uri COMPANIES_URI = Uri.parse("content://" + AUTHORITY + "/" + Paths.COMPANIES);
	}
	
	protected static class Paths {
		public static final String COMPANIES = "companies";
	}
	
	@Override
	public boolean onCreate() {
        registerDataset(AUTHORITY, Paths.COMPANIES, CompanyTable.class);
		registerDataset(AUTHORITY, Paths.COMPANIES + "/*", CompanyTable.class);
		return true;
	}

    public static class CompanyTable extends SQLiteTable {
        public static interface Columns extends SQLiteTable.Columns {
            @Unique(Unique.OnConflict.REPLACE)
            @Column(Column.Type.TEXT)
            public static final String NAME = "name";

            @Column(Column.Type.TEXT)
            public static final String CATEGORY_CODE = "category_code";

            @Column(Column.Type.TEXT)
            public static final String DESCRIPTION = "description";

            @Column(Column.Type.TEXT)
            public static final String PERMALINK = "permalink";

            @Column(Column.Type.TEXT)
            public static final String CRUNCHBASE_URL = "crunchbase_url";

            @Column(Column.Type.TEXT)
            public static final String HOMEPAGE_URL = "homepage_url";

            @Column(Column.Type.TEXT)
            public static final String NAMESPACE = "namespace";

            @Column(Column.Type.TEXT)
            public static final String OVERVIEW = "overview";

            @Column(Column.Type.TEXT)
            public static final String IMAGE_URL = "image_url";
        }
    }
}