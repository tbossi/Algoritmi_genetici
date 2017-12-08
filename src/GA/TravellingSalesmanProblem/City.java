package GA.TravellingSalesmanProblem;

public class City {
    private final int x;
    private final int y;
    private final String name;

    public City(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public double distanceTo(City city) {
        int xDistance = x - city.x;
        int yDistance = y - city.y;
        double distance = Math.sqrt( (xDistance * xDistance) + (yDistance * yDistance) );

        return distance;
    }

    @Override
    public String toString() {
        return name+"_("+x+", "+y+")";
    }
}
