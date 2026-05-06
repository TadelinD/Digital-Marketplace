import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class User{

    private String userName;
    private String password;
    private int currency;
    private List<String> myListings;
    private List<String> bought;

    public User(String userName, String password, int currency, 
        List<String> myListings, List<String> bought){
        this.userName = userName;
        this.password = password;
        this.currency = currency;
        this.myListings = myListings;
        this.bought = bought;

    }

    public void getUserInfo(){
        System.out.println(this.currency);
        System.out.println(this.myListings.toString());
        System.out.println(this.bought.toString());
    }

    public void addToBought(String item){
        this.bought.add(item);

    }

    public List<String> getBought() {
        return this.bought;
    }

    public List<String> getMyListings() {
        return this.myListings;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public int getCurrency() {
        return this.currency;
    }

    public void rmFromList(String listingid){
        myListings.remove(listingid);
        editingFile();
        
    }

    public void addToList(String listingid){
        myListings.add(listingid);
        editingFile();
        
    }

    public void deductAmount(int price){
        currency = currency - price;
        editingFile(); 
    }

    public void editingFile(){
        Path filePath = Paths.get("./UserData.txt");
        try {
            List<String> lines = Files.readAllLines(filePath);
            
            String sellingString = "";

            if(myListings.size() > 0){
                for(String listing:myListings){
                    sellingString += " " + listing;
                }
                sellingString = sellingString.substring(1);
            }

            String boughtString = "";

            if(bought.size() > 0){
                for(String items:bought){
                    boughtString += " " + items;
                }
                boughtString = boughtString.substring(1);
            }

            String currencyString = String.valueOf(currency);

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(this.userName)) {
                    lines.set(i + 2, currencyString);
                    lines.set(i + 3, "Bought:" + boughtString);
                    lines.set(i + 4, "Selling:" + sellingString);
                }
            }

            Files.write(filePath, lines);
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}