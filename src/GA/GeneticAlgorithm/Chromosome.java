package GA.GeneticAlgorithm;

public abstract class Chromosome<T extends Chromosome<?>> {
    public abstract double getFitness();
    public abstract void mutate(double mutationRate);
    public abstract T crossover(T otherParent);
}

