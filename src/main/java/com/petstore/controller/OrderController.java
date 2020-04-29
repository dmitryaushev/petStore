package com.petstore.controller;

import com.petstore.config.HttpUtil;
import com.petstore.config.JsonUtil;
import com.petstore.model.ApiResponse;
import com.petstore.model.Order;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.LinkedHashMap;

public class OrderController {

    String url = "https://petstore.swagger.io/v2/store/order/";
    MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

    public Response getResponse(String id) throws IOException {
        return HttpUtil.sendGet(url + id);
    }

    public Order get(Response response) {
        Order result = null;
        try {
            result = HttpUtil.get(response, Order.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public LinkedHashMap getInventory() {
        String path = "https://petstore.swagger.io/v2/store/inventory";
        LinkedHashMap result = new LinkedHashMap<>();
        try {
            result = HttpUtil.sendGetInventory(path, LinkedHashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response postResponse(Order order) throws IOException {
        return HttpUtil.sendPost(url, RequestBody.create(jsonMediaType,
                JsonUtil.writeValueAsString(order)));
    }

    public Order post(Response response) {
        Order result = new Order();
        try {
            result = HttpUtil.post(response, Order.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response deleteResponse(String id) throws IOException {
        return HttpUtil.sendDelete(url + id);
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
}
