package GA.GeneticAlgorithm.Selection;

import GA.GeneticAlgorithm.Population.Chromosome;
import GA.GeneticAlgorithm.Population.Population;

import java.util.Random;

public class RouletteSelection extends Selection {
    public RouletteSelection(Random random) {
        super(random);
    }

    @Override
    public <C extends Chromosome<?>, P extends Population<C>> C select(P population) {
        double totalFitness = 0;
        for (int i = 0; i < population.populationSize(); i++) {
            totalFitness += positiveFitness(population.get(i).getFitness());
        }
        double chosen = random.nextDouble() * totalFitness;
        double aggregator = 0;
        for (int i = 0; i < population.populationSize(); i++) {
            aggregator += positiveFitness(population.get(i).getFitness());
            if (aggregator >= chosen)
                return population.get(i);
        }
        return null;
    }

    /* Handles fitness with negative values*/
    private double positiveFitness(double originalFitness) {
        return originalFitness / 2 + Double.MAX_VALUE / 2;
    }
}
