package com.rottentomatoes.app.models;

import com.google.gson.annotations.SerializedName;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider.MovieTable;

import java.util.List;

import io.pivotal.arca.provider.ColumnName;

public class Movie {

    protected static class Fields {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String YEAR = "year";
        public static final String MPAA_RATING = "mpaa_rating";
        public static final String RUNTIME = "runtime";
        public static final String CRITICS_CONSENSUS = "critics_consensus";
        public static final String RELEASE_DATES = "release_dates";
        public static final String RATINGS = "ratings";
        public static final String SYNOPSIS = "synopsis";
        public static final String POSTERS = "posters";
        public static final String ABRIDGED_CAST = "abridged_cast";
    }

    @ColumnName(MovieTable.Columns.ID)
    @SerializedName(Fields.ID)
    private String mId;

    @ColumnName(MovieTable.Columns.TITLE)
    @SerializedName(Fields.TITLE)
    private String mTitle;

    @ColumnName(MovieTable.Columns.YEAR)
    @SerializedName(Fields.YEAR)
    private String mYear;

    @ColumnName(MovieTable.Columns.MPAA_RATING)
    @SerializedName(Fields.MPAA_RATING)
    private String mMpaaRating;

    @ColumnName(MovieTable.Columns.RUNTIME)
    @SerializedName(Fields.RUNTIME)
    private String mRuntime;

    @ColumnName(MovieTable.Columns.CRITICS_CONSENSUS)
    @SerializedName(Fields.CRITICS_CONSENSUS)
    private String mCriticsConsensus;

    @ColumnName(MovieTable.Columns.SYNOPSIS)
    @SerializedName(Fields.SYNOPSIS)
    private String mSynopsis;

    @SerializedName(Fields.RELEASE_DATES)
    private ReleaseDates mReleaseDates;

    @SerializedName(Fields.RATINGS)
    private Ratings mRatings;

    @SerializedName(Fields.POSTERS)
    private Posters mPosters;

    @SerializedName(Fields.ABRIDGED_CAST)
    private List<Cast> mAbridgedCast;

    public String getId() {
        return mId;
    }

    public Posters getPosters() {
        return mPosters;
    }
	
	@ColumnName(MovieTable.Columns.IMAGE_URL)
	public String getImageUrl() {
	    final Posters posters = getPosters();
	    return posters != null ? posters.getDetailed() : null;
	}
}
