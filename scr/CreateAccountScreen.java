import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateAccountScreen{

    public void showCreateAccount(){
        //Scanner Cscanner = new Scanner(System.in);
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        System.out.println("\u001B[33m" + "                       Create Account" + "\u001B[0m");
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        System.out.println();
        System.out.println("Please enter the following:");
        
        
        String userName = "";
        while(true){
                System.out.print("Username:");
                userName =  Main.scanner.nextLine();
                boolean check = true;

                try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if(line.equals(userName)){
                            check = false;
                        }  
                    }

                    if(check){
                        break;
                    }
                    else{
                        System.out.println();
                        System.out.println("Username already taken.");
                        System.out.println("Please enter another one.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
            }

        }
        System.out.print("PassWord:");
        String password =  Main.scanner.nextLine();

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