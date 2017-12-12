package GA.KnapsackProblem;

import GA.GeneticAlgorithm.GeneticAlgorithm;
import GA.GeneticAlgorithm.GeneticAlgorithmListener;
import GA.GeneticAlgorithm.GeneticAlgorithmSettings;
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

        contents.add(new Content(2, 0, "A"));
        contents.add(new Content(0.1, 0, "B"));
        contents.add(new Content(0.6, 0, "C"));
        contents.add(new Content(6, 0, "D"));
        contents.add(new Content(8.28, 0, "E"));
        contents.add(new Content(1.7, 0, "F"));
        contents.add(new Content(1.7, 0, "G"));
        contents.add(new Content(12, 0, "H"));
        contents.add(new Content(4.3, 0, "I"));
        contents.add(new Content(10.72, 0, "J"));
        contents.add(new Content(0.6, 0, "K"));
        contents.add(new Content(0.6, 0, "L"));
        contents.add(new Content(0.55, 0, "M"));
        contents.add(new Content(0.76, 0, "N"));
        contents.add(new Content(0.83, 0, "O"));
        contents.add(new Content(2.26, 0, "P"));
        contents.add(new Content(1.51, 0, "Q"));
        contents.add(new Content(0.17, 0, "R"));
        contents.add(new Content(5.09, 0, "S"));
        contents.add(new Content(1, 0, "T"));
        contents.add(new Content(0.65, 0, "U"));

        return contents;
    }

    public static void main(String[] args) {
        Random rng = new Random(1);
        ISelection selection = new TournamentSelection(rng, 4);
        GeneticAlgorithmSettings settings = new GeneticAlgorithmSettings(
                rng, 1, 1, 1, 0.275, selection, 7, 21);
        KnapsackProblem TSP = new KnapsackProblem(settings, ContentsToInclude(), 20);

        TSP.solveProblem();
    }
}
