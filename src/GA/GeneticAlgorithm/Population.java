package GA.GeneticAlgorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Population<C extends Chromosome<?>> {
    private final Class<C> clazz;
    private C[] population;
    private C[] fittestListCache;

    public Population(Class<C> clazz, int populationSize) {
        this.clazz = clazz;
        population = newArray(populationSize);
    }

    public C get(int index) {
        return population[index];
    }

    public void set(int index, C chromosome) {
        population[index] = chromosome;
        fittestListCache = null;
    }

    public int populationSize() {
        return population.length;
    }

    public C[] getFittestList() {
        if (fittestListCache != null) {
            return fittestListCache;
        }

        fittestListCache = newArray(populationSize());
        System.arraycopy(population, 0, fittestListCache, 0, fittestListCache.length);

        Arrays.sort(fittestListCache, (chromosome1, chromosome2) -> {
            double fit1 = chromosome1.getFitness();
            double fit2 = chromosome2.getFitness();
            if (fit1 < fit2)
                return 1;
            else if (fit1 > fit2)
                return -1;
            return 0;
        });
        return fittestListCache;
    }

    public C selection(int tournamentSize) {
        if (tournamentSize < 1) {
            throw new IllegalArgumentException();
        }
        Population<C> tournament = new Population<>(clazz, tournamentSize);

        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * populationSize());
            tournament.set(i, get(randomId));
        }

        C fittest = tournament.getFittestList()[0];
        return fittest;
    }

    @SuppressWarnings("unchecked")
    private C[] newArray(int size) {
        return (C[]) Array.newInstance(this.clazz, size);
    }
}