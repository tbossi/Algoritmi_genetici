package GA.TravellingSalesmanProblem;

import GA.GeneticAlgorithm.Population;
import java.util.ArrayList;

public class TourPopulation extends Population<Tour> {

    public TourPopulation(int populationSize) {
        super(Tour.class, populationSize);
    }

    public static TourPopulation initialPopulation(int populationSize, ArrayList<City> cities) {
        TourPopulation population = new TourPopulation(populationSize);
        for (int i = 0; i < populationSize; i++) {
            population.set(i, Tour.randomNew(cities));
        }
        return population;
    }
}
