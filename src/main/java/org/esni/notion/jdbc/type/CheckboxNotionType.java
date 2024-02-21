package org.esni.notion.jdbc.type;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;

public class CheckboxNotionType extends AbstractNotionType{

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                getIDNotionColumn(name, colName),
                new NotionColumn(colName, SqlTypeName.BOOLEAN, "$." + name + ".checkbox")
        };
    }

}
