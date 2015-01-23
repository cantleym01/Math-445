package bankserver;

import java.io.IOException;

public class Bank
{
   private BankAccount[] accounts;

   /**
      Constructs a bank account with a given number of accounts.
      @param size the number of accounts
     * @throws java.io.IOException
   */
   public Bank(int size) throws IOException
   {
      accounts = new BankAccount[size];
      for (int i = 0; i < accounts.length; i++)
      {
         accounts[i] = new BankAccount();
      }
   }

   /**
      Deposits money into a bank account.
      @param accountNumber the account number
      @param amount the amount to deposit
   */
   public String deposit(int accountNumber, double amount) throws InterruptedException
   {
      BankAccount account = accounts[accountNumber];
      return account.deposit(amount);
   }

   /**
      Withdraws money from a bank account.
      @param accountNumber the account number
      @param amount the amount to withdraw
   */
   public String withdraw(int accountNumber, double amount)
   {
      BankAccount account = accounts[accountNumber];
      return account.withdraw(amount);
   }

   /**
      Gets the balance of a bank account.
      @param accountNumber the account number
      @return the account balance
   */
   public double getBalance(int accountNumber)
   {
      BankAccount account = accounts[accountNumber];
      return account.getBalance();
   }
}
