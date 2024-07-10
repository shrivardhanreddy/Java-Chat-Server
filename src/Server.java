import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 8000;
    private static String serverName;

    public static void main(String[] args) throws IOException {
        serverName = args.length > 0 ? args[0] : generateRandomName();
        ExecutorService executor = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server " + serverName + " is listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ClientHandler(clientSocket));
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                processRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void processRequest() throws IOException, InterruptedException {
            Thread.sleep(5000); // Simulate CPU time
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                out.println("Connected to server " + serverName);
            } finally {
                clientSocket.close();
            }
        }
    }

    private static String generateRandomName() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder name = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            name.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return name.toString();
    }
}
