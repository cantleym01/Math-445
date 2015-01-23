package bankserver;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Bank bank;
    
    public BankService(Socket aSocket, Bank aBank) {
        s = aSocket;
        bank = aBank;
    }
    
    public void run() {
        try {
            try {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            catch (InterruptedException ex) {
                Logger.getLogger(BankService.class.getName()).log(Level.SEVERE, null, ex);
            }            
            finally {
                s.close();
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public void doService() throws IOException, InterruptedException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }
            String command = in.next();
            if (command.equals("QUIT")) {
                return;
            }
            else {
                executeCommand(command);
            }
        }
    }
    
    public void executeCommand(String command) throws InterruptedException {
        int account = in.nextInt();

        if (command.equals("DEPOSIT")) {
            double amount = in.nextDouble();
            Deposit depo = new Deposit(bank, account, amount, out);
            Thread t = new Thread(depo);
            t.start();
        }
        else if (command.equals("WITHDRAW")) {
            double amount = in.nextDouble();
            Withdraw with = new Withdraw(bank, account, amount, out);
            Thread t = new Thread(with);
            t.start();
        }
        else if (!command.equals("BALANCE")) {
            out.println("Invalid command");
            out.flush();
            return;
        }
        else {
            out.println("Account: " + account + " $" + bank.getBalance(account));
            out.flush();
        }
    }
}
