import java.util.HashMap;

public class Router {
    private HashMap<String, Response> routes = new HashMap<>();

    public boolean routeExists(String route) {
        return this.routes.containsKey(route);
    }

    public void addRoute(String route) {
        this.routes.put("/", new Response());
    }
}
