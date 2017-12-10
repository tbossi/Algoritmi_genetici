package GA.GeneticAlgorithm;

import GA.GeneticAlgorithm.Selection.ISelection;

import java.util.Random;

public class GeneticAlgorithmSettings {
    public final int Elite;
    public final double CrossoverProbability;
    public final double MutationProbability;
    public final double MutationRate;
    public final ISelection Selection;

    public final Random Random;
    public final int Iteration;
    public final int PopulationSize;

    public GeneticAlgorithmSettings(Random rng, int elite, double crossoverProbability, double mutationProbability,
                                    double mutationRate, ISelection selection, int populationSize, int iteration) {
        if (rng == null) throw  new IllegalArgumentException();
        if (elite < 0) throw new IllegalArgumentException();
        if (crossoverProbability > 1 || crossoverProbability < 0) throw new IllegalArgumentException();
        if (mutationProbability > 1 || mutationProbability < 0) throw new IllegalArgumentException();
        if (selection == null) throw new IllegalArgumentException();
        if (mutationRate < 0) throw new IllegalArgumentException();
        if (populationSize < 1) throw new IllegalArgumentException();
        if (iteration < 0) throw new IllegalArgumentException();

        this.Random = rng;
        this.Elite = elite;
        this.CrossoverProbability = crossoverProbability;
        this.Selection = selection;
        this.MutationProbability = mutationProbability;
        this.MutationRate = mutationRate;
        this.PopulationSize = populationSize;
        this.Iteration = iteration;
    }
}
