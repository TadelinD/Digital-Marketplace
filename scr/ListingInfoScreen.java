import java.util.Map;
import java.util.Scanner;

public class ListingInfoScreen {

    public static void showListingInfo(String number, User user) {
        ListingIndex allListings = new ListingIndex();
        Map<String, String> info = allListings.getListInfo(number);
        System.out.println();
        System.out.println("\u001B[36m" + "==============================" + "\u001B[0m");
        System.out.println("\u001B[33m" + "        LISTING INFO" + "\u001B[0m");
        System.out.println("\u001B[36m" + "==============================" + "\u001B[0m");
        System.out.println("Id:           " + info.get("number"));
        System.out.println("Name:        " + info.get("name"));
        System.out.println("Description: " + info.get("description"));
        System.out.println("Price:       " + info.get("price"));
        System.out.println("Owner:       " + info.get("owner"));
        System.out.println();
        //choice options to go back or buy:
        System.out.println("[B] Back          [P] Purchase");
        System.out.println("\u001B[36m" + "==============================" + "\u001B[0m");
        System.out.println();
        Scanner choiceScanner = new Scanner(System.in);
        String choice = choiceScanner .nextLine().toLowerCase();

        //call back:
        //to do:
        BrowseListingScreen blScreen = new BrowseListingScreen();
        if (choice.equals("b")) {
            blScreen.openBrowseListing(user);
        }

        //call purchase
        if (choice.equals("p") && !(info.get("owner")).equals(user.getUserName())) {
            try {
                System.out.println(allListings.buyListing(number, user));
                blScreen.openBrowseListing(user);
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
        else if ((info.get("owner")).equals(user.getUserName())) {
            System.out.println("You can't buy that!");
            blScreen.openBrowseListing(user);
        }
        
        choiceScanner.close();
        System.out.println();
    }
}