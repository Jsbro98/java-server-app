package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private ServerSocket server;
    private boolean stillListening = true;

    public MyServer(int portNumber) {
        this.start(portNumber);
    }

    private void start(int portNumber) {
        if (initServer(portNumber)) {
            startListening();
        }
    }

    private boolean initServer(int portNumber) {
        try {
            server = new ServerSocket(portNumber);
            System.out.println("Server is created");
            return true;
        } catch (IOException e) {
            System.out.println("Server failed to create");
            e.printStackTrace();
            return false;
        }
    }

    private void startListening() {
        while (stillListening) {

            try (
                    Socket serverSocket = server.accept();
                    PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))
            ) {
                System.out.println("--Server connected to client");

                String input;
                while ((input = in.readLine()) != null) {

                    if (input.equalsIgnoreCase("exit")) {
                        stopServer();
                        break;
                    }

                    out.println("Echo: " + input);
                }

            } catch (IOException e) {
                throw new RuntimeException("Something happened listening to the client", e);
            }
        }
    }


    private void stopServer() {
        try {
            server.close();
            stillListening = false;
            System.out.println("Server closed");
        } catch (IOException e) {
            throw new RuntimeException("Server failed to close", e);
        }
    }
}