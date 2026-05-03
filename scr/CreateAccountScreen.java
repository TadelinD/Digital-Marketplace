import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateAccountScreen{

    public void showCreateAccount(){
        //Scanner Cscanner = new Scanner(System.in);
        System.out.println("=======================");
        System.out.println("Create Account Screen");
        System.out.println("=======================");
        System.out.println();
        System.out.println("Please enter the following:");
        
        
        System.out.print("Username:");
        String userName =  Main.scanner.nextLine();
        System.out.print("PassWord:");
        String password =  Main.scanner.nextLine();
        System.out.println(userName);
        System.out.println(password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("UserData.txt", true))) {
            writer.write(userName + "\n");
            writer.write(password + "\n");
            writer.write("0\n");
            writer.write("Bought: \n");
            writer.write("Selling: \n");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

}