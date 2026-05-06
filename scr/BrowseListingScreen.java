import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class BrowseListingScreen {
    public void openBrowseListing(User user) {
        //list all items in listings txt
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        System.out.println("\u001B[33m" + "                       ALL LISTINGS" + "\u001B[0m");
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        try {
            Path path = Path.of("ListingData.txt");
            List<String> lines = Files.readAllLines(path);

            for (int i = 1; i < lines.size(); i++) { // skip first line
                String line = lines.get(i);

                String[] info = line.split(",");

                String number = info[0];
                String name = info[1];
                String owner = info[4];

                System.out.println("          ID: " + number + " | Name: " + name + " | Owner: " + owner);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");

        //choice options:
        Scanner scanner = new Scanner(System.in);
        while (true) { 
            System.out.println("[#] Enter an ID number to view a listing      [C] Create a listing      [U] View your User Info");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("c")) {
                CreateScreen cScreen = new CreateScreen();
                cScreen.showCreate(user);
                break;

            }
            else if (choice.equals("u")) {
                //to do: go to user info
            }
            else {
                try {
                    int id = Integer.parseInt(choice);
                    ListingInfoScreen ulScreen = new ListingInfoScreen();
                    ulScreen.showListingInfo(choice, user);
                    

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
            
        }
    }

     public static void main(String[] args) { //dont forget to remove!!!!!
        List<String> myListings = new ArrayList<>();
        List<String> bought = new ArrayList<>();
        myListings.add("4");
        User u = new User("bob", "pass", 100, myListings, bought);
        BrowseListingScreen blScreen = new BrowseListingScreen();
        blScreen.openBrowseListing(u);
    }
}
