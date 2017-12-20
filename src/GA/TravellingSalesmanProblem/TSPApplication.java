package GA.TravellingSalesmanProblem;

import GA.TravellingSalesmanProblem.View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TSPApplication extends Application {
    private static final String title = "Travelling Salesman Problem";

    public static void launch(String... args) {
        launch(TSPApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(title);
        stage.setScene(Scene());
        stage.show();
    }

    public Scene Scene() throws IOException {
        return new Scene(View.Home, 1200, 600);
    }
}
