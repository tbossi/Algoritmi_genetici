package GA.TravellingSalesmanProblem.Controller;

import GA.GeneticAlgorithm.GeneticAlgorithmSettings;
import GA.GeneticAlgorithm.Selection.ISelection;
import GA.GeneticAlgorithm.Selection.RankSelection;
import GA.GeneticAlgorithm.Selection.RouletteSelection;
import GA.GeneticAlgorithm.Selection.TournamentSelection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

public class SettingsController extends Controller {
    @FXML
    private Label crossoverLabel;
    @FXML
    private Label mutationLabel;
    @FXML
    private Label mutationRateLabel;
    @FXML
    private Label populationLabel;
    @FXML
    private Label eliteLabel;
    @FXML
    private Label iterationLabel;
    @FXML
    private Label selectionLabel;
    @FXML
    private Label tournamentSizeLabel;

    @FXML
    private Spinner<Double> crossoverSpinner;
    @FXML
    private Spinner<Double> mutationSpinner;
    @FXML
    private Spinner<Double> mutationRateSpinner;
    @FXML
    private Spinner<Integer> populationSpinner;
    @FXML
    private Spinner<Integer> eliteSpinner;
    @FXML
    private Spinner<Integer> iterationSpinner;
    @FXML
    private ChoiceBox<String> selectionChoice;
    @FXML
    private Spinner<Integer> tournamentSizeSpinner;

    @FXML
    @Override
    public void initialize() {
        crossoverLabel.setText("Crossover probability");
        setSpinner(crossoverSpinner, new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1,1,0.01));

        mutationLabel.setText("Mutation probability");
        setSpinner(mutationSpinner, new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1,1,0.01));

        mutationRateLabel.setText("Mutation rate");
        setSpinner(mutationRateSpinner, new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1,0.06,0.01));

        populationLabel.setText("Population");
        setSpinner(populationSpinner, new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 15));

        eliteLabel.setText("Elite");
        setSpinner(eliteSpinner, new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1));
        ((SpinnerValueFactory.IntegerSpinnerValueFactory)eliteSpinner.getValueFactory())
                .maxProperty().bind(populationSpinner.valueProperty());

        iterationLabel.setText("Iterations");
        setSpinner(iterationSpinner, new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 10));

        selectionLabel.setText("Selection Method");
        selectionChoice.setItems(FXCollections.observableArrayList("Roulette", "Tournament", "Rank"));
        selectionChoice.setValue("Rank");

        tournamentSizeLabel.setText("Tournament size");
        setSpinner(tournamentSizeSpinner, new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 10));
    }

    public GeneticAlgorithmSettings getSettings() {
        Random rng = new Random();

        ISelection selection = null;
        if (selectionChoice.getValue().equals("Roulette")) {
            selection = new RouletteSelection(rng);
        } else if (selectionChoice.getValue().equals("Tournament")) {
            selection = new TournamentSelection(rng, tournamentSizeSpinner.getValue());
        } else if (selectionChoice.getValue().equals("Rank")) {
            selection = new RankSelection(rng);
        }

        GeneticAlgorithmSettings settings = new GeneticAlgorithmSettings(rng, eliteSpinner.getValue(),
                crossoverSpinner.getValue(), mutationSpinner.getValue(), mutationRateSpinner.getValue(),
                selection, populationSpinner.getValue(), iterationSpinner.getValue());
        return settings;
    }

    private <T> void setSpinner(Spinner<T> spinner, SpinnerValueFactory<T> factory) {
        spinner.setValueFactory(factory);
        spinner.setEditable(true);
    }
}
