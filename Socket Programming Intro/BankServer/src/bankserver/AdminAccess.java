package bankserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class AdminAccess implements Runnable{
    private String pass;
    private boolean access;
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
                out.println("Missing LOGIN Password... ");
                out.flush();
                return;
            }
            LOGIN(in.next());
        }
        else if (command.equals("STATUS")) {
            STATUS();
        }
        else if (command.equals("PASSWORD")) {
            if (!in.hasNext()) {
                out.println("Missing PASSWORD NewPassword... ");
                out.flush();
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
            out.println("That is not a valid command... ");
            out.flush();
        }
    }
    
    public void LOGIN(String password) {
        if (password.equals(pass)) {
            access = true;
            clients++;
            out.println("Logging In... ");
            out.flush();
        }
        else {
            out.println("Wrong Password... ");
            out.flush();
        }
    }
    
    
    public void STATUS() {
        if (access) {
            out.println("There are: " + clients + " logins so far...");
            out.flush();
        }
        else {
            out.println("You Do Not Have Access... ");
            out.flush();
        }
    }

    public void PASSWORD (String newPassword) {
        if (access) {
            pass = newPassword;
            access = false; //logout without messages
            out.println("New Password Set...");
            out.flush();
        }
        else {
            out.println("You Must Be Logged In To Change The Password... ");
            out.flush();
        }
    }
        
    public void LOGOUT() {
        if (access) {
            access = false; //lock user out from commands
            out.println("Logging Out... ");
            out.flush();
        }
        else {
            out.println("You Are Not Logged In... ");
            out.flush();
        }
    }
    
    public void SHUTDOWN() {
        if (access) {
            access = false; //logout without messages
            out.println("Shutting Down... ");
            out.flush();
            System.exit(0);
        }
        else {
            out.println("You Must Be Logged In To Shutdown... ");
            out.flush();
        }
    }
}
