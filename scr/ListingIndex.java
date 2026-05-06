import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
            String latestUniqueID = getListInfoScanner.nextLine();
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
                    information.put ("owner", info[4]);
                    break;
                }
            }
            getListInfoScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return information;
    }

    //completes a purchase for a user:
    public String buyListing(String listingId, User user) throws IOException {
        //parse from file:

        File myFile = new File("ListingData.txt");
        Path path = Paths.get("ListingData.txt");
        Scanner getListInfoScanner = new Scanner(myFile);
        String [] info;
        String latestUniqueID = getListInfoScanner.nextLine();
        while (getListInfoScanner.hasNextLine()) {
            String data = getListInfoScanner.nextLine();
            String lineToRemove = data;
            // Parse line here (e.g., data.split(","))
            info = data.split(",");
            if (info[0].equals(listingId)) {
                //deduce price
                user.deductAmount(Integer.parseInt(info[3]));

                Path userPath = Paths.get("UserData.txt");
                List<String> lines = Files.readAllLines(userPath);
                List<String> updatedLines = new ArrayList<>();
                boolean correctUser = false;
                // delete from seller's selling list in the data file
                for (String line : lines) {

                    // detect user
                    if (line.equals(info[4])) {
                        correctUser = true;
                        updatedLines.add(line);
                        continue;
                    }

                    // stop when next user starts (empty line resets block)
                    if (line.trim().isEmpty()) {
                        correctUser = false;
                        updatedLines.add(line);
                        continue;
                    }

                    // modify selling line
                    if (correctUser && line.startsWith("Selling:")) {
                        String userData = line.substring(8).trim(); // remove "Selling:"
                        String[] items = userData.split(" ");

                        List<String> newItems = new ArrayList<>();

                        boolean removed = false;

                        for (String item : items) {
                            if (!removed && item.equals(info[0])) {
                                removed = true; // remove only one occurrence
                                continue;
                            }
                            newItems.add(item);
                        }

                        String newLine = "Selling:" + String.join(" ", newItems);
                        updatedLines.add(newLine);
                    } else {
                        updatedLines.add(line);
                    }
                }

                Files.write(userPath, updatedLines);

                //add to purchase history of user
                lines = Files.readAllLines(userPath);
                correctUser = false;
                List<String> boughtLines = new ArrayList<>();
                for (String line : lines) {
                    // detect user
                    if (line.equals(user.getUserName())) {
                        correctUser = true;
                        boughtLines.add(line);
                        continue;
                    }

                    // stop when next user starts (empty line resets block)
                    if (line.trim().isEmpty()) {
                        correctUser = false;
                        boughtLines.add(line);
                        continue;
                    }

                    //modify bought line:
                    if (correctUser && line.startsWith("Bought:")) {
                        String boughtData = line.substring(7).trim(); // remove "Bought:"
                        
                        if (boughtData.isEmpty()) {
                            // no items yet
                            line = "Bought:" + listingId;
                        } else {
                            // append new item
                            line = "Bought:" + boughtData + " " + listingId;
                        }

                        boughtLines.add(line);
                    } else {
                        boughtLines.add(line);
                    }
                }
                Files.write(userPath, boughtLines);
                //-------------------------------------------------------------------------------------
                //remove from listing file
                List<String> out = Files.lines(path).filter(line -> !line.equals(lineToRemove)).collect(Collectors.toList());
                Files.write(path, out);
                return "Sucessfully Deleted";
            }
        }
        getListInfoScanner.close();

        return "Not Sucessfully Deleted";
    }

    //creating a listing for the user
    public String createListing(String name, String description, int price, User user) throws IOException {
        //add to list of listings txt first:
        Path path = Path.of("ListingData.txt");
        List<String> listingLines = Files.readAllLines(path);
        int latestUniqueID = Integer.parseInt(listingLines.get(0).trim());
        int newID = latestUniqueID + 1;
        listingLines.set(0, String.valueOf(newID));
        String newListing = newID + "," + name + "," + description + "," + price + "," + user.getUserName();
        listingLines.add(newListing);
        Files.write(path, listingLines);

        //change user data txt:
        Path userPath = Paths.get("UserData.txt");
        List<String> lines = Files.readAllLines(userPath);
        boolean correctUser = false;
        List<String> sellingLines = new ArrayList<>();
            for (String line : lines) {
                // detect user
                if (line.equals(user.getUserName())) {
                    correctUser = true;
                    sellingLines.add(line);
                    continue;
                }

                // stop when next user starts (empty line resets block)
                if (line.trim().isEmpty()) {
                    correctUser = false;
                    sellingLines.add(line);
                    continue;
                }

                //modify bought line:
                if (correctUser && line.startsWith("Selling:")) {
                    String sellingtData = line.substring(8).trim(); // remove "Bought:"
                    
                    if (sellingtData.isEmpty()) {
                        // no items yet
                        line = "Selling:" + newID;
                    } else {
                        // append new item
                        line = "Selling:" + sellingtData + " " + newID;
                    }

                    sellingLines.add(line);
                } else {
                    sellingLines.add(line);
                }
            }
            Files.write(userPath,sellingLines);

            //add to user object:
            user.addToList(Integer.toString(newID));

        return "Sucessfully Created";
    }

    public String editListing(String number, String name, String description, int price, User user) throws IOException {
        //find number and rewrite line
        Path path = Path.of("ListingData.txt");
        List<String> listingLines = Files.readAllLines(path);
        List<String> updatedLines = new ArrayList<>();
        for (String line : listingLines) {
            String [] current = line.split(",");
            // detect line to change
            // replace the correct line
            if (current[0].equals(number)) {
                String newLine = number + "," + name + "," + description + "," + price + "," + user.getUserName();
                updatedLines.add(newLine);
            } else {
                updatedLines.add(line);
            }
        }

        // overwrite file with updated content
        Files.write(path, updatedLines);
        return "Successfully Updated";
    }

    

}