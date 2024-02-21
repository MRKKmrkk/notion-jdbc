package org.esni.notion.jdbc.type.impl;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.AbstractNotionType;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#formula
 * Example:
 * {
 *   "Days until launch": {
 *     "id": "CSoE",
 *     "type": "formula",
 *     "formula": {
 *       "type": "number",
 *       "number": 56
 *     }
 *   }
 * }
 */
public class FormulaNotionType extends AbstractNotionType {

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s'].formula", name))
        };
    }

}
