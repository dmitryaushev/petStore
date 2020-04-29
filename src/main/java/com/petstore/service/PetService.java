package com.petstore.service;

import com.petstore.config.Console;
import com.petstore.model.ApiResponse;
import com.petstore.model.Category;
import com.petstore.model.Pet;
import com.petstore.model.Tag;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PetService {

    private com.petstore.controller.PetController petController = new com.petstore.controller.PetController();
    private Console console = new Console();

    public void create() {

        Pet pet = mapPet();

        Response response = null;
        try {
            response = petController.postResponse(pet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 405) {
            console.write(response.message());
        } else if (code == 200) {
            console.write(petController.post(response).toString());
        }
    }

    public void get() {

        console.write("Enter pet id");
        int id = Integer.parseInt(console.read());

        Response response = null;
        try {
            response = petController.getResponse(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 404) {
            console.write(response.message());
        } else if (code == 200) {
            console.write(petController.get(response).toString());
        }
    }

    public void update() {

        Pet pet = mapPet();

        Response response = null;
        try {
            response = petController.putResponse(pet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();

        if (code == 400 || code == 404 || code == 405) {
            console.write(response.message());
        } else if (code == 200) {
            console.write(petController.put(response).toString());
        }
    }

    public void updateNameAndStatus() {

        console.write("Enter pet id");
        int id = Integer.parseInt(console.read());
        console.write("Enter name");
        String name = console.read();
        console.write("Enter status");
        String status = console.read();

        Response response = null;
        try {
            response = petController.postNameAndStatusResponse(id, name, status);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 400 || code == 404) {
            console.write(response.message());
        } else if (code == 200) {
            console.write(petController.postNameAndStatus(response).toString());
        }
    }

    public void updateImage() {

        console.write("Enter pet id");
        int id = Integer.parseInt(console.read());
        console.write("Enter path to file with file name");
        String path = console.read();

        File file = new File(path);
        if (!file.exists()) {
            console.write("File not exist");
            return;
        }

        ApiResponse result = petController.uploadImage(id, file);
        console.write(result.toString());
    }

    public void delete() {

        console.write("Enter pet id");
        int id = Integer.parseInt(console.read());

        Response response = null;
        try {
            response = petController.deleteResponse(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 400 || code == 404) {
            console.write(response.message());
        } else if (code == 200) {
            console.write(petController.delete(response).toString());
        }
    }

    public void findByStatus() {

        console.write("Choose status:");
        console.write("available | pending | sold");
        String status = console.read();

        Response response = null;
        try {
            response = petController.findByStatusResponse(status);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 400) {
            console.write(response.message());
        } else if (code == 200) {
            List<Pet> result = petController.findByStatus(response);
            result.forEach(x -> console.write(x.toString()));
        }
    }

    public Pet mapPet() {

        console.write("Enter pet id");
        String id = console.read();
        console.write("Enter name");
        String name = console.read();
        console.write("Enter status");
        String status = console.read();

        console.write("Create category");
        console.write("Category id");
        String categoryId = console.read();
        console.write("Category name");
        String categoryName = console.read();
        Category category = new Category();
        category.setId(Long.parseLong(categoryId));
        category.setName(categoryName);

        console.write("Enter photo urls");
        console.write("first");
        String firstUrl = console.read();
        console.write("second");
        String secondUrl = console.read();
        List<String> urls = new ArrayList<>();
        urls.add(firstUrl);
        urls.add(secondUrl);

        console.write("Create tags");
        console.write("Tag id");
        String firstTagId = console.read();
        console.write("Tag name");
        String firstTagName = console.read();
        console.write("Tag id");
        String secondTagId = console.read();
        console.write("Tag name");
        String secondTagName = console.read();
        Tag firstTag = new Tag();
        firstTag.setId(Long.parseLong(firstTagId));
        firstTag.setName(firstTagName);
        Tag secondTag = new Tag();
        secondTag.setId(Long.parseLong(secondTagId));
        secondTag.setName(secondTagName);
        List<Tag> tags = new ArrayList<>();
        tags.add(firstTag);
        tags.add(secondTag);

        Pet pet = new Pet();
        pet.setId(Long.parseLong(id));
        pet.setName(name);
        pet.setStatus(status);
        pet.setCategory(category);
        pet.setPhotoUrls(urls);
        pet.setTags(tags);

        return pet;
    }

}
