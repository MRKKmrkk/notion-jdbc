package org.esni.notion.jdbc.type;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#date
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
                getIDNotionColumn(name, colName),
                new NotionColumn(colName + "_START", SqlTypeName.VARCHAR, "$." + name + ".date.start"),
                new NotionColumn(colName + "_END", SqlTypeName.VARCHAR, "$." + name + ".date.end"),
                new NotionColumn(colName + "_TIME_ZONE", SqlTypeName.VARCHAR, "$." + name + ".date.time_zone")
        };
    }

}
