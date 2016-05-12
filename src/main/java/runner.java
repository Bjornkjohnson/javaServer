
public class runner {
    public static void main(String [] args) {
        try {
            ArgsParser parser = new ArgsParser(args);
            Router router = new Router();
            router.addRoute("/", new Response());
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
