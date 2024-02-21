package org.esni.notion.jdbc.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.sql.type.SqlTypeName;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.type.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NotionTypes {

    private static Map<String, AbstractNotionType> typeMap;

    static {
        typeMap = new HashMap<>();
        typeMap.put("checkbox", new CheckboxNotionType());
        typeMap.put("created_by", new CreatedByNotionType());
        typeMap.put("created_time", new CreatedTimeNotionType());
        typeMap.put("date", new DateNotionType());
        typeMap.put("email", new EmailNotionType());
        typeMap.put("files", new FilesNotionType());


    }

    public static NotionColumn[] listNotionColumnByNotionType(String type, String name, String colName) {
        if (!typeMap.containsKey(type)) {
            log.warn(" unknow type '{}', try to case to string", type);
            return new NotionColumn[]{
                    new NotionColumn(colName, SqlTypeName.VARCHAR, String.format("$.%s", name))
            };
        }

        return typeMap.get(type).listNotionColumns(name, colName);
    }

}
