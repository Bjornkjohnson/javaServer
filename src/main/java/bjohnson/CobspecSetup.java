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
        buildFileRoutes();
        buildTeaPotRoutes();
        buildParamEchoRoute();
        return router;
    }

    private void buildParamEchoRoute() {
        router.addRoute("GET /parameters", new EchoParamsResponseBuilder());
    }

    private void buildRootRoute() {
        router.addRoute("GET /", new TwoHundredOKResponseBuilder());
        router.addRoute("HEAD /", new HeadResponseBuilder());

    }

    private void buildTeaPotRoutes() {
        router.addRoute("GET /coffee", new FourEighteenResponseBuilder());
        router.addRoute("GET /tea", new HeadResponseBuilder());

    }

    private void buildFormRoute() {
        router.addRoute("GET /form", new FileReadResponseBuilder(publicDir));
        router.addRoute("PUT /form", new FileWriterResponseBuilder(publicDir));
        router.addRoute("POST /form", new FileWriterResponseBuilder(publicDir));
        router.addRoute("DELETE /form", new FileDeleteResponseBuilder(publicDir));

    }

    private void buildMethodOptionsRoute() {

        router.addRoute("GET /method_options", new TwoHundredOKResponseBuilder());
        router.addRoute("PUT /method_options", new TwoHundredOKResponseBuilder());
        router.addRoute("POST /method_options", new TwoHundredOKResponseBuilder());
        router.addRoute("HEAD /method_options", new TwoHundredOKResponseBuilder());
        OptionsResponseBuilder optionsResponseBuilder = new OptionsResponseBuilder();
        optionsResponseBuilder.allowAll();
        router.addRoute("OPTIONS /method_options",  optionsResponseBuilder);
    }

    private void buildMethodOptions2Route() {

        router.addRoute("GET /method_options2", new TwoHundredOKResponseBuilder());
        OptionsResponseBuilder optionsResponseBuilder = new OptionsResponseBuilder();
        optionsResponseBuilder.setHeaders("GET,OPTIONS");
        router.addRoute("OPTIONS /method_options2",  optionsResponseBuilder);
    }

    private void buildImageRoutes() {
        router.addRoute("GET /image.jpeg", new FileReadResponseBuilder(publicDir));
        router.addRoute("GET /image.gif", new FileReadResponseBuilder(publicDir));
        router.addRoute("GET /image.png", new FileReadResponseBuilder(publicDir));

    }

    private void buildRedirectRoute() {
        router.addRoute("GET /redirect", new RedirectResponseBuilder("http://localhost:5000/"));

    }

    private void buildFileRoutes() {
        router.addRoute("GET /file1", new FileReadResponseBuilder(publicDir));
        router.addRoute("GET /text-file.txt", new FileReadResponseBuilder(publicDir));
        router.addRoute("GET /partial_content.txt", new FileReadResponseBuilder(publicDir));


    }


}
