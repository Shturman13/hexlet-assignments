package exercise;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> estateList, Integer numberOfElements) {
        estateList.sort((v1, v2) -> Double.compare(v1.getArea(), v2.getArea()));
        var requiredEstateList = estateList.stream().limit(numberOfElements).map(Objects::toString)
                .collect(Collectors.toList());
        return requiredEstateList;
    }
}
// END
