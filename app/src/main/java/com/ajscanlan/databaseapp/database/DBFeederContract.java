package com.ajscanlan.databaseapp.database;

import android.provider.BaseColumns;

/**
 * Created by Alexander Scanlan on 07/10/2015
 */
public final class DBFeederContract {

    public DBFeederContract(){}

    static abstract class PersonTable implements BaseColumns{
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_EMAIL = "email";
    }

}
