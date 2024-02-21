package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#number
 * Example:
 * {
 *   "Number of subscribers": {
 *     "id": "WPj%5E",
 *     "name": "Number of subscribers",
 *     "type": "number",
 *     "number": {
 *       "format": "number"
 *     }
 *   }
 * }
 */
public class NumberNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['number']", name))
        };
    }

}
