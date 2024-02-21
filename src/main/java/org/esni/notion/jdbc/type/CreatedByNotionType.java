package org.esni.notion.jdbc.type;

import com.google.common.base.Strings;
import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#created-by
 * Example:
 * {
 *   "Created time": {
 *     "id": "eB_%7D",
 *     "type": "created_time",
 *     "created_time": "2022-10-24T22:54:00.000Z"
 *   }
 * }
 */
public class CreatedByNotionType extends AbstractNotionType{

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.%s.id", name)),
                new NotionColumn(colName + "_OBJECT", SqlTypeName.VARCHAR, String.format("$.%s.object", name)),
        };
    }

}
