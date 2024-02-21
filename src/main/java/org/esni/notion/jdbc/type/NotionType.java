package org.esni.notion.jdbc.type;

import org.esni.notion.jdbc.model.NotionColumn;

public interface NotionType {

    NotionColumn[] listNotionColumns(String name, String colName);

}
