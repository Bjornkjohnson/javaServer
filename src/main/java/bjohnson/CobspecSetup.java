package bjohnson;

import bjohnson.ResponseHandlers.*;

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
        buildRedirectRoute();
        return router;
    }

    private void buildRootRoute() {
        router.addRoute("GET /", new GenericResponseBuilder());
    }

    private void buildFormRoute() {
        router.addRoute("GET /form", new FileReadResponseBuilder(publicDir));
        router.addRoute("PUT /form", new GenericResponseBuilder());
        router.addRoute("POST /form", new GenericResponseBuilder());
    }

    private void buildMethodOptionsRoute() {

        router.addRoute("GET /method_options", new GenericResponseBuilder());
        router.addRoute("PUT /method_options", new GenericResponseBuilder());
        router.addRoute("POST /method_options", new GenericResponseBuilder());
        router.addRoute("HEAD /method_options", new GenericResponseBuilder());
        OptionsResponseBuilder optionsResponseBuilder = new OptionsResponseBuilder();
        optionsResponseBuilder.allowAll();
        router.addRoute("OPTIONS /method_options",  optionsResponseBuilder);
    }

    private void buildImageRoutes() {
        router.addRoute("GET /image.jpeg", new ImageResponseBuilder(publicDir));
        router.addRoute("GET /image.gif", new ImageResponseBuilder(publicDir));
        router.addRoute("GET /image.png", new ImageResponseBuilder(publicDir));

    }

    private void buildRedirectRoute() {
        router.addRoute("GET /redirect", new RedirectResponseHandler("http://localhost:5000/"));

    }


}
