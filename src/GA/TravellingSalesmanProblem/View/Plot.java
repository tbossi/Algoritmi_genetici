package GA.TravellingSalesmanProblem.View;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.List;

public class Plot extends Pane {
    private Axes axes;

    public Plot(Axes axes) {
        this.axes = axes;

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().setAll(axes);
    }

    public void addPoints(List<Point2D> points, int pointRadius, int lineStroke, boolean connected) {
        points.forEach(p -> getChildren().add(getPoint(mapX(p.getX()), mapY(p.getY()), pointRadius)));
        if (connected)
            getChildren().add(getPath(points, lineStroke));
    }

    public void removePoints() {
        getChildren().setAll(axes);
    }

    private Path getPath(List<Point2D> points, int stroke) {
        Path path = new Path();
        path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
        path.setStrokeWidth(stroke);

        path.setClip(new Rectangle(0, 0, axes.getPrefWidth(), axes.getPrefHeight()));

        if (points.size() <= 1)
            return path;

        Point2D start = points.get(0);
        path.getElements().add(new MoveTo(mapX(start.getX()), mapY(start.getY())));

        for (int i = 1; i < points.size(); i++) {
            path.getElements().add(new LineTo(mapX(points.get(i).getX()), mapY(points.get(i).getY())));
        }
        return path;
    }

    private Circle getPoint(double x, double y, int pointRadius) {
        Circle c = new Circle();
        c.setCenterX(x);
        c.setCenterY(y);
        c.setRadius(pointRadius);
        c.fillProperty().setValue(Color.RED);
        return c;
    }

    private double mapX(double x) {
        double tx = axes.getPrefWidth() / 2;
        double sx = axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound());
        return x * sx + tx;
    }

    private double mapY(double y) {
        double ty = axes.getPrefHeight() / 2;
        double sy = axes.getPrefHeight() / (axes.getYAxis().getUpperBound() - axes.getYAxis().getLowerBound());
        return -y * sy + ty;
    }
}
