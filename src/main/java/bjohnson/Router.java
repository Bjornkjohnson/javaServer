package bjohnson;

import bjohnson.ResponseHandlers.FourOhFiveResponseBuilder;
import bjohnson.ResponseHandlers.ResponseBuilderInterface;
import bjohnson.ResponseHandlers.FourOhFourResponseBuilder;

import java.util.HashMap;
import java.util.Vector;

public class Router {
    private final HashMap<String, ResponseBuilderInterface> routesWithMethods = new HashMap<>();
    private final Vector<String> routes = new Vector<>();

    public boolean routeExists(String route) {
        return routes.contains(route);
    }

    public void addRoute(String route, ResponseBuilderInterface responseBuilder) {
        addRouteName(route);
        routesWithMethods.put(route, responseBuilder);
    }

    private void addRouteName(String route) {
        routes.add(extractRouteName(route));
    }

    private String extractRouteName(String route) {
        return route.split(" ")[1];
    }

    public ResponseBuilderInterface getResponse(String route) {
        if (routeExists(extractRouteName(route))){
            return routesWithMethods.getOrDefault(route, new FourOhFiveResponseBuilder());
        }
        return new FourOhFourResponseBuilder();
    }
}
