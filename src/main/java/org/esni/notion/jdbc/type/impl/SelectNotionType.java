package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#select
 * Example:
 * {
 *   "Department": {
 *     "id": "Yc%3FJ",
 *     "type": "select",
 *     "select": {
 *       "id": "ou@_",
 *       "name": "jQuery",
 *       "color": "purple"
 *     }
 *   }
 * }
 */
public class SelectNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['select'].['name']", name)),
        };
    }

}
