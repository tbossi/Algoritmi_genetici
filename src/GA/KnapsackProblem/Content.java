package GA.KnapsackProblem;

public class Content {
    private final double weight;
    private final double value;
    private final String name;

    public Content(double weight, double value, String name) {
        if (weight < 0) throw new IllegalArgumentException();
        this.weight = weight;
        this.value = value;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "_" + "(" + weight + ", " + value + ")";
    }
}
