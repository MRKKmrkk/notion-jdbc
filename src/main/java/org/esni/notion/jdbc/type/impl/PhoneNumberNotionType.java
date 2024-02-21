package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#phone-number
 * Example:
 * {
 *   "Example phone number property": {
 *     "id": "%5DKhQ",
 *     "name": "Example phone number property",
 *     "type": "phone_number",
 *     "phone_number": {}
 *   }
 * }
 */
public class PhoneNumberNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].['phone_number']", name))
        };
    }

}
