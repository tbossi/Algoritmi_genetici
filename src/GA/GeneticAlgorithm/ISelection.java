package GA.GeneticAlgorithm;

public interface ISelection {
    <C extends Chromosome<?>, P extends Population<C>> C select(P population);
}

