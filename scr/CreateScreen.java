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
                    System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
                    System.out.println("               Successfully Created");
                    BrowseListingScreen blScreen = new BrowseListingScreen();
                    blScreen.openBrowseListing(user);
                };
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("\u001B[36m" + "==============================" + "\u001B[0m");
        }
}
