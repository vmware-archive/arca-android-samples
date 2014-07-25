package com.rottentomatoes.app.operations;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.rottentomatoes.app.application.RottenTomatoesRequests;
import com.rottentomatoes.app.models.Movie;
import com.rottentomatoes.app.models.MovieType;
import com.rottentomatoes.app.models.MoviesResponse;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider;
import com.rottentomatoes.app.providers.RottenTomatoesContentProvider.MovieTypeTable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.pivotal.arca.dispatcher.ErrorBroadcaster;
import io.pivotal.arca.provider.DataUtils;
import io.pivotal.arca.service.Operation;
import io.pivotal.arca.service.ServiceError;
import io.pivotal.arca.service.Task;
import io.pivotal.arca.threading.Identifier;

public class MovieListOperation extends Operation {

    private final String mType;

    public MovieListOperation(Uri uri, final String type) {
        super(uri);
        mType = type;
    }

	public MovieListOperation(final Parcel in) {
		super(in);
        mType = in.readString();
	}

    @Override
	public void writeToParcel(final Parcel dest, final int flags) {
		super.writeToParcel(dest, flags);
        dest.writeString(mType);
	}

	@Override
	public Set<Task<?>> onCreateTasks() {
		final Set<Task<?>> set = new HashSet<Task<?>>();
		set.add(new MovieListTask(mType));
		return set;
	}

	@Override
	public void onSuccess(final Context context, final List<Task<?>> completed) {
		final ContentResolver resolver = context.getContentResolver();
		resolver.notifyChange(RottenTomatoesContentProvider.Uris.MOVIES_URI, null, false);
		resolver.notifyChange(RottenTomatoesContentProvider.Uris.MOVIE_CATEGORIES_URI, null, false);
	}

	@Override
	public void onFailure(final Context context, final ServiceError error) {
		ErrorBroadcaster.broadcast(context, getUri(), error.getCode(), error.getMessage());
	}
	
	public static final Parcelable.Creator<MovieListOperation> CREATOR = new Parcelable.Creator<MovieListOperation>() {
		@Override
		public MovieListOperation createFromParcel(final Parcel in) {
			return new MovieListOperation(in);
		}

		@Override
		public MovieListOperation[] newArray(final int size) {
			return new MovieListOperation[size];
		}
	};

    public static class MovieListTask extends Task<List<Movie>> {

        private final String mType;

        public MovieListTask(final String type) {
            mType = type;
        }

        @Override
        public Identifier<?> onCreateIdentifier() {
            return new Identifier<String>("movie_list:" + mType);
        }

        @Override
        public List<Movie> onExecuteNetworking(final Context context) throws Exception {
            final MoviesResponse response = RottenTomatoesRequests.getMovieList(mType, 50, "ca");
            return response.getMovies();
        }

        @Override
        public void onExecuteProcessing(final Context context, final List<Movie> data) throws Exception {
            final ContentResolver resolver = context.getContentResolver();

            final ContentValues[] movieValues = DataUtils.getContentValues(data);
            resolver.bulkInsert(RottenTomatoesContentProvider.Uris.MOVIES_URI, movieValues);

            final String whereClause = MovieTypeTable.Columns.TYPE + "=?";
            final String[] whereArgs = new String[] { mType };
            resolver.delete(RottenTomatoesContentProvider.Uris.MOVIE_TYPES_URI, whereClause, whereArgs);

            final ContentValues[] movieTypeValues = MovieType.getContentValues(data, mType);
            resolver.bulkInsert(RottenTomatoesContentProvider.Uris.MOVIE_TYPES_URI, movieTypeValues);
        }
    }

}
