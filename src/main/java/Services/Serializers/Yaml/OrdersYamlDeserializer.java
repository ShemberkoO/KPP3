package Services.Serializers.Yaml;

import Models.Order;
import Models.Dish;
import Models.Client;
import Services.IRead;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//
//public class OrdersYamlDeserializer implements IRead<Order> {
//
//    @Override
//    public List<Order> ReadListFromFile(String filename) {
//        List<Order> orders = new ArrayList<>();
//        Yaml yaml = new Yaml(new Constructor(Map.class));  // Використовуємо для читання YAML як Map
//
//        try (FileReader reader = new FileReader(filename)) {
//            Iterable<Object> yamlObjects = yaml.loadAll(reader);
//            for (Object obj : yamlObjects) {
//                Map<String, Object> orderData = (Map<String, Object>) obj;
//                orders.add(convertMapToOrder(orderData));
//            }
//        } catch (IOException e) {
//            System.err.println("Помилка при читанні з файлу: " + e.getMessage());
//        }
//
//        return orders;
//    }
//
//    private Order convertMapToOrder(Map<String, Object> orderData) {
//        int orderID = (int) orderData.get("orderID");
//
//        // Відновлюємо клієнта, якщо інформація про нього є в YAML
//        Client client = null;
//        if (orderData.containsKey("client")) {
//            Map<String, Object> clientData = (Map<String, Object>) orderData.get("client");
//            client = convertMapToClient(clientData);
//        }
//
//        // Відновлюємо список страв
//        List<Dish> dishes = new ArrayList<>();
//        List<Map<String, Object>> dishesList = (List<Map<String, Object>>) orderData.get("dishes");
//        for (Map<String, Object> dishData : dishesList) {
//            dishes.add(convertMapToDish(dishData));
//        }
//
//        return new Order(orderID, client, dishes);
//    }
//
//    private Client convertMapToClient(Map<String, Object> clientData) {
//        String firstName = (String) clientData.get("firstName");
//        String lastName = (String) clientData.get("lastName");
//        return new Client(firstName, lastName);
//    }
//
//    private Dish convertMapToDish(Map<String, Object> dishData) {
//        int id = (int) dishData.get("id");
//        String name = (String) dishData.get("name");
//        double cost = (double) dishData.get("cost");
//        int weightInGrams = (int) dishData.get("weightInGrams");
//        return new Dish(id, name, cost, weightInGrams);
//    }
//}


import Models.Order;
import Services.IRead;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import Models.Order;
import Services.IWrite;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrdersYamlDeserializer implements IRead<Order> {
    private final Yaml yaml;
    public  OrdersYamlDeserializer(){
        Constructor constructor = new Constructor(List.class);
        this.yaml = new Yaml(constructor);
    }
    @Override
    public List<Order> ReadListFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {

            List<Order> list = yaml.load(reader);
            System.out.println("Students deserialized from YAML file: " + filename);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


