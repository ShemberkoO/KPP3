package Services.Serializers.Yaml;

import Models.Order;
import Services.IWrite;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrdersYamlSerializer implements IWrite<Order> {
    private final Yaml yaml;

    public OrdersYamlSerializer() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // Use block style for readability
        options.setIndent(2);
        this.yaml = new Yaml(options);
    }

    @Override
    public void WriteListToFile(List<Order> list, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            yaml.dump(list, writer);
            System.out.println("Students serialized to YAML file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
