package org.esni.notion.jdbc.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.*;
import org.esni.notion.jdbc.type.impl.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NotionTypes {

    private static final Map<String, AbstractNotionType> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put("checkbox", new CheckboxNotionType());
        typeMap.put("created_by", new CreatedByNotionType());
        typeMap.put("created_time", new CreatedTimeNotionType());
        typeMap.put("date", new DateNotionType());
        typeMap.put("email", new EmailNotionType());
        typeMap.put("files", new FilesNotionType());
        typeMap.put("formula", new FormulaNotionType());
        typeMap.put("last_edited_by", new LastEditedByNotionType());
        typeMap.put("last_edited_time", new LastEditedTimeNotionType());
        typeMap.put("multi_select", new MultiSelectNotionType());
        typeMap.put("number", new NumberNotionType());
        // ignore peopo
        typeMap.put("phone_number", new PhoneNumberNotionType());
        typeMap.put("relation", new RelationNotionType());
        // roll up
        typeMap.put("rich_text", new RichTextNotionType());
        typeMap.put("select", new SelectNotionType());
        typeMap.put("status", new StatusNotionType());
        typeMap.put("title", new TitleNotionType());
        typeMap.put("url", new UrlNotionType());
        // ignore Unique ID and Veifcation

    }

    public static NotionColumn[] listNotionColumnByNotionType(String type, String name, String colName) {
        if (!typeMap.containsKey(type)) {
            log.warn(" unknow type '{}', try to case to string", type);
            return new NotionColumn[]{
                    new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.['%s']", name))
            };
        }

        return typeMap.get(type).listNotionColumns(name, colName);
    }

}
