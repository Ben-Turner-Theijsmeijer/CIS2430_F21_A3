package bturnert_a3.ePortfolio;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

/**
 * This class creates an instance of the WelcomePanel.
 * @author Ben Turner-Theijsmeijer
 * @version 1.0
 */
public class WelcomePanel extends JPanel{
    
    /**
     * Created a new WelcomePanel object.
     */
    public WelcomePanel(){
        //--------------------- BASIC PANEL SET UP ---------------------\\
        this.setSize(DisplayFrame.WIDTH,DisplayFrame.HEIGHT);
        this.setLayout(new BorderLayout());
        // this.setBackground(new Color(0x908CCF));
        this.setVisible(false);

        //--------------------- BUFFERS FOR PROPER ALIGNMENT ---------------------\\
        JPanel leftBuffer = new JPanel();
        // leftBuffer.setBackground(new Color(0x908CCF));
        leftBuffer.setPreferredSize(new Dimension(30,100));
        JPanel rightBuffer = new JPanel();
        // rightBuffer.setBackground(new Color(0x908CCF));
        rightBuffer.setPreferredSize(new Dimension(30,100));
        JPanel bottomBuffer = new JPanel();
        // bottomBuffer.setBackground(new Color(0x908CCF));
        bottomBuffer.setPreferredSize(new Dimension(100,150));

        //--------------------- WELCOME MESSAGE ---------------------\\
        JLabel welcomeMessage = new JLabel("<html>"+"Welcome to ePortfolio.<br><br><br><br>"+"<html>"+"Choose a command from the \"Commands\" menu to buy or sell an investment, update prices for all investments, get gain for the portoflio, search for relevant investments, or quit the program"+"<html>");
        welcomeMessage.setFont(new Font("Arial",Font.BOLD,17));

        //--------------------- WECOMEPANEL REGION LINKING ---------------------\\
        this.add(leftBuffer,BorderLayout.WEST);
        this.add(rightBuffer,BorderLayout.EAST);
        this.add(bottomBuffer,BorderLayout.SOUTH);
        this.add(welcomeMessage,BorderLayout.CENTER);
    }
}
