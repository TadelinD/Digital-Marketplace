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
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        System.out.println("\u001B[33m" + "                       LOG IN" + "\u001B[0m");
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        System.out.println();
        System.out.println("Please enter the following:");
        
        
        
        boolean exists = false;
        String StoredPW = "";
        String userName = "";

        while(!exists){
            System.out.print("Username:");
            userName =  Main.scanner.nextLine();
            try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if(line.equals(userName)){
                        StoredPW = reader.readLine();
                        exists = true;
                    }  
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!exists){
                System.out.println();
                System.out.println("User does not exist.");
                System.out.println("Please enter valid username.");
                System.out.println();
            }
        }

        boolean checkPass = false;

        while(!checkPass){
            System.out.print("PassWord:");
            String password =  Main.scanner.nextLine();
            if(password.equals(StoredPW)){
                System.out.println("Logging in ...");
                System.out.println();
                startSession(userName);
                checkPass = true;
            }
            else{
                System.out.println();
                System.out.println("Incorrect Password");
                System.out.println("Please type in correct password.");
                System.out.println();

            }

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
                    System.out.println(parts.toString());
                    if(parts.length > 1){
                        String[] listings = parts[1].split(" ");
                        for(String num:listings){
                            bought.add(num);
                        }
                    }

                    line = reader.readLine();
                    String[] partsTwo= line.split(":");
                    if(partsTwo.length > 1){
                        String[] listingsTwo = partsTwo[1].split(" ");
                        for(String num:listingsTwo){
                            myListings.add(num);
                        }
                    }

                    User u = new User(userName, StoredPW, currency, myListings, bought);
                    BrowseListingScreen blScreen = new BrowseListingScreen();
                    blScreen.openBrowseListing(u);
                    
                }  
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}