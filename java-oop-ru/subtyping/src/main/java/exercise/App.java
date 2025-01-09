package exercise;

import java.util.HashMap;
import java.util.Map;
// BEGIN
public class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage dataBase) {
        Map<String, String> originalDataBase = dataBase.toMap();
        Map<String, String> swappedDataBase = new HashMap<>();

        originalDataBase.forEach((key, value) -> {
            swappedDataBase.put(value, key);
        });
        return new InMemoryKV(swappedDataBase);
    }
}
// END
