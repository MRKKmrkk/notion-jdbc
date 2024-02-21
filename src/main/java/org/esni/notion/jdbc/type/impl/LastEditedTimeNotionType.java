package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#last-edited-by
 * Example:
 * {
 *   "Last edited time": {
 *     "id": "%3Defk",
 *     "type": "last_edited_time",
 *     "last_edited_time": "2023-02-24T21:06:00.000Z"
 *   }
 * }
 */
public class LastEditedTimeNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['last_edited_time']", name))
        };
    }

}
