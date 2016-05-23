package bjohnson;

class runner {
    public static void main(String [] args) {
        try {
            ArgsParser parser = new ArgsParser(args);
            Router router = new CobspecSetup(parser.getDirectory()).buildRoutes();
            Server server = new Server(router, parser.getDirectory());
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
