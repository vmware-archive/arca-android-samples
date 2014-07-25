package com.crunchbase.app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    private static final int PAGE_SIZE = 30;

    protected static interface Fields {
        public static final String TOTAL = "total";
        public static final String PAGE = "page";
        public static final String RESULTS = "results";
    }

    @SerializedName(Fields.TOTAL)
    private Integer mTotal;

    @SerializedName(Fields.PAGE)
    private Integer mPage;

    @SerializedName(Fields.RESULTS)
    private List<Company> mResults;


    public Integer getTotal() {
        return mTotal;
    }

    public Integer getPage() {
        return mPage;
    }

    public List<Company> getResults() {
        return mResults;
    }

    public int getNextPage() {
        final int total = getTotal();
        final int page = getPage();
        return (PAGE_SIZE * page) < total ? page + 1 : -1;
    }
}
