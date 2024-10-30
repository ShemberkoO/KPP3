//package Services.IOThreads.Orders;
//
//import Models.Dish;
//import Models.Order;
//import Models.Client; // Додайте імпорт для моделі Client
//import Services.IRead;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderReadService implements IRead<Order> {
//
//    @Override
//    public List<Order> ReadListFromFile(String filename) {
//        List<Order> orders = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            Order currentOrder = null;
//
//            while ((line = reader.readLine()) != null) {
//                line = line.trim();
//
//                // Читаємо ID замовлення
//                if (line.startsWith("Order ID:")) {
//                    int orderID = Integer.parseInt(line.split(":")[1].trim());
//                    currentOrder = new Order(orderID, null, new ArrayList<>()); // Ініціалізуємо новий об'єкт Order
//                }
//                // Читаємо інформацію про клієнта
//                else if (line.startsWith("Client:")) {
//                    String[] clientParts = line.split(":")[1].trim().split(" ");
//                    String firstName = clientParts[0];
//                    String lastName = clientParts[1];
//                    Client client = new Client(firstName, lastName);
//                    if (currentOrder != null) {
//                        currentOrder.setClient(client); // Встановлюємо клієнта для поточного замовлення
//                    }
//                }
//                // Читаємо інформацію про страви
//                else if (line.startsWith("Dish ID:")) {
//                    String[] dishParts = line.split(", ");
//                    int id = Integer.parseInt(dishParts[0].split(":")[1].trim());
//                    String name = dishParts[1].split(":")[1].trim();
//                    double cost = Double.parseDouble(dishParts[2].split(":")[1].replace(',','.').trim());
//                    int weight = Integer.parseInt(dishParts[3].split(":")[1].trim());
//
//                    Dish dish = new Dish(id, name, cost, weight);
//                    if (currentOrder != null) {
//                        currentOrder.getDishes().add(dish); // Додаємо страву до поточного замовлення
//                    }
//                }
//
//                // Якщо є порожній рядок, вважаємо, що закінчилось поточне замовлення
//                if (line.isEmpty() && currentOrder != null) {
//                    orders.add(currentOrder); // Додаємо замовлення до списку
//                    currentOrder = null; // Очищаємо для наступного замовлення
//                }
//            }
//
//            // Додаємо останнє замовлення, якщо воно є
//            if (currentOrder != null) {
//                orders.add(currentOrder);
//            }
//
//            System.out.println("Список замовлень успішно прочитано з файлу: " + filename);
//        } catch (IOException e) {
//            System.err.println("Помилка при читанні з файлу: " + e.getMessage());
//        } catch (NumberFormatException e) {
//            System.err.println("Помилка формату чисел у файлі: " + e.getMessage());
//        }
//
//        return orders;
//    }
//}


package Services.IOThreads.Orders;

import Models.Dish;
import Models.Order;
import Services.IRead;
import Services.IOThreads.Dishes.DishesReadService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderReadService implements IRead<Order> {

    private final DishesReadService dishesReadService = new DishesReadService();

    @Override
    public List<Order> ReadListFromFile(String filename) {
        List<Order> orders = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Order order = (Order) ois.readObject();
                    String dishFileName = "dishes_order_" + order.getOrderID() + ".txt";
                    List<Dish> dishes = dishesReadService.ReadListFromFile(dishFileName);
                    order.setDishes(dishes);

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