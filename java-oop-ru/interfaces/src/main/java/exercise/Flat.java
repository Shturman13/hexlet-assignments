package exercise;

// BEGIN
public class Flat implements Home {
    private Double area;
    private Double balconyArea;
    private Integer floor;

    Flat(Double area, Double balconyArea, Integer floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public Double getArea() {
        return area + balconyArea;
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
        return "Квартира площадью " + getArea() + " метров на " + floor
                + " этаже";
    }
}

// END
