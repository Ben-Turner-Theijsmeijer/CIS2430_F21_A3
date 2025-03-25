package bturnert_a3.ePortfolio;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * This Class hold the function for manipulation of Investment items in an ArrayList.
 * <p>
 * Contains the main class for execution of the program.
 * 
 * @author Ben Turner-Theijsmeijer
 * @version 3.0
 */
public class Portfolio {

    public static String symbol;
    public static double currentInvestmentGain;
    public static String argZero;
    public static boolean validFile;

    static Scanner scan = new Scanner(System.in);

    static ArrayList <Investment> InvestmentList = new ArrayList <Investment>();
    static HashMap <String, ArrayList<Integer>> NameKeys = new HashMap <String, ArrayList<Integer>>();

    //------------------------------------------------coomand line arguments------------------------------------------------
    /**
     * Checks if a command line argument was provided or not.
     * @param args String array passed to method representing the command line arguments.
     * @return Returns either true if there is a command line argument or false if not.
     */
    private static boolean isCommandLineArgument(String[] args){
        if(args.length == 0){
            System.out.println("No file specified to load data from");
            return false;
        }
        else{
            argZero = args[0];
            System.out.println("Attempting to read from file: " + args[0]);
            return true;
        } 
    }
    
    //------------------------------------------------check if Investment ArrayList is empty------------------------------------------------
    /**
     * Checks if the Investments ArrayList is empty or not, returning a boolean value representing if the list is empty or not.
     * @return Returns either true if the ArrayList is empty or false if it is not empty.
     */
    public static boolean investmentsIsEmpty(){
        boolean isEmpty = false;
        if(InvestmentList.isEmpty()){
            isEmpty = true;
        }
        return isEmpty;
    }

    //------------------------------------------------check Investment ArrayList size------------------------------------------------
    /**
     * Check the size of the Investments ArrayList, Returning an integer representing the size.
     * @return Returns an integer representing he size of the Investments ArrayList
     */
    public static int InvestmentSize(){
        return InvestmentList.size();
    }

    //------------------------------------------------return symbol or paticular Investment------------------------------------------------
    /**
     * Pulls the Symbol of a specified Investment out of the ArrayList and returns it to the calling function.
     * @param index Integer representing the location in the ArrayList of the particual Investment to be pulled from.
     * @return Returns a String representing the symbol of the particular Investment.
     */
    public static String symbolofSpecificElement(int index){
        if(index > InvestmentSize() || index < 0){
            return "Symbol not found";
        }
        return InvestmentList.get(index).getSymbol();
    }

    //------------------------------------------------return name or paticular Investment------------------------------------------------
    /**
     * Pulls the name of a specified Investment out of the ArrayList and returns it to the calling function.
     * @param index Integer representing the location in the ArrayList of the particual Investment to be pulled from.
     * @return Returns a String representing the name of the particular Investment.
     */
    public static String nameOfSpecificElement(int index){
        if(index > InvestmentSize() || index < 0){
            return "Name not found";
        }
        return InvestmentList.get(index).getName();
    }

    //------------------------------------------------return price or paticular Investment------------------------------------------------
    /**
     * Pulls the price of a specified Investment out of the ArrayList and returns it to the calling function.
     * @param index Integer representing the location in the ArrayList of the particual Investment to be pulled from.
     * @return Returns a double representing the price of the particular Investment.
     */
    public static Double priceOfSpecificElement(int index){
        if(index > InvestmentSize() || index < 0){
            return 0.00;
        }
        return InvestmentList.get(index).getPrice();
    }

    //------------------------------------------------return paticular Investment------------------------------------------------
    /**
     * Pulls the specified Investment out of the ArrayList and returns it to the calling function.
     * @param index Integer representing the location in the ArrayList of the particual Investment to be pulled.
     * @return Returns a String representing the particular Investment.
     */
    public static String specificElement(int index){
        return InvestmentList.get(index).toString();
    }


