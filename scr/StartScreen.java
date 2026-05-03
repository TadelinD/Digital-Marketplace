import java.util.Scanner;

public class StartScreen{


    public void open(){
        //Scanner scanner = new Scanner(System.in);
        System.out.println("\nHello Welcome to the \nDigital MarketPlace");
        System.out.println("\nChoose one of the following options:");
        System.out.println();
        System.out.println("(1) Create an Account");
        System.out.println("(2) Log in");
        System.out.println("(3) Quit");
        System.out.println();
        System.out.println("Enter:");

        int choice = Main.scanner.nextInt();
        Main.scanner.nextLine();
        
        if(choice == 1){
            openCreateAccount();
        }
        else if(choice == 2){
            openLogin();;
        }
        else{
            System.out.println("Quitting...");
        }

    }

    public void openCreateAccount(){
        System.out.println();
        CreateAccountScreen caScreen= new CreateAccountScreen();
        caScreen.showCreateAccount();
        open();
    }

    public void openLogin(){
        System.out.println();
        LoginScreen lScreen= new LoginScreen();
        lScreen.showLogin();
    }


}
