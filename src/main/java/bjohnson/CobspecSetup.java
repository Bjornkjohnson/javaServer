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
        buildMethodOptions2Route();
        buildImageRoutes();
        buildRedirectRoute();
        buildfile1Route();
        return router;
    }

    private void buildRootRoute() {
        router.addRoute("GET /", new GenericResponseBuilder());
    }

    private void buildFormRoute() {
        router.addRoute("GET /form", new FileReadResponseBuilder(publicDir));
        router.addRoute("PUT /form", new FileWriterResponseBuilder(publicDir));
        router.addRoute("POST /form", new FileWriterResponseBuilder(publicDir));
        router.addRoute("DELETE /form", new FileDeleteResponseBuilder(publicDir));

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

    private void buildMethodOptions2Route() {

        router.addRoute("GET /method_options2", new GenericResponseBuilder());
        OptionsResponseBuilder optionsResponseBuilder = new OptionsResponseBuilder();
        optionsResponseBuilder.setHeaders("GET,OPTIONS");
        router.addRoute("OPTIONS /method_options2",  optionsResponseBuilder);
    }

    private void buildImageRoutes() {
        router.addRoute("GET /image.jpeg", new ImageResponseBuilder(publicDir));
        router.addRoute("GET /image.gif", new ImageResponseBuilder(publicDir));
        router.addRoute("GET /image.png", new ImageResponseBuilder(publicDir));

    }

    private void buildRedirectRoute() {
        router.addRoute("GET /redirect", new RedirectResponseHandler("http://localhost:5000/"));

    }

    private void buildfile1Route() {
        router.addRoute("GET /file1", new FileReadResponseBuilder(publicDir));

    }


}
