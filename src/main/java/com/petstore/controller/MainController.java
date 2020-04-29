package com.petstore.controller;

import com.petstore.config.Console;
import com.petstore.service.OrderService;
import com.petstore.service.PetService;
import com.petstore.service.UserService;

public class MainController {

    private Console console = new Console();
    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();
    private PetService petService = new PetService();

    public void run() {
        console.write("\nWelcome");
        while (true) {
            console.write("\nChoose a command.");
            console.write("Press L to show commands list.");
            console.write("Press Q to exit.");
            String command = console.read();
            switch (command) {
                case "L":
                    console.write("Create order | Get order | Delete order | Get inventory");
                    console.write("Create user | Get user | Update user | Delete user | Login | Logout | " +
                            "Create users with list | Create users with array");
                    console.write("Create pet | Get pet | Update pet | Update pet name and status | " +
                            "Update pet image | Delete pet | Find by status");
                    break;
                case "Q":
                    System.exit(0);

                case "Create order":
                    orderService.create();
                    break;
                case "Get order":
                    orderService.get();
                    break;
                case "Delete order":
                    orderService.delete();
                    break;
                case "Get inventory":
                    orderService.getInventory();
                    break;

                case "Create user":
                    userService.create();
                    break;
                case "Get user":
                    userService.get();
                    break;
                case "Update user":
                    userService.update();
                    break;
                case "Delete user":
                    userService.delete();
                    break;
                case "Login":
                    userService.login();
                    break;
                case "Logout":
                    userService.logout();
                    break;
                case "Create users with list":
                    userService.createWithList();
                    break;
                case "Create users with array":
                    userService.createWithArray();
                    break;

                case "Create pet":
                    petService.create();
                    break;
                case "Get pet":
                    petService.get();
                    break;
                case "Update pet":
                    petService.update();
                    break;
                case "Update pet name and status":
                    petService.updateNameAndStatus();
                    break;
                case "Update pet image":
                    petService.updateImage();
                    break;
                case "Delete pet":
                    petService.delete();
                    break;
                case "Find by status":
                    petService.findByStatus();
                    break;

                default:
                    console.write("Wrong input, try again");
                    break;
            }
        }
    }
}