    //------------------------------------------------check if symbol is in Investment ArrayList------------------------------------------------
    /**
     * Iterates through all Investment objects in the InvestmentList ArrayList. Checks if the symbol matches symbol in the current Investment object. Returning the position in the ArrayList if found.
     * @return Returns an integer representing  either -1 if the symbol is not found in any of the Investment objects, or if found in the ArrayList indicating the position in the InvestmentList ArrayList where the symbol was matched. 
     */
    private static int inInvestments(){
        int counter = 0;
        for(Investment temp: InvestmentList){
            if(temp.getSymbol().equalsIgnoreCase(symbol)){ 
                return counter;
            }
            counter++;
        }
        return -1; // not in list
    }
    
    //------------------------------------------------get search key------------------------------------------------
    /**
     * Takes a string and returns a lowercase copy with all leading and trailing white space removed.
     * @param searchWord String representing the Word passed to the function that must be updated before proceeding.
     * @return Returns a String representing the line that was read.
     */
    private static String searchWord(String searchWord){
        searchWord = searchWord.trim();
        searchWord = searchWord.toLowerCase();
        return searchWord;
    }

    //------------------------------------------------separate search keywords------------------------------------------------
    /**
     * Splits the keywords string into sepaerate words and returns them as an array of strings.
     * @param enteredString String passed to method representing the key words to search for.
     * @return Reurns an array of strings representing the enteredString after parsing it wherever there is a space.
     */
    private static String[] separate (String enteredString){
        String [] parts = enteredString.split("[ ]+");
        return parts;
    }

    //------------------------------------------------OK to show based off keywords------------------------------------------------
    /**
     * Decides if it is okay to display an investment based on the keywords matching.
     * @param keys String array passed to method representing the enteredString after parsing it wherever there is a space.
     * @param currentName String representing the name of the current investment.
     * @return Returns a boolean value representing okay to display investment or not; true = okay, flase = not.
     */
    private static boolean showKeyword (String []keys, String currentName){
        boolean keywordMatch = true;
        for(String temp: keys){
            if(!currentName.contains(temp.concat(" "))){
                keywordMatch = false;
            }
        }
        return keywordMatch;
    }
    
    //------------------------------------------------get valid priceRange------------------------------------------------
    /**
     * Checks if the entered range for search function is valid.
     * @param enteredRange String passed to method representing the range for the search method entered by the user. 
     * @return Returns a value in the range 0-5, where the value returned indicates the specific case. 0 = invalid, 1 = no range, 2 = specific price, 3 = specific price or higher, 4 = specific price or lower, 5 = price range.
     */
    private static int validRange(String enteredRange){
        if(enteredRange.isBlank()){
            return 1;
        }
        else if(enteredRange.matches("[0-9.]+")){
            return 2;
        }
        else if(enteredRange.matches("[0-9.]+" + "-")){
           return 3;
        }
        else if(enteredRange.matches("-" + "[0-9.]+")){
            return 4;
        }
        else if(enteredRange.matches("[0-9.]+" + "-" + "[0-9.]+")){
            return 5;
        }
        else{
            return 0;
        }
    }

    //------------------------------------------------OK to show based off price range------------------------------------------------
    /**
     * Takes in all parameters relating to search price range and computes wether or not it is okay to display the investment based on the price range matching.
     * @param min Double representing the minimum value that price can be to match in the even the entered range was specific price or higher, or full price range. -1 if not set.
     * @param max Double representing the maxmum value that price can be to match in the event the entered range was specific price or lower, or full price range. -1 if not set.
     * @param specificPrice Double representing the value that price must match in the event the entered range was specific price. -1 if not set.
     * @param rangeType Integer representing the value in the range 0-5, where the value indicates the specific case. 0 = invalid, 1 = no range, 2 = specific price, 3 = specific price or higher, 4 = specific price or lower, 5 = price range.
     * @param currentPrice Double representing the price of the current investment.
     * @return Returns a boolean represetning if it is okay to display investment or not; true = okay, false = not.
     */
    private static boolean showRange(double min, double max, double specificPrice, int rangeType, double currentPrice){
        boolean rangeMatch = false;
        switch (rangeType){
            case 1:     //no price range search (left blank)
                break;
            case 2:     //specific price search (1000.00)
                if(specificPrice == currentPrice){
                    rangeMatch = true;
                }
                break;
            case 3:     //specific price or higher search (1000.00-)
                if(min <= currentPrice){
                    rangeMatch = true;
                }
                break;
            case 4:     //specific price or lower search (-1000.00)
                if(max >= currentPrice){
                    rangeMatch = true;
                }
                break;
            case 5:     //price range search (100.00-999.99)
                if(min <= currentPrice && max >= currentPrice){
                    rangeMatch = true;
                }
                break;
        }
        return rangeMatch;
    }

