package bturnert_a3.ePortfolio;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class creates an instance of the ePortfolio program window. Includes the Buy, Sell, Update, GetGain, and Search panels.
 * @author Ben Turner-Theijsmeijer
 * @version 1.0
 */
public class DisplayFrame extends JFrame implements ActionListener{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    public int CHOICE = 0;

    private JMenuBar commandBar;
    private JMenu optionSelect;
    private JMenuItem buy;
    private JMenuItem sell;
    private JMenuItem update;
    private JMenuItem getGain;
    private JMenuItem search;
    private JMenuItem quit;

    private WelcomePanel welcomeScreen;
    private BuyPanel buyScreen;
    private SellPanel sellScreen;
    private UpdatePanel updateScreen;
    private GetGainPanel getGainScreen;
    private SearchPannel searchScreen;


    /**
     * Created a new DisplayFrame (ePortfolio program window) object.
     */
    public DisplayFrame(){
        //--------------------- BASIC PANEL SET UP ---------------------\\
        this.setLayout(new BorderLayout());
        this.setTitle("ePortfolio");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(false);

        //--------------------- MENU STUFF ---------------------\\
        commandBar = new JMenuBar();
        optionSelect = new JMenu("Commands");
        optionSelect.setFont(new Font("Arial",Font.BOLD,15));
        //buy menu parts
        buy = new JMenuItem("Buy");
        buy.addActionListener(this);
        buy.setFont(new Font("Arial",Font.BOLD,15));
        optionSelect.add(buy);
        //sell menu parts
        sell = new JMenuItem("Sell");
        sell.addActionListener(this);
        sell.setFont(new Font("Arial",Font.BOLD,15));
        optionSelect.add(sell);
        //update menu parts
        update = new JMenuItem("Update");
        update.addActionListener(this);
        update.setFont(new Font("Arial",Font.BOLD,15));
        optionSelect.add(update);
        //get gain menu parts
        getGain = new JMenuItem("GetGain");
        getGain.addActionListener(this);
        getGain.setFont(new Font("Arial",Font.BOLD,15));
        optionSelect.add(getGain);
        //search menu parts
        search = new JMenuItem("Search");
        search.addActionListener(this);
        search.setFont(new Font("Arial",Font.BOLD,15));
        optionSelect.add(search);
        //quit menu parts
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        quit.setFont(new Font("Arial",Font.BOLD,15));
        optionSelect.add(quit);

        //--------------------- WINDOW STUFF ---------------------\\
        welcomeScreen = new WelcomePanel();
        buyScreen = new BuyPanel();
        sellScreen = new SellPanel();
        updateScreen = new UpdatePanel();
        getGainScreen = new GetGainPanel();
        searchScreen = new SearchPannel();

        //--------------------- ADDING ELMENTS TO WINDOW ---------------------\\
        commandBar.add(optionSelect);
        setJMenuBar(commandBar);

        this.add(welcomeScreen);
        this.add(buyScreen);
        this.add(sellScreen);
        this.add(updateScreen);
        this.add(getGainScreen);
        this.add(searchScreen);

        //--------------------- BASIC PANEL SET UP PART 2 ---------------------\\
        welcomeScreen.setVisible(true);
        this.setVisible(true);
        
    }

