package com.petstore.controller;

import com.petstore.config.HttpUtil;
import com.petstore.config.JsonUtil;
import com.petstore.model.ApiResponse;
import com.petstore.model.User;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class UserController {

    String url = "https://petstore.swagger.io/v2/user/";
    MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

    public Response getResponse(String username) throws IOException {
        return HttpUtil.sendGet(url + username);
    }

    public User get(Response response) {
        User result = null;
        try {
            result = HttpUtil.get(response, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response postResponse(User user) throws IOException {
        return HttpUtil.sendPost(url, RequestBody.create(jsonMediaType,
                JsonUtil.writeValueAsString(user)));
    }

    public ApiResponse post(User user) {
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.post(postResponse(user), ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ApiResponse postWithList(List users) {
        String path = "createWithList";
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.sendPostCollection(url + path, RequestBody.create(jsonMediaType,
                    JsonUtil.writeValueAsString(users)), ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ApiResponse postWithArray(User[] users) {
        String path = "createWithArray";
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.sendPostCollection(url + path, RequestBody.create(jsonMediaType,
                    JsonUtil.writeValueAsString(users)), ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response putResponse(User user, String username) throws IOException {
        return HttpUtil.sendPut(url + username, RequestBody.create(jsonMediaType,
                JsonUtil.writeValueAsString(user)));
    }

    public ApiResponse put(Response response) {
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.put(response, ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response deleteResponse(String username) throws IOException {
        return HttpUtil.sendDelete(url + username);
    }

    public ApiResponse delete(Response response) {
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.delete(response, ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response loginResponse(String username, String password) throws IOException {
        String path = String.format("login?username=%s&password=%s", username, password);
        return HttpUtil.sendLogin(url + path);
    }

    public ApiResponse login(Response response) {
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.login(response, ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ApiResponse logout() {
        String path = "logout";
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.logout(url + path, ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
