package GA.TravellingSalesmanProblem.Controller;

import GA.GeneticAlgorithm.GeneticAlgorithm;
import GA.GeneticAlgorithm.GeneticAlgorithmListener;
import GA.GeneticAlgorithm.Population.Population;
import GA.TravellingSalesmanProblem.Model.City;
import GA.TravellingSalesmanProblem.Model.Tour;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class HomeController extends Controller {
    @FXML
    public MenuItem generateMenuItem;
    @FXML
    public PopulationController populationController;
    @FXML
    public SettingsController settingsController;
    @FXML
    public GraphController graphController;

    @FXML
    public Button startButton;
    @FXML
    public Label currentIterationLabel;

    private List<City> cities;

    @Override
    public void initialize() {
        generateMenuItem.setOnAction(actionEvent ->
                generateRandomCitiesDialog()
                .showAndWait()
                .ifPresent(value -> {
                    populationController.deleteCities();
                    graphController.deleteCities();
                    cities = generateRandomCities(value);
                    populationController.addCities(cities);
                    graphController.addCities(cities, false);
                }));
        startButton.setOnMouseClicked(mouseEvent -> runAlgorithm());
    }

    private void runAlgorithm() {
        if (cities == null || cities.size() <= 1)
            return;

        GeneticAlgorithm<Tour> algorithm = new GeneticAlgorithm<>(settingsController.getSettings(),
                new Tour.RandomTour(cities));

        Task task = new Task<Population<Tour>>() {
            @Override
            protected Population<Tour> call() throws Exception {
                startButton.setDisable(true);
                Population<Tour> finalPop = algorithm.run(new GeneticAlgorithmListener<Tour>() {
                    @Override
                    public void onIterationStart(int iteration, Population<Tour> population) {
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onIterationEnd(int iteration, Population<Tour> population) {
                        List<City> citiesToShow = population.getFittestList().get(0).asList();
                        Platform.runLater(() -> {
                            currentIterationLabel.setText("Iteration: "+iteration);
                            graphController.deleteCities();
                            graphController.addCities(citiesToShow, true);
                        });
                    }
                });
                startButton.setDisable(false);
                return finalPop;
            }
        };

        Thread runner = new Thread(task);
        runner.start();
    }

    private List<City> generateRandomCities(int number) {
        ArrayList<City> cities = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            City city = new City(Math.random()*200-100, Math.random()*200-100, randomName());
            cities.add(city);
        }
        return cities;
    }

    private String randomName() {
        int length = (int) (Math.random()*13+3);
        char[] name = new char[length];
        for (int i = 0; i < name.length; i++) {
            name[i] = (char)(((int) (Math.random()*26)) + 'a');
        }
        return new String(name);
    }

    private Dialog<Integer> generateRandomCitiesDialog() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Generate random cities");
        dialog.setHeaderText(null);

        ButtonType generateButton = new ButtonType("Generate", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(generateButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Spinner<Integer> citiesSpinner = new Spinner<>();
        citiesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 5000, 10));
        citiesSpinner.setEditable(true);
        grid.add(new Label("Number of cities"), 0, 0);
        grid.add(citiesSpinner, 1, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(buttonType -> {
            if (buttonType.equals(generateButton)) {
                return citiesSpinner.getValue();
            }
            return null;
        });

        return dialog;
    }
}
