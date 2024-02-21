package org.esni.notion.jdbc.type;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;

public abstract class AbstractNotionType {

    public NotionColumn getIDNotionColumn(String name, String colName) {
        return new NotionColumn(colName + "_ID", SqlTypeName.VARCHAR, "$." + name + ".id");
    }

    public abstract NotionColumn[] listNotionColumns(String name, String colName);

}
