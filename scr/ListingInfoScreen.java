import java.util.ArrayList;
import java.util.List;
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
        String choice = choiceScanner .nextLine();

        //call back:
        //to do:

        //call purchase
        if (choice.equals("p")) {
            try {
                allListings.buyListing(number, user);
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
        
        choiceScanner.close();
        System.out.println();
    }
    public static void main(String[] args) { //dont forget to remove!!!!!
        //for testing purposes
        List<String> myListings = new ArrayList<>();
        List<String> bought = new ArrayList<>();
        User u = new User("tad", "pass", 100, myListings, bought);
        showListingInfo("1", u);
    }
}