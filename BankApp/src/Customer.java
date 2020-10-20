import java.util.*;
import java.io.*;

public class Customer extends Account implements Serializable{
    String name ;
    String customerUser;
    String customerPass;
    Customer() {}

    public Customer(Customer obj)
    {
        this.accountNumber = obj.accountNumber;
        this.name = obj.name;
        this.customerUser = obj.customerUser;
        this.customerPass = obj. customerPass;
        this.balance = obj.balance;
    }

    void scanCustomerDetails(String year) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Customer Name : ");
        name = sc.nextLine().toUpperCase();

        System.out.print("Enter the Customer Username : ");
        customerUser = sc.nextLine();
        System.out.print("Enter the Customer Password : ");
        customerPass = sc.nextLine();
        scanAccountDetails();

        accountNumber = year + name.substring(0,2).toUpperCase() + Integer.toString(customercount +1);
        System.out.println("\nYour Account Number  : "+accountNumber);
        File objfile = new File("File\\"+accountNumber);
        FileOutputStream fos = new FileOutputStream(objfile);
        ObjectOutputStream objOs=new ObjectOutputStream(fos);
        objOs.writeObject(this);
        fos.close();
        objOs.close();
    }
    void displaycustomerDetails(String identity) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream("File\\"+identity);
        ObjectInputStream objIS = new ObjectInputStream(fis);
        Customer newObject = (Customer)objIS.readObject();
        System.out.printf("%10s  |%10s  |%9s  |  %.1f  \n", newObject.accountNumber, newObject.customerUser, newObject.name, newObject.balance);
        fis.close();
        objIS.close();
    }

    boolean checkcustomer(String username, String password, String acc ) throws IOException,ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream("File\\"+acc);
        ObjectInputStream objIS = new ObjectInputStream(fis);
        Customer newObject = (Customer)objIS.readObject();
        String tempuser = newObject.customerUser;
        String temppass = newObject.customerPass;
        if(tempuser.equals(username) && temppass.equals(password))
        {
            return  true;
        }

        return false;
    }
}