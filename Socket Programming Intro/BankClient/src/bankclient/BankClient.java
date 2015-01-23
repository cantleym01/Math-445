package bankclient;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankClient {

    public static void main(String[] args) throws IOException{
        final int SBAP_PORT = 8888;
        final int ADMIN_PORT = 8889;
        
        Socket s;
        s = new Socket("localhost", SBAP_PORT);
        InputStream instream = s.getInputStream();
        OutputStream outstream = s.getOutputStream();
        Scanner in = new Scanner(instream);
        PrintWriter out = new PrintWriter(outstream);
        
        String command = "DEPOSIT 3 99999\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        String response = in.nextLine();
        System.out.println("Receiving: " + response);

        command = "WITHDRAW 3 500\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "DEPOSIT 3 505\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        
        //cannot recieve message here, error will pop-up
        //can refine the commands to be input in a neater fashion,
        //as well as refine how to show user responses from the server
        //right now it is simple testing to say yes or no if it works
        
        command = "WITHDRAW 3 1000\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        //second response to show the deposit from before this withdrawl goes through
        response = in.nextLine();
        System.out.println("Receiving: " + response);

        command = "QUIT\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();

        s.close();
        
        /* ADMIN COMMAND TEST */
        Socket a;
        a = new Socket("localhost", ADMIN_PORT);
        instream = a.getInputStream();
        outstream = a.getOutputStream();
        in = new Scanner(instream);
        out = new PrintWriter(outstream);
        
        command = "LOGIN passhorde\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "LOGIN passwurd\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "PASSWORD passhorde\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "LOGIN passwurd\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "LOGIN passhorde\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "STATUS\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "SHUTDOWN\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        a.close();
    }
}
