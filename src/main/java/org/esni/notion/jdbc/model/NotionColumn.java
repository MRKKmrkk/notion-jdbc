package org.esni.notion.jdbc.model;

import org.apache.calcite.sql.type.SqlTypeName;

public class NotionColumn {

    private String columnName;
    private SqlTypeName type;
    private String jsonPath;

    public NotionColumn(String columnName, SqlTypeName type, String jsonPath) {
        this.columnName = columnName;
        this.type = type;
        this.jsonPath = jsonPath;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public SqlTypeName getType() {
        return type;
    }

    public void setType(SqlTypeName type) {
        this.type = type;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

}
