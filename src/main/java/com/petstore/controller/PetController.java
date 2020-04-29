package com.petstore.controller;

import com.petstore.config.HttpUtil;
import com.petstore.config.JsonUtil;
import com.petstore.model.ApiResponse;
import com.petstore.model.Pet;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PetController {

    String url = "https://petstore.swagger.io/v2/pet/";
    MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

    public Response getResponse(int id) throws IOException {
        return HttpUtil.sendGet(url + id);
    }

    public Pet get(Response response) {
        Pet result = null;
        try {
            result = HttpUtil.get(response, Pet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response postResponse(Pet pet) throws IOException {
        return HttpUtil.sendPost(url, RequestBody.create(jsonMediaType,
                JsonUtil.writeValueAsString(pet)));
    }

    public Pet post(Response response) {
        Pet result = new Pet();
        try {
            result = HttpUtil.post(response, Pet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response deleteResponse(int id) throws IOException {
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

    public Response findByStatusResponse(String status) throws IOException {
        String path = "findByStatus?status=" + status;
        return HttpUtil.sendFindByStatus(url + path);
    }

    public List<Pet> findByStatus(Response response) {
        List<Pet> result = new ArrayList<>();
        try {
            result = HttpUtil.findByStatus(response, Pet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response putResponse(Pet pet) throws IOException {
        return HttpUtil.sendPut(url, RequestBody.create(jsonMediaType,
                JsonUtil.writeValueAsString(pet)));
    }

    public Pet put(Response response) {

        Pet result = new Pet();
        try {
            result = HttpUtil.put(response, Pet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response postNameAndStatusResponse(int id, String name, String status) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .add("status", status)
                .build();
        return HttpUtil.sendPost(url + id, requestBody);
    }

    public ApiResponse postNameAndStatus(Response response) {
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.post(response, ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response uploadImageResponse(int id, File file) throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
                .build();
        String path = id + "/uploadImage";
        return HttpUtil.sendPost(url + path, requestBody);
    }

    public ApiResponse uploadImage(int id, File file) {
        ApiResponse result = new ApiResponse();
        try {
            result = HttpUtil.post(uploadImageResponse(id, file), ApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
