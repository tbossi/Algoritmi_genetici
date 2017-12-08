package GA.GeneticAlgorithm;

public class GeneticAlgorithmSettings {
    public final int Elite;

    public final double CrossoverProbability;
    public final int TournamentSize;

    public final double MutationProbability;
    public final double MutationRate;

    public GeneticAlgorithmSettings(int elite, double crossoverProbability, int tournamentSize,
                                    double mutationProbability, double mutationRate) {
        if (elite < 0) throw new IllegalArgumentException();
        if (crossoverProbability > 1 || crossoverProbability < 0) throw new IllegalArgumentException();
        if (mutationProbability > 1 || mutationProbability < 0) throw new IllegalArgumentException();
        if (tournamentSize < 0) throw new IllegalArgumentException();
        if (mutationRate < 0) throw new IllegalArgumentException();

        this.Elite = elite;

        this.CrossoverProbability = crossoverProbability;
        this.TournamentSize = tournamentSize;

        this.MutationProbability = mutationProbability;
        this.MutationRate = mutationRate;
    }
}
