package GA.TravellingSalesmanProblem.Model;

public class City {
    private final double x;
    private final double y;
    private final String name;

    public City(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(City city) {
        double xDistance = x - city.x;
        double yDistance = y - city.y;

        return Math.sqrt( (xDistance * xDistance) + (yDistance * yDistance) );
    }

    @Override
    public String toString() {
        return name+"_("+x+", "+y+")";
    }
}
