package bturnert_a3.ePortfolio;

import java.text.DecimalFormat;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * This class creates an instance of the SellPanel.
 * @author Ben Turner-Theijsmeijer
 * @version 1.0
 */
public class SellPanel extends JPanel implements ActionListener{

    private JButton resetButton;
    public JButton sellButton;

    private JTextField symbolField;
    private JTextField quantityField;
    private JTextField priceField;
    public JTextArea returnMessageField;
    private JScrollPane scrollableMessagePane;
    
    /**
     * Created a new BuyPanel object.
     */
    public SellPanel(){
        //------------------ BASIC PANEL SET UP ------------------\\
        this.setSize(DisplayFrame.WIDTH,DisplayFrame.HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(false);

        //===================== FIELDS PANEL COMPONENTS START =====================\\
        //------------------ JLABELS FOR SELL PANEL ------------------\\
        JLabel sellText = new JLabel("Selling an Investment:");
        sellText.setFont(new Font("Arial",Font.BOLD,17));
        
        JLabel symbolText = new JLabel("Symbol");
        symbolText.setBounds(60,30,100,30);
        symbolText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel quantityText = new JLabel("Quantity");
        quantityText.setBounds(60,100,100,30);
        quantityText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel priceText = new JLabel("Price");
        priceText.setBounds(60,170,100,30);
        priceText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel messageText = new JLabel("Messages:");
        messageText.setFont(new Font("Arial",Font.BOLD,17));

        //------------------ TEXTFIELDS FOR SELL PANEL ------------------\\
        symbolField = new JTextField();
        symbolField.setFont(new Font("Arial",Font.PLAIN,17));
        symbolField.setBounds((DisplayFrame.WIDTH/12)*3,30,160,30);

        quantityField = new JTextField();
        quantityField.setFont(new Font("Arial",Font.PLAIN,17));
        quantityField.setBounds((DisplayFrame.WIDTH/12)*3,100,100,30);

        priceField = new JTextField();
        priceField.setFont(new Font("Arial",Font.PLAIN,17));
        priceField.setBounds((DisplayFrame.WIDTH/12)*3,170,100,30);
        //===================== FIELDS PANEL COMPONENTS EMD =====================\\

        //===================== BUTTONS PANEL COMPONENTS START =====================\\
        //------------------ JBUTTONS FOR SELL PANEL ------------------\\
        resetButton = new JButton();
        resetButton.setBounds(DisplayFrame.WIDTH/12,DisplayFrame.HEIGHT/8,80,30);
        resetButton.setFont(new Font("Arial",Font.PLAIN,17));
        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        sellButton = new JButton();
        sellButton.setBounds(DisplayFrame.WIDTH/12,DisplayFrame.HEIGHT/4,80,30);
        sellButton.setFont(new Font("Arial",Font.PLAIN,17));
        sellButton.setText("Sell");
        sellButton.setFocusable(false);
        sellButton.addActionListener(this);
        //===================== BUTTONS PANEL COMPONENTS END =====================\\

        //===================== LOWER PANEL COMPONENTS START =====================\\
        //------------------ TEXTAREA AND SCROLL BARS FOR SELL PANEL ------------------\\
        returnMessageField = new JTextArea(5,40);
        returnMessageField.setBounds(50,30,(DisplayFrame.WIDTH/6)*5,10);
        returnMessageField.setFont(new Font("Arial",Font.PLAIN,17));

        scrollableMessagePane = new JScrollPane(returnMessageField);
        scrollableMessagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableMessagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //===================== LOWER PANEL COMPONENTS END =====================\\

        //------------------ SUBPANELS FOR SELL PANEL ------------------\\
        JPanel fieldsHalf = new JPanel(null);
        fieldsHalf.setBounds(0,0,(DisplayFrame.WIDTH/6)*4,(DisplayFrame.HEIGHT/14)*13);

        JPanel buttonHalf = new JPanel(null);
        buttonHalf.setBounds((DisplayFrame.WIDTH/6)*4,0,(DisplayFrame.WIDTH/6)*2,(DisplayFrame.HEIGHT/14)*13);

        JPanel topHalf = new JPanel(null);

        JPanel lowerHalf = new JPanel();
        lowerHalf.setPreferredSize(new Dimension(DisplayFrame.WIDTH, 0));

        //------------------ ADDING ELEMENTS TO SUB PANELS  ------------------\\
        buttonHalf.add(resetButton);
        buttonHalf.add(sellButton);

        fieldsHalf.add(symbolText);
        fieldsHalf.add(quantityText);
        fieldsHalf.add(priceText);
        fieldsHalf.add(symbolField);
        fieldsHalf.add(quantityField);
        fieldsHalf.add(priceField);

        topHalf.add(fieldsHalf);
        topHalf.add(buttonHalf);
        lowerHalf.add(scrollableMessagePane);
        
        //------------------ ADDING SUBPANELS TO PANEL ------------------\\
        this.add(sellText);
        this.add(topHalf);
        this.add(messageText);
        this.add(lowerHalf);
    }
    

    //------------------ ACTION LSITENER FOR SELL PANEL ------------------\\
    /**
     * Invoked when an action occurs in the SellPanel.
     * @param event Variable that indicates if a component defined action has occured.
     */
    @Override
    public void actionPerformed(ActionEvent event){
        //JBUTTONS
        if(event.getSource()==resetButton){
            System.out.println("RESET BUTTON PRESSED");
            returnMessageField.setText("");
            emptyFields();
        }
        else if(event.getSource()==sellButton){
            System.out.println("SELL BUTTON PRESSED");
            returnMessageField.setText("");
            System.out.println("Symbol: " + symbolField.getText());
            System.out.println("Quantity: " + quantityField.getText());
            System.out.println("Price: " + priceField.getText());
            //check validity of possible investment parameters
            if(!sellSymbolValidate() || !sellQuantityValidate() || !sellPriceValidate()){
                returnMessageField.setText(returnMessageField.getText() + "Sell aborted, please address Errors and try again\n");
            }
            //parameters were valid
            else{
                Portfolio.symbol = (String)symbolField.getText();
                int sellResult = Portfolio.sell(Integer.parseInt((String)quantityField.getText()), Double.parseDouble((String)priceField.getText()));
                sellPrintReturnMessage(sellResult);
                if(sellResult >=3 && sellResult <= 4)   emptyFields();
            }
            
        }
    }

    //------------------ EXCEPTION HANDLING FOR SELL OPERATION ------------------\\
    /**
     * Checks for exceptions in the entered symbol.
     * @return Returns a boolean representing whether the entered symbol is valid or not.
     */
    private boolean sellSymbolValidate(){
        String tempSymbol = (String)symbolField.getText();
        if(tempSymbol.isBlank()){
            returnMessageField.setText(returnMessageField.getText() + "Error: Symbol field cannot be left blank\n");
            return false;
        }
        return true;
    }

    /**
     * Checks for exceptions in the entered quantity.
     * @return Returns a boolean representing whether the entered quantity is valid or not.
     */
    private boolean sellQuantityValidate(){
        String tempQuantity = (String)quantityField.getText();
        if(tempQuantity.isBlank()){
            returnMessageField.setText(returnMessageField.getText() + "Error: Quantity field cannot be left blank\n");
            return false;
        }
        else if(!tempQuantity.matches("[0-9]+")){
            returnMessageField.setText(returnMessageField.getText() + "Error: Quantity field can only contain Natual Numbers, and cannot be negative\n");
            return false;
        }
        return true;
    }

    /**
     * Checks for exceptions in the entered sell price.
     * @return Returns a boolean representing whether the entered price is valid or not.
     */
    private boolean sellPriceValidate(){
        String tempPrice = (String)priceField.getText();
        if(tempPrice.isBlank()){
            returnMessageField.setText(returnMessageField.getText() + "Error: Price field cannot be left blank\n");
            return false;
        }
        else if(!tempPrice.matches("[0-9.]+")){
            returnMessageField.setText(returnMessageField.getText() + "Error: Price field can only contain Rational Numbers, and cannot be negative\n");
            return false;
        }
        return true;
    }

    //------------------ TEXT FIELD UPDATING FUNCTIONS ------------------\\
    /**
     * Depending on the passed parameter, set the text in the returnMessageField to the appropriate message. 
     * @param sellResult Integer representing the outcome of the sell operation, 0 = no investment in ePortfolio to sell, 1 = specified Investment does not exist, 
     * 2 = specified quantity > Investment quantity, 3 = successfully sold all of Investment, 4 = Successfully sold part of Investment, any other = falied for undefined reason.
     */
    private void sellPrintReturnMessage(int sellResult){
        DecimalFormat twoDecimal = new DecimalFormat();
        twoDecimal.setMaximumFractionDigits(2);
        if(sellResult == 0)    returnMessageField.setText(returnMessageField.getText() + "Error: Cannot sell any investments at this time as you current have none\n");

        else if(sellResult == 1)    returnMessageField.setText(returnMessageField.getText() + "Error: Specified investment does not exist in our records\n");

        else if(sellResult == 2)    returnMessageField.setText(returnMessageField.getText() + "Error: Specified quantity cannot be larger than quantity currently owned\n");

        else if(sellResult == 3)    returnMessageField.setText(returnMessageField.getText() + "Sell successful, all of investment sold for $" + twoDecimal.format(Portfolio.currentInvestmentGain) + "\n");

        else if(sellResult == 4)    returnMessageField.setText(returnMessageField.getText() + "Sell successful, part of investment sold for $" + twoDecimal.format(Portfolio.currentInvestmentGain) + "\n");

        else    returnMessageField.setText(returnMessageField.getText() + "Sell aborted, Undefined reason\n");


    }

    /**
     * clears the editable TextFields in the SellPanel.
     */
    private void emptyFields(){
        symbolField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

}
