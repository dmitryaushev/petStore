package com.petstore.service;

import com.petstore.config.Console;
import com.petstore.model.Order;
import okhttp3.Response;

import java.io.IOException;
import java.util.LinkedHashMap;

public class OrderService {

    private com.petstore.controller.OrderController orderController = new com.petstore.controller.OrderController();
    private Console console = new Console();

    public void create() {

        console.write("Enter order id");
        String orderId = console.read();
        console.write("Enter pet id");
        String petId = console.read();
        console.write("Enter quantity");
        String quantity = console.read();
        console.write("Enter ship date in format \"yyyy-mm-dd\"");
        String shipDate = console.read();
        console.write("Enter status: placed, approved, delivered");
        String status = console.read();
        console.write("Order complete? Y|N");
        String answer = console.read();
        boolean complete = isComplete(answer);

        Order order = new Order();
        order.setId(Long.parseLong(orderId));
        order.setPetId(Long.parseLong(petId));
        order.setQuantity(Integer.parseInt(quantity));
        order.setShipDate(shipDate);
        order.setStatus(status);
        order.setComplete(complete);

        Response response = null;
        try {
            response = orderController.postResponse(order);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 200) {
            console.write(orderController.post(response).toString());
        } else if (code == 400) {
            console.write(response.message());
        }

    }

    public void get() {

        console.write("Enter order id");
        String id = console.read();

        if (!id.matches("^[1-9]\\d*$") || Integer.parseInt(id) > 10) {
            console.write("Invalid id");
            return;
        }

        Response response = null;
        try {
            response = orderController.getResponse(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 200) {
            console.write(orderController.get(response).toString());
        } else if (code == 404 || code == 400) {
            console.write(response.message());
        }
    }

    public void delete() {

        console.write("Enter order id");
        String id = console.read();

        if (!id.matches("^[1-9]\\d*$")) {
            console.write("Invalid id");
            return;
        }

        Response response = null;
        try {
            response = orderController.deleteResponse(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int code = response.code();
        if (code == 200) {
            console.write(orderController.delete(response).toString());
        } else if (code == 404 || code == 400) {
            console.write(response.message());
        }
    }

    public void getInventory() {
        LinkedHashMap<String, Integer> result = orderController.getInventory();
        LinkedHashMap<String, Integer> inventory = new LinkedHashMap<>();
        result.forEach((k, v) -> {
            if (k.equals("available") || k.equals("pending") || k.equals("sold")) {
                inventory.put(k, v);
            }
        });
        console.write(inventory.toString());
    }

    private boolean isComplete(String answer) {
        boolean bool = false;
        switch (answer) {
            case "Y":
                bool = true;
                break;
            case "N":
                break;
            default:
                console.write("Wrong input, try again");
                isComplete(console.read());
        }
        return bool;
    }
}
