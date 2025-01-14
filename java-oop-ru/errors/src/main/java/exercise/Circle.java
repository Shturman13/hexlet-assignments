package exercise;

// BEGIN
public class Circle {
    private Point circleCenter;
    private Integer radius;

    public Circle(Point circleCenter, Integer radius) {
        this.circleCenter = circleCenter;
        this.radius = radius;
    }

    public float getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Радиус не может быть отрицательным");
        }
            return (float) ((getRadius() * getRadius()) * Math.PI);
    }

    public Integer getRadius() {
        return radius;
    }
}
// END
