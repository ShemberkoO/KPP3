import Models.Order;
import Services.IOThreads.Orders.OrderReadService;
import Services.IOThreads.Orders.OrderWriteService;
import Services.IRead;
import Services.IWrite;
import Services.Serializers.Json.OrdersJsonDeserializer;
import Services.Serializers.Json.OrdersJsonSerializer;
import Services.Serializers.Native.OrdersNativeDeserializer;
import Services.Serializers.Native.OrdersNativeSerializer;
import Services.Serializers.Yaml.OrdersYamlDeserializer;
import Services.Serializers.Yaml.OrdersYamlSerializer;

import java.util.List;

public class IOEngine {
    String currentFileName;
    IRead<Order> currentReader;
    IWrite<Order> currentWriter;

    IOEngine(){
       setIOWR();
    }

    public  void setIOWR(){
        currentFileName = FilesPath.IO_FILE;
        currentReader = new OrderReadService();
        currentWriter = new OrderWriteService();
    }

    public  void setNativeWR(){
        currentFileName = FilesPath.NATIVE_FILE;
        currentReader = new OrdersNativeDeserializer();
        currentWriter = new OrdersNativeSerializer();
    }

    public  void setJsonWR(){
        currentFileName = FilesPath.JSON_FILE;
        currentReader = new OrdersJsonDeserializer();
        currentWriter = new OrdersJsonSerializer();
    }

    public  void setYamlWR(){
        currentFileName = FilesPath.YAML_FILE;
        currentReader = new OrdersYamlDeserializer();
        currentWriter = new OrdersYamlSerializer();
    }

    public  void write(List<Order> orders){
        currentWriter.WriteListToFile(orders, currentFileName);
    }

    public  void read(){
        var list = currentReader.ReadListFromFile(currentFileName);
        System.out.println(list);
    }

}
