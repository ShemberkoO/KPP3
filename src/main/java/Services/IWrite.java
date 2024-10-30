package Services;

import java.util.List;

public interface IWrite<T> {
    void WriteListToFile(List<T> list, String fileName);
}
