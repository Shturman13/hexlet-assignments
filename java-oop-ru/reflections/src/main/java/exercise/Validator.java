package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        var notPassedValidation = new ArrayList<String>();
        for (Field field : address.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        notPassedValidation.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return notPassedValidation;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        var listOfErrors = new HashMap<String, List<String>>();
        for (Field field : address.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                var value = field.get(address);
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (value == null) {
                        listOfErrors.computeIfAbsent(field.getName(), k -> new ArrayList<>())
                                .add("can not be null");


                    } else {
                        if (field.isAnnotationPresent(MinLength.class)) {
                            MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                            int minLength = minLengthAnnotation.minLength();
                            if (value instanceof String &&  ((String) value).length() < minLength) {
                                listOfErrors.computeIfAbsent(field.getName(), k -> new ArrayList<>())
                                        .add("length less than " + minLength);
                            }
                        }
                    }

                } else if (field.isAnnotationPresent(MinLength.class)) {
                        // Если поле не @NotNull, но имеет @MinLength, проверяем только на длину
                    MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                    int minLength = minLengthAnnotation.minLength();
                    if (value instanceof String && ((String) value).length() < minLength) {
                        listOfErrors.computeIfAbsent(field.getName(), k -> new ArrayList<>())
                                .add("length less than " + minLength);
                    }
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return listOfErrors;
    }
}
// END
