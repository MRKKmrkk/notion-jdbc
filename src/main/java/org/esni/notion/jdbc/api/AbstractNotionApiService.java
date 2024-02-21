package org.esni.notion.jdbc.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public abstract class AbstractNotionApiService {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    final private Headers headers;
    final private OkHttpClient client;
    final private ObjectMapper objMapper;

    public AbstractNotionApiService(String token) {
        this.headers = new Headers
                .Builder()
                .add("Authorization", "Bearer " + token)
                .add("Content-Type", "application/json")
                .add("Notion-Version", "2022-06-28")
                .build();

        this.client = new OkHttpClient.Builder()
                .addInterceptor(new RetryInterceptor(3))
                .build();

        this.objMapper = new ObjectMapper();
    }

    public JsonNode post(String endpoint, String json) throws IOException {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request
                .Builder()
                .url(endpoint)
                .headers(this.headers)
                .post(body)
                .build();


        Response response = this.client.newCall(request).execute();
        String val = response.body().string();
        System.out.println(val);
        return val == null ? this.objMapper.readTree("{}") : this.objMapper.readTree(val);


    }

}
