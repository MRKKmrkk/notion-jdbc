package org.esni.notion.jdbc.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.util.Pair;
import org.esni.notion.jdbc.util.Models;
import org.esni.notion.jdbc.util.NotionTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotionDatabaseModel {

    public static NotionDatabaseModel getNotionDatabaseModelByJson(JsonNode node) {
        String databaseId = node.get("url").asText().replace("https://www.notion.so/", "");
        if (databaseId.isEmpty()) {
            throw new RuntimeException("database id can not be empty");
        }

        String modelName = Models.formatColumnName(node.get("title").get(0).get("text").get("content").asText());
        NotionDatabaseModel model = new NotionDatabaseModel(
                modelName.isEmpty() ? Models.formatColumnName(databaseId) : modelName,
                databaseId
        );

        // build meta columns
        model.addMetaColumn("_OBJECT", SqlTypeName.VARCHAR, "$.object");
        model.addMetaColumn("_ID", SqlTypeName.VARCHAR, "$.id");
        model.addMetaColumn("_COVER", SqlTypeName.VARCHAR, "$.cover");
        model.addMetaColumn("_ICON", SqlTypeName.VARCHAR, "$.icon");
        model.addMetaColumn("_CREATED_TIME", SqlTypeName.VARCHAR, "$.created_time");
        model.addMetaColumn("_CREATED_BY_OBJECT", SqlTypeName.VARCHAR, "$.created_by.object");
        model.addMetaColumn("_CREATED_BY_ID", SqlTypeName.VARCHAR, "$.created_by.id");
        model.addMetaColumn("_LAST_EDITED_TIME", SqlTypeName.VARCHAR, "$.last_edited_time");
//        model.addMetaColumn("_TITLE", SqlTypeName.VARCHAR, "$.title");
//        model.addMetaColumn("_DESCRIPTION", SqlTypeName.VARCHAR, "$.description");
//        model.addMetaColumn("_IS_INLINE", SqlTypeName.BOOLEAN, "$.is_inline");
        model.addMetaColumn("_PARENT", SqlTypeName.VARCHAR, "$.parent");
        model.addMetaColumn("_URL", SqlTypeName.VARCHAR, "$.url");
        model.addMetaColumn("_PUBLIC_URL", SqlTypeName.VARCHAR, "$.public_url");
        model.addMetaColumn("_ARCHIVED", SqlTypeName.BOOLEAN, "$.archived");

        // build columns
        for (JsonNode prop : node.get("properties")) {
            String name = prop.get("name").asText();
            if (name.isEmpty()) {
                throw new RuntimeException("name can not be empty");
            }
            String colName = Models.formatColumnName(name);

            String type = prop.get("type").asText();
            Arrays.stream(NotionTypes.listNotionColumnByNotionType(type, name, colName))
                    .forEach(model::addColumn);
        }

        return model;
    }

    private static RelDataType toNullableRelDataType(JavaTypeFactory typeFactory, SqlTypeName sqlTypeName) {
        return typeFactory.createTypeWithNullability(typeFactory.createSqlType(sqlTypeName), true);
    }

    private final List<NotionColumn> columns;
    private final List<NotionMetaColumn> metaColumns;
    private final String modelName;
    private final String databaseId;
    private RelDataType type;

    private NotionDatabaseModel(String modelName, String databaseId) {
        this.columns = new ArrayList<>();
        this.metaColumns = new ArrayList<>();
        this.modelName = modelName;
        this.databaseId = databaseId;
        this.type = null;
    }

    public String getModelName() {
        return modelName;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public List<NotionColumn> getColumns() {
        return columns;
    }

    public List<NotionMetaColumn> getMetaColumns() {
        return metaColumns;
    }

    public void addColumn(String columnName, SqlTypeName type, String jsonPath) {
        this.columns.add(new NotionColumn(columnName, type, jsonPath));
    }

    public void addColumn(NotionColumn column) {
        this.columns.add(column);
    }

    public void addMetaColumn(String columnName, SqlTypeName type, String jsonPath) {
        this.metaColumns.add(new NotionMetaColumn(columnName, type, jsonPath));
    }

    public RelDataType getRelDataType(RelDataTypeFactory typeFactory) {

        if (this.type == null) {
            List<Pair<String, RelDataType>> typeStrut = Stream.concat(this.metaColumns.stream(), this.columns.stream())
                    .map(column -> new Pair<>(column.getColumnName(), toNullableRelDataType((JavaTypeFactory) typeFactory, column.getType())))
                    .collect(Collectors.toList());

            this.type = typeFactory.createStructType(typeStrut);
        }

        return this.type;
    }

}
