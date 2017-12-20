package GA.TravellingSalesmanProblem.Controller;

import GA.TravellingSalesmanProblem.Model.City;
import GA.TravellingSalesmanProblem.View.Axes;
import GA.TravellingSalesmanProblem.View.Plot;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Collectors;

public class GraphController extends Controller {
    @FXML
    private StackPane container;

    private Plot plot;
    @Override
    public void initialize() {
        Axes axes = new Axes(500, 500, -100, 100, 10, -100, 100, 10);
        plot = new Plot(axes);

        container.getChildren().add(plot);
        container.setPadding(new Insets(20));
        container.setBorder(new Border(new BorderStroke(Color.DARKGRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public void addCities(List<City> cities, boolean connected) {
        plot.addPoints(
                cities.stream()
                        .map(c -> new Point2D(c.getX(), c.getY()))
                        .collect(Collectors.toList()),
                4, 3, connected);
    }

    public void deleteCities() {
        plot.removePoints();
    }
}