    //------------------------------------------------Add or update element in the HashMap------------------------------------------------
    /**
     * Adds an element or updates an exisitng element in the HashMap.
     * @param currentName String representing the name of the current Investment.
     * @param index Integer representing the position in the ArrayList.
     */
    private static void addToHashMap(String currentName, Integer index){
        String[] nameParts = currentName.split("[ ]+");
        for(String temp: nameParts){
            ArrayList <Integer> foundAt = new ArrayList <Integer>();
            foundAt.clear();
            //name part is in HashMap
            if(NameKeys.containsKey(temp)){
                foundAt = NameKeys.get(temp);
                foundAt.add(index);
                NameKeys.put(temp, foundAt);
            }
            //Name part is not in HashMap
            else{
                foundAt.add(index);
                NameKeys.put(temp, foundAt);
            }
        }
    }

    //------------------------------------------------remove element from the HashMap------------------------------------------------
    /**
     * Removes elements form the HashMap when they are completely sold off.
     * @param currentName String representing the name of the current Investment.
     * @param index Integer representing the position in the ArrayList.
     */
    private static void removeFromHashMap(String currentName, Integer index){
        String[] nameParts = currentName.split("[ ]+");

        System.out.println("investment placement = " + index);

        for(String temp: nameParts){
            ArrayList <Integer> foundAt = new ArrayList <Integer>();
            foundAt.clear();
            //name part is in HashMap
            if(NameKeys.containsKey(temp)){
                foundAt = NameKeys.get(temp);
                if(foundAt.contains(index)){
                    if(foundAt.size() == 1){
                        NameKeys.remove(temp);
                    }
                    for(int i = 0; i < foundAt.size(); i++){
                        if(foundAt.get(i) == index){
                            foundAt.remove(i);
                        }
                    }
                }
                NameKeys.replace(temp, foundAt);
            }
        }
    }

    //------------------------------------------------search using the HashMap------------------------------------------------
    /**
     * Searches for element in ArrayList using HashMap.
     * @param searchWord String representing the specified search words.
     * @param searchSymbol String representing the specified search symbol.
     * @param rangeType Integer representing the type of seach Price range was inputed.
     * @param min Double representing the minimum value the price range must be to match.
     * @param max Double representing the maximim value the price range can be to match.
     * @param specificPrice Double representing the specific price that must be match.
     */
    private static void searchUsingHashMap(String searchWord, String searchSymbol, int rangeType, double min, double max, double specificPrice){
        String[] nameParts = searchWord.split("[ ]+");
        //NOT DONE

    }

