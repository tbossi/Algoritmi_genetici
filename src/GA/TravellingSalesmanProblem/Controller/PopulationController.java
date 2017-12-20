package GA.TravellingSalesmanProblem.Controller;

import GA.TravellingSalesmanProblem.Model.City;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class PopulationController extends Controller {
    @FXML
    private TableView<City> populationTable;
    @FXML
    private TableColumn<City, String> nameColumn;
    @FXML
    private TableColumn<City, Double> xColumn;
    @FXML
    private TableColumn<City, Double> yColumn;

    private ObservableList<City> cities;

    public PopulationController() {
        cities = FXCollections.observableArrayList();
    }

    @Override
    public void initialize() {
        nameColumn.setText("City");
        nameColumn.setCellValueFactory(cityNameCell -> new SimpleStringProperty(cityNameCell.getValue().getName()));
        xColumn.setText("x");
        xColumn.setCellValueFactory(cityXCell -> new SimpleObjectProperty<>(cityXCell.getValue().getX()));
        yColumn.setText("y");
        yColumn.setCellValueFactory(cityYCell -> new SimpleObjectProperty<>(cityYCell.getValue().getY()));
        populationTable.setItems(cities);
    }

    public void addCity(City c) {
        cities.add(c);
    }

    public void addCities(List<City> c) {
        cities.addAll(c);
    }

    public void deleteCities() {
        cities.clear();
    }

    public List<City> getCities() {
        return cities;
    }
}
