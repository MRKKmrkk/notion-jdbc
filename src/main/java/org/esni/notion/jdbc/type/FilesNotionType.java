package org.esni.notion.jdbc.type;

import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;

/**
 * Doc: https://developers.notion.com/reference/page-property-values#files
 * Example:
 * {
 *   "Blueprint": {
 *     "id": "tJPS",
 *     "type": "files",
 *     "files": [
 *       {
 *         "name": "Project blueprint",
 *         "type": "external",
 *         "external": {
 *           "url": "https://www.figma.com/file/g7eazMtXnqON4i280CcMhk/project-alpha-blueprint?node-id=0%3A1&t=nXseWIETQIgv31YH-1"
 *         }
 *       }
 *     ]
 *   }
 * }
 *
 * todo: files notion type not raw yet
 */
public class FilesNotionType extends AbstractNotionType{

    @Override
    public NotionColumn[] listNotionColumns(String name, String colName) {
        return new NotionColumn[]{
                getIDNotionColumn(name, colName),
                new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.%s.Blueprint", name))
        };
    }

}
