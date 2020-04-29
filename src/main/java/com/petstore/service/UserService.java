package com.petstore.service;

import com.petstore.config.Console;
import com.petstore.model.User;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private com.petstore.controller.UserController userController = new com.petstore.controller.UserController();
    private Console console = new Console();


    public void create() {

        User user = mapUser();
        console.write(userController.post(user).toString());
    }

    public void get() {

        console.write("Enter username");
        String username = console.read();

        Response response = null;
        try {
            response = userController.getResponse(username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 200) {
            console.write(userController.get(response).toString());
        } else if (code == 404 || code == 400) {
            console.write(response.message());
        }
    }

    public void update() {

        console.write("Enter username");
        String username = console.read();

        User user = new User();
        console.write("Enter id");
        long id = Long.parseLong(console.read());
        console.write("Enter first name");
        String firstName = console.read();
        console.write("Enter last name");
        String lastName = console.read();
        console.write("Enter email");
        String email = console.read();
        console.write("Enter password");
        String password = console.read();
        console.write("Enter phone number");
        String phone = console.read();
        console.write("Enter user status");
        String userStatus = console.read();

        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(Integer.parseInt(userStatus));

        Response response = null;
        try {
            response = userController.putResponse(user, username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 404 || code == 400) {
            console.write(response.message());
        } else if (code == 200) {
            console.write(userController.put(response).toString());
        }
    }

    public void delete() {

        console.write("Enter username");
        String username = console.read();

        Response response = null;
        try {
            response = userController.deleteResponse(username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 200) {
            console.write(userController.delete(response).toString());
        } else if (code == 404 || code == 400) {
            console.write(response.message());
        }
    }

    public void login() {

        console.write("Enter username");
        String username = console.read();
        console.write("Enter password");
        String password = console.read();

        Response response = null;
        try {
            response = userController.loginResponse(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 400) {
            console.write(response.message());
        } else if (code == 200) {
            console.write(userController.login(response).toString());
        }
    }

    public void logout() {

        console.write(userController.logout().toString());
    }

    public void createWithList() {

        console.write("Enter count of users");
        int count = Integer.parseInt(console.read());

        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            console.write(String.format("User №%s", i + 1));
            users.add(mapUser());
        }

        console.write(userController.postWithList(users).toString());
    }

    public void createWithArray() {

        console.write("Enter count of users");
        int count = Integer.parseInt(console.read());

        User[] users = new User[count];
        for (int i = 0; i < count; i++) {
            console.write(String.format("User №%s", i + 1));
            users[i] = mapUser();
        }

        console.write(userController.postWithArray(users).toString());
    }

    private User mapUser() {

        console.write("Enter id");
        String id = console.read();
        console.write("Enter username");
        String username = console.read();
        console.write("Enter first name");
        String firstName = console.read();
        console.write("Enter last name");
        String lastName = console.read();
        console.write("Enter email");
        String email = console.read();
        console.write("Enter password");
        String password = console.read();
        console.write("Enter phone number");
        String phone = console.read();
        console.write("Enter user status");
        String userStatus = console.read();

        User user = new User();
        user.setId(Long.parseLong(id));
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(Integer.parseInt(userStatus));

        return user;
    }
}
