package GA.TravellingSalesmanProblem;

import GA.GeneticAlgorithm.*;
import GA.GeneticAlgorithm.Population.Population;
import GA.GeneticAlgorithm.Population.PopulationBuilder;
import GA.GeneticAlgorithm.Selection.ISelection;
import GA.GeneticAlgorithm.Selection.TournamentSelection;

import java.util.ArrayList;
import java.util.Random;

public class TravellingSalesmanProblem {
    private GeneticAlgorithm<Tour> algorithm;

    public TravellingSalesmanProblem(ArrayList<City> citiesToVisit, GeneticAlgorithmSettings settings) {
        PopulationBuilder<Tour> popBuilder = new PopulationBuilder<>(new Tour.RandomTour(citiesToVisit));
        this.algorithm = new GeneticAlgorithm<>(settings, popBuilder);
    }

    private void solveProblem() {
        System.out.println("Starting: ");
        Population<Tour> finalPopulation = algorithm.run(new GeneticAlgorithmListener<Tour>() {
            @Override
            public void onIterationStart(int iteration, Population<Tour> population) {
                System.out.println("\nIteration "+iteration);
                ArrayList<Tour> list = population.getFittestList();
                System.out.println("Tours ordered by fitness: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("  [" + i + "]\tdistance: " + list.get(i).getDistance());
                    System.out.println("        " + list.get(i));
                }
            }

            @Override
            public void onIterationEnd(int iteration, Population<Tour> population) {}
        });

        ArrayList<Tour> list = finalPopulation.getFittestList();
        System.out.println("Finished");
        System.out.println("Final tour: " + list.get(0));
        System.out.println("Final distance: " + list.get(0).getDistance());
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
        Random rng = new Random(1);
        ISelection selection = new TournamentSelection(rng, 7);
        GeneticAlgorithmSettings settings = new GeneticAlgorithmSettings(
                rng,1, 0.8, 0.8, 0.055, selection, 10, 10);
        TravellingSalesmanProblem TSP = new TravellingSalesmanProblem(CitiesToVisit(), settings);

        TSP.solveProblem();
    }
}
