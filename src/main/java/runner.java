
public class runner {
    public static void main(String [] args) {
        try {
            ArgsParser parser = new ArgsParser(args);
            Router router = new Router();
            router.addRoute("GET /", new Response());
            router.addRoute("PUT /form", new Response());
            router.addRoute("POST /form", new Response());
            Response methodOptions = new Response();
            methodOptions.addHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
            methodOptions.addHeader("Content-Length", "0");
            router.addRoute("GET /method_options", methodOptions);
            router.addRoute("PUT /method_options", methodOptions);
            router.addRoute("POST /method_options", methodOptions);
            router.addRoute("HEAD /method_options", methodOptions);
            router.addRoute("OPTIONS /method_options",methodOptions);
//            System.out.println(router.getResponse("GET /method_options").buildResponse());

            Server server = new Server(router);
            System.out.println("Starting Server on port " + parser.getPort() );
            server.start(parser.getPort());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Invalid Syntax!");
            System.out.print("Example : ");
            System.out.println("java -jar /User/somebody/project/my_jar.jar -p 5000 -d MY_DIR");
        }

    }
}
