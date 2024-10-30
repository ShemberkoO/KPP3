package Services.Serializers.Native;

import Models.Dish;
import Models.Order;
import Services.IOThreads.Dishes.DishesReadService;
import Services.IRead;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import Models.Dish;
import Models.Order;
import Services.IRead;
import Services.IOThreads.Dishes.DishesReadService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersNativeDeserializer implements IRead<Order> {

    private final DishesReadService dishesReadService = new DishesReadService(); // Створюємо екземпляр DishesReadService

    @Override
    public List<Order> ReadListFromFile(String filename) {
        List<Order> orders = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Order order = (Order) ois.readObject();

                    orders.add(order);
                } catch (EOFException e) {

                    break;
                } catch (ClassNotFoundException e) {
                    System.err.println("Помилка при читанні об'єкта: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка при читанні з файлу: " + e.getMessage());
        }

        return orders;
    }
}
