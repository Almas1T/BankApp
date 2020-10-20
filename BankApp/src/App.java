import java.io.*;
import java.util.*;
public class App implements Serializable{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ClassNotFoundException,FileNotFoundException{
        clrscr();
        Customer[] obj = new Customer[50];
        Bank bk = new Bank();
        File objFile = new File("File\\");
        if(!objFile.exists())
        {
            objFile.mkdir();
        }
        String name[] = objFile.list();
        Bank.customercount = name.length;

        while(true)
        {
            int n,n1,n2,n3;
            boolean rep = true;
            clrscr();
            System.out.println("Bank Application");
            System.out.println("1. Admin Login\n2. Customer Login\n3. Exit");
            n = sc.nextInt();
            switch (n)
            {
                case 1:
                    //Admin Login
                    clrscr();
                    System.out.println("Admin Login");
                    System.out.print("Enter the admin username : ");
                    String username = sc.next();
                    System.out.print("Enter the admin password : ");
                    String password = sc.next();

                    if(username.equals(bk.adminUserName) && password.equals(bk.adminPassWord))
                    {
                        while(rep)
                        {
                            clrscr();
                            System.out.println("Admin Access");
                            System.out.println("1. Create New User\n2. Display User Details\n3. Go to Main Menu");
                            n1 = sc.nextInt();
                            switch (n1)
                            {
                                case 1:
                                    // Create New User
                                    // it create new user using the scancustomerdetails method of class customer
                                    clrscr();
                                    obj[Bank.customercount] = new Customer();
                                    obj[Bank.customercount].scanCustomerDetails(String.valueOf(2020));
                                    Bank.customercount++;
                                    System.out.println("\nNEW USER CREATED SUCCESSFULLY!\nPRESS ENTER FOR MORE OPTION");
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;


                                case 2:
                                    if(Bank.customercount==0)
                                    {
                                        System.out.println("\nNO DATA FOUND!\nPRESS ENTER FOR MORE OPTION");
                                        sc.nextLine();
                                        sc.nextLine();
                                        break;
                                    }
                                    clrscr();
                                    name = objFile.list();
                                    System.out.println("Display User In Order");
                                    System.out.println("1. Display\n2. Go to Main Menu");
                                    n3 = sc.nextInt();
                                    switch (n3)
                                    {
                                        case 1:
                                            for(int i=0;i<name.length;i++)
                                            {
                                                for(int j=0;j<name.length-1;j++)
                                                {
                                                    if (Integer.parseInt(name[j].substring(7))>Integer.parseInt(name[j+1].substring(7)))
                                                    {
                                                        String temp;
                                                        temp = name[j];
                                                        name[j] = name[j+1];
                                                        name[j+1] = temp;

                                                    }
                                                }
                                            }
                                            break;

                                        default:
                                            break;
                                    }
                                    clrscr();

                                    System.out.printf("%10s  |%10s  |%9s  |  %6s", "ACCOUNT ID", "USERNAME", "NAME","BALANCE");
                                    System.out.println();

                                    for(int i=0;i<name.length;i++)
                                    {
                                        Customer obj1 = new Customer();
                                        obj1.displaycustomerDetails(name[i]);
                                        System.out.println(name[i]);
                                    }
                                    System.out.println("\nPRESS ENTER FOR MORE OPTION");
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;

                                case 3:
                                    rep = false;
                                    break;

                                default:
                                    System.out.println("INVALID INPUT");
                                    break;

                            }
                        }
                    }
                    else
                    {
                        System.out.print("\nInvalid Username or Password!!!....\nGO TO MAIN MENU  PRESS ENTER");
                        sc.nextLine();
                        sc.nextLine();
                    }
                    break;

                case 2:
                    clrscr();
                    System.out.println("Customer Login");
                    System.out.print("Enter Account Id : ");
                    String acc = sc.next();
                    System.out.print("Enter customer username : ");
                    username = sc.next();
                    System.out.print("Enter customer password : ");
                    password = sc.next();
                    boolean check = false;
                    Customer custobj = new Customer();
                    File checkFile = new File("File\\"+acc);
                    if(checkFile.exists())// it check whether the file of customer using the account id
                        {
                            check = custobj.checkcustomer(username, password,acc);
                        }
                    else
                        {
                            System.out.print("\nEnter Valid Account Id");
                        }
                    if(check)
                    {
                        FileInputStream fis = new FileInputStream("File\\"+acc);
                        ObjectInputStream objIS = new ObjectInputStream(fis);
                        Customer newObject = (Customer)objIS.readObject(); // read the whole object from the file and cast to customer class
                        rep = true;
                        while(rep)
                        {// while loop repeats the customer login menu until the customer press 5 for main menu
                            clrscr();
                            System.out.println("Customer Access");
                            System.out.println("1. Account Details\n2. Withdraw Money\n3. Deposit Money\n4. Transfer Money\n5. Go To Main Menu");
                            n2 = sc.nextInt();
                            switch (n2)
                            {
                                case 1:
                                    // Check balance
                                    clrscr();
                                    System.out.printf("%10s  |%10s  |%9s    |  %6s", "ACCOUNT ID", "USERNAME", "NAME","BALANCE");
                                    System.out.println();
                                    custobj.displaycustomerDetails(acc);
                                    System.out.println("\nPRESS ENTER FOR MORE OPTION");
                                    sc.nextLine();
                                    sc.nextLine();
                                    break;


                                    case 2:
                                        clrscr();
                                        newObject.balance = custobj.withdrawal(newObject.balance);
                                        custobj = newObject;
                                        fis.close();
                                        objIS.close();
                                        File objfile = new File("File\\"+acc);
                                        FileOutputStream fos = new FileOutputStream(objfile);
                                        ObjectOutputStream objOs=new ObjectOutputStream(fos);
                                        objOs.writeObject(custobj);
                                        fos.close();
                                        objOs.close();
                                        sc.nextLine();
                                        sc.nextLine();
                                        break;

                                        case 3:
                                            clrscr();
                                            newObject.balance = custobj.deposit(newObject.balance);
                                            custobj = newObject;
                                            fis.close();
                                            objIS.close();
                                            File objfile1 = new File("File\\"+acc);//make a file of customer using accountid
                                            FileOutputStream fos1 = new FileOutputStream(objfile1);
                                            ObjectOutputStream objOs1=new ObjectOutputStream(fos1);
                                            objOs1.writeObject(custobj);
                                            fos1.close();
                                            objOs1.close();
                                            sc.nextLine();
                                            sc.nextLine();
                                            break;

                                        case 4:
                                            clrscr();
                                            System.out.print("Enter the Account ID to Which U want to transfer : ");
                                            String acc1 = sc.next();
                                            boolean tf = false;
                                            File checkFile1 = new File("File\\"+acc1);
                                            if(checkFile1.exists())
                                            {
                                                tf = true;
                                            }
                                            float amt = 0;

                                            if(tf)
                                            {
                                                FileInputStream fis1 = new FileInputStream("File\\"+acc1);
                                                ObjectInputStream objIS1 = new ObjectInputStream(fis1);
                                                Customer newObject1 = (Customer)objIS1.readObject();
                                                System.out.print("Enter the amount U want transfer : ");
                                                amt = sc.nextFloat();
                                                // It validate the entered amount using the if condition
                                                if(amt<0)
                                                {
                                                    System.out.println("\nAmount entered is negative.so you can not withdraw \nPRESS ENTER FOR MORE OPTION");
                                                }
                                                else if(amt < newObject.balance && newObject.balance-amt>=0)
                                                {
                                                    newObject.balance -= amt;
                                                    newObject1.balance += amt;
                                                    System.out.println("\nTransfer successful!.\nFinal Balance is "+newObject.balance+"\nPRESS ENTER FOR MORE OPTION" );
                                                }
                                                else if (amt > newObject.balance)
                                                {
                                                    System.out.println("\nTransfer failed, not enough balance!\nPRESS ENTER FOR MORE OPTION");
                                                }
                                                else
                                                {
                                                    System.out.println("\nLess Balance..Transaction Failed..\nPRESS ENTER FOR MORE OPTION");

                                                }
                                                custobj = newObject;
                                                Customer tempobj = new Customer();
                                                tempobj = newObject1;
                                                fis.close();
                                                objIS.close();
                                                fis1.close();
                                                objIS1.close();
                                                File objfile2 = new File("File\\"+acc);//make a file of customer using accountid
                                                FileOutputStream fos2 = new FileOutputStream(objfile2);
                                                ObjectOutputStream objOs2=new ObjectOutputStream(fos2);
                                                objOs2.writeObject(custobj);
                                                File objfile3 = new File("File\\"+acc1);//make a file of customer using accountid
                                                FileOutputStream fos3 = new FileOutputStream(objfile3);
                                                ObjectOutputStream objOs3=new ObjectOutputStream(fos3);
                                                objOs3.writeObject(tempobj);
                                                fos2.close();  objOs2.close();  fos3.close();  objOs3.close();
                                                sc.nextLine();
                                                sc.nextLine();
                                            }
                                            else
                                            {
                                                System.out.println("\nINVALID ACCOUNT ID !!..........\nPRESS ENTER FOR MORE OPTION");
                                                sc.nextLine();
                                                sc.nextLine();
                                            }
                                            break;

                                        case 5:
                                            rep = false;
                                            break;

                                        default:
                                            System.out.println("INVALID INPUT");
                                            break;
                                    }// end of switch for customer menu
                                }// while loop for repeating the customer menu
                            }// end of if after checking the username and password of the customer login
                            else
                            {
                                System.out.print("\nInvalid Username or Password!!!....\nGO TO MAIN MENU  PRESS ENTER");
                                sc.nextLine();
                                sc.nextLine();

                            }
                            break;
                case 3:
                    System.exit(0);

                default:
                    System.out.println("INVALID INPUT");
                    break;

            }

        }
    }
    static void clrscr()
    {
        /* This function is for clearing the command prompt */
        try
        {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
