package knock_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Knock_Server {
    public static void main(String[] args) throws IOException {
        final int SBAP_PORT = 8888;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for clients to connect... ");
        
        //infinite loops ftw
        while(true) {
            Socket s = server.accept();
            System.out.println("Client connected... ");
            KnockKnock joke = new KnockKnock(s);
            Thread t = new Thread(joke);
            t.start();
        }
    }
    
}
