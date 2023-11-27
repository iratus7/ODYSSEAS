package com.example.odysseas;

import android.provider.BaseColumns;

public class TableData {
    public TableData()
    {}
    public  static  abstract class  TableInfo implements BaseColumns
    {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public  static final String CENTER_ID = "center_id";
        public static final String COORDINATE = "coordinate";
        public  static  final String DATABASE_NAME = "kv_db";
        public  static  final String TABLE_NAME = "kv_table";
    }
}
