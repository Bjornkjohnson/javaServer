public class CobspecSetup {

    private Router router = new Router();

    public Router buildRoutes() {
        buildRootRoute();
        buildFormRoute();
        buildMethodOptionsRoute();
        return router;
    }

    private void buildRootRoute() {
        router.addRoute("GET /", new GenericResponseBuilder());
    }

    private void buildFormRoute() {
        router.addRoute("PUT /form", new GenericResponseBuilder());
        router.addRoute("POST /form", new GenericResponseBuilder());
    }

    private void buildMethodOptionsRoute() {
        router.addRoute("GET /method_options", new GenericResponseBuilder());
        router.addRoute("PUT /method_options", new GenericResponseBuilder());
        router.addRoute("POST /method_options", new GenericResponseBuilder());
        router.addRoute("HEAD /method_options", new GenericResponseBuilder());
        router.addRoute("OPTIONS /method_options", new GenericResponseBuilder());
    }
}
