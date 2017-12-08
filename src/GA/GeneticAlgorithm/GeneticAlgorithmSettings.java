package GA.GeneticAlgorithm;

import java.util.Random;

public class GeneticAlgorithmSettings {
    public final int Elite;
    public final double CrossoverProbability;
    public final double MutationProbability;
    public final double MutationRate;
    public final ISelection Selection;

    public final Random Random;

    public GeneticAlgorithmSettings(Random rng, int elite, double crossoverProbability, double mutationProbability,
                                    double mutationRate, ISelection selection) {
        if (rng == null) throw  new IllegalArgumentException();
        if (elite < 0) throw new IllegalArgumentException();
        if (crossoverProbability > 1 || crossoverProbability < 0) throw new IllegalArgumentException();
        if (mutationProbability > 1 || mutationProbability < 0) throw new IllegalArgumentException();
        if (selection == null) throw new IllegalArgumentException();
        if (mutationRate < 0) throw new IllegalArgumentException();

        this.Random = rng;
        this.Elite = elite;
        this.CrossoverProbability = crossoverProbability;
        this.Selection = selection;
        this.MutationProbability = mutationProbability;
        this.MutationRate = mutationRate;
    }
}
