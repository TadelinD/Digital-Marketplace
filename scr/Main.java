import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {

			//initiate listing index for all actions regarding listings
			ListingIndex allListings = new ListingIndex();

			StartScreen sScreen= new StartScreen();
			sScreen.open();
			Main.scanner.close();
	}

}