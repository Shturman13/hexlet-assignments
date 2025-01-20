package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize(Car car) throws JsonProcessingException {
        ObjectMapper object = new ObjectMapper();
        return object.writeValueAsString(car);
    }

    public static Car deserialize(String car) throws IOException {
        ObjectMapper object = new ObjectMapper();
        Car objectCar = object.readValue(car, Car.class);
        return objectCar;
    }
    // END
}
