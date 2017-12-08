package GA.GeneticAlgorithm;

import java.util.Random;

public class RouletteSelection extends Selection {
    public RouletteSelection(Random random) {
        super(random);
    }

    @Override
    public <C extends Chromosome<?>, P extends Population<C>> C select(P population) {
        double totalFitness = 0;
        for (int i = 0; i < population.populationSize(); i++) {
            totalFitness += population.get(i).getFitness();
        }
        double chosen = random.nextDouble() * totalFitness;
        double aggregator = 0;
        for (int i = 0; i < population.populationSize(); i++) {
            aggregator += population.get(i).getFitness();
            if (aggregator >= chosen)
                return population.get(i);
        }
        return null;
    }
}
