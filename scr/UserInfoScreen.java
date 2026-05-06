import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UserInfoScreen{

    public void showUserInfo(User u){
        while(true){
            System.out.println("\u001B[36m" + "=================================" + "\u001B[0m");
            System.out.println("\u001B[33m" + "        USER INFO" + "\u001B[0m");
            System.out.println("\u001B[36m" + "=================================" + "\u001B[0m");

            System.out.println("Name:" + u.getUserName());
            System.out.println("Password:" + u.getPassword());
            System.out.println("Currency:" + u.getCurrency());

            List<String> bought= u.getBought();

            String boughtString = "";
            for(String item:bought){
                boughtString += " " + item;
            }
            if(bought.size() > 0){
                boughtString = boughtString.substring(1);
            }
            System.out.println("Listings you Own:" + boughtString);
            System.out.println("Your Listings:");
            List<String> myListings = u.getMyListings();
            for(String myItem:myListings){
                String itemName = "";
                try (BufferedReader reader = new BufferedReader(new FileReader("ListingData.txt"))) {
                    String line;
                    line = reader.readLine();
                    while ((line = reader.readLine()) != null) {
                        String[] lineInfo = line.split(",");
                        if(lineInfo[0].equals(myItem)){
                            itemName = lineInfo[1];
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("[" + myItem + "] " + itemName);
            }


            System.out.println();
            System.out.println("[B] Back      [#]Press listing number to edit");
            System.out.println("\u001B[36m" + "=================================" + "\u001B[0m");
            System.out.println("Input:");
            String choice = Main.scanner.nextLine().toLowerCase();

        
            if(choice.equals("b")){
                //go back to browsing
                BrowseListingScreen blScreen = new BrowseListingScreen();
                blScreen.openBrowseListing(u);
                break;
            }
            else{
                boolean isNum = false;
                try{
                    Integer.parseInt(choice);
                    isNum = true;
                } catch(Exception e){
                    System.out.println("Invalid option");
                }

                if(isNum){
                    if(myListings.contains(choice)){
                        myListingInfoScreen mlScreen = new myListingInfoScreen();
                        mlScreen.showMyListingInfo(choice, u);
                    }
                    else{
                        System.out.println("Invalid option");
                        System.out.println();
                    }

                }
            
            }

        }
        
    }
}