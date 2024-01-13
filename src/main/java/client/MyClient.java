package client;

import java.io.*;
import java.net.Socket;

public class MyClient {
    private Socket client;
    private PrintWriter out;

    public MyClient(int portNumber) {
        start(portNumber);
    }

    private void start(int portNumber) {
        initClient(portNumber);
    }

    private void initClient(int portNumber) {
        try {
            client = new Socket("localhost", portNumber);
            out = new PrintWriter(client.getOutputStream(), true);

            out.println("Hello, server!");
            out.println("How are you?");
            out.println("exit");

            out.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Client not created");
            e.printStackTrace();
        }
    }
}
