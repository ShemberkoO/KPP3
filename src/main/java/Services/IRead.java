package Services;

import java.util.List;

public interface IRead<T> {
    List<T> ReadListFromFile(String filename);
}
