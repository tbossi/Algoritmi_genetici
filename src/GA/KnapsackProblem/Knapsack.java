package GA.KnapsackProblem;

import GA.GeneticAlgorithm.Population.Chromosome;
import java.util.*;

public class Knapsack extends Chromosome<Knapsack> {
    private Map<Content, Boolean> contentMap;
    private final double maxWeight;

    public Knapsack(Map<Content, Boolean> contentMap, double maxWeight) {
        this.contentMap = contentMap;
        this.maxWeight = maxWeight;
    }

    @Override
    public double getFitness() {
        double contentWeight = getWeight();
        double contentValue = getValue();

        double weightFitness = (contentWeight <= maxWeight ? contentWeight : (maxWeight - contentWeight))/maxWeight;
        double valueFitness = contentValue >= 0 ? 1 + Math.sqrt(contentValue) : Math.pow(2, contentValue);
        return weightFitness * valueFitness;
    }

    public double getWeight() {
        return contentMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .mapToDouble(entry -> entry.getKey().getWeight())
                .sum();
    }

    public double getValue() {
        return contentMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .mapToDouble(entry -> entry.getKey().getValue())
                .sum();
    }

    @Override
    public void mutate(double mutationRate) {
        for (Map.Entry<Content,Boolean> entry: contentMap.entrySet()) {
            if (Math.random() < mutationRate) {
                contentMap.replace(entry.getKey(), !entry.getValue());
            }
        }
    }

    @Override
    public Knapsack crossover(Knapsack otherParent) {
        HashMap<Content, Boolean> contents = new HashMap<>();
        contentMap.keySet().forEach(key -> {
            contents.put(key, otherParent.contentMap.get(key) || contentMap.get(key));
            //TODO: generalize crossover
        });
        return new Knapsack(contents, maxWeight);
    }

    @Override
    public String toString() {
        return contentMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(entry -> entry.getKey().toString())
                .reduce((s, s2) -> s + ", " + s2).orElse("Empty");
    }

    static class RandomKnapsack extends Chromosome.RandomBuilder<Knapsack> {
        private List<Content> availableObjects;
        private double maxWeight;

        public RandomKnapsack(List<Content> availableObjects, double maxWeight) {
            this.availableObjects = availableObjects;
            this.maxWeight = maxWeight;
        }

        @Override
        public Knapsack create() {
            Map<Content, Boolean> contentMap = new HashMap<>();
            availableObjects.forEach(obj -> contentMap.put(obj, Math.random() < 0.5));
            return new Knapsack(contentMap, maxWeight);
        }
    }
}
