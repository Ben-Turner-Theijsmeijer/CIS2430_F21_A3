package bturnert_a3.ePortfolio;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * This class creates and edits objects of type Investment
 * <p>
 * SuperClass for both MutualFund and Stock classes.
 * 
 * @author Ben Turner-Theijsmeijer
 * @version 2.0
 */
public abstract class Investment {
    //----------------------------Investment Common variables---------------------------------
    private String name;
    private String symbol;
    private int quantity;
    private double price;
    private double bookValue;

    static Scanner scan = new Scanner(System.in);

    //----------------------------Constructors--------------------------------
    /**
     * Create a new Investment object, given the data provided.
     * @param symbolOfInvestment represents the symbol for the Investment object. This is required as the only perameter as it must be specfied beforehand in the even the Investment already exists.
     */
    public Investment(String symbolOfInvestment){
        this.setName();
        this.setSymbol(symbolOfInvestment);
        this.setQuantity();
        this.setPrice();
        this.setBookValue();
    }

    /**
     * Create a new Investment object, given the data provided.
     * @param newName String passed to method representing the new Name for the object.
     * @param newSymbol String passed to method representing the new symbol for the object.
     * @param newQuantity Int passed to method representing the new quantity for the object.
     * @param newPrice Double passed to method representing the new Price for the object.
     */
    public Investment(String newName, String newSymbol, int newQuantity, double newPrice){
        this.manualSetName(newName);
        this.manualSetSymbol(newSymbol);
        this.manualSetQuantity(newQuantity);
        this.manualSetPrice(newPrice);
        this.setBookValue();
    }

    /**
     * Create a new Investment object, given the data provided.
     * @param newName String passed to method representing the new name for the object.
     * @param newSymbol String passed to method representing the new symbol for the object.
     * @param newQuantity Int passed to method representing the new quantity for the object.
     * @param newPrice Double passed to method representing the new Price for the object.
     * @param newBookValue Double passed to method representing the new book value that has been calcuated elsewhere.
     */
    public Investment(String newName, String newSymbol, int newQuantity, double newPrice, double newBookValue){
        this.manualSetName(newName);
        this.manualSetSymbol(newSymbol);
        this.manualSetQuantity(newQuantity);
        this.manualSetPrice(newPrice);
        this.manualSetBookValue(newBookValue);
    }
    //----------------------------toString function--------------------------------
    /**
     * Return a string containing all information pertaining to a specifific Investment object.
     * <p>
     *  Abstract implementation of toString method.
     */
    @Override
    public abstract String toString();
    
    //----------------------------set and get functions--------------------------------
    /**
     * Gets the name of this Investment object and returns it as a String.
     * @return Returns the String representing the name for this Investment object.
     */
     public String getName() {
        return this.name;
    }

    /**
     * Prompts the user for a Name for the current Investment object. Scans a line from system.in and sets the current Investment's Name equal to what was read.
     */
    public void setName() {
        String enteredName;
        do{
            System.out.print("Enter the Name of the Investment: ");
            enteredName = scan.nextLine();
        }while(enteredName.isBlank());
        this.name = enteredName;
    }

    /**
     * Gets the symbol of this Investment object and returns it as a String.
     * @return Returns a String representing the symbol for this Investment object.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Sets the current Investment's symbol equal to what was passed by the param.
     * @param symbolOfInvestment String passed to method representing the symbol for the current Investment, Sets the current Investment's symbol equal to symbolofInvestment.
     */
    public void setSymbol(String symbolOfInvestment) {
        this.symbol = symbolOfInvestment;
    }

    /**
     * Gets the quantity of this Investment object and returns it as an integer.
     * @return Returns an interger representing the quantity for this Investment object.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Prompts the user for a quantity for the current Investment object. Scans an integer from system.in and sets the current Investment's quantity equal to what was read.
     * <p>
     * Loops  until a valid integer value is entered by the user.
     */
    public void setQuantity() {
        boolean isValid = false;
        int tempQuantity = 0;
        System.out.print("Enter Quantity: ");
        do{
            if(scan.hasNextInt()){
                tempQuantity = scan.nextInt();
                if(tempQuantity <= 0){
                    System.out.print("Sorry: Quantity cannot be negative try again:");
                }
                else{
                    this.quantity += tempQuantity;
                    isValid = true;
                }
            }
            else{
                System.out.print("Sorry: invlid Quantity please try again: ");
                scan.next();
            }
        }while(isValid == false);
    }

    /**
     * Gets the price of this Investment object and returns it as a double.
     * @return Returns a double representing the price for this Investment object.
     */
    public double getPrice() {
        DecimalFormat twoDecimal = new DecimalFormat();
        twoDecimal.setMaximumFractionDigits(2);
        return Double.parseDouble(twoDecimal.format(this.price));
    }

