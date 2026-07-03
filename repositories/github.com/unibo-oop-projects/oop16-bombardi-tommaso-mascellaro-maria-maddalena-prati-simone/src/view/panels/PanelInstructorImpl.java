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
import model.admin.products.Instructor;
import model.admin.products.Season;
import view.panels.interfaces.PanelInstructor;
/**
 * 
 * panel instructor class. 
 */
public class PanelInstructorImpl extends JPanel implements PanelInstructor {
    private static final long serialVersionUID = 1L;
    private final JLabel labPrice;
    private final JComboBox<String> listHours;
    private final JComboBox<String> listSeason;
    private final JTextField numPerson;
    private final JButton btnRetMenu;
    private final JButton btnCart;
    //COLORE
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;

    private static final int BOR = 20;
    private static final int LAB = FrameSize.WIDTH.getValue() / 40;
    private static final int LAB1 = FrameSize.WIDTH.getValue() / 25;
    private static final int LAB2 = FrameSize.WIDTH.getValue() / 35;
    private static final int GAP = FrameSize.WIDTH.getValue() / 45;
    private static final int ROWS = 6;
    private static final int COL = 2;
    private static final String MYFONT = "Tahoma";

    private void resetComp() {
        listSeason.setSelectedIndex(0);
        numPerson.setText("1");
        listHours.setSelectedIndex(0);
        labPrice.setText("");
    }

    /**
     * panel instructor constructor.
     */
    public PanelInstructorImpl() {
        super();
        final Controller ci = Controller.getController();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));
        final JPanel pn = new JPanel();
        final JPanel pc = new JPanel();
        final JPanel ps = new JPanel();
        this.add(pn, BorderLayout.NORTH);
        this.add(pc, BorderLayout.CENTER);
        this.add(ps, BorderLayout.SOUTH);
        pc.setLayout(new GridLayout(ROWS, COL, GAP, GAP));

        //TITOLO
        final JLabel title = new JLabel("Scegli il maestro per la tua lezione di sci o snowboard");
        title.setFont(new Font(MYFONT, Font.PLAIN, LAB1));
        title.setForeground(Color.RED);
        pn.add(title);
        //ORARI LEZIONI
        final JLabel emp = new JLabel("");
        pc.add(emp);
        final JLabel emp1 = new JLabel("");
        pc.add(emp1);
        final JLabel hours = new JLabel("Scegli il tipo di lezione");
        hours.setFont(new Font(MYFONT, Font.PLAIN, LAB2));
        pc.add(hours);
        this.listHours = new JComboBox<String>();
        for (final Instructor elem : ci.getInstructors()) {
            this.listHours.addItem(elem.getDescription());
        }
        pc.add(listHours);
        //STAGIONE
        final JLabel season = new JLabel("Scegli il periodo");
        season.setFont(new Font(MYFONT, Font.PLAIN, LAB2));
        pc.add(season);
        this.listSeason = new JComboBox<String>();
        for (final Season elem : ci.getSeasons()) {
            this.listSeason.addItem(elem.getPeriod());
        }
        pc.add(listSeason);
        //NUMERO PARTECIPANTI
        final JLabel person = new JLabel("Numero di partecipanti (1-4)");
        person.setFont(new Font(MYFONT, Font.PLAIN, LAB2));
        pc.add(person);
        this.numPerson = new JTextField("1");
        pc.add(numPerson);
        //AGGIUNGI AL CARRELLO
        final JButton btnAddCart = new JButton("Aggiungi al carrello");
        btnAddCart.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnAddCart);
        btnAddCart.addActionListener(e-> {
            final Instructor ore = ci.getInstructor(this.listHours.getSelectedItem().toString()).get();
            final Season s = ci.getSeason(this.listSeason.getSelectedItem().toString()).get();
            try {
                ci.addInstructor(ore, this.numPerson.getText(), s);
                JOptionPane.showMessageDialog(null, "Lezione aggiunta al carrello");
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(null, "Il numero di allievi deve essere compreso tra 1 e 4");
            }
            resetComp();
        });
        //PREZZO
        final JLabel emp2 = new JLabel("");
        pc.add(emp2);
        this.labPrice = new JLabel();
        this.labPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB1));
        pc.add(labPrice);
        //BOTTONE CALCOLA PREZZO
        final JButton btnPrice = new JButton("Calcola prezzo");
        btnPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnPrice);
        btnPrice.addActionListener(e-> {
                boolean exc = false;
                String price = null;
                final String per = numPerson.getText();
                final Instructor ore = ci.getInstructor(listHours.getSelectedItem().toString()).get();
                final Season s = ci.getSeason(listSeason.getSelectedItem().toString()).get();
                try {
                    price = ci.getInstructorPrice(ore, per, s);
                } catch (IllegalArgumentException exception) {
                    exc = true;
                    JOptionPane.showMessageDialog(null, "Il numero di allievi deve essere compreso tra 1 e 4");
                    resetComp();
                }
                if (!exc) {
                    labPrice.setText(" Prezzo = " + price + " Euro");
                }
        });
        //BOTTONE RITORNA AL MENU
        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnRetMenu);
        this.btnRetMenu.addActionListener(e-> {
                resetComp();
        });
        //CARRELLO
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
        //SFONDO
        final Color bluette = new Color(C1, C2, C3);
        this.setBackground(bluette);
        pn.setBackground(bluette);
        ps.setBackground(bluette);
        pc.setBackground(bluette);
        this.setVisible(false);
    }
    @Override
    public JPanel getPanelInstructor() {
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
