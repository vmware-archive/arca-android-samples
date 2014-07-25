package com.rottentomatoes.app.providers;

import android.net.Uri;

import io.pivotal.arca.provider.Column;
import io.pivotal.arca.provider.DatabaseProvider;
import io.pivotal.arca.provider.Joins;
import io.pivotal.arca.provider.SQLiteTable;
import io.pivotal.arca.provider.SQLiteView;
import io.pivotal.arca.provider.Select;
import io.pivotal.arca.provider.SelectFrom;
import io.pivotal.arca.provider.Unique;

public class RottenTomatoesContentProvider extends DatabaseProvider {
	
	public static final String AUTHORITY = "com.rottentomatoes.app.providers.RottenTomatoesContentProvider";
	
	public static final class Uris {
		public static final Uri MOVIES_URI = Uri.parse("content://" + AUTHORITY + "/" + Paths.MOVIES);
		public static final Uri MOVIE_TYPES_URI = Uri.parse("content://" + AUTHORITY + "/" + Paths.MOVIE_TYPES);
        public static final Uri MOVIE_CATEGORIES_URI = Uri.parse("content://" + AUTHORITY + "/" + Paths.MOVIE_CATEGORIES);
	}
	
	protected static final class Paths {
		public static final String MOVIES = "movies";
		public static final String MOVIE_TYPES = "movie_types";
        public static final String MOVIE_CATEGORIES = "movie_categories";
	}
	
	@Override
	public boolean onCreate() {
		registerDataset(AUTHORITY, Paths.MOVIES, MovieTable.class);
		registerDataset(AUTHORITY, Paths.MOVIES + "/*", MovieTable.class);
		registerDataset(AUTHORITY, Paths.MOVIE_TYPES, MovieTypeTable.class);
		registerDataset(AUTHORITY, Paths.MOVIE_TYPES + "/*", MovieTypeTable.class);
        registerDataset(AUTHORITY, Paths.MOVIE_CATEGORIES, MovieTypeView.class);
        registerDataset(AUTHORITY, Paths.MOVIE_CATEGORIES + "/*", MovieTypeView.class);
		return true;
	}

    public static class MovieTable extends SQLiteTable {
        public static interface Columns extends SQLiteTable.Columns {

            @Unique(Unique.OnConflict.REPLACE)
            @Column(Column.Type.TEXT)
            public static final String ID = "id";

            @Column(Column.Type.TEXT)
            public static final String TITLE = "title";

            @Column(Column.Type.TEXT)
            public static final String YEAR = "year";

            @Column(Column.Type.TEXT)
            public static final String MPAA_RATING = "mpaa_rating";

            @Column(Column.Type.TEXT)
            public static final String RUNTIME = "runtime";

            @Column(Column.Type.TEXT)
            public static final String CRITICS_CONSENSUS = "critics_consensus";

            @Column(Column.Type.TEXT)
            public static final String SYNOPSIS = "synopsis";

            @Column(Column.Type.TEXT)
            public static final String IMAGE_URL = "image_url";
        }
    }

    public static class MovieTypeTable extends SQLiteTable {
        public static interface Columns extends SQLiteTable.Columns {
            @Column(Column.Type.TEXT)
            public static final String MOVIE_ID = "movie_id";

            @Column(Column.Type.TEXT)
            public static final String TYPE = "type";
        }
    }

    public static class MovieTypeView extends SQLiteView {

        @SelectFrom("MovieTable as movies")

        @Joins({
            "LEFT JOIN MovieTypeTable as types ON movies.id = types.movie_id",
        })

        public static interface Columns {
            @Select("movies.id")
            public static final String _ID = "_id";

            @Select("movies.id")
            public static final String ID = "id";

            @Select("movies.title")
            public static final String TITLE = "title";

            @Select("movies.image_url")
            public static final String IMAGE_URL = "image_url";

            @Select("types.type")
            public static final String TYPE = "type";
        }
    }

}
