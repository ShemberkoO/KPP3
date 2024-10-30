import Models.Client;
import Models.Dish;
import Models.Order;
import Seeds.OrdersSeeds;
import Services.IOThreads.Orders.OrderReadService;
import Services.IOThreads.Orders.OrderWriteService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        IOEngine engine = new IOEngine();
        engine.write(OrdersSeeds.getOrders());
        engine.read();
        engine.setJsonWR();
        engine.write(OrdersSeeds.getOrders());
        engine.read();
        engine.setNativeWR();
        engine.write(OrdersSeeds.getOrders());
        engine.read();
        engine.setYamlWR();
        engine.write(OrdersSeeds.getOrders());
        engine.read();
    }
}
