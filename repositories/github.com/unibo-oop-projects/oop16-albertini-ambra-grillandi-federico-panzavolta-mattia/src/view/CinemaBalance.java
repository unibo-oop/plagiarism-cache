package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *This is the GUI for view the cinema's balance.
 */
public class CinemaBalance extends JDialog {

    private static final long serialVersionUID = -2585360265374396684L;

    private static final double HEIGHT_PERC = 0.15;
    private static final double WIDTH_PERC = 0.5;
    private static final int COLS = 3;
    private static final int ROWS = 1;
    private static final String TITLE = "Cinema Balance";

    private Double balance;
    private Double boxOffice;
    private Double cinemaExpanse;

    /**
     * Create the JDialog for view the cinema balance.
     */
    public CinemaBalance() {
        super();

        //Set dimension of the window
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));

        //Set layout and add components to it
        this.setBalanceVariable();
        this.getContentPane().setLayout(new GridLayout(ROWS, COLS));
        this.getContentPane().add(new JLabel("Balance: " + this.balance));
        this.getContentPane().add(new JLabel("BoxOffice: " + this.boxOffice));
        this.getContentPane().add(new JLabel("CinemaExpanse: " + this.cinemaExpanse));

        //Fix Setting of this JDialog
        this.setTitle(TITLE);
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method set the value of balance variables.
     */
    private void setBalanceVariable() {
        this.balance = ViewImpl.getController().totBalance();
        this.boxOffice = ViewImpl.getController().cinemaBoxOffice();
        this.cinemaExpanse = ViewImpl.getController().cinemaExpense();
    }
}
