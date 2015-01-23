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
public class Withdraw implements Runnable{
    Bank bank;
    int account;
    double withdrawAmount;
    PrintWriter out;
    
    public Withdraw(Bank aBank, int aAccount, double amount, PrintWriter pw) {
        bank = aBank;
        account = aAccount;
        withdrawAmount = amount;
        out = pw;
    }
    public void run() {
        out.println(bank.withdraw(account, withdrawAmount));
        out.flush();
    }
}
