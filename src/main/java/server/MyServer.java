package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private ServerSocket server;
    private PrintWriter out;
    private BufferedReader in;
    private boolean stillListening = true;

    public MyServer(int portNumber) {
        this.start(portNumber);
    }

    private void start(int portNumber) {
        if (initServer(portNumber)) {
            startListening();
        } else {
            try {
                throw new IOException("Server was not created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean initServer(int portNumber) {
        try  {
            this.server = new ServerSocket(portNumber);
            System.out.println("Server is created");
            return true;
        } catch (IOException e) {
            System.out.println("Server failed to create");
            e.printStackTrace();
            return false;
        }
    }

    private void startListening() {
        try {
            while (stillListening) {
                Socket serverSocket = server.accept();

                out = new PrintWriter(serverSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

                String input;
                while ((input = in.readLine()) != null) {
                    if (input.equalsIgnoreCase("exit")) {
                        stopServer();
                        break;
                    }

                    System.out.println("Echo: " + input);
                    out.println(input);

                }
            }

            out.close();
            in.close();

        } catch (IOException e) {
            throw new RuntimeException("Something happened listening to the client", e);
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