    /*================================================================================================================================================
    ||                                                        COMMAND LOOP OPERATIONS                                                               ||
    ================================================================================================================================================*/
    //------------------------------------------------buy operation------------------------------------------------
    /**
     * Creates Stock and MutualFund objects and adds them to the associated ArrayList. 
     * <p>
     * Alternatively increases the varaibles in pre-existing Stock and MutualFund objects found in their associated ArrayLists.
     * @param investmentType Intefer representing the type of investment to be created, 1 = Stock, 2 = MutualFund.
     * @param tempName String representing the name of the investment.
     * @param tempQuantity Integer representing the quantity of the investment.
     * @param tempPrice Double representing the price of the investment.
     * @return Returns an integer representing the outcome of the buy operation, 0 = failed, 1 = successful new investment, 2 = successful existing investment.
     */
    public static int buy(int investmentType, String tempName, int tempQuantity, double tempPrice){
        int investmentLocation = inInvestments();
        //if the investment does not already exist creating a new investment
        if(investmentLocation == -1){       //NEW INVESTMENT
            //new Stock investment
            if(investmentType == 1){
                Stock newStock = new Stock(tempName, symbol, tempQuantity, tempPrice);
                addToHashMap(newStock.getName().toLowerCase(), InvestmentList.size());
                InvestmentList.add(newStock);
                System.out.println("New Stock investment created:");
            }
            //new Mutual Fund investment
            else if(investmentType == 2){
                MutualFund newMutualFund = new MutualFund(tempName, symbol, tempQuantity, tempPrice);
                addToHashMap(newMutualFund.getName().toLowerCase(), InvestmentList.size());
                InvestmentList.add(newMutualFund);
                System.out.println("New Mutual Fund investment created:");
            }
            //getInvestmentType did not work properly
            else{
                System.out.println("program is broken, did not read investment type properly, investment type = " + investmentType);
            }
            return 1; //meaning buy was successful for a new investment
        }
        //if the investment does exist retreiving the investment from the arrayList
        else if(investmentLocation > -1){       //PRE-EXISTING INVESTMENT
            //is an exisitng Stock investment
            if((investmentType == 1) && (InvestmentList.get(investmentLocation) instanceof Stock)){
                Stock existingStock = (Stock)InvestmentList.get(investmentLocation);
                System.out.println("Buying more of " + existingStock.getName() + " Stock:");
                existingStock.buyUpdate(tempQuantity, tempPrice);
                InvestmentList.set(investmentLocation, existingStock);
                return 2; //meaning buy was successful for an exisiting investment
            }
            //is an exisitng MutualFund investment
            else if((investmentType == 2) && (InvestmentList.get(investmentLocation) instanceof MutualFund)){
                MutualFund existingMutualFund = (MutualFund)InvestmentList.get(investmentLocation);
                System.out.println("Buying more of " + existingMutualFund.getName() + " Mutual Fund:");
                existingMutualFund.buyUpdate(tempQuantity, tempPrice);
                InvestmentList.set(investmentLocation, existingMutualFund);
                return 2; //meaning buy was successful for an exisiting investment
            }
            //is an exisitng Investment, but Investment type and buy type did not match
            else{
                System.out.println("Sorry, you are trying to buy an Investment with the symbol: "+ symbol);
                System.out.println("This symbol has already been used to specify an existing Investment of a different type.");
                System.out.println("No two investments may be represented by the same symbol.");
                System.out.println("Aborting buy operation, please try again.");
                return 0; //meaning unsuccessful becuase of invalid type
            }
        }
        else{   //something is wrong if this prints
            System.out.println("program is broken, did not perform buy operation properly, investment type = " + investmentType);
            return 0;
        }

    }

