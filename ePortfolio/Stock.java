package bturnert_a3.ePortfolio;

/**
 * This class creates and edits objects of type Stock.
 * <p> 
 * SubClass of the Investment class. 
 * Stock contains inforamtion on a stock's name, symbol, price, quantity, and book value
 *
 * @author Ben Turner-Theijsmeijer 
 * @version 3.0
 */
public class Stock extends Investment{
    //--------------------------------Stock Specific variables--------------------------------
    private static double commission = 9.99;

    //--------------------------------Constructor--------------------------------
    /**
     * Create a new Stock object, given the data provided.
     * @param symbolOfStock represents the symbol for the stock object. This is required as the only perameter as it must be specfied beforehand in the even the stock already exists.
     */
    public Stock(String symbolOfStock){
        super(symbolOfStock);
    }

    /**
     * Create a new Stock object, given the data provided.
     * @param newName String passed to method representing the new name for the object.
     * @param newSymbol String passed to method representing the new symbol for the object.
     * @param newQuantity Int passed to method representing the new quantity for the object.
     * @param newPrice Double passed to method representing the new Price for the object.
     */
    public Stock(String newName, String newSymbol, int newQuantity, double newPrice){
        super(newName, newSymbol, newQuantity, newPrice);
    }

    /**
     * Create a new Stock object, given the data provided.
     * @param newName String passed to method representing the new name for the object.
     * @param newSymbol String passed to method representing the new symbol for the object.
     * @param newQuantity Int passed to method representing the new quantity for the object.
     * @param newPrice Double passed to method representing the new Price for the object.
     * @param newBookValue Double passed to method representing the new book value that has been calcuated elsewhere.
     */
    public Stock(String newName, String newSymbol, int newQuantity, double newPrice, double newBookValue){
        super(newName, newSymbol, newQuantity, newPrice, newBookValue);
    }
    //--------------------------------toString function--------------------------------
    @Override
    public String toString() {
        return "Type = \"Stock\"" + "\nSymbol = \"" + getSymbol() + "\"\nName = \"" + getName() + "\"\nQuantity = \"" + getQuantity() + "\"\nPrice = \"" + getPrice() + "\"\nBookValue = \"" + getBookValue() + "\"\n";
    }

    //--------------------------------set and get functions--------------------------------
    /**
     * Sets the current Stock's book value equal to the result of multiplying the current Stock's price by the quantity and adding the commission fee.
     */
    @Override
    public void setBookValue() {
        int tempQuantity = getQuantity();
        double tempPrice = getPrice();
        manualSetBookValue(tempQuantity * tempPrice + commission);
    }

    //--------------------------------Buy operations on Stock--------------------------------
    /**
     * Updates the current Stock's book value to the result of adding the multiplication of the updated Stock's price by the new quantity and adding the commission fee to the current book value.
     * @param tempPrice Double passed to method representing the new Price for the Stock object.
     * @param tempQuantity Integer passed to method representing the new quantity of the stock being bought.
     */
    @Override
    public void buyUpdateBookValue(double tempPrice, int tempQuantity){
        double tempBookValue = getBookValue();
        manualSetBookValue(tempBookValue + ((tempQuantity * tempPrice) + commission));
    }

    //--------------------------------Sell Operations on Stock--------------------------------
    /**
     * Prompts the user for the quantity of the current Stock object to be sold. Scans an integer from system.in and removes what was read from the current Stock's quantity. 
     * Prints the gain from selling the specified quantity of the Stock object
     * If after selling, the quantity for the current Stock object equals 0, returns 1.
     * If after selling, the quantity for the current Stock object does not equal 0, updates the book value for the current Stock object and returns 0.
     * @return Returns either the interger 0 representing that not all of the Stock object was sold or 1 representing all of the Stock object was sold.
     */
    @Override
    public double sellUpdateQuantity(int tempQuantity){
        double sellValue = 0;
        double gain = 0;
        double tempBookValue = getBookValue();
        double currentPrice = getPrice();
        int currentQuantity = getQuantity();
        //selling the whole stock
        if(tempQuantity == currentQuantity){
            sellValue = currentPrice * tempQuantity - commission;
            gain = sellValue - tempBookValue;
            System.out.printf("You sold all of this stock for a total gain of: $%.2f\n", gain);
            return gain;
        }
        //selling only part
        else{
            sellValue = currentPrice * tempQuantity - commission;
            tempBookValue = tempBookValue * (tempQuantity / currentQuantity);
            gain = sellValue - tempBookValue;
            System.out.printf("You sold part of this stock for a total gain of: $%.2f\n", gain);
            //updating bookvalue
            lowerBookValue(tempBookValue);
        }
        //updating quantity
        lowerQuantity(tempQuantity);
        return gain;
    }

    //--------------------------------Get Gain Operation on Stock--------------------------------
    /**
     * Calculates the gain aquired if all currently held Stock objects were sold completely at their current prices.
     * @return Returns a double representing the gain aquired if all currently held Stock objects were sold completely at their current prices.
     */
    @Override
    public double getGain(){
        int tempQuantity = getQuantity();
        double tempPrice = getPrice();
        double tempBookValue = getBookValue();
        double sellValue = tempPrice * tempQuantity - commission;
        double gain = sellValue - tempBookValue;
        return gain;
    }
}
