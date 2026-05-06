import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            startSession(userName);
        }
    }

    public void startSession(String userName){
        try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
            String line;
            String StoredPW = "";
            List<String> myListings = new ArrayList<>();
            List<String> bought = new ArrayList<>();
            int currency = 0;

            while ((line = reader.readLine()) != null) {
                if(line.equals(userName)){
                    StoredPW = reader.readLine();
                    currency = Integer.parseInt(reader.readLine());

                    line = reader.readLine();
                    String[] parts= line.split(":");
                    if(parts[1] != " "){
                        String[] listings = parts[1].split(" ");
                        for(String num:listings){
                            bought.add(num);
                        }
                    }

                    line = reader.readLine();
                    String[] partsTwo= line.split(":");
                    if(parts[1] != " "){
                        String[] listingsTwo = partsTwo[1].split(" ");
                        for(String num:listingsTwo){
                            myListings.add(num);
                        }
                    }

                    User u = new User(userName, StoredPW, currency, myListings, bought);
                    
                }  
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}