package GA.TravellingSalesmanProblem;

import GA.GeneticAlgorithm.GeneticAlgorithm;
import GA.GeneticAlgorithm.GeneticAlgorithmSettings;

public class TSPGeneticAlgorithm extends GeneticAlgorithm<Tour,TourPopulation> {
    public TSPGeneticAlgorithm(GeneticAlgorithmSettings settings) {
        super(settings);
    }

    protected TourPopulation newPopulation(TourPopulation oldPopulation) {
        return new TourPopulation(oldPopulation.populationSize());
    }
}
