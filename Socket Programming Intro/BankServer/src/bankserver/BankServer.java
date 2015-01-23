package bankserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer  {    
    public static void main(String[] args) throws IOException {
        final int ACCOUNTS_LENGTH = 10;
        Bank bank = new Bank(ACCOUNTS_LENGTH);
        final int SBAP_PORT = 8888;
        final int ACCESS_PORT = 8889;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for clients to connect... ");
        ServerSocket admin = new ServerSocket(ACCESS_PORT);
        
        AdminAccess access;
        BankService service;
        
        //infinite loops ftw
        while(true) {
            Socket s = server.accept();
            System.out.println("Client connected... ");
            service = new BankService(s, bank);
            Thread t = new Thread(service);
            t.start();
            
            Socket a = admin.accept();
            access = new AdminAccess(a, "passwurd");
            Thread th = new Thread(access);
            th.start();
        }
    }
}