    /**
     * Prompts the user for a price for the current Investment object. Scans a double from system.in and sets the current Investment's price equal to what was read.
     * <p>
     * Loops  until a valid double value is entered by the user.
     */
    public void setPrice() {
        boolean isValid = false;
        double tempPrice = 0;
        System.out.print("Enter Price: ");
        do{
            if(scan.hasNextDouble()){
                tempPrice = scan.nextDouble();
                if(tempPrice <= 0){
                    System.out.print("Sorry: Price cannot be negative try again:");
                }
                else{
                    this.price = tempPrice;
                    isValid = true;
                }
            }
            else{
                System.out.print("Sorry: invlid Price please try again: ");
                scan.next();
            }
        }while(isValid == false);
    }

    /**
     * Gets the book value of this Investment object and returns it as a double.
     * @return Returns a double representing the book value for this Investment object.
     */
    public double getBookValue() {
        return this.bookValue;
    }

    /**
     * Sets the current Investment's book value equal to the result of multiplying the current Investment's Price by the Quantity
     * <p>
     * Abstract implementation of setBookValue
     */
    public abstract void setBookValue();

    /**
     * Sets the current Investment's name to newName.
     * @param newName String passed to method representing the new name for the object.
     */
    public void manualSetName(String newName){
        this.name = newName;
    }

    /**
     * Sets the current Investment's symbol to newSymbol.
     * @param newSymbol String passed to method representing the new symbol for the object.
     */
    public void manualSetSymbol(String newSymbol){
        this.symbol = newSymbol;
    }

    /**
     * Sets the current Investment's quantity to newQuantity.
     * @param newQuantity Int passed to method representing the new quantity for the object.
     */
    public void manualSetQuantity(int newQuantity){
        this.quantity += newQuantity;
    }

    /**
     * Sets the current Investment's price to newPrice.
     * @param newPrice Double passed to method representing the new Price for the object.
     */
    public void manualSetPrice(double newPrice){
        this.price = newPrice;
    }
    
    /**
     * Sets the current Investment's book value equal to newBookValue without calculation.
     * @param newBookValue Double passed to method representing the new book value that has been calcuated elsewhere
     */
    public void manualSetBookValue(double newBookValue){
        this.bookValue = newBookValue;
    }

    //--------------------------------Buy operations on Investments--------------------------------
    /**
     * Updates the current Investment object's price, quantity, and book value when a buy operation is performed.
     * @param passedQuantity Integer representing the quantity of the investment to be bought
     * @param passedPrice Double representing the new price the investment will be bought at
     */
    public void buyUpdate(int passedQuantity, double passedPrice){
        manualSetQuantity(passedQuantity);
        manualSetPrice(passedPrice);
        double tempPrice = getPrice();
        int tempQuantity = getQuantity();
        buyUpdateBookValue(tempPrice, tempQuantity);
    }

    /**
     * Updates the current Investment's book value to the result of adding the multiplication of the updated Investment's price by the new quantity to the current book value.
     * <p>
     * Abstract implementation of buyUpdateBookValue.
     * @param tempPrice Double passed to method representing the new Price for the Investment object.
     * @param tempQuantity Integer passed to method representing the new quantity of the Investment being bought.
     */
    public abstract void buyUpdateBookValue(double tempPrice, int tempQuantity);

    //--------------------------------sell operations on Investments--------------------------------
    //done mostly in subclasses
    /**
     * Prompts the user for the quantity of the current Investment object to be sold. Scans an integer from system.in and removes what was read from the current Investment's quantity. 
     * Prints the gain from selling the specified quantity of the Investment object
     * If after selling, the quantity for the current Investment object equals 0, returns 1.
     * If after selling, the quantity for the current Investment object does not equal 0, updates the book value for the current Investment object and returns 0.
     * <p>
     * Abstract implementation of sellUpdateQuantity
     * @return Returns either the interger 0 representing that not all of the Investment object was sold or 1 representing all of the Investment object was sold.
     */
    public abstract double sellUpdateQuantity(int tempQuantity);

    /**
     * Lowers the current Investment object's quantity by tempQuantity.
     * @param tempQuantity Integer passed to method representing the quantity to be removed from the curent Investment object
     */
    public void lowerQuantity(int tempQuantity){
        int currentQuantity = getQuantity();
        this.quantity = currentQuantity - tempQuantity;
    }

    /**
     * Lowers the current Investment object's bookValue by tempBookValue.
     * @param tempBookValue Double passed to method representing the amountof bookValue to be removed from the curent investment object
     */
    public void lowerBookValue(double tempBookValue) {
        double currentBookValue = getBookValue();
        this.bookValue = currentBookValue - tempBookValue;
    }

    //--------------------------------Get Gain Operation on Investments--------------------------------
    /**
     * Calculates the gain aquired if all currently held Investment objects were sold completely at their current prices.
     * @return Returns a double representing the gain aquired if all currently held Investment objects were sold completely at their current prices.
     * Abstract implementation of getGain.
     */
    public abstract double getGain();

}
