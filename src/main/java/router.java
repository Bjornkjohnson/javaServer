import java.util.HashMap;

public class Router {
    private HashMap<String, Response> routes = new HashMap<>();

    public boolean routeExists(String route) {
        return this.routes.containsKey(route);
    }

    public void addRoute(String route, Response response) {
        this.routes.put(route, response);
    }

    public Response getResponse(String route) {
        if (routeExists(route)){
            return routes.get(route);
        }
        Response response = new Response();
        response.setStatus("404 Not Found");
        return response;

    }
}
