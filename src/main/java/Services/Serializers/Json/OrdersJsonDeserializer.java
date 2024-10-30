package Services.Serializers.Json;

import Models.Order;
import Services.IRead;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class OrdersJsonDeserializer implements IRead<Order> {
    private final Gson gson;

    public OrdersJsonDeserializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<Order> ReadListFromFile(String filename) {
        List<Order> orders = null;

        try (FileReader reader = new FileReader(filename)) {
            Type orderListType = new TypeToken<List<Order>>() {}.getType();

            orders = gson.fromJson(reader, orderListType);
            System.out.println("Список замовлень успішно прочитано з файлу: " + filename);
        } catch (IOException e) {
            System.err.println("Помилка при читанні з файлу: " + e.getMessage());
        }

        return orders;
    }
}
