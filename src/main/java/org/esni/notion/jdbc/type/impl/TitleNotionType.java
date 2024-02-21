package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#title
 * Example:
 * {
 *   "Title": {
 *     "id": "title",
 *     "type": "title",
 *     "title": [
 *       {
 *         "type": "text",
 *         "text": {
 *           "content": "A better title for the page",
 *           "link": null
 *         },
 *         "annotations": {
 *           "bold": false,
 *           "italic": false,
 *           "strikethrough": false,
 *           "underline": false,
 *           "code": false,
 *           "color": "default"
 *         },
 *         "plain_text": "This is also not done",
 *         "href": null
 *       }
 *     ]
 *   }
 * }
 */
public class TitleNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].title[*].['plain_text']", name))
        };
    }

}
