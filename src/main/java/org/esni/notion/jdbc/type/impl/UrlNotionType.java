package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#url
 * Example:
 * {
 *   "Website": {
 *     "id": "bB%3D%5B",
 *     "type": "url",
 *     "url": "https://developers.notion.com/"
 *   }
 * }
 */
public class UrlNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['url']", name))
        };
    }

}
