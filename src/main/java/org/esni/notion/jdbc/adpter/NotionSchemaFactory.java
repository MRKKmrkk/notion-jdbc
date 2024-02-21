package org.esni.notion.jdbc.adpter;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;
import org.esni.notion.jdbc.api.NotionApiService;

import java.util.Map;

public class NotionSchemaFactory implements SchemaFactory {

    @Override
    public Schema create(SchemaPlus parentSchema, String name, Map<String, Object> operand) {
        String token = (String) operand.get("token");
        NotionApiService.create(token);

        return new NotionSchema();
    }

}
