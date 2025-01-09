package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private static Map<String, String> dataBase;

    InMemoryKV(Map<String, String> dataBase) {
        var mutableDataBase = new HashMap<>(dataBase);
        this.dataBase = mutableDataBase;
    }

    @Override
    public void set(String key, String value) {
        dataBase.put(key, value);
    }

    @Override
    public void unset(String key) {
        dataBase.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return dataBase.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Map.copyOf(dataBase);
    }
}
// END
