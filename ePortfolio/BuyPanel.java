package bturnert_a3.ePortfolio;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * This class creates an instance of the BuyPanel.
 * @author Ben Turner-Theijsmeijer
 * @version 1.0
 */
public class BuyPanel extends JPanel implements ActionListener{

    private JButton resetButton;
    private JButton buyButton;

    public JComboBox typeSelect;
    private String[] typeOptions = {"Stock","Mutual Fund"};

    private JTextField symbolField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextArea returnMessageField;
    private JScrollPane scrollableMessagePane;

    /**
     * Created a new BuyPanel object.
     */
    public BuyPanel(){
        //------------------ BASIC PANEL SET UP ------------------\\
        this.setSize(DisplayFrame.WIDTH,DisplayFrame.HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // this.setBackground(new Color(0x60A642));
        this.setVisible(false);

        //===================== FIELDS PANEL COMPONENTS START =====================\\
        //------------------ JLABELS FOR BUY PANEL ------------------\\
        JLabel buyText = new JLabel("Buying an Investment:");
        buyText.setFont(new Font("Arial",Font.BOLD,17));
        
        JLabel typeText = new JLabel("Type");
        typeText.setBounds(60,10,100,30);
        typeText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel symbolText = new JLabel("Symbol");
        symbolText.setBounds(60,50,100,30);
        symbolText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel nameText = new JLabel("Name");
        nameText.setBounds(60,90,100,30);
        nameText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel quantityText = new JLabel("Quantity");
        quantityText.setBounds(60,130,100,30);
        quantityText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel priceText = new JLabel("Price");
        priceText.setBounds(60,170,100,30);
        priceText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel messageText = new JLabel("Messages:");
        messageText.setFont(new Font("Arial",Font.BOLD,17));

        //------------------ COMBO BOX FOR BUY PANEL ------------------\\
        typeSelect = new JComboBox(typeOptions);
        typeSelect.setBounds((DisplayFrame.WIDTH/12)*3,10,160,30);
        typeSelect.setFont(new Font("Arial",Font.PLAIN,17));
        typeSelect.setSelectedIndex(0);
        typeSelect.addActionListener(this);

        //------------------ TEXTFIELDS FOR BUY PANEL ------------------\\
        symbolField = new JTextField();
        symbolField.setFont(new Font("Arial",Font.PLAIN,17));
        symbolField.setBounds((DisplayFrame.WIDTH/12)*3,50,160,30);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial",Font.PLAIN,17));
        nameField.setBounds((DisplayFrame.WIDTH/12)*3,90,230,30);

        quantityField = new JTextField();
        quantityField.setFont(new Font("Arial",Font.PLAIN,17));
        quantityField.setBounds((DisplayFrame.WIDTH/12)*3,130,100,30);

        priceField = new JTextField();
        priceField.setFont(new Font("Arial",Font.PLAIN,17));
        priceField.setBounds((DisplayFrame.WIDTH/12)*3,170,100,30);
        //===================== FIELDS PANEL COMPONENTS END =====================\\

        //===================== BUTTONS PANEL COMPONENTS START =====================\\
        //------------------ JBUTTONS FOR BUY PANEL -----------------\\
        resetButton = new JButton();
        resetButton.setBounds(DisplayFrame.WIDTH/12,DisplayFrame.HEIGHT/8,80,30);
        resetButton.setFont(new Font("Arial",Font.PLAIN,17));
        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        buyButton = new JButton();
        buyButton.setBounds(DisplayFrame.WIDTH/12,DisplayFrame.HEIGHT/4,80,30);
        buyButton.setFont(new Font("Arial",Font.PLAIN,17));
        buyButton.setText("Buy");
        buyButton.setFocusable(false);
        buyButton.addActionListener(this);
        //===================== BUTTONS PANEL COMPONENTS END =====================\\

        //===================== LOWER PANEL COMPONENTS START =====================\\
        //------------------ TEXTAREA AND SCROLL BARS FOR BUY PANEL ------------------\\
        returnMessageField = new JTextArea(5,40);
        returnMessageField.setBounds(50,30,(DisplayFrame.WIDTH/6)*5,10);
        returnMessageField.setFont(new Font("Arial",Font.PLAIN,17));

        scrollableMessagePane = new JScrollPane(returnMessageField);
        scrollableMessagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableMessagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //===================== LOWER PANEL COMPONENTS END =====================\\

        //------------------ SUBPANELS FOR BUY PANEL ------------------\\
        JPanel fieldsHalf = new JPanel(null);
        // fieldsHalf.setBackground(new Color(0xFFFFFF));
        fieldsHalf.setBounds(0,0,(DisplayFrame.WIDTH/6)*4,(DisplayFrame.HEIGHT/14)*13);

        JPanel buttonHalf = new JPanel(null);
        buttonHalf.setBounds((DisplayFrame.WIDTH/6)*4,0,(DisplayFrame.WIDTH/6)*2,(DisplayFrame.HEIGHT/14)*13);
        // buttonHalf.setBackground(new Color(0x004A42));

        JPanel topHalf = new JPanel(null);
        // topHalf.setBackground(new Color(0xAA00AA));

        JPanel lowerHalf = new JPanel();
        lowerHalf.setPreferredSize(new Dimension(DisplayFrame.WIDTH, 0));
        // lowerHalf.setBackground(new Color(0xAA4042));

        //------------------ ADDING ELEMENTS TO SUB PANELS  ------------------\\
        buttonHalf.add(resetButton);
        buttonHalf.add(buyButton);

        fieldsHalf.add(typeText);
        fieldsHalf.add(symbolText);
        fieldsHalf.add(nameText);
        fieldsHalf.add(quantityText);
        fieldsHalf.add(priceText);
        fieldsHalf.add(typeSelect);
        fieldsHalf.add(symbolField);
        fieldsHalf.add(nameField);
        fieldsHalf.add(quantityField);
        fieldsHalf.add(priceField);

        topHalf.add(fieldsHalf);
        topHalf.add(buttonHalf);
        // lowerHalf.add(messageText);
        lowerHalf.add(scrollableMessagePane);
        
        //------------------ ADDING SUBPANELS TO PANEL ------------------\\
        this.add(buyText);
        this.add(topHalf);
        this.add(messageText);
        this.add(lowerHalf);
        // this.add(scrollableMessagePane);

    }
    
    //------------------ ACTION LISTENER FOR BUY PANEL ------------------\\
    /**
     * Invoked when an action occurs in the BuyPanel.
     * @param event Variable that indicates if a component defined action has occured.
     */
    @Override
    public void actionPerformed(ActionEvent event){
        //JBUTTONS
        if(event.getSource()==resetButton){             //RESET BUTTON
            System.out.println("RESET BUTTON PRESSED");
            returnMessageField.setText("");
            emptyFields();
        }
        else if(event.getSource()==buyButton){          //BUY BUTTON
            System.out.println("BUY BUTTON PRESSED");
            returnMessageField.setText("");
            System.out.println("Type: " + typeSelect.getSelectedItem());
            System.out.println("Symbol: " + symbolField.getText());
            System.out.println("Name: " + nameField.getText());
            System.out.println("Quantity: " + quantityField.getText());
            System.out.println("Price: " + priceField.getText());
            int typeValidity = investmentType();
            //check validity of possible investment parameters
            if(!buySymbolValidate() || !buyNameValidate() || !buyQuantityValidate() || !buyPriceValidate() || typeValidity == 0){
                returnMessageField.setText(returnMessageField.getText() + "Buy aborted, please address Errors and try again\n");
            }
            //parameters were valid
            else{
                Portfolio.symbol = (String)symbolField.getText().trim();
                String tempPrice = (String)priceField.getText().replace(",", "");
                int buyResult = Portfolio.buy(typeValidity, (String)nameField.getText().trim(), Integer.parseInt((String)quantityField.getText()), Double.parseDouble(tempPrice));
                buyPrintReturnMessage(buyResult);
                emptyFields();
            }
        }
        //JCOMBO BOX
        else if(event.getSource()==typeSelect){
            String temp = (String)typeSelect.getSelectedItem();
            System.out.println("Set to " + temp + " investment type");
        }
    }

    /**
     * Calculates the investment type based on what is selected in the JComboBox.
     * @return Returns a number based on what Investment type was selected at the time of purchase.
     */
    private int investmentType(){
            String tempType = (String)typeSelect.getSelectedItem();
            if(tempType.equalsIgnoreCase("Stock"))    return 1;
            else if(tempType.equalsIgnoreCase("Mutual Fund"))    return 2;
            else return 0;
        }

    //------------------ EXCEPTION HANDLING FOR BUY OPERATION ------------------\\
    /**
     * Checks for exceptions in the entered symbol.
     * @return Returns a boolean representing whether the entered symbol is valid or not.
     */
    private boolean buySymbolValidate(){
        String tempSymbol = (String)symbolField.getText();
        if(tempSymbol.isBlank()){
            returnMessageField.setText(returnMessageField.getText() + "Error: Symbol field cannot be left blank\n");
            return false;
        }
        return true;
    }

    /**
     * Checks for exceptions in the entered name.
     * @return Returns a boolean representing whether the entered name is valid or not.
     */
    private boolean buyNameValidate(){
        String tempName = (String)nameField.getText();
        if(tempName.isBlank()){
            returnMessageField.setText(returnMessageField.getText() + "Error: Name field cannot be left blank\n");
            return false;
        }
        return true;
    }

    /**
     * Checks for exceptions in the entered quantity.
     * @return Returns a boolean representing whether the entered quantity is valid or not.
     */
    private boolean buyQuantityValidate(){
        String tempQuantity = (String)quantityField.getText().replace(",", "");
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
     * Checks for exceptions in the entered price.
     * @return Returns a boolean representing whether the entered price is valid or not.
     */
    private boolean buyPriceValidate(){
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
     * @param buyResult Integer representing the outcome of the buy operation, 1 = successful buy for new Investment, 2 = successful buy for existing Investment, any other means failure.
     */
    private void buyPrintReturnMessage(int buyResult){
        if(buyResult == 1)    returnMessageField.setText(returnMessageField.getText() + "Buy successful for new Investment\n");
        
        else if(buyResult == 2)    returnMessageField.setText(returnMessageField.getText() + "Buy successful for exisitng Investment\n");
        
        else    returnMessageField.setText(returnMessageField.getText() + "Buy aborted, different Investment types cannot have the same symbol\nPlease edit your investment again\n");

    }

    /**
     * clears the editable TextFields in the BuyPanel.
     */
    private void emptyFields(){
        symbolField.setText("");
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }
}
