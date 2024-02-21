package org.esni.notion.jdbc.model;

import org.apache.calcite.sql.type.SqlTypeName;

public class NotionMetaColumn extends NotionColumn{

    public NotionMetaColumn(String columnName, SqlTypeName type, String jsonPath) {
        super(columnName, type, jsonPath);
    }

}
