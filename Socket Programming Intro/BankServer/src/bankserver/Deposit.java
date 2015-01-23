/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Deposit implements Runnable{
    Bank bank;
    int account;
    double depositAmount;
    PrintWriter out;
    
    public Deposit(Bank aBank, int aAccount, double amount, PrintWriter pw) {
        bank = aBank;
        account = aAccount;
        depositAmount = amount;
        out = pw;
    }
    public void run() {
        try {
            out.println(bank.deposit(account, depositAmount));
            out.flush();
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
