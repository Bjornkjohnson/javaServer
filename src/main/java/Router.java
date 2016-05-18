import java.util.HashMap;

public class Router {
    private HashMap<String, ResponseBuilderInterface> routes = new HashMap<>();

    public boolean routeExists(String route) {
        return this.routes.containsKey(route);
    }

    public void addRoute(String route, ResponseBuilderInterface responseBuilder) {
        this.routes.put(route, responseBuilder);
    }

    public ResponseBuilderInterface getResponse(String route) {
        if (routeExists(route)){
            return routes.get(route);
        }
//        Response response = new Response();
//        response.setStatus("404 Not Found");
        return new FourOhFourBuilder();

    }
}
