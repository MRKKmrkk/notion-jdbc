package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

public class CheckboxNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
//                getIDNotionColumn(name, colName),
                new NotionColumn(colName, SqlTypeName.BOOLEAN, String.format("$.['%s'].['checkbox']", name))
        };
    }

}
