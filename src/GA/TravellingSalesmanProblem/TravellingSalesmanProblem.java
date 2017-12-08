package GA.TravellingSalesmanProblem;

import GA.GeneticAlgorithm.GeneticAlgorithmSettings;

import java.util.ArrayList;

public class TravellingSalesmanProblem {
    private ArrayList<City> citiesToVisit;
    private TSPGeneticAlgorithm algorithm;
    private final int iterations;

    public TravellingSalesmanProblem(ArrayList<City> citiesToVisit, GeneticAlgorithmSettings settings, int iterations) {
        this.citiesToVisit = citiesToVisit;
        this.algorithm = new TSPGeneticAlgorithm(settings);
        this.iterations = iterations;
    }

    private void solveProblem() {
        TourPopulation pop = TourPopulation.initialPopulation(10, citiesToVisit);
        System.out.println("Starting: ");

        for (int i = 0; i < iterations; i++) {
            System.out.println("\nIteration "+i);
            pop = evolutionStep(pop);
        }

        Tour[] list = pop.getFittestList();

        System.out.println("Finished");
        System.out.println("Final tour: " + list[0]);
        System.out.println("Final distance: " + list[0].getDistance());
        System.out.println("Solution:");
        System.out.println(list[0]);
    }

    private TourPopulation evolutionStep(TourPopulation oldPopulation) {
        Tour[] list = oldPopulation.getFittestList();
        System.out.println("Tours ordered by fitness: ");
        for (int i = 0; i < list.length; i++) {
            System.out.println("  [" + i + "]\tdistance: " + list[i].getDistance());
            System.out.println("        " + list[i]);
        }

        return algorithm.evolve(oldPopulation);
    }

    public static ArrayList<City> CitiesToVisit() {
        ArrayList<City> tour = new ArrayList<>();

        tour.add(new City(60, 200, "Aaa"));
        tour.add(new City(177, 196, "Bbb"));
        tour.add(new City(20, 160, "Ccc"));
        tour.add(new City(198, 166, "Ddd"));
        tour.add(new City(141, 139, "Eee"));
        tour.add(new City(104, 38, "Fff"));
        tour.add(new City(200, 43, "Ggg"));
        tour.add(new City(20, 15, "Hhh"));

        return tour;
    }

    public static void main(String[] args) {
        GeneticAlgorithmSettings settings = new GeneticAlgorithmSettings(1, 0.8, 7, 0.8, 0.055);
        TravellingSalesmanProblem TSP = new TravellingSalesmanProblem(CitiesToVisit(), settings, 10);

        TSP.solveProblem();
    }
}
