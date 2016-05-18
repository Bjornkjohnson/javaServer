public class CobspecSetup {

    private Router router = new Router();
    private String publicDir;

    public CobspecSetup(String publicDir){
        this.publicDir = publicDir;
    }

    public Router buildRoutes() {
        buildRootRoute();
        buildFormRoute();
        buildMethodOptionsRoute();
        buildImageRoutes();
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

    private void buildImageRoutes() {
        router.addRoute("GET /image.jpeg", new ImageResponseBuilder(publicDir));
        router.addRoute("GET /image.gif", new ImageResponseBuilder(publicDir));
        router.addRoute("GET /image.png", new ImageResponseBuilder(publicDir));

    }
}
