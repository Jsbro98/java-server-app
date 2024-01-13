import client.MyClient;
import server.MyServer;

public class TCP {
    public static void main(String[] args) {
        new Thread(() -> {
            MyServer server = new MyServer(8080);
        }).start();

        MyClient client = new MyClient(8080);
    }
}
