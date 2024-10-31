package Services.Serializers;

import Models.Client;
import Models.Dish;
import Models.Order;
import Services.Serializers.Json.OrdersJsonDeserializer;
import Services.Serializers.Json.OrdersJsonSerializer;
import Services.Serializers.Native.OrdersNativeDeserializer;
import Services.Serializers.Native.OrdersNativeSerializer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OrderSDServicesTest {

    private final String testFileName = "test_orders.dat"; // or .json based on the serializer
    private Object serializer;
    private Object deserializer;

    @BeforeEach
    void setUp() {
        // No setup needed here, the serializer and deserializer are set in the parameterized test.
    }

    @AfterEach
    void tearDown() {
        // Delete the test file after each test
        File testFile = new File(testFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    // Provide different serializers and deserializers
    static Stream<Object[]> provideSerializers() {
        return Stream.of(
                new Object[]{new OrdersJsonSerializer(), new OrdersJsonDeserializer(), "test_orders.json"},
                new Object[]{new OrdersNativeSerializer(), new OrdersNativeDeserializer(), "test_orders.dat"}
        );
    }

    @ParameterizedTest
    @MethodSource("provideSerializers")
    void testSerializationAndDeserialization(Object serializer, Object deserializer, String fileName) {
        // Create a list of orders for testing
        List<Dish> dishes = Arrays.asList(
                new Dish(1, "Pasta", 5.99, 300),
                new Dish(2, "Pizza", 8.99, 400)
        );
        Order order1 = new Order(1, new Client("ss", "sss"), dishes);
        Order order2 = new Order(2, new Client("ee", "eee"), Arrays.asList(
                new Dish(3, "Burger", 9.99, 500)
        ));
        List<Order> ordersToWrite = Arrays.asList(order1, order2);

        // Serialize the orders to the specified file
        if (serializer instanceof OrdersJsonSerializer) {
            ((OrdersJsonSerializer) serializer).WriteListToFile(ordersToWrite, fileName);
        } else if (serializer instanceof OrdersNativeSerializer) {
            ((OrdersNativeSerializer) serializer).WriteListToFile(ordersToWrite, fileName);
        }

        // Deserialize the orders from the specified file
        List<Order> ordersRead;
        if (deserializer instanceof OrdersJsonDeserializer) {
            ordersRead = ((OrdersJsonDeserializer) deserializer).ReadListFromFile(fileName);
        } else {
            ordersRead = ((OrdersNativeDeserializer) deserializer).ReadListFromFile(fileName);
        }

        assertNotNull(ordersRead);
        assertEquals(ordersToWrite.size(), ordersRead.size());
        for (int i = 0; i < ordersToWrite.size(); i++) {
            assertEquals(ordersToWrite.get(i).getOrderID(), ordersRead.get(i).getOrderID());
            // Assert that dishes are null for deserialized orders
            assertNull(ordersRead.get(i).getDishes());
            assertNotNull(ordersToWrite.get(i).getDishes());
            assertEquals(ordersToWrite.get(i).getClient().getFirstName(), ordersRead.get(i).getClient().getFirstName());
            assertEquals(ordersToWrite.get(i).getClient().getLastName(), ordersRead.get(i).getClient().getLastName());
            // Add additional assertions here to check other properties if necessary
        }
    }
}
