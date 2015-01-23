package bankserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
   A bank account has a balance that can be changed by 
   deposits and withdrawals.
*/
public class BankAccount
{  
   private double balance;
   private Lock balanceChangeLock;
   private Condition  sufficientFundsCondition;
   private final double MoneyCap = 100000.00;

   /**
      Constructs a bank account with a zero balance.
     * @param sock
   */
   public BankAccount() throws IOException
   {   
      balance = 0;
      balanceChangeLock = new ReentrantLock();
      sufficientFundsCondition = balanceChangeLock.newCondition();
   }

   /**
      Constructs a bank account with a given balance.
      @param initialBalance the initial balance
   */
   public BankAccount(double initialBalance)
   {   
      balance = initialBalance;
   }

   /**
      Deposits money into the bank account.
      @param amount the amount to deposit
   */
   public String deposit(double amount) throws InterruptedException
   {
       String ret;
      balanceChangeLock.lock();
      try
      {  
        //Client cannot have an account of more than $100000
        while (balance + amount > MoneyCap) {
            //now await for a withdraw to happen, so that the rest can go in
            sufficientFundsCondition.await();
        }
        
        balance += amount;
    }
    finally
    {
       balanceChangeLock.unlock();
       ret = "$" + amount + " deposited. Current balance is: $" + balance;
    }
      
      return ret;
}

   /**
      Withdraws money from the bank account.
      @param amount the amount to withdraw
   */
   public String withdraw(double amount)
   {   
       String ret;
      balanceChangeLock.lock();
      try
      {
         double newBalance = balance - amount;
         if (newBalance > 0) {
            balance = newBalance;
            sufficientFundsCondition.signalAll();

            ret = "$" + amount + " withdrawn. Current balance is: $" + balance;
         }
         else {
             ret = "Insufficient funds...";
         }
      }
      finally
      {
         balanceChangeLock.unlock();
      }
      
      return ret;
   }

   /**
      Gets the current balance of the bank account.
      @return the current balance
   */
   public double getBalance()
   {   
      return balance;
   }
}

