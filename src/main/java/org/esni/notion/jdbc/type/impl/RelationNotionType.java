package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#relation
 * Example:
 * {
 *   "Related tasks": {
 *     "id": "hgMz",
 *     "type": "relation",
 *     "relation": [
 *       {
 *         "id": "dd456007-6c66-4bba-957e-ea501dcda3a6"
 *       },
 *       {
 *         "id": "0c1f7cb2-8090-4f18-924e-d92965055e32"
 *       }
 *     ],
 *     "has_more": false
 *   }
 * }
 */
public class RelationNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['relation'].[*].['id']", name)),
                new NotionColumn(colName + "_HAS_MORE", SqlTypeName.BOOLEAN, String.format("$.['%s'].['has_more']", name))
        };
    }

}
