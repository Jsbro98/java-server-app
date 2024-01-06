package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MyClient {
    private Socket client;
    private BufferedReader in;

    public MyClient(int portNumber) {
        start(portNumber);
    }

    public void start(int portNumber) {
        if (!initClient(portNumber)) {
            try {
                throw new IOException("Failed to init client");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // continue here
        startListening();
    }

    public boolean initClient(int portNumber) {
        try {
            client = new Socket("server", portNumber);
            System.out.println("Client created");
            return true;
        } catch (IOException e) {
            System.out.println("Client not created");
            e.printStackTrace();
            return false;
        }
    }
}
