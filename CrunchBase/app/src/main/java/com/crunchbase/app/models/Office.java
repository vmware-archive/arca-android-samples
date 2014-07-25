package com.crunchbase.app.models;

import com.google.gson.annotations.SerializedName;

public class Office {

    protected static class Fields {
        public static final String DESCRIPTION = "description";
        public static final String ADDRESS1 = "address1";
        public static final String ADDRESS2 = "address2";
        public static final String ZIP_CODE = "zip_code";
        public static final String CITY = "city";
        public static final String STATE_CODE = "state_code";
        public static final String COUNTRY_CODE = "country_code";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
    }

    @SerializedName(Fields.DESCRIPTION)
    private String mDescription;

    @SerializedName(Fields.ADDRESS1)
    private String mAddress1;

    @SerializedName(Fields.ADDRESS2)
    private String mAddress2;

    @SerializedName(Fields.ZIP_CODE)
    private String mZipCode;

    @SerializedName(Fields.CITY)
    private String mCity;

    @SerializedName(Fields.STATE_CODE)
    private String mStateCode;

    @SerializedName(Fields.COUNTRY_CODE)
    private String mCountryCode;

    @SerializedName(Fields.LATITUDE)
    private Float mLatitude;

    @SerializedName(Fields.LONGITUDE)
    private Float mLongitude;
	
}
