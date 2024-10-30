package Services.Serializers.Json;

import Models.Order;
import Services.IWrite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrdersJsonSerializer implements IWrite<Order> {
    private final Gson gson;

    public OrdersJsonSerializer() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void WriteListToFile(List<Order> list, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            this.gson.toJson(list, file);
            System.out.println("serialized to JSON " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
