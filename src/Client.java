import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String fromServer = in.readLine();
            System.out.println("Received message: " + fromServer);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                host);
            System.exit(1);
        }
    }
}