    //------------------------------------------------sell operation------------------------------------------------
    /**
     * Deletes pre-existing Stock and MutualFund objects form their associated ArrayLists.
     * <p>
     * Alternatively lowers the variables in pre-existing Stock and MutualFund objects found in the associated ArrayList.
     * @param tempQuantity Integer representing the quantity of the investment to sell.
     * @param tempPrice Double representing the price to sell the investment at.
     * @return Returns an integer representing the outcome of the sell operation, 0 = no investments avaiable to sell, 1 = specified investment does not exist, 2 = cannot sell more than is owned, 3 = sold all of investment, 4 = sold part of investment, -1 = i dont know what went wrong.
     */
    public static int sell(int tempQuantity, double tempPrice){
        //checking if there are investments that can be sold
        if(investmentsIsEmpty() == true){
            System.out.println("Error: Cannot sell any investments at this time as you current have none");
            return 0; // 0 = no investments avaiable to sell
        }
        //varaiable declaration
        int remove = 0;
        int investmentLocation = inInvestments();   //check if entered symbol is in InvestmentsList
        //symbol is not found in ArrayList
        if(investmentLocation == -1){
            System.out.println("Error: Cannot sell specified investment as it does not currently exist");
            return 1; //1 = specified investment does not exist
        }
        //symbol is found in InvestmentsList
        else if(investmentLocation > -1){
            //Investment is of type Stock
            if(InvestmentList.get(investmentLocation) instanceof Stock){
                Stock stockForSale = (Stock)InvestmentList.get(investmentLocation);
                if(tempQuantity > stockForSale.getQuantity())    return 2; //2 = cannot sell more than is owned
                System.out.println("selling " + stockForSale.getName() + " Stock:");
                stockForSale.manualSetPrice(tempPrice);
                if(tempQuantity == stockForSale.getQuantity())  remove = 1;
                else    remove = 0;
                currentInvestmentGain = stockForSale.sellUpdateQuantity(tempQuantity);
                InvestmentList.set(investmentLocation, stockForSale);
            }
            //Investment is of type MutualFund
            else if(InvestmentList.get(investmentLocation) instanceof MutualFund){
                MutualFund mutualFundForSale = (MutualFund)InvestmentList.get(investmentLocation);
                if(tempQuantity > mutualFundForSale.getQuantity())    return 2; //2 = cannot sell more than is owned
                System.out.println("selling " + mutualFundForSale.getName() + " Mutual Fund:");
                mutualFundForSale.manualSetPrice(tempPrice);
                if(tempQuantity == mutualFundForSale.getQuantity())  remove = 1;
                else    remove = 0;
                currentInvestmentGain = mutualFundForSale.sellUpdateQuantity(tempQuantity);
                InvestmentList.set(investmentLocation, mutualFundForSale);
            }
            if(remove == 1){
                removeFromHashMap(InvestmentList.get(investmentLocation).getName(), investmentLocation);
                InvestmentList.remove(investmentLocation);  //removing object from list if all of it was sold
                return 3; //3 = sold all of investment 
            }
            else    return 4; //4 = sold part of investment
        }
        else    return -1; //-1 = should never happen and means something isnt right
    }

    //------------------------------------------------update operation------------------------------------------------
    /**
     * Updates the Price associated with pre-existing Stock and MutualFund objects found in the associated ArrayList.
     * @param index Integer representing the currently selected investment in the ArrayList.
     * @param tempPrice Double representing the new price of the investment at index.
     * @return Returns and integer representing the outcome of the update operation, 0 = nothing to update, 1 = updated Stock successfully, 2 = updated MutualFund successfully.
     */
    public static int update(int index, Double tempPrice){
        //checking if there are investments that can be updated
        if(investmentsIsEmpty() == true){
            System.out.println("Error: Cannot sell any investments at this time as you current have none");
            return 0;
        }
        //there are investments to be updated
        if(InvestmentList.get(index) instanceof Stock){
            Stock currentStock = (Stock)InvestmentList.get(index);
            System.out.println("Updating " + currentStock.getName() + " stock price:");
            currentStock.manualSetPrice(tempPrice);
            System.out.printf("%s updated to $%.2f per unit\n", currentStock.getName(), currentStock.getPrice());
            InvestmentList.set(index, currentStock);
            return 1;
        }
        //current element is of type MutualFund
        else if(InvestmentList.get(index) instanceof MutualFund){
            MutualFund currentMutualFund = (MutualFund)InvestmentList.get(index);
            System.out.println("Updating " + currentMutualFund.getName() + " mutual fund price:");
            currentMutualFund.manualSetPrice(tempPrice);
            System.out.printf("%s updated to $%.2f per unit\n", currentMutualFund.getName(), currentMutualFund.getPrice());
            InvestmentList.set(index, currentMutualFund);
        }
        return 2;
    }

    //------------------------------------------------getGains operation------------------------------------------------
    /**
     * Outputs the theoretical gain that could be obtained by the user if they completely sold all Stock and MutualFund investments in their portfolio at their current prices.
     * @param index Integer representing the currently selected investment in the ArrayList.
     */
    public static void getGain(int index){
        //current element is of type Stock
        if(InvestmentList.get(index) instanceof Stock){
            Stock currentStock = (Stock)InvestmentList.get(index);
            currentInvestmentGain = currentStock.getGain();
        }
        //current element is of type MutualFund
        else if(InvestmentList.get(index) instanceof MutualFund){
            MutualFund currentMutualFund = (MutualFund)InvestmentList.get(index);
            currentInvestmentGain = currentMutualFund.getGain();
        }
        System.out.printf("Gain = $%.2f\n", currentInvestmentGain);
    }

