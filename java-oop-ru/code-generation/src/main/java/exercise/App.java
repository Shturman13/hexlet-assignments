package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws JsonProcessingException {
        var readyToSave = car.serialize(car);
        try {
            Files.writeString(path, readyToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        try {
            String jsonString = Files.readString(path);
            return Car.deserialize(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
