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
 * This class creates an instance of the UpdatePanel.
 * @author Ben Turner-Theijsmeijer
 * @version 1.0
 */
public class UpdatePanel extends JPanel implements ActionListener{

    public JButton previousButton;
    public JButton nextButton;
    private JButton saveButton;

    public JTextField symbolField;
    public JTextField nameField;
    public JTextField priceField;
    public JTextArea returnMessageField;
    private JScrollPane scrollableMessagePane;

    public int index = 0;
    public int size = 0;
    
    /**
     * Created a new UpdatePanel object.
     */
    public UpdatePanel(){
        //------------------ BASIC PANEL SET UP ------------------\\
        this.setSize(DisplayFrame.WIDTH,DisplayFrame.HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // this.setBackground(new Color(0x60A642));
        this.setVisible(false);

        //===================== FIELDS PANEL COMPONENTS START =====================\\
        //------------------ JLABELS FOR UPDATE PANEL ------------------\\
        JLabel updateText = new JLabel("Updating Investments:");
        updateText.setFont(new Font("Arial",Font.BOLD,17));
        
        JLabel symbolText = new JLabel("Symbol");
        symbolText.setBounds(60,30,100,30);
        symbolText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel nameText = new JLabel("Name");
        nameText.setBounds(60,90,100,30);
        nameText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel priceText = new JLabel("Price");
        priceText.setBounds(60,170,100,30);
        priceText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel messageText = new JLabel("Messages:");
        messageText.setFont(new Font("Arial",Font.BOLD,17));

        //------------------ TEXTFIELDS FOR UPDATE PANEL ------------------\\
        symbolField = new JTextField();
        symbolField.setFont(new Font("Arial",Font.PLAIN,17));
        symbolField.setBounds((DisplayFrame.WIDTH/12)*3,30,160,30);
        symbolField.setEditable(false);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial",Font.PLAIN,17));
        nameField.setBounds((DisplayFrame.WIDTH/12)*3,100,230,30);
        nameField.setEditable(false);


        priceField = new JTextField();
        priceField.setFont(new Font("Arial",Font.PLAIN,17));
        priceField.setBounds((DisplayFrame.WIDTH/12)*3,170,100,30);
        //===================== FIELDS PANEL COMPONENTS EMD =====================\\

        //===================== BUTTONS PANEL COMPONENTS START =====================\\
        //------------------ JBUTTONS FOR UPDATE PANEL ------------------\\
        previousButton = new JButton();
        previousButton.setBounds(DisplayFrame.WIDTH/12,(DisplayFrame.HEIGHT/10),80,30);
        previousButton.setFont(new Font("Arial",Font.PLAIN,17));
        previousButton.setText("Prev");
        previousButton.setFocusable(false);
        previousButton.addActionListener(this);

        nextButton = new JButton();
        nextButton.setBounds(DisplayFrame.WIDTH/12,(DisplayFrame.HEIGHT/10)*2,80,30);
        nextButton.setFont(new Font("Arial",Font.PLAIN,17));
        nextButton.setText("Next");
        nextButton.setFocusable(false);
        nextButton.addActionListener(this);

        saveButton = new JButton();
        saveButton.setBounds(DisplayFrame.WIDTH/12,(DisplayFrame.HEIGHT/10)*3,80,30);
        saveButton.setFont(new Font("Arial",Font.PLAIN,17));
        saveButton.setText("Save");
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);
        //===================== BUTTONS PANEL COMPONENTS END =====================\\

        //===================== LOWER PANEL COMPONENTS START =====================\\
        //------------------ TEXTAREA AND SCROLL BARS FOR UPDATE PANEL ------------------\\
        returnMessageField = new JTextArea(7,40);
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
        buttonHalf.add(previousButton);
        buttonHalf.add(nextButton);
        buttonHalf.add(saveButton);

        fieldsHalf.add(symbolText);
        fieldsHalf.add(nameText);
        fieldsHalf.add(priceText);
        fieldsHalf.add(symbolField);
        fieldsHalf.add(nameField);
        fieldsHalf.add(priceField);

        topHalf.add(fieldsHalf);
        topHalf.add(buttonHalf);
        lowerHalf.add(scrollableMessagePane);
        
        //------------------ ADDING SUBPANELS TO PANEL ------------------\\
        this.add(updateText);
        this.add(topHalf);
        this.add(messageText);
        this.add(lowerHalf);

    }

    //------------------ ACTION LSITENER FOR UPDATE PANEL ------------------\\
    /**
     * Invoked when an action occurs in the UpdatePanel.
     * @param event Variable that indicates if a component defined action has occured.
     */
    @Override
    public void actionPerformed(ActionEvent event){
        //JBUTTONS
        if(event.getSource()==previousButton){
            System.out.println("PREVIOUS BUTTON PRESSED");
            size = Portfolio.InvestmentSize() - 1;
            if(index <= 0)    previousButton.setEnabled(false);
            else{
                index--;
                if(index <= 0)    previousButton.setEnabled(false);
                updateInvestmentFields();
                nextButton.setEnabled(true);
            }
        }
        else if(event.getSource()==nextButton){
            System.out.println("NEXT BUTTON PRESSED");
            size = Portfolio.InvestmentSize() - 1;
            if(index >= size)   nextButton.setEnabled(false);
            else{
                index++;
                if(index >= size)   nextButton.setEnabled(false);
                updateInvestmentFields();
                previousButton.setEnabled(true);
            }
        }
        else if(event.getSource()==saveButton){
            System.out.println("SAVE BUTTON PRESSED");
            if(!updatePriceValidate()){
                returnMessageField.setText(returnMessageField.getText() + "Update aborted, please address Errors and try again\n");
            }
            else{
                int updateResult = Portfolio.update(index, Double.parseDouble((String)priceField.getText()));
                updatePrintReturnMessage(updateResult);
            }
        }
    }

    //------------------ EXCEPTION HANDLING FOR UPDATE OPERATION ------------------\\
    /**
     * Checks for exceptions in the entered Price.
     * @return Returns a boolean representing whether the entered price range is valid or not.
     */
    private boolean updatePriceValidate(){
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
     * @param updateResult Integer representing the outcome of the update operation, 1 = successful for Stock Investment, 2 = successful for MutualFund Investment, any other means failure.
     */
    private void updatePrintReturnMessage(int updateResult){
        if(updateResult == 1){
            returnMessageField.setText("Updated Stock Investment's price to $" + Portfolio.priceOfSpecificElement(index) + "\n");
            returnMessageField.setText(returnMessageField.getText() + Portfolio.specificElement(index));
        }
        else if(updateResult == 2){
            returnMessageField.setText("Updated MutualFund Investment price to $" + Portfolio.priceOfSpecificElement(index) + "\n");
            returnMessageField.setText(returnMessageField.getText() + Portfolio.specificElement(index));
        } 
        else returnMessageField.setText(returnMessageField.getText() + "Update aborted, Nothing to sell\n");
    }

    /**
     * Updates the Fields in the UpdatePanel to match the data for the currently selected Investment based on the index.
     */
    private void updateInvestmentFields(){
        symbolField.setText(Portfolio.symbolofSpecificElement(index));
        nameField.setText(Portfolio.nameOfSpecificElement(index));
        priceField.setText("" + Portfolio.priceOfSpecificElement(index));
        returnMessageField.setText("Selected Investment Information:\n" + Portfolio.specificElement(index));
        System.out.println("current index = " + index);
    }
}
