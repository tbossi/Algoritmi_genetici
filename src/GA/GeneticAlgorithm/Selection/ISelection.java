package GA.GeneticAlgorithm.Selection;

import GA.GeneticAlgorithm.Population.Chromosome;
import GA.GeneticAlgorithm.Population.Population;

public interface ISelection {
    <C extends Chromosome<?>, P extends Population<C>> C select(P population);
}

