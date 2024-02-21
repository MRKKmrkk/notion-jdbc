package org.esni.notion.jdbc.api;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class NotionApiService extends AbstractNotionApiService {

    public static NotionApiService notionApiService = null;

    public static NotionApiService create(String token) {
        if (notionApiService == null) {
            notionApiService = new NotionApiService(token);
        }

        return notionApiService;
    }

    public static NotionApiService get() {
        if (notionApiService == null) {
            throw new RuntimeException("you need create notion api service before you get it");
        }

        return notionApiService;
    }

    private NotionApiService(String token) {
        super(token);
    }

    public JsonNode queryNotionDatabases() throws IOException {
        return super.post(
                "https://api.notion.com/v1/search",
                "{\"filter\":{\"value\":\"database\",\"property\":\"object\"}}"
        );
    }

    public JsonNode queryNotionPages(String databaseId) throws IOException {
        return super.post(
                "https://api.notion.com/v1/databases/" + databaseId + "/query",
                ""
        );
    }

}
