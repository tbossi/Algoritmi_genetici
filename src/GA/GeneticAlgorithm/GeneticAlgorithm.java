package GA.GeneticAlgorithm;

import GA.GeneticAlgorithm.Population.Chromosome;
import GA.GeneticAlgorithm.Population.Population;
import GA.GeneticAlgorithm.Population.PopulationBuilder;

public class GeneticAlgorithm<C extends Chromosome<C>> {
    private final GeneticAlgorithmSettings settings;
    private final PopulationBuilder<C> populationBuilder;

    public GeneticAlgorithm(GeneticAlgorithmSettings settings, PopulationBuilder<C> populationBuilder) {
        this.settings = settings;
        this.populationBuilder = populationBuilder;
    }

    public Population<C> run(GeneticAlgorithmListener<C> listener) {
        Population<C> population = populationBuilder.initialPopulation(settings.PopulationSize);
        for (int i = 0; i < settings.Iteration; i++) {
            listener.onIterationStart(i, population);
            population = evolve(population);
            listener.onIterationEnd(i, population);
        }
        return population;
    }

    public Population<C> evolve(Population<C> oldPopulation) {
        Population<C> newPopulation = new Population<>(oldPopulation.populationSize());

        if (settings.Random.nextDouble() < settings.CrossoverProbability) {
            setElite(newPopulation, oldPopulation);
            crossoverPopulation(newPopulation, oldPopulation);
        } else {
            clonePopulation(newPopulation, oldPopulation);
        }

        if (settings.Random.nextDouble() < settings.MutationProbability) {
            mutate(newPopulation);
        }

        return newPopulation;
    }

    private void mutate(Population<C> population) {
        for (int i = settings.Elite; i < population.populationSize(); i++) {
            population.get(i).mutate(settings.MutationRate);
        }
    }

    private void clonePopulation(Population<C> newPopulation, Population<C> oldPopulation) {
        for (int i = 0; i < newPopulation.populationSize(); i++) {
            newPopulation.set(i, oldPopulation.get(i));
        }
    }

    private void setElite(Population<C> newPopulation, Population<C> oldPopulation) {
        for (int i = 0; i < settings.Elite; i++) {
            newPopulation.set(i, oldPopulation.getFittestList().get(i));
        }
    }

    private void crossoverPopulation(Population<C> newPopulation, Population<C> oldPopulation) {
        for (int i = settings.Elite; i < newPopulation.populationSize(); i++) {
            C parent1 = oldPopulation.selection(settings.Selection);
            C parent2 = oldPopulation.selection(settings.Selection);
            newPopulation.set(i, parent1.crossover(parent2));
        }
    }
}
