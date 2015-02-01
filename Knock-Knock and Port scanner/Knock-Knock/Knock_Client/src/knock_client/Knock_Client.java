package knock_client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Knock_Client {
    public static void main(String[] args) throws IOException {
        final int JOKE_PORT = 8888;
        
        Socket s;
        s = new Socket("localhost", JOKE_PORT);
        InputStream instream = s.getInputStream();
        OutputStream outstream = s.getOutputStream();
        Scanner in = new Scanner(instream);
        PrintWriter out = new PrintWriter(outstream);
        
        String response = in.nextLine();
        System.out.println("Server: " + response);
        
        String command = "WHO'S THERE?";
        System.out.print("Client: " + command + "\n");
        out.println(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Server: " + response);

        command = response + " WHO?";
        System.out.print("Client: " + command + "\n");
        out.println(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Server: " + response);

        command = "STAHP";
        System.out.print("Sending: " + command + "\n");
        out.println(command);
        out.flush();

        s.close();
    }
    
}
