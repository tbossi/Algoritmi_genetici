package GA.GeneticAlgorithm;

public abstract class GeneticAlgorithm<C extends Chromosome<C>, P extends Population<C>> {
    private GeneticAlgorithmSettings settings;

    public GeneticAlgorithm(GeneticAlgorithmSettings settings) {
        this.settings = settings;
    }

    public P evolve(P oldPopulation) {
        P newPopulation = newPopulation(oldPopulation);

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

    private void mutate(P population) {
        for (int i = settings.Elite; i < population.populationSize(); i++) {
            population.get(i).mutate(settings.MutationRate);
        }
    }

    private void clonePopulation(P newPopulation, P oldPopulation) {
        for (int i = 0; i < newPopulation.populationSize(); i++) {
            newPopulation.set(i, oldPopulation.get(i));
        }
    }

    private void setElite(P newPopulation, P oldPopulation) {
        for (int i = 0; i < settings.Elite; i++) {
            newPopulation.set(i, oldPopulation.getFittestList()[i]);
        }
    }

    private void crossoverPopulation(P newPopulation, P oldPopulation) {
        for (int i = settings.Elite; i < newPopulation.populationSize(); i++) {
            C parent1 = oldPopulation.selection(settings.Selection);
            C parent2 = oldPopulation.selection(settings.Selection);
            newPopulation.set(i, parent1.crossover(parent2));
        }
    }

    protected abstract P newPopulation(P oldPopulation);
}
