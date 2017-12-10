package GA.GeneticAlgorithm.Population;

import GA.GeneticAlgorithm.Selection.ISelection;

import java.util.ArrayList;
import java.util.Collections;

public class Population<C extends Chromosome<?>> {
    private ArrayList<C> population;
    private ArrayList<C> fittestListCache;

    public Population(int populationSize) {
        this.population = newArray(populationSize);
    }

    public C get(int index) {
        if (index < 0 || index > populationSize()) throw new IndexOutOfBoundsException();
        return population.get(index);
    }

    public void set(int index, C chromosome) {
        if (index < 0 || index > populationSize()) throw new IndexOutOfBoundsException();
        population.set(index, chromosome);
        fittestListCache = null;
    }

    public int populationSize() {
        return population.size();
    }

    public ArrayList<C> getFittestList() {
        if (fittestListCache != null) {
            return fittestListCache;
        }

        fittestListCache = clone(population);
        Collections.sort(fittestListCache, (chromosome1, chromosome2) -> {
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

    public C selection(ISelection selection) {
        return selection.select( this);
    }

    private ArrayList<C> newArray(int size) {
        ArrayList<C> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(null);
        }
        return list;
    }

    private ArrayList<C> clone(ArrayList<C> list) {
        ArrayList<C> newList = new ArrayList<>();
        newList.addAll(list);
        return newList;
    }
}

