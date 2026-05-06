import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {

			StartScreen sScreen= new StartScreen();
			sScreen.open();
			Main.scanner.close();
	}

}