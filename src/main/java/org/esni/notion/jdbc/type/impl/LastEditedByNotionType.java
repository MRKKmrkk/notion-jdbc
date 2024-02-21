package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#last-edited-by
 * Example:
 * {
 *   "Last edited by column name": {
 *     "id": "uGNN",
 *     "type": "last_edited_by",
 *     "last_edited_by": {
 *       "object": "user",
 *       "id": "9188c6a5-7381-452f-b3dc-d4865aa89bdf",
 *       "name": "Test Integration",
 *       "avatar_url": "https://s3-us-west-2.amazonaws.com/public.notion-static.com/3db373fe-18f6-4a3c-a536-0f061cb9627f/leplane.jpeg",
 *       "type": "bot",
 *       "bot": {}
 *     }
 *   }
 * }
 */
public class LastEditedByNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['last_edited_by']", name))
        };
    }

}
