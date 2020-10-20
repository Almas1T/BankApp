import java.io.*;

class Bank implements Serializable
{
    static int customercount = 0;
    String bankName;
    String bankAddress;
    String adminUserName;
    String adminPassWord;


    Bank()
    {
        adminUserName = "admin";
        adminPassWord = "admin";
    }
}