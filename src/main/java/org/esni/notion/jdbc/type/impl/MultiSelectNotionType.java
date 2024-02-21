package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#multi-select
 * Example:
 * {
 *   "Programming language": {
 *     "id": "QyRn",
 *     "name": "Programming language",
 *     "type": "multi_select",
 *     "multi_select": {
 *       "options": [
 *         {
 *           "id": "tC;=",
 *           "name": "TypeScript",
 *           "color": "purple"
 *         },
 *         {
 *           "id": "e4413a91-9f84-4c4a-a13d-5b4b3ef870bb",
 *           "name": "JavaScript",
 *           "color": "red"
 *         },
 *         {
 *           "id": "fc44b090-2166-40c8-8c58-88f2d8085ec0",
 *           "name": "Python",
 *           "color": "gray"
 *         }
 *       ]
 *     }
 *   }
 * }
 */
public class MultiSelectNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].multi_select[*].['name']", name))
        };
    }

}
