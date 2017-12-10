package GA.KnapsackProblem;

import GA.GeneticAlgorithm.*;
import GA.GeneticAlgorithm.Population.Population;
import GA.GeneticAlgorithm.Selection.ISelection;
import GA.GeneticAlgorithm.Selection.TournamentSelection;

import java.util.ArrayList;
import java.util.Random;

public class KnapsackProblem {
    private GeneticAlgorithm<Knapsack> algorithm;

    public KnapsackProblem(GeneticAlgorithmSettings settings, ArrayList<Content> contents, double maxWeight) {
        this.algorithm = new GeneticAlgorithm<>(settings, new Knapsack.RandomKnapsack(contents, maxWeight));
    }

    private void solveProblem() {
        System.out.println("Starting: ");
        Population<Knapsack> finalPopulation = algorithm.run(new GeneticAlgorithmListener<Knapsack>() {
            @Override
            public void onIterationStart(int iteration, Population<Knapsack> population) {
                System.out.println("\nIteration " + iteration);
                ArrayList<Knapsack> list = population.getFittestList();
                System.out.println("Tours ordered by fitness: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("  [" + i + "]\tweight: " + list.get(i).getWeight()
                            + "\t| value: " + list.get(i).getValue());
                    System.out.println("        " + list.get(i));
                }
            }

            @Override
            public void onIterationEnd(int iteration, Population<Knapsack> population) {
            }
        });

        ArrayList<Knapsack> list = finalPopulation.getFittestList();
        System.out.println("Finished");
        System.out.println("Final knapsack: " + list.get(0));
        System.out.println("Final weight: " + list.get(0).getWeight() + "\t| value: " + list.get(0).getValue());
    }

    public static ArrayList<Content> ContentsToInclude() {
        ArrayList<Content> contents = new ArrayList<>();

        contents.add(new Content(15, 5, "Water"));
        contents.add(new Content(0.7, 0.6, "Candy"));
        contents.add(new Content(2, 3, "A"));
        contents.add(new Content(0.1, -3, "B"));
        contents.add(new Content(1.003, 15, "C"));
        contents.add(new Content(6.33, 4.89, "D"));
        contents.add(new Content(8.28, 3.05, "E"));
        contents.add(new Content(0.7, 1.2, "F"));

        return contents;
    }

    public static void main(String[] args) {
        Random rng = new Random(1);
        ISelection selection = new TournamentSelection(rng, 7);
        GeneticAlgorithmSettings settings = new GeneticAlgorithmSettings(
                rng, 1, 0.8, 0.8, 0.055, selection, 10, 10);
        KnapsackProblem TSP = new KnapsackProblem(settings, ContentsToInclude(), 30);

        TSP.solveProblem();
    }
}
