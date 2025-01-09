package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String pathToFile;
    private static Map<String, String> dataBase;

    FileKV (String pathToFile, Map<String, String> initialDataBase) {
        this.pathToFile = pathToFile;
        this.dataBase = new HashMap<>(initialDataBase);
        loadDataFromFile();
    }

    public void loadDataFromFile() {
        var file = Utils.readFile(pathToFile);
        Utils.deserialize(file);
        }

    public void saveDataToFile() {
        var jsonDataFile = Utils.serialize(dataBase);
        Utils.writeFile(pathToFile, jsonDataFile);
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
