package GA.TravellingSalesmanProblem.View;

import GA.TravellingSalesmanProblem.Controller.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;

import java.io.IOException;

public class View {
    public static final Parent Home = load("Home.fxml");

    private static final Callback<Class<?>, Object> controllerFactory;
    static {
        ControllerFactory factory = new ControllerFactory();
        controllerFactory = factory.asCallback();
    }

    private static <T> T load(String resourceName) {
        FXMLLoader loader = new FXMLLoader(View.class.getResource(resourceName));
        loader.setControllerFactory(controllerFactory);
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
