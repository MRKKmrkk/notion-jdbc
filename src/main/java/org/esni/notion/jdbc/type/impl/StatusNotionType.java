package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#status
 * Example:
 * {
 *   "Status": {
 *     "id": "Z%3ClH",
 *     "type": "status",
 *     "status": {
 *       "id": "539f2705-6529-42d8-a215-61a7183a92c0",
 *       "name": "In progress",
 *       "color": "blue"
 *     }
 *   }
 * }
 */
public class StatusNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['status'].['name']", name))
        };
    }

}
