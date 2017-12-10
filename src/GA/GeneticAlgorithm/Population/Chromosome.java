package GA.GeneticAlgorithm.Population;

public abstract class Chromosome<T extends Chromosome<?>> {
    public abstract double getFitness();
    public abstract void mutate(double mutationRate);
    public abstract T crossover(T otherParent);

    public static <C extends Chromosome<?>> C randomNew(RandomBuilder<C> builder) {
        return builder.create();
    }

    protected abstract static class RandomBuilder<C extends Chromosome<?>> {
        public abstract C create();
    }
}

