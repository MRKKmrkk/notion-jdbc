package org.esni.notion.jdbc.adpter;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.esni.notion.jdbc.api.NotionApiService;
import org.esni.notion.jdbc.model.NotionColumn;
import org.esni.notion.jdbc.model.NotionDatabaseModel;
import org.esni.notion.jdbc.model.NotionMetaColumn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotionTable extends AbstractTable implements ScannableTable {

    private final NotionDatabaseModel model;
    private final NotionApiService api;

    public NotionTable(NotionDatabaseModel model) {
        this.model = model;
        this.api = NotionApiService.get();
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory relDataTypeFactory) {
        return this.model.getRelDataType(relDataTypeFactory);
    }

    // todo: 优化时间复杂度
    @Override
    public Enumerable<Object[]> scan(DataContext dataContext) {

        ArrayList<Object[]> datas = new ArrayList<>();
        JsonNode node = null;

        try {
            node = this.api.queryNotionPages(model.getDatabaseId());
        } catch (IOException e) {
            e.printStackTrace();
            return new NotionEnumerable<>(datas);
        }

        List<NotionMetaColumn> metaColumns = this.model.getMetaColumns();
        List<NotionColumn> columns = this.model.getColumns();

        for (JsonNode result : node.get("results")) {
            ArrayList<Object> data = new ArrayList<>();
            String resource = result.toString();

            for (NotionColumn metaColumn : metaColumns) {
                data.add(JsonPath.read(resource, metaColumn.getJsonPath()));
            }

            String propResource = result.get("properties").toString();
            for (NotionColumn column : columns) {
                data.add(JsonPath.read(propResource, column.getJsonPath()));
            }


            datas.add(data.toArray());
        }

        return new NotionEnumerable<>(datas);
    }

}
