package GA.GeneticAlgorithm.Population;

public class PopulationBuilder<C extends Chromosome<?>> {
    private Chromosome.RandomBuilder<C> randomBuilder;

    public PopulationBuilder(C.RandomBuilder<C> randomBuilder) {
        this.randomBuilder = randomBuilder;
    }

    public Population<C> initialPopulation(int populationSize) {
        Population<C> population = new Population<>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            population.set(i, C.randomNew(randomBuilder));
        }
        return population;
    }
}
