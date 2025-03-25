package bturnert_a3.ePortfolio;

import java.text.DecimalFormat;

import java.awt.Font;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * This class creates an instance of the GetGainPanel.
 * @author Ben Turner-Theijsmeijer
 * @version 1.0
 */
public class GetGainPanel extends JPanel{

    private double totalGain;
    private JTextField totalField;
    public JTextArea returnMessageField;
    private JScrollPane scrollableMessagePane;
    
    /**
     * Created a new GetGainPanel object.
     */
    public GetGainPanel(){
        //------------------ BASIC PANEL SET UP ------------------\\
        this.setSize(DisplayFrame.WIDTH,DisplayFrame.HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(false);

        //===================== FIELDS PANEL COMPONENTS START =====================\\
        //------------------ JLABELS FOR GAINS PANEL ------------------\\
        JLabel getGainText = new JLabel("Getting total Gain:");
        getGainText.setFont(new Font("Arial",Font.BOLD,17));
        
        JLabel totalText = new JLabel("Total Gain");
        totalText.setBounds(60,30,100,30);
        totalText.setFont(new Font("Arial",Font.PLAIN,17));
        
        JLabel messageText = new JLabel("Individual Gains:");
        messageText.setFont(new Font("Arial",Font.BOLD,17));

        //------------------ TEXTFIELDS FOR GAINS PANEL ------------------\\
        totalField = new JTextField();
        totalField.setFont(new Font("Arial",Font.PLAIN,17));
        totalField.setBounds((DisplayFrame.WIDTH/12)*3,30,160,30);
        totalField.setEditable(false);
        //===================== FIELDS PANEL COMPONENTS EMD =====================\\

        //===================== LOWER PANEL COMPONENTS START =====================\\
        //------------------ TEXTAREA AND SCROLL BARS FOR GAINS PANEL ------------------\\
        returnMessageField = new JTextArea(14,40);
        returnMessageField.setBounds(50,30,(DisplayFrame.WIDTH/6)*5,10);
        returnMessageField.setFont(new Font("Arial",Font.PLAIN,17));

        scrollableMessagePane = new JScrollPane(returnMessageField);
        scrollableMessagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableMessagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //===================== LOWER PANEL COMPONENTS END =====================\\

        //------------------ SUBPANELS FOR GAINS PANEL ------------------\\
        JPanel fieldsHalf = new JPanel(null);
        fieldsHalf.setBounds(0,0,DisplayFrame.WIDTH,(DisplayFrame.HEIGHT/10)*9);

        JPanel topHalf = new JPanel(null);

        JPanel lowerHalf = new JPanel();
        lowerHalf.setPreferredSize(new Dimension(DisplayFrame.WIDTH, (DisplayFrame.HEIGHT/5)*3));

        //------------------ ADDING ELEMENTS TO SUB PANELS  ------------------\\
        fieldsHalf.add(totalText);
        fieldsHalf.add(totalField);

        topHalf.add(fieldsHalf);
        lowerHalf.add(scrollableMessagePane);
        
        //------------------ ADDING SUBPANELS TO PANEL ------------------\\
        this.add(getGainText);
        this.add(topHalf);
        this.add(messageText);
        this.add(lowerHalf);

    }

    //------------------ TEXT FIELD UPDATING FUNCTIONS / GAIN CLACULATION ------------------\\
    /**
     * Calculates the gain for theoretically selling all currently owned Investments at their current prices.
     * <p>
     * Updates the Fields in the GetGainPanel to match the data for all currently owned Investments.
     */
    public void getTotalGain(){
        int index = 0;
        int size = Portfolio.InvestmentSize() - 1;
        returnMessageField.setText("");
        totalGain = 0;
        DecimalFormat twoDecimal = new DecimalFormat();
        twoDecimal.setMaximumFractionDigits(2);

        for(index = 0; index <= size; index++){
            Portfolio.getGain(index);
            returnMessageField.setText(returnMessageField.getText() + "Investment's Information:\n" + Portfolio.specificElement(index) + "Gain on Investment = $" + twoDecimal.format(Portfolio.currentInvestmentGain) + "\n");
            totalGain += Portfolio.currentInvestmentGain;
        }
        totalField.setText(" $" + twoDecimal.format(totalGain));
    }


}
