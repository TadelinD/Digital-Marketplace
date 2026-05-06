import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateScreen {

        public void showCreate(User user) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\u001B[36m" + "==============================" + "\u001B[0m");
            System.out.println("\u001B[33m" + "        CREATE A LISTING" + "\u001B[0m");
            System.out.println("\u001B[36m" + "==============================" + "\u001B[0m");

            System.out.print("  Enter a Name: ");
            String name = scanner.nextLine();
            System.out.println();

            System.out.print("  Enter a Description: ");
            String description = scanner.nextLine();
            System.out.println();

            System.out.print("  Enter a Price: ");
            int price = Integer.parseInt(scanner.nextLine());

            ListingIndex allListings = new ListingIndex();

            try {
                if (allListings.createListing(name, description, price, user).equals("Successfully Created")) {
                    //TO DO: call back to user profile
                };
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("\u001B[36m" + "==============================" + "\u001B[0m");
        }

        public static void main(String[] args) { //dont forget to remove!!!!!
            List<String> myListings = new ArrayList<>();
            List<String> bought = new ArrayList<>();
            User u = new User("bob", "pass", 100, myListings, bought);
            CreateScreen screen = new CreateScreen();
            screen.showCreate(u);
        }
}
