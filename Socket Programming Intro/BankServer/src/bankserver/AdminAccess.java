package bankserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class AdminAccess implements Runnable{
    private String pass;
    private boolean access;
    public boolean exit;
    private int clients;
    private Socket a;
    private Scanner in;
    private PrintWriter out;
    
    //constructor that sets the Admin password and denies access at first
    public AdminAccess(Socket admin, String password) throws IOException {
        pass = password;
        access = false;
        clients = 0;
        a = admin;
        exit = false;
    }
    
    public void run() {
        try {
            try {
                in = new Scanner(a.getInputStream());
                out = new PrintWriter(a.getOutputStream());
                doService();
            }
            finally {
                a.close();
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }
            String command = in.next();
            executeCommand(command);
        }
    }
    
    public void executeCommand(String command) {
        if (command.equals("LOGIN")) {
            if (!in.hasNext()) {
                System.out.println("Missing LOGIN Password... ");
                return;
            }
            LOGIN(in.next());
        }
        else if (command.equals("STATUS")) {
            STATUS();
            System.out.println(in.next());
        }
        else if (command.equals("PASSWORD")) {
            if (!in.hasNext()) {
                System.out.println("Missing PASSWORD NewPassword... ");
                return;
            }
            PASSWORD(in.next());
        }
        else if (command.equals("LOGOUT")) {
            LOGOUT();
        }
        else if (command.equals("SHUTDOWN")) {
            SHUTDOWN();
        }
        else {
            System.out.println("That is not a valid command... ");
        }
        
        out.flush();
    }
    
    public void LOGIN(String password) {
        if (password.equals(pass)) {
            access = true;
            clients++;
            System.out.println("Logging In... ");
        }
        else {
            System.out.println("Wrong Password... ");
        }
    }
    
    
    public void STATUS() {
        if (access) {
            System.out.println("There are: " + clients + " logins so far...");
        }
        else {
            System.out.println("You Do Not Have Access... ");
        }
    }

    public void PASSWORD (String newPassword) {
        if (access) {
            pass = newPassword;
            LOGOUT();
            System.out.println("New Password Set...");
        }
        else {
            System.out.println("You Must Be Logged In To Change The Password... ");
        }
    }
        
    public void LOGOUT() {
        if (access) {
            access = false; //lock user out from commands
            System.out.println("Logging Out... ");
        }
        else {
            System.out.println("You Are Not Logged In... ");
        }
    }
    
    public void SHUTDOWN() {
        if (access) {
            LOGOUT();
            System.out.println("Shutting Down... ");
            System.exit(0);
        }
        else {
            System.out.println("You Must Be Logged In To Shutdown... ");
        }
    }
}
