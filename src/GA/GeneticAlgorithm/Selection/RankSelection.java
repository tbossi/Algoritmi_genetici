package GA.GeneticAlgorithm.Selection;

import GA.GeneticAlgorithm.Population.Chromosome;
import GA.GeneticAlgorithm.Population.Population;

import java.util.ArrayList;
import java.util.Random;

public class RankSelection extends Selection {
    public RankSelection(Random random) {
        super(random);
    }

    @Override
    public <C extends Chromosome<?>, P extends Population<C>> C select(P population) {
        ArrayList<C> list = population.getFittestList();
        double extracted = Math.random();
        double aggregator = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            aggregator += probability(list.size(), i+1);
            if (aggregator >= extracted)
                return list.get(i);
        }
        return null;
    }

    private double probability(int size, int ranking) {
        return (Math.pow(2, size - ranking) / (Math.pow(2, size) - 1) + 1) / (size + 1);
    }
}
