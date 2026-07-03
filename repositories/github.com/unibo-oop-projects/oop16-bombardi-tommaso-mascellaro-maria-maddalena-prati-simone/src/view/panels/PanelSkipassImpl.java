package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.admin.products.Season;
import model.admin.products.Skipass;
import view.panels.interfaces.PanelSkipass;
/**
 * 
 * panel skipass class.
 *
 */
public class PanelSkipassImpl extends JPanel implements PanelSkipass {

    private static final long serialVersionUID = 1L;
    private final JLabel labPrice;
    private final JComboBox<String> listDays;
    private final JComboBox<String> listSeason;
    private final JTextField quantity;
    private final JButton btnRetMenu;
    private final JButton btnCart;
    //COLORE
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;

    private static final int BOR = 20;
    private static final int LAB = FrameSize.WIDTH.getValue() / 40;
    private static final int LAB1 = FrameSize.WIDTH.getValue() / 25;
    private static final String MYFONT = "Tahoma";
    private static final int GAP = FrameSize.WIDTH.getValue() / 45;
    private static final int ROWS = 6;
    private static final int COL = 2;

    private void resetComp() {
        labPrice.setText("");
        quantity.setText("1");
        listDays.setSelectedIndex(0);
        listSeason.setSelectedIndex(0);
    }
    /**
     * panel skipass constructor.
     */
    public PanelSkipassImpl() {
        super();
        final Controller ci = Controller.getController();
        final JPanel pn = new JPanel();
        final JPanel pc = new JPanel();
        final JPanel ps = new JPanel();

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));
        this.add(pn, BorderLayout.NORTH);
        this.add(pc, BorderLayout.CENTER);
        this.add(ps, BorderLayout.SOUTH);
        pc.setLayout(new GridLayout(ROWS, COL, GAP, GAP));

        final JLabel title = new JLabel("Acquista lo skipass per la tua vacanza");
        title.setFont(new Font(MYFONT, Font.PLAIN, LAB1));
        title.setForeground(Color.RED);
        pn.add(title);

        final JLabel emp = new JLabel("");
        pc.add(emp);
        final JLabel emp1 = new JLabel("");
        pc.add(emp1);
        //GIORNI
        final JLabel days = new JLabel("Scegli la durata");
        pc.add(days);
        this.listDays = new JComboBox<String>();
        for (final Skipass elem : ci.getSkipass()) {
            this.listDays.addItem(elem.getDescription());
        }
        pc.add(listDays);
        //STAGIONE
        final JLabel season = new JLabel("Scegli il periodo");
        pc.add(season);
        this.listSeason = new JComboBox<String>();
        for (final Season elem : ci.getSeasons()) {
            this.listSeason.addItem(elem.getPeriod());
        }
        pc.add(listSeason);
        final JLabel number = new JLabel("Numero di skipass (1-10)");
        pc.add(number);
        this.quantity = new JTextField("1");
        pc.add(quantity);
        final JLabel emp2 = new JLabel("");
        pc.add(emp2);
        this.labPrice = new JLabel();
        this.labPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB1));
        pc.add(labPrice);
        final JButton btnAddCart = new JButton("Aggiungi al carrello");
        btnAddCart.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnAddCart);
        btnAddCart.addActionListener(e-> {
                final Season s = ci.getSeason(this.listSeason.getSelectedItem().toString()).get();
                final Skipass sk = ci.getSkipass(this.listDays.getSelectedItem().toString()).get();
                try {
                    ci.addSkipass(sk, this.quantity.getText(), s);
                    JOptionPane.showMessageDialog(null, "Skipass aggiunto al carrello");
                } catch (IllegalArgumentException exc) {
                    JOptionPane.showMessageDialog(null, "Il numero di skipass deve essere compreso tra 1 e 10");
                }
                resetComp();
        });
        final JButton btnPrice = new JButton("Calcola prezzo");
        btnPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnPrice);

        btnPrice.addActionListener(e-> {
                boolean exc = false;
                String price = null;
                final String num = quantity.getText();
                final Skipass sk = ci.getSkipass(listDays.getSelectedItem().toString()).get();
                final Season s = ci.getSeason(listSeason.getSelectedItem().toString()).get();
                try {
                    price = ci.getSkipassPrice(sk, num, s);
                } catch (IllegalArgumentException exception) {
                    exc = true;
                    JOptionPane.showMessageDialog(null, "Il numero di skipass deve essere compreso tra 1 e 10");
                    resetComp();
                }
                if (!exc) {
                    labPrice.setText(" Prezzo = " + price + " Euro");
                }
        });

        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnRetMenu);
        this.btnRetMenu.addActionListener(e-> {
                resetComp();
        });
        final ImageIcon imgCart = new ImageIcon(getClass().getResource("/carrello.gif"));
        this.btnCart = new JButton(imgCart);
        final Image scaledImage = imgCart.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / 12), (int) (FrameSize.HEIGHT.getValue() / 10), Image.SCALE_DEFAULT);

        imgCart.setImage(scaledImage);
        ps.add(btnCart);
        btnCart.setOpaque(false);
        btnCart.setContentAreaFilled(false);
        btnCart.setBorderPainted(false);
        this.btnCart.addActionListener(e-> {
                resetComp();
        });
        final Color bluette = new Color(C1, C2, C3);
        this.setBackground(bluette);
        pn.setBackground(bluette);
        pc.setBackground(bluette);
        ps.setBackground(bluette);
        this.setVisible(false);
    }
    @Override
    public JPanel getPanelSkipass() {
        return this;
    }
    @Override
    public JButton getBtnPrev() {
        return this.btnRetMenu;
    }
    @Override
    public JButton getBtnCart() {
        return this.btnCart;
    }
}
