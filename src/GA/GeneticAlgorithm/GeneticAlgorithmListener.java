package GA.GeneticAlgorithm;

import GA.GeneticAlgorithm.Population.Chromosome;
import GA.GeneticAlgorithm.Population.Population;

public interface GeneticAlgorithmListener<C extends Chromosome<C>> {
    void onIterationStart(int iteration, Population<C> population);
    void onIterationEnd(int iteration, Population<C> population);
}
