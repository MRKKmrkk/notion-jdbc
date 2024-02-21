package org.esni.notion.jdbc.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Models {

    public static String formatColumnName(String columnName) {
        return columnName.replace(" ", "_").toUpperCase();
    }

    public static NotionColumn getIDNotionColumn(String name, String colName) {
        return new NotionColumn(colName + "_ID", SqlTypeName.VARCHAR, "$." + name + ".id");
    }

    /**
     * Doc: https://developers.notion.com/reference/page-property-values#example-date-page-property-value-as-returned-in-a-get-page-request
     * Data Example:
     * {
     *   "Email": {
     *     "id": "y%5C%5E_",
     *     "type": "email",
     *     "email": "ada@makenotion.com"
     *   }
     * }
     */
    public static NotionColumn[] getDateNotionColumn(String name, String colName) {
        return new NotionColumn[]{
                getIDNotionColumn(name, colName),
                new NotionColumn(colName + "_START", SqlTypeName.VARCHAR, "$." + name + ".date.start"),
                new NotionColumn(colName + "_END", SqlTypeName.VARCHAR, "$." + name + ".date.end"),
                new NotionColumn(colName + "_TIME_ZONE", SqlTypeName.VARCHAR, "$." + name + ".date.time_zone")
        };
    }

    /**
     * Doc: https://developers.notion.com/reference/page-property-values#checkbox
     * Data Example:
     * {
     *   "Task completed": {
     *     "id": "ZI%40W",
     *     "type": "checkbox",
     *     "checkbox": true
     *   }
     * }
     */
    public static NotionColumn[] getCheckboxNotionColumn(String name, String colName) {
        return new NotionColumn[]{
                getIDNotionColumn(name, colName),
                new NotionColumn(colName, SqlTypeName.BOOLEAN, "$." + name + ".checkbox")
        };
    }

    /**
     * Doc: https://developers.notion.com/reference/page-property-values#created-by
     * Data Example:
     * {
     *   "Created time": {
     *     "id": "eB_%7D",
     *     "type": "created_time",
     *     "created_time": "2022-10-24T22:54:00.000Z"
     *   }
     * }
     */
    public static NotionColumn[] getCreatedByNotionColumn(String name, String colName) {
        return new NotionColumn[]{
                getIDNotionColumn(name, colName),
                new NotionColumn(colName, SqlTypeName.BOOLEAN, "$." + name + ".checkbox")
        };
    }

    public static NotionColumn[] getTitleNotionColumn(String name, String colName) {
        return new NotionColumn[]{
                getIDNotionColumn(name, colName),
                new NotionColumn(colName, SqlTypeName.VARCHAR, "$." + name + ".title")
        };
    }

    public static NotionColumn[] getNotionColumnByType(String type, String name, String colName) {
        NotionColumn[] cols;

        switch (type) {
            case "date":
                cols = getDateNotionColumn(name, colName);
                break;

            // Doc: https://developers.notion.com/reference/property-object#checkbox
            case "checkbox":
                cols = getCheckboxNotionColumn(name, colName);
                break;

            case "title":
                cols = getTitleNotionColumn(name, colName);
                break;

            default:
                log.warn("ignore unknow type '{}'", type);
                cols = new NotionColumn[0];
        }

        return cols;
    }

}