    //------------------------------------------------search operation------------------------------------------------
    /**
     * Searches the Stock and MutualFund Arraylists for specific objects based on three search paramiters. Paramiters are as follows: 1) symbol 2) key words 3) a price range.
     * Prints all objects that meet search requirements and  does not print those that do not.
     * @param index Integer representing the index of the current Investment in the ArrayList.
     * @param tempSymbol String representing the symbol to seach for.
     * @param tempKey String representing the key words to search for.
     * @param tempRange String representing the price range to search for.
     * @return Returns a boolean value representing if the current element should be printed based off the search critereia. 
     */
    public static boolean search(int index, String tempSymbol, String tempKey, String tempRange){
        //getting search options
        boolean displayable = false;
        int rangeType = 0;
        String searchSymbol, searchKeyWord, searchPriceRange;
        System.out.println("1) Symbol to search for: " + tempSymbol);
        searchSymbol = searchWord(tempSymbol);
        System.out.println("2) Key word(s) to search for: " + tempKey);
        searchKeyWord = searchWord(tempKey);
        String []keywords = separate(searchKeyWord);
        System.out.println("3) Price range to search for: " + tempRange);
        searchPriceRange = searchWord(tempRange);
        rangeType = validRange(searchPriceRange);
        
        //computing values for price range
        double min = -1;
        double max = -1;
        double specificPrice = -1;
        String []splitRange = searchPriceRange.split("-");
        searchPriceRange = searchPriceRange.replaceAll("-", "");
        //parsing the search range if it is not empty
        switch (rangeType){
            case 1:     //no price range search (left blank)
                break;
            case 2:     //specific price search (1000.00)
                specificPrice = Double.parseDouble(searchPriceRange);
                break;
            case 3:     //specific price or higher search (1000.00-)
                min = Double.parseDouble(searchPriceRange);
                break;
            case 4:     //specific price or lower search (-1000.00)
                max = Double.parseDouble(searchPriceRange);
                break;
            case 5:     //price range search (100.00-999.99)
                min = Double.parseDouble(splitRange[0]);
                max = Double.parseDouble(splitRange[1]);
                break;
        }      

        boolean keywordMatch, rangeMatch;
        String currentName;
        Double currentPrice;
        //loop to display all Stock metting search criteria
        Investment current = InvestmentList.get(index);
        keywordMatch = true;
        rangeMatch = false;
        currentName = current.getName().toLowerCase().concat(" ");
        currentPrice = current.getPrice();
        //display all investments
        if(searchSymbol.isBlank() && searchKeyWord.isBlank() && searchPriceRange.isBlank()){                                        //no symbol,    no word,    no range
            System.out.println(current.toString());
            displayable = true;
        }
        //search with only symbol specified
        else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && searchKeyWord.isBlank() && searchPriceRange.isBlank()){       //symbol,       no word,    no range
            System.out.println(current.toString());
            displayable = true;
        }
        //search with only keywords specified
        else if(searchSymbol.isBlank() && !searchKeyWord.isBlank() && searchPriceRange.isBlank()){                                  //no symbol,    word,       no range
            keywordMatch = showKeyword(keywords, currentName);
            if(keywordMatch == true) {
                System.out.println(current.toString());
                displayable = true;
            }
        }
        //search with symbol and keywords specified
        else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && !searchKeyWord.isBlank() && searchPriceRange.isBlank()){      //symbol,       word,       no range
            keywordMatch = showKeyword(keywords, currentName);
            if(keywordMatch == true){
                System.out.println(current.toString());
                displayable = true;
            }
        }
        //search with just range specified
        else if(searchSymbol.isBlank() && searchKeyWord.isBlank() && !searchPriceRange.isBlank()){                                  //no symbol,    no word,    range
            rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
            if(rangeMatch == true){
                System.out.println(current.toString());
                displayable = true;
            }
        }
        //search with symbol and range specified
        else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && searchKeyWord.isBlank() && !searchPriceRange.isBlank()){      //symbol,       no word,    range
            rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
            if(rangeMatch == true){
                System.out.println(current.toString());
                displayable = true;
            }
        }
        //search with keywords and range specified
        else if(searchSymbol.isBlank() && !searchKeyWord.isBlank() && !searchPriceRange.isBlank()){                                 //no symbol,    word,       range
            keywordMatch = showKeyword(keywords, currentName);
            rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
            if(keywordMatch == true && rangeMatch == true){
                System.out.println(current.toString());
                displayable = true;
            }
        }
        //search with all conditions specified
        else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && !searchKeyWord.isBlank() && !searchPriceRange.isBlank()){     //symbol,       word,       range
            keywordMatch = showKeyword(keywords, currentName);
            rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
            if(keywordMatch == true && rangeMatch == true){
                System.out.println(current.toString());
                displayable = true;
            }
        }
        return displayable;
    }

    //------------------------------------------------read from file operation------------------------------------------------
    /**
     * Attempts to read from a file specified as the first command line param.
     * @param args String array representing the command line arguments.
     */
    private static void readFromFile(String[] args){
        //returns if there was no input file
        if(validFile == false){
            return;
        }
        //temporary values to hold information pulled from file
        String objectType, tempName, tempSymbol, fullLine;
        String[] partsOfLine;
        int tempQuantity;
        double tempPrice, tempBookValue;

        //try to read input file
        try {
            File inputFile = new File(args[0]);
            Scanner scanFile = new Scanner(inputFile);
            //loop through file until the end is reached, read in inforamtion for each investment
            while(scanFile.hasNextLine()){
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                objectType = partsOfLine[1];                         //get objectType
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempSymbol = partsOfLine[1];                        //get symbol
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempName = partsOfLine[1];                          //get name
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempQuantity = Integer.parseInt(partsOfLine[1]);    //get quantity
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempPrice = Double.parseDouble(partsOfLine[1]);     //get price
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempBookValue = Double.parseDouble(partsOfLine[1]); //get bookValue
                fullLine = scanFile.nextLine();

                //if Investment is of type Stock, create and add a new Stock object to ArrayList
                if(objectType.equalsIgnoreCase("Stock")){
                    Stock newStock = new Stock(tempName, tempSymbol, tempQuantity, tempPrice, tempBookValue);
                    InvestmentList.add(newStock);

                }
                //if Investment is of type MutualFund, create and add a new MutualFund object to ArrayList
                else if(objectType.equalsIgnoreCase("MutualFund")){
                    MutualFund newMutualFund = new MutualFund(tempName, tempSymbol, tempQuantity, tempPrice, tempBookValue);
                    InvestmentList.add(newMutualFund);
                }
                addToHashMap(tempName.toLowerCase(), InvestmentList.size() - 1);

            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file. Running program without input data");
        }


    }

    //------------------------------------------------write to file operation------------------------------------------------
    /**
     * Writes to a file specified as the first command line param or if none was specified creating a new file.
     */
    public static void writeToFile(){
        //checking if there are investments
        if(investmentsIsEmpty() == true && validFile == false){
            System.out.println("No investements in portfolio at this time and no file was specified to read from. Therefore: no file will be written to");
            return;
        }
        //check if there was information in Investments arraylist but no input file specified
        //if there was, get a file name from the user to store data in
        String fileName;
        if(investmentsIsEmpty() == false && validFile == false){
            System.out.println("No file was specified for input. output will be written to the defaultOutputFile");
            fileName = "defaultOutputFile.txt";
        }
        //if there was a file specified, set fileName to file specified
        else{
            fileName = argZero;
        }
        
        //try to write to file specified by fileName
        try {
            PrintWriter fileWriter = new PrintWriter(fileName);
            for(Investment temp: InvestmentList){                             //iterate though all objects in array list
                fileWriter.println(temp.toString());
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: could write to file " + argZero);
            return;
        }

    }
    /**
     * Runs the ePortfolio program until such a time as the exit condition is entered.
    * @param args String array representing the command line arguments.
    */
    public static void main(String[] args){
        validFile = isCommandLineArgument(args);
        readFromFile(args);

        DisplayFrame window = new DisplayFrame();
    }
}
