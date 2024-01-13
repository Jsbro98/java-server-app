package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    private Socket client;
    private PrintWriter out;
    private Scanner scan;

    public MyClient(int portNumber) {
        start(portNumber);
    }

    private void start(int portNumber) {
        try {
            client = new Socket("localhost", portNumber);
            out = new PrintWriter(client.getOutputStream(), true);
            scan = new Scanner(System.in);

            while (true) {
                String input = scan.nextLine();

                out.println(input);

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            out.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Client not created");
            e.printStackTrace();
        }
    }
}
