package org.esni.notion.jdbc.adpter;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;
import org.esni.notion.jdbc.api.NotionApiService;
import org.esni.notion.jdbc.model.NotionDatabaseModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NotionSchema extends AbstractSchema {

    private final NotionApiService api;

    public NotionSchema() {
        this.api = NotionApiService.get();
    }

    @Override
    protected Map<String, Table> getTableMap() {
        HashMap<String, Table> tableMap = new HashMap<>();
        JsonNode node = null;

        try {
            node = this.api.queryNotionDatabases();
        } catch (IOException e) {
            e.printStackTrace();
            return tableMap;
        }

        for (JsonNode result : node.get("results")) {
            NotionDatabaseModel model = NotionDatabaseModel.getNotionDatabaseModelByJson(result);
            tableMap.put(model.getModelName(), new NotionTable(model));
        }

        return tableMap;
    }

}