    /**
     * Invoked when an action occurs in the Display frame, or more specifically the Jmenu.
     * @param event Variable that indicates if a component defined action has occured.
     */
    @Override
    public void actionPerformed(ActionEvent event){
        //--------------------- DISPLAY BUY WINDOW ---------------------\\
        if(event.getSource()==buy){
            System.out.println("BUY ACTIVE");
            welcomeScreen.setVisible(false);
            buyScreen.setVisible(true);
            sellScreen.setVisible(false);
            updateScreen.setVisible(false);
            getGainScreen.setVisible(false);
            searchScreen.setVisible(false);
            CHOICE = 1;
            buyScreen.typeSelect.setSelectedIndex(0);
        }
        //--------------------- DISPLAY SELL WINDOW ---------------------\\
        else if(event.getSource()==sell){
            System.out.println("SELL ACTIVE");
            welcomeScreen.setVisible(false);
            buyScreen.setVisible(false);
            sellScreen.setVisible(true);
            updateScreen.setVisible(false);
            getGainScreen.setVisible(false);
            searchScreen.setVisible(false);
            CHOICE = 2;
            if(Portfolio.investmentsIsEmpty() == true){
                sellScreen.sellButton.setEnabled(false);
                sellScreen.returnMessageField.setText("Oops: Cannot sell any investments at this time\n          As no investments are currently in the Portfolio\n");
            }
            else{
                sellScreen.sellButton.setEnabled(true);
                sellScreen.returnMessageField.setText("");
            }
        }
        //--------------------- DISPLAY UPDATE WINDOW ---------------------\\
        else if(event.getSource()==update){
            System.out.println("UPDATE ACTIVE");
            welcomeScreen.setVisible(false);
            buyScreen.setVisible(false);
            sellScreen.setVisible(false);
            updateScreen.setVisible(true);
            getGainScreen.setVisible(false);
            searchScreen.setVisible(false);
            CHOICE = 3;
            updateScreen.index = 0;
            updateScreen.size = Portfolio.InvestmentSize();
            updateScreen.previousButton.setEnabled(false);
            if(Portfolio.investmentsIsEmpty() == true){
                updateScreen.nextButton.setEnabled(false);
                updateScreen.returnMessageField.setText("Oops: Cannot update any investments at this time\n          As no investments are currently in the Portfolio\n");
            }
            else{
                if(Portfolio.InvestmentSize() == 1) updateScreen.nextButton.setEnabled(false);
                else updateScreen.nextButton.setEnabled(true);
                updateScreen.returnMessageField.setText("Selected Investment Information:\n" + Portfolio.specificElement(0));
                updateScreen.symbolField.setText(Portfolio.symbolofSpecificElement(0));
                updateScreen.nameField.setText(Portfolio.nameOfSpecificElement(0));
                updateScreen.priceField.setText("" + Portfolio.priceOfSpecificElement(0));
            }
        }
        //--------------------- DISPLAY GETGAIN WINDOW ---------------------\\
        else if(event.getSource()==getGain){
            System.out.println("GETGAIN ACTIVE");
            welcomeScreen.setVisible(false);
            buyScreen.setVisible(false);
            sellScreen.setVisible(false);
            updateScreen.setVisible(false);
            getGainScreen.setVisible(true);
            searchScreen.setVisible(false);
            CHOICE = 4;
            if(Portfolio.investmentsIsEmpty() == true){
                getGainScreen.returnMessageField.setText("Oops: Cannot get gain on investments at this time\n          As no investments are currently in the Portfolio\n");
            }
            else{
                getGainScreen.getTotalGain();
            }
        }
        //--------------------- DISPLAY SEARCH WINDOW ---------------------\\
        else if(event.getSource()==search){
            System.out.println("SEARCH ACTIVE");
            welcomeScreen.setVisible(false);
            buyScreen.setVisible(false);
            sellScreen.setVisible(false);
            updateScreen.setVisible(false);
            getGainScreen.setVisible(false);
            searchScreen.setVisible(true);
            CHOICE = 5;
            if(Portfolio.investmentsIsEmpty() == true){
                searchScreen.searchButton.setEnabled(false);
                searchScreen.returnMessageField.setText("Oops: Cannot search for any investments at this time\n          As no investments are currently in the Portfolio\n");
            }
            else{
                searchScreen.searchButton.setEnabled(true);
            }
        }
        //--------------------- QUIT ---------------------\\
        else if(event.getSource()==quit){
            System.out.println("QUIT ACTIVE");
            CHOICE = 6;
            Portfolio.writeToFile();
            System.out.println("CLOSING PROGRAM");
            this.setVisible(false);
            this.dispose();
        }
        else{
            System.out.println("UNDEFINED ACTION OCCURED");
        }

    }
}
