package Seeds;

import Models.Client;
import Models.Dish;
import Models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersSeeds {
    public static List<Order> getOrders() {
        // Створення даних для тестування
        Client client1 = new Client( "Іван","Іванов");
        Client client2 = new Client( "Марія", "Петренко");

        Dish dish1 = new Dish(101, "Піца", 150.50, 500);
        Dish dish2 = new Dish(102, "Салат", 70.30, 200);
        Dish dish3 = new Dish(103, "Сік", 30.00, 250);

        List<Dish> dishesOrder1 = new ArrayList<>();
        dishesOrder1.add(dish1);
        dishesOrder1.add(dish2);

        List<Dish> dishesOrder2 = new ArrayList<>();
        dishesOrder2.add(dish3);

        Order order1 = new Order(1, client1, dishesOrder1);
        Order order2 = new Order(2, client2, dishesOrder2);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        return orders;
    }
}
