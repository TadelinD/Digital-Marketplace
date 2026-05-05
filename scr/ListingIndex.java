import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//manages all of the actions that involve the list of listings
public class ListingIndex{

    //finds the listing from the lists.txt
    //returns a map with all of its information
    public Map<String, String> getListInfo (String number) {
        //creates a "dictionary" mapping descriptions to their values:
        Map<String, String> information = new HashMap<>();

        //parse from file:
        try {
            File myFile = new File("ListingData.txt");
            Scanner getListInfoScanner = new Scanner(myFile);
            while (getListInfoScanner.hasNextLine()) {
                String data = getListInfoScanner.nextLine();
                // Parse line here (e.g., data.split(","))
                String [] info = data.split(",");
                if (info[0].equals(number)) {
                    //add to map
                    information.put ("number", info[0]);
                    information.put ("name", info[1]);
                    information.put ("description", info[2]);
                    information.put ("price", info[3]);
                    break;
                }
            }
            getListInfoScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return information;
    }

    public static void main(String[] args) {
        ListingIndex allListings = new ListingIndex();

        Map<String, String> info = allListings.getListInfo("1");

        System.out.println(info);
    }
    
}