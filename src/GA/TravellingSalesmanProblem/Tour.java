package GA.TravellingSalesmanProblem;

import GA.GeneticAlgorithm.Population.Chromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour extends Chromosome<Tour> {
    private List<City> tour;

    public Tour(List<City> tour) {
        this.tour = tour;
    }

    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    public int tourSize() {
        return tour.size();
    }

    @Override
    public double getFitness() {
        return 1 / (double) getDistance();
    }

    public int getDistance() {
        int tourDistance = 0;
        for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
            City fromCity = getCity(cityIndex);
            City destinationCity = getCity( (cityIndex + 1) % tourSize() );

            tourDistance += fromCity.distanceTo(destinationCity);
        }
        return tourDistance;
    }

    @Override
    public void mutate(double mutationRate) {
        for (int tourPos1 = 0; tourPos1 < tourSize(); tourPos1++) {
            if (Math.random() < mutationRate) {
                int tourPos2 = (int) (tourSize() * Math.random());
                swap(tourPos1, tourPos2);
            }
        }
    }

    private void swap(int position1, int position2) {
        City city1 = getCity(position1);
        City city2 = getCity(position2);
        tour.set(position1, city2);
        tour.set(position2, city1);
    }

    @Override
    public Tour crossover(Tour otherParent) {
        ArrayList<City> offspringCities =  new ArrayList<>();

        int startPos = (int) (Math.random() * tourSize());
        int endPos = (int) (Math.random() * tourSize());

        for (int i = 0; i < tourSize(); i++) {
            if ((startPos < endPos && i > startPos && i < endPos) || (startPos > endPos && i < startPos && i > endPos)) {
                offspringCities.add(getCity(i));
            } else {
                offspringCities.add(null);
            }
        }

        for (int i = 0; i < otherParent.tourSize(); i++) {
            if (!offspringCities.contains(otherParent.getCity(i))) {
                for (int j = 0; j < tourSize(); j++) {
                    if (offspringCities.get(j) == null) {
                        offspringCities.set(j, otherParent.getCity(i));
                        break;
                    }
                }
            }
        }

        Tour child = new Tour(offspringCities);
        return child;
    }

    @Override
    public String toString() {
        return tour.stream().map(City::toString).reduce((s, s2) -> s + " -> " + s2).get();
    }

    static class RandomTour extends Chromosome.RandomBuilder<Tour> {
        private List<City> tour;

        public RandomTour(List<City> tour) {
            this.tour = tour;
        }

        @Override
        public Tour create() {
            ArrayList<City> cities = new ArrayList<>();
            tour.forEach(city -> cities.add(city));
            Collections.shuffle(cities);
            return new Tour(cities);
        }
    }
}
