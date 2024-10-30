package Services.Serializers.Native;

import Models.Dish;
import Models.Order;
import Services.IWrite;
import Services.IOThreads.Dishes.DishesWriteService;

import java.io.*;
import java.util.List;

public class OrdersNativeSerializer implements IWrite<Order> {

    private final DishesWriteService dishesWriteService = new DishesWriteService();

    @Override
    public void WriteListToFile(List<Order> orders, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Order order : orders) {
                oos.writeObject(order);

            }
            System.out.println("Список замовлень успішно записано у файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл: " + e.getMessage());
        }
    }
}