package com.crunchbase.app.models;

import com.crunchbase.app.providers.CrunchBaseContentProvider.CompanyTable.Columns;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.pivotal.arca.provider.ColumnName;

public class Company {

    protected static class Fields {
        public static final String NAME = "name";
        public static final String CATEGORY_CODE = "category_code";
        public static final String DESCRIPTION = "description";
        public static final String PERMALINK = "permalink";
        public static final String CRUNCHBASE_URL = "crunchbase_url";
        public static final String HOMEPAGE_URL = "homepage_url";
        public static final String NAMESPACE = "namespace";
        public static final String OVERVIEW = "overview";
        public static final String IMAGE = "image";
        public static final String OFFICES = "offices";
    }

    @ColumnName(Columns.NAME)
    @SerializedName(Fields.NAME)
    private String mName;

    @ColumnName(Columns.CATEGORY_CODE)
    @SerializedName(Fields.CATEGORY_CODE)
    private String mCategoryCode;

    @ColumnName(Columns.DESCRIPTION)
    @SerializedName(Fields.DESCRIPTION)
    private String mDescription;

    @ColumnName(Columns.PERMALINK)
    @SerializedName(Fields.PERMALINK)
    private String mPermalink;

    @ColumnName(Columns.CRUNCHBASE_URL)
    @SerializedName(Fields.CRUNCHBASE_URL)
    private String mCrunchbaseUrl;

    @ColumnName(Columns.HOMEPAGE_URL)
    @SerializedName(Fields.HOMEPAGE_URL)
    private String mHomepageUrl;

    @ColumnName(Columns.NAMESPACE)
    @SerializedName(Fields.NAMESPACE)
    private String mNamespace;

    @ColumnName(Columns.OVERVIEW)
    @SerializedName(Fields.OVERVIEW)
    private String mOverview;

    @SerializedName(Fields.IMAGE)
    private Image mImage;

    @SerializedName(Fields.OFFICES)
    private List<Office> mOffices;

    @ColumnName(Columns.IMAGE_URL)
	public String getImageUrl() {
	    return mImage != null ? mImage.getLastImageUrl() : null;
	}
}
