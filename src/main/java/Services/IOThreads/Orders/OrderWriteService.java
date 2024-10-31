//package Services.IOThreads.Orders;
//
//import Models.Dish;
//import Models.Order;
//import Models.Client; // Додайте імпорт для моделі Client
//import Services.IWrite;
//
//import java.io.*;
//import java.util.List;
//
//public class OrderWriteService implements IWrite<Order> {
//
//    @Override
//    public void WriteListToFile(List<Order> orders, String fileName) {
//        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
//            for (Order order : orders) {
//                // Записуємо інформацію про замовлення
//                writer.printf("Order ID: %d%n", order.getOrderID());
//
//                // Додаємо інформацію про клієнта
//                Client client = order.getClient(); // Передбачаємо, що Order має метод getClient()
//                if (client != null) {
//                    writer.printf("Client: %s %s%n", client.getFirstName(), client.getLastName());
//                }
//
//                // Записуємо страви в замовленні
//                for (Dish dish : order.getDishes()) {
//                    writer.printf("Dish ID: %d, Name: %s, Cost: %.2f, Weight: %d%n",
//                            dish.getId(),
//                            dish.getName(),
//                            dish.getCost(),
//                            dish.getWeightInGrams());
//                }
//                // Додаємо роздільник між замовленнями
//                writer.println(); // Порожній рядок для відділення замовлень
//            }
//            System.out.println("Список замовлень успішно записано у файл: " + fileName);
//        } catch (IOException e) {
//            System.err.println("Помилка при записі у файл: " + e.getMessage());
//        }
//    }
//}

package Services.IOThreads.Orders;

import Models.Dish;
import Models.Order;
import Services.IWrite;
import Services.IOThreads.Dishes.DishesWriteService;

import java.io.*;
import java.util.List;

public class OrderWriteService implements IWrite<Order> {

    private final DishesWriteService dishesWriteService = new DishesWriteService();

    @Override
    public void WriteListToFile(List<Order> orders, String fileName) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            for (Order order : orders) {
                oos.writeObject(order);

                String dishFileName = "dishes_order_" + order.getOrderID() + ".txt";
                dishesWriteService.WriteListToFile(order.getDishes(), dishFileName);
            }

            System.out.println("Список замовлень успішно записано у файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл: " + e.getMessage());
        }
    }
}
