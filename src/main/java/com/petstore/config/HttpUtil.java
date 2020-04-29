package com.petstore.config;

import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HttpUtil {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static Response sendGet(String url) throws IOException {
        return CLIENT.newCall(new Request.Builder()
                .get()
                .url(url)
                .build()).execute();
    }

    public static <T> T get(Response response, Class<T> valueType) throws IOException {
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static Response sendPost(String url, RequestBody body) throws IOException {
        return CLIENT.newCall(new Request.Builder()
                .post(body)
                .url(url)
                .build()).execute();
    }

    public static <T> T post(Response response, Class<T> valueType) throws IOException {
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static <T> T sendPostCollection(String url, RequestBody body, Class<T> valueType) throws IOException {
        Response response = CLIENT.newCall(new Request.Builder()
                .post(body)
                .url(url)
                .build()).execute();
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static Response sendPut(String url, RequestBody body) throws IOException {
        return CLIENT.newCall(new Request.Builder()
                .put(body)
                .url(url)
                .build()).execute();
    }

    public static <T> T put(Response response, Class<T> valueType) throws IOException {
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static Response sendDelete(String url) throws IOException {
        return CLIENT.newCall(new Request.Builder()
                .delete()
                .url(url)
                .build()).execute();
    }

    public static <T> T delete(Response response, Class<T> valueType) throws IOException {
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static Response sendLogin(String url) throws IOException {
        return CLIENT.newCall(new Request.Builder()
                .url(url)
                .build()).execute();
    }

    public static <T> T login(Response response, Class<T> valueType) throws IOException {
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static <T> T logout(String url, Class<T> valueType) throws IOException {
        Response response = CLIENT.newCall(new Request.Builder()
                .url(url)
                .build()).execute();
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static Response sendFindByStatus(String url) throws IOException {
        return CLIENT.newCall(new Request.Builder()
                .get()
                .url(url)
                .build()).execute();
    }

    public static <T> List<T> findByStatus(Response response, Class<T> valueType) throws IOException {
        List<T> result = JsonUtil.readListValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }

    public static <T> T sendGetInventory(String url, Class<T> valueType) throws IOException {
        Response response = CLIENT.newCall(new Request.Builder()
                .get()
                .url(url)
                .build()).execute();
        T result = JsonUtil.readValue(response.body().string(), valueType);
        Optional.ofNullable(response.body()).ifPresent(ResponseBody::close);
        return result;
    }
}
