package GA.GeneticAlgorithm.Selection;

import GA.GeneticAlgorithm.Population.Chromosome;
import GA.GeneticAlgorithm.Population.Population;

import java.util.Random;

public class TournamentSelection extends Selection {
    private final int tournamentSize;

    public TournamentSelection(Random random, int tournamentSize) {
        super(random);

        if (tournamentSize < 1) throw new IllegalArgumentException();
        this.tournamentSize = tournamentSize;
    }

    @Override
    public <C extends Chromosome<?>, P extends Population<C>> C select(P population) {
        Population<C> tournament = new Population<>(tournamentSize);

        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (random.nextDouble() * population.populationSize());
            tournament.set(i, population.get(randomId));
        }

        C fittest = tournament.getFittestList().get(0);
        return fittest;
    }
}

