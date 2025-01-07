package exercise;

// BEGIN
public class Cottage implements Home {
    private Double area;
    private Integer floorCount;

    Cottage(Double area, Integer floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public Double getArea() {
        return area;
    };

    public Integer compareTo(Home another) {
        if (getArea().equals(another.getArea())) {
            return 0;
        } else if (getArea() > another.getArea()) {
            return 1;
        } else {
            return -1;
        }
    };

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + area
                + " метров";
    }
}
// END
