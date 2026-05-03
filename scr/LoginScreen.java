import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginScreen{

    public void showLogin(){
        System.out.println("=======================");
        System.out.println("Login");
        System.out.println("=======================");
        System.out.println();
        System.out.println("Please enter the following:");
        
        
        System.out.print("Username:");
        String userName =  Main.scanner.nextLine();
        String StoredPW = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.equals(userName)){
                    StoredPW = reader.readLine();
                }  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("PassWord:");
        String password =  Main.scanner.nextLine();
        if(password.equals(StoredPW)){
            System.out.println("Success");
        }
    }
}