import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class myListingInfoScreen {

    public void showMyListingInfo(String number, User user) {
        ListingIndex allListings = new ListingIndex();
        Map<String, String> info = allListings.getListInfo(number);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("\u001B[36m" + "=================================" + "\u001B[0m");
            System.out.println("\u001B[33m" + "        MY LISTING INFO" + "\u001B[0m");
            System.out.println("\u001B[36m" + "=================================" + "\u001B[0m");
            System.out.println("Id:          " + info.get("number"));
            System.out.println("Name:        " + info.get("name"));
            System.out.println("Description: " + info.get("description"));
            System.out.println("Price:       " + info.get("price"));
            System.out.println("\u001B[36m" + "=================================" + "\u001B[0m");
            System.out.println("[B] Back   [X] Delete   [N] Change Name   [P] Change Price    [D] Change Description");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("b")) {
                //TO DO: go back to user profile
                break;
            }

            else if (choice.equals("n")) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                try {
                    allListings.editListing(number, newName, info.get("description"), Integer.parseInt(info.get("price")), user);
                    info.put("name", newName);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("Name changed to: " + newName);
            }

            else if (choice.equals("x")) {
                try {
                    allListings.deleteListing(number, user);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            }

            else if (choice.equals("d")) {
                System.out.print("Enter new description: ");
                String newDesc = scanner.nextLine();
                try {
                    allListings.editListing(number, info.get("name"), newDesc, Integer.parseInt(info.get("price")), user);
                    info.put("description", newDesc);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            else if (choice.equals("p")) {
                System.out.print("Enter new price: ");
                int newPrice = Integer.parseInt(scanner.nextLine());
                try {
                    allListings.editListing(number, info.get("name"), info.get("description"), newPrice, user);
                    info.put("price", Integer.toString(newPrice));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            else {
                System.out.println("Invalid option");
            }
        }
    }

    
    public static void main(String[] args) { //dont forget to remove!!!!!
        List<String> myListings = new ArrayList<>();
        List<String> bought = new ArrayList<>();
        myListings.add("1");
        User u = new User("bob", "pass", 100, myListings, bought);
        myListingInfoScreen screen = new myListingInfoScreen();
        screen.showMyListingInfo("1", u);
    }
}
