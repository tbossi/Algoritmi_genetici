package GA.TravellingSalesmanProblem.Controller;

import javafx.util.Callback;
import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
    public HomeController getHomeController() {
        return new HomeController();
    }

    public SettingsController getSettingsController() {
        return new SettingsController();
    }

    public PopulationController getPopulationController() {
        return new PopulationController();
    }

    public Callback<Class<?>, Object> asCallback() {
        Map<Class<?>, Controller> controllers = new HashMap<>();
        controllers.put(HomeController.class, getHomeController());
        controllers.put(SettingsController.class, getSettingsController());
        controllers.put(PopulationController.class, getPopulationController());

        Callback<Class<?>, Object> controllerFactory = type -> {
            if (controllers.containsKey(type)) {
                return controllers.get(type);
            } else {
                try {
                    return type.newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc);
                }
            }
        };

        return controllerFactory;
    }
}
