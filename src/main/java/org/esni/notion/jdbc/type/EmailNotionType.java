package org.esni.notion.jdbc.type;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#email
 * Example:
 * {
 *   "Email": {
 *     "id": "y%5C%5E_",
 *     "type": "email",
 *     "email": "ada@makenotion.com"
 *   }
 * }
 */
public class EmailNotionType extends AbstractNotionType{

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                getIDNotionColumn(name, colName),
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.%s.email", name))
        };
    }

}
