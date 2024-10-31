package Services.Serializers.Yaml;

import Models.Client;
import Models.Dish;
import Models.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdersYamlSDSTest {

    private static final String TEST_FILENAME = "test_orders.yaml";
    private OrdersYamlSerializer serializer;
    private OrdersYamlDeserializer deserializer;

    @BeforeEach
    void setUp() {
        serializer = new OrdersYamlSerializer();
        deserializer = new OrdersYamlDeserializer();
    }

    @AfterEach
    void tearDown() {

        File testFile = new File(TEST_FILENAME);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testSerializationWithClient() {

        List<Dish> dishes = Arrays.asList(
                new Dish(1, "Pasta", 5.99, 300),
                new Dish(2, "Pizza", 8.99, 400)
        );
        Order order = new Order(1, new Client("John", "Doe"), dishes);
        List<Order> ordersToWrite = Arrays.asList(order);

        serializer.WriteListToFile(ordersToWrite, TEST_FILENAME);
        List<Order> ordersRead = deserializer.ReadListFromFile(TEST_FILENAME);

        assertNotNull(ordersRead);
        assertEquals(1, ordersRead.size());
        assertEquals("John", ordersRead.get(0).getClient().getFirstName());
        assertEquals("Doe", ordersRead.get(0).getClient().getLastName());
        assertEquals(2, ordersRead.get(0).getDishes().size());
    }

    @Test
    void testSerializationWithoutClientWhenMoreThanThreeDishes() {

        List<Dish> dishes = Arrays.asList(
                new Dish(1, "Pasta", 5.99, 300),
                new Dish(2, "Pizza", 8.99, 400),
                new Dish(3, "Burger", 9.99, 500),
                new Dish(4, "Salad", 4.99, 200)
        );
        Order order = new Order(1, new Client("John", "Doe"), dishes);
        List<Order> ordersToWrite = Arrays.asList(order);

        serializer.WriteListToFile(ordersToWrite, TEST_FILENAME);
        List<Order> ordersRead = deserializer.ReadListFromFile(TEST_FILENAME);

        assertNotNull(ordersRead);
        assertEquals(1, ordersRead.size());
        assertNull(ordersRead.get(0).getClient());
        assertEquals(4, ordersRead.get(0).getDishes().size());
    }
}
