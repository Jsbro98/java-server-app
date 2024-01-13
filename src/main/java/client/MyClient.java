package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    public MyClient(int portNumber) {
        start(portNumber);
    }

    private void start(int portNumber) {
        try (
                Socket client = new Socket("localhost", portNumber);
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Scanner scan = new Scanner(System.in);
        ) {
            System.out.println("--Client connected to server");
            System.out.println("Type \"exit\" to stop");
            while (true) {
                String input = scan.nextLine();

                out.println(input);

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println(in.readLine());
            }

        } catch (IOException e) {
            System.out.println("Client not created");
            e.printStackTrace();
        }
    }
}
