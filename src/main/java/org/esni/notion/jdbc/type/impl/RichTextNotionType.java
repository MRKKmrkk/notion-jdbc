package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#rich-text
 * Example:
 * {
 *   "Description": {
 *     "id": "HbZT",
 *     "type": "rich_text",
 *     "rich_text": [
 *       {
 *         "type": "text",
 *         "text": {
 *           "content": "There is some ",
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
 *         "plain_text": "There is some ",
 *         "href": null
 *       },
 *       {
 *         "type": "text",
 *         "text": {
 *           "content": "text",
 *           "link": null
 *         },
 *         "annotations": {
 *           "bold": true,
 *           "italic": false,
 *           "strikethrough": false,
 *           "underline": false,
 *           "code": false,
 *           "color": "default"
 *         },
 *         "plain_text": "text",
 *         "href": null
 *       },
 *       {
 *         "type": "text",
 *         "text": {
 *           "content": " in this property!",
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
 *         "plain_text": " in this property!",
 *         "href": null
 *       }
 *     ]
 *   }
 * }
 */
public class RichTextNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].rich_text[*].['plain_text']", name))
        };
    }

}
