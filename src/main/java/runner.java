
public class runner {
    public static void main(String [] args) {
        try {
            ArgsParser parser = new ArgsParser(args);
            Server server = new Server();
            server.start(parser.getPort());
            System.out.println("Starting Server on port " + parser.getPort() );
        } catch (Exception e) {
            System.out.println("Invalid Syntax!");
            System.out.print("Example : ");
            System.out.println("java -jar /User/somebody/project/my_jar.jar -p 5000 -d MY_DIR");
        }

    }
}
