import java.io.*;
import java.util.*;
public class Account extends Bank implements Serializable{
    String accountNumber;
    double balance = 0;
    double amt;

    void scanAccountDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Opening Balance greater than 0 : ");
        while (true)
        {
            balance = sc.nextDouble();
            if (balance>0)
            {
                break;
            }
            else
            {
                System.out.print("Enter the amount greater than 0 : ");
            }
        }
    }

    void displayAccountDetails(){
        System.out.format("%10s  |  %.1f\n",accountNumber,balance);

    }

    double deposit(double balance1){
        Scanner sc = new Scanner(System.in);
        System.out.println("Your Account Balance is "+balance1);
        System.out.print("Enter Amount U Want to Deposit : ");
        amt = sc.nextFloat();
        if(amt<0)  //it does not allow to deposit a negative amount
        {
            System.out.println("\nAmount entered is negative so you can not deposit \nPRESS ENTER FOR MORE OPTION");
        }
        else
        {
            balance1 += amt;
            System.out.println("\nTransaction successful!.\nFinal Balance is "+balance1+"\nPRESS ENTER FOR MORE OPTION");
            return balance1;
        }
        return balance1 ;//return the updated balance
    }

    double withdrawal(double balance1)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Your Account Balance is "+balance1);
            System.out.print("Enter Amount U Want to withdraw : ");
            amt = sc.nextFloat();
            if(amt<0)
            {
                System.out.println("\nAmount entered is negative. so you can not withdraw \nPRESS ENTER FOR MORE OPTION");
                break;
            }
            else if(balance1>=amt)
            {
                balance1 -= amt;
                System.out.println("\nTransaction successful!.\nFinal Balance is "+balance1+"\nPRESS ENTER FOR MORE OPTION");
                return balance1; // returns the updated the value
            }
            else
            {
                System.out.println("\nLess Balance Transaction Failed.\nPRESS ENTER FOR MORE OPTION");
                break;
            }

        }
        return balance1;
    }

    double payment(double balance1)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Your Account Balance is "+balance1);
            System.out.print("Enter Amount to be paid : ");
            amt = sc.nextFloat();
            if(balance1-amt<0)
            {
                System.out.println("\nNegative Balance. So payment cannot be done. \nPRESS ENTER FOR MORE OPTION");
                break;
            }
            else if(balance1>=amt)
            {
                balance1 -= amt;
                System.out.println("\nTransaction successful!.\nFinal Balance is "+balance1+"\nPRESS ENTER FOR MORE OPTION");
                return balance1; // returns the updated the value
            }
            else
            {
                System.out.println("\nLess Balance Transaction Failed.\nPRESS ENTER FOR MORE OPTION");
                break;
            }

        }
        return balance1;
    }

    double Transfer(double balance1)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("Your Account Balance is "+balance1);
            System.out.print("Enter Amount U Want to Transfer : ");
            amt = sc.nextFloat();
            if(amt<0)
            {
                System.out.println("\nAmount entered is negative. so you can not Transfer \nPRESS ENTER FOR MORE OPTION");
                break;
            }
            else if(balance1>=amt)
            {
                balance1 -= amt;
                System.out.println("\nTransaction successful!.\nFinal Balance is "+balance1+"\nPRESS ENTER FOR MORE OPTION");
                return balance1; // returns the updated the value
            }
            else
            {
                System.out.println("\nLess Balance Transaction Failed.\nPRESS ENTER FOR MORE OPTION");
                break;
            }

        }
        return balance1;
    }

}













