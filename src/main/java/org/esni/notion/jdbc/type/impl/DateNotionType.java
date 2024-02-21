package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: c
 * Example:
 * {
 *   "Due date": {
 *     "id": "M%3BBw",
 *     "type": "date",
 *     "date": {
 *       "start": "2023-02-07",
 *       "end": null,
 *       "time_zone": null
 *     }
 *   }
 * }
 */
public class DateNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
//                getIDNotionColumn(name, colName),
                new NotionColumn(colName + "_START", SqlTypeName.VARCHAR, String.format("$.['%s'].['date'].['start']", name)),
                new NotionColumn(colName + "_END", SqlTypeName.VARCHAR, String.format("$.['%s'].['date'].['end']", name)),
                new NotionColumn(colName + "_TIME_ZONE", SqlTypeName.VARCHAR, String.format("$.['%s'].['date'].['time_zone']", name))

        };
    }

}
