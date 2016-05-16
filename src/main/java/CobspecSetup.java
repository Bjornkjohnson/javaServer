public class CobspecSetup {

    private Router router = new Router();

    public Router buildRoutes() {
        buildRootRoute();
        buildFormRoute();
        buildMethodOptionsRoute();
        return router;
    }

    private void buildRootRoute() {
        router.addRoute("GET /", new Response());
    }

    private void buildFormRoute() {
        router.addRoute("PUT /form", new Response());
        router.addRoute("POST /form", new Response());
    }

    private void buildMethodOptionsRoute() {
        Response methodOptions = new Response();
        methodOptions.addHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        methodOptions.addHeader("Content-Length", "0");
        router.addRoute("GET /method_options", methodOptions);
        router.addRoute("PUT /method_options", methodOptions);
        router.addRoute("POST /method_options", methodOptions);
        router.addRoute("HEAD /method_options", methodOptions);
        router.addRoute("OPTIONS /method_options",methodOptions);
    }
}
