import java.util.Scanner;

public class StartScreen{


    public void open(){
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        System.out.println("\u001B[33m" + "        WELCOME TO THE DIGITAL MARKETPLACE" + "\u001B[0m");
        System.out.println("\u001B[36m" + "============================================================" + "\u001B[0m");
        System.out.println("\nChoose one of the following options:");
        System.out.println();
        System.out.println("[1] Create an Account");
        System.out.println("[2] Log in");
        System.out.println("[3] Quit");
        System.out.println();

        

        while(true){
            System.out.println("Enter:");
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();

            if(choice == 1){
                openCreateAccount();
                break;
            }
            else if(choice == 2){
                openLogin();
                break;
            }
            else if(choice == 3){
                System.out.println("Quitting...");
                break;
            }
            else{
                System.out.println();
                System.out.println("Invalid option");
                System.out.println();
            }
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
