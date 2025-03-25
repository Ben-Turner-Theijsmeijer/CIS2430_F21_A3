package bturnert_a3.ePortfolio;

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
 * This class creates an instance of the SearchPanel.
 * @author Ben Turner-Theijsmeijer
 * @version 1.0
 */
public class SearchPannel extends JPanel implements ActionListener{
    
    private JButton resetButton;
    public JButton searchButton;

    private JTextField symbolField;
    private JTextField keywordField;
    private JTextField lowPriceField;
    private JTextField highPriceField;
    public JTextArea returnMessageField;
    private JScrollPane scrollableMessagePane;

    /**
     * Created a new SearchPannel object.
     */
    public SearchPannel(){
        //------------------ BASIC PANEL SET UP ------------------\\
        this.setSize(DisplayFrame.WIDTH,DisplayFrame.HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(false);

        //===================== FIELDS PANEL COMPONENTS START =====================\\
        //------------------ JLABELS FOR SEARCH PANEL ------------------\\
        JLabel searchText = new JLabel("Searching Investments:");
        searchText.setFont(new Font("Arial",Font.BOLD,17));
        
        JLabel symbolText = new JLabel("Symbol");
        symbolText.setBounds(60,15,100,30);
        symbolText.setFont(new Font("Arial",Font.PLAIN,17));

        JLabel keywordsText = new JLabel("<html>"+ "Name <br> Keywords" + "<html>");
        keywordsText.setBounds(60,52,100,40);
        keywordsText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel lowRangeText = new JLabel("Low Price");
        lowRangeText.setBounds(60,105,100,30);
        lowRangeText.setFont(new Font("Arial",Font.PLAIN,17));

        JLabel highRangeText = new JLabel("High Price");
        highRangeText.setBounds(60,150,100,30);
        highRangeText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel messageText = new JLabel("Search Results:");
        messageText.setFont(new Font("Arial",Font.BOLD,17));

        //------------------ TEXTFIELDS FOR SELL PANEL ------------------\\
        symbolField = new JTextField();
        symbolField.setFont(new Font("Arial",Font.PLAIN,17));
        symbolField.setBounds((DisplayFrame.WIDTH/12)*3,15,160,30);

        keywordField = new JTextField();
        keywordField.setFont(new Font("Arial",Font.PLAIN,17));
        keywordField.setBounds((DisplayFrame.WIDTH/12)*3,60,230,30);

        lowPriceField = new JTextField();
        lowPriceField.setFont(new Font("Arial",Font.PLAIN,17));
        lowPriceField.setBounds((DisplayFrame.WIDTH/12)*3,105,100,30);

        highPriceField = new JTextField();
        highPriceField.setFont(new Font("Arial",Font.PLAIN,17));
        highPriceField.setBounds((DisplayFrame.WIDTH/12)*3,150,100,30);
        //===================== FIELDS PANEL COMPONENTS EMD =====================\\

        //===================== BUTTONS PANEL COMPONENTS START =====================\\
        //------------------ JBUTTONS FOR BUY PANEL -----------------\\
        resetButton = new JButton();
        resetButton.setBounds(DisplayFrame.WIDTH/12,DisplayFrame.HEIGHT/8,90,30);
        resetButton.setFont(new Font("Arial",Font.PLAIN,17));
        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        searchButton = new JButton();
        searchButton.setBounds(DisplayFrame.WIDTH/12,DisplayFrame.HEIGHT/4,90,30);
        searchButton.setFont(new Font("Arial",Font.PLAIN,17));
        searchButton.setText("Search");
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        //===================== BUTTONS PANEL COMPONENTS END =====================\\

        //===================== LOWER PANEL COMPONENTS START =====================\\
        //------------------ TEXTAREA AND SCROLL BARS FOR UPDATE PANEL ------------------\\
        returnMessageField = new JTextArea(8,40);
        returnMessageField.setBounds(50,30,(DisplayFrame.WIDTH/6)*5,10);
        returnMessageField.setFont(new Font("Arial",Font.PLAIN,17));

        scrollableMessagePane = new JScrollPane(returnMessageField);
        scrollableMessagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableMessagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //===================== LOWER PANEL COMPONENTS END =====================\\

        //------------------ SUBPANELS FOR UPDATE PANEL ------------------\\
        JPanel fieldsHalf = new JPanel(null);
        fieldsHalf.setBounds(0,0,(DisplayFrame.WIDTH/6)*4,(DisplayFrame.HEIGHT/10)*9);

        JPanel buttonHalf = new JPanel(null);
        buttonHalf.setBounds((DisplayFrame.WIDTH/6)*4,0,(DisplayFrame.WIDTH/6)*2,(DisplayFrame.HEIGHT/10)*9);

        JPanel topHalf = new JPanel(null);

        JPanel lowerHalf = new JPanel();
        lowerHalf.setPreferredSize(new Dimension(DisplayFrame.WIDTH, 0));

        //------------------ ADDING ELEMENTS TO SUB PANELS  ------------------\\
        buttonHalf.add(resetButton);
        buttonHalf.add(searchButton);

        fieldsHalf.add(symbolText);
        fieldsHalf.add(keywordsText);
        fieldsHalf.add(lowRangeText);
        fieldsHalf.add(highRangeText);
        fieldsHalf.add(symbolField);
        fieldsHalf.add(keywordField);
        fieldsHalf.add(lowPriceField);
        fieldsHalf.add(highPriceField);

        topHalf.add(fieldsHalf);
        topHalf.add(buttonHalf);
        lowerHalf.add(scrollableMessagePane);
        
        //------------------ ADDING SUBPANELS TO PANEL ------------------\\
        this.add(searchText);
        this.add(topHalf);
        this.add(messageText);
        this.add(lowerHalf);
    }

    //------------------ ACTION LISTENER FOR UPDATE PANEL ------------------\\
    /**
     * Invoked when an action occurs in the SearchPannel.
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
        else if(event.getSource()==searchButton){
            System.out.println("SEARCH BUTTON PRESSED");
            System.out.println("Symbol: " + symbolField.getText());
            System.out.println("name Keywords: " + keywordField.getText());
            System.out.println("Low Price: " + lowPriceField.getText());
            System.out.println("High Price: " + highPriceField.getText());
            searching();
        }
    }

    /**
     * Checks for exceptions in the entered Low Price Range.
     * @return Returns a boolean representing whether the entered price range is valid or not.
     */
    private boolean searchLowerRangeValidate(){
        String tempLower = (String)lowPriceField.getText();
        if(!tempLower.matches("[0-9.]+")){
            if(tempLower.isBlank()) return true;
            returnMessageField.setText(returnMessageField.getText() + "Error: Lower Price field can only contain Rational Numbers, and cannot be negative\n");
            return false;
        }
        return true;
    }

    /**
     * Checks for exceptions in the entered Upper Price Range.
     * @return Returns a boolean representing whether the entered price range is valid or not.
     */
    private boolean searchUpperRangeValidate(){
        String tempUpper = (String)highPriceField.getText();
        if(!tempUpper.matches("[0-9.]+")){
            if(tempUpper.isBlank()) return true;
            returnMessageField.setText(returnMessageField.getText() + "Error: High Price field can only contain Rational Numbers, and cannot be negative\n");
            return false;
        }
        return true;
    }

    //------------------ SEARCH FUNCTION ------------------\\
    /**
     * Searches all investments currently in the ArrayList and displays those that meet the search criteria.
     * <p>
     * Updates the message Field in the SearchPanel to match the search parameters.
     */
    public void searching(){
        int index = 0;
        int size = Portfolio.InvestmentSize() - 1;
        returnMessageField.setText("");
        boolean searchResult = false;
        String searchRange = "";
        if(!searchLowerRangeValidate() || !searchUpperRangeValidate()){
            returnMessageField.setText("Search aborted, please address Errors and try again\n");
        }
        else{
            searchRange = createRange().trim();
            for(index = 0; index <= size; index++){
                searchResult = Portfolio.search(index, (String)symbolField.getText(), (String)keywordField.getText(), searchRange);
                if(searchResult){
                    returnMessageField.setText(returnMessageField.getText() + "Investment's Information:\n" + Portfolio.specificElement(index) + "\n");
                }
            }
        }
        
    }

    //------------------ RANGE CREATION FUNTION ------------------\\
    /**
     * Created the price range String for passing to the search function in ePortfolio.
     * @return Returns a String representing the combined lower and upper ranges of the search.
     */
    private String createRange(){
        String tempLower = (String)lowPriceField.getText();
        String tempUpper = (String)highPriceField.getText();
        String returnString = "";
        if(tempLower.isBlank() && tempUpper.isBlank()){
            return returnString;
        }
        else if(tempLower.isBlank() && tempUpper.matches("[0-9.]+")){
            returnString = returnString.concat("-");
            returnString = returnString.concat(tempUpper);
            return returnString;
        }
        else if(tempLower.matches("[0-9.]+") && tempUpper.isBlank()){
            returnString = returnString.concat(tempLower);
            returnString = returnString.concat("-");
            return returnString;
        }
        else if(tempLower.matches("[0-9.]+") && tempUpper.matches("[0-9.]+")){
            returnString = returnString.concat(tempLower);
            returnString = returnString.concat("-");
            returnString = returnString.concat(tempUpper);
            return returnString;
        }
        else{
            return "";
        }
    }
    //------------------ TEXT FIELD UPDATING FUNCTION ------------------\\
    /**
     * clears the editable TextFields in the SearchPanel.
     */
    private void emptyFields(){
        symbolField.setText("");
        keywordField.setText("");
        lowPriceField.setText("");
        highPriceField.setText("");
    }
}
