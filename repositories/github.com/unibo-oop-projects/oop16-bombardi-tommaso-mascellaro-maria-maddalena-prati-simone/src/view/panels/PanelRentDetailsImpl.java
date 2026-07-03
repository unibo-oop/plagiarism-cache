package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.admin.products.Object2;
import model.admin.products.Season;
import view.panels.interfaces.PanelRentDetails;
/**
 * 
 * class panel rent details.
 *
 */
public class PanelRentDetailsImpl extends JPanel implements PanelRentDetails {

    private static final long serialVersionUID = 1L;
    private final transient Controller ci;
    private final JButton btnRetMenu;
    private final JButton btnCart;
    private JLabel labImg;
    private JLabel labSize;
    private JLabel labQuantity;
    private JLabel labTitle;
    private JLabel labPrice;
    private JLabel labSeason;
    private JComboBox<String> comboSeason;
    private JLabel labDays;
    private JTextField days;
    private JComboBox<String> comboSize;
    private JTextField quantity;
    private final JPanel pn;
    private final JPanel p1;
    private final JPanel p2;
    private JLabel emp;
    private JLabel emp1;
    private final String[] size = new String[] {"Small", "Medium", "Large"};
    private transient Optional<Object2> obj;
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;
    private static final int BOR = FrameSize.WIDTH.getValue() / 40;
    private static final int GAP = FrameSize.WIDTH.getValue() / 30;
    private static final int LAB = FrameSize.WIDTH.getValue() / 40;
    private static final int LAB2 = FrameSize.WIDTH.getValue() / 30;
    private static final String MYFONT = "Tahoma";
    /**
     * reset component after error or add cart.
     */
    private void resetComp() {
        this.days.setText("1");
        this.quantity.setText("1");
        this.comboSeason.setSelectedIndex(0);
        this.comboSize.setSelectedIndex(0);
        this.labPrice.setText("");
    }
    /**
     * set price of object.
     * @param s
     *        price
     */
    private void setPriceLabel(final String s) {
        this.labPrice.setText(s + " Euro");
    }
    /**
     * panel rent constructor.
     */
    public PanelRentDetailsImpl() {
        super();
        this.ci = Controller.getController();
        this.obj = Optional.empty();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));

        final Container x = new Container();
        this.pn = new JPanel();
        final JPanel ps = new JPanel();
        this.add(pn, BorderLayout.NORTH);
        this.add(x, BorderLayout.CENTER);
        this.add(ps, BorderLayout.SOUTH);
        x.setLayout(new GridLayout(1, 2));
        this.p1 = new JPanel();
        this.p2 = new JPanel();
        this.p2.setLayout(new GridLayout(8, 2, GAP, GAP));
        x.add(p1);
        x.add(p2);
        //BOTTONE AGGIUNGI AL CARRELLO
        final JButton btnAddCart = new JButton("Aggiungi al carrello");
        btnAddCart.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnAddCart);
        btnAddCart.addActionListener(e-> {
            try {
                ci.addRentObject(this.obj.get(), this.quantity.getText(), this.days.getText(), ci.getSeason(this.comboSeason.getSelectedItem().toString()).get());
                JOptionPane.showMessageDialog(null, "Prodotto aggiunto al carrello");
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(null, "Il numero di oggetti deve essere compreso tra 1 e 10 e il numero di giorni tra 1 e 20");
            }
            resetComp();
        });
        //BOTTONE PREZZO
        final JButton btnPrice = new JButton("Calcola Prezzo");
        btnPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnPrice);
        btnPrice.addActionListener(e-> {
            try {
                setPriceLabel(ci.getRentPrice(this.obj.get(), this.quantity.getText(), this.days.getText(), ci.getSeason(this.comboSeason.getSelectedItem().toString()).get()));
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(null, "Il numero di oggetti deve essere compreso tra 1 e 10 e il numero di giorni tra 1 e 20");
                resetComp();
            }
        });
        //BOTTONE MENU
        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnRetMenu);
        //BOTTONE CARRELLO
        final ImageIcon imgCart = new ImageIcon(getClass().getResource("/carrello.gif"));
        this.btnCart = new JButton(imgCart);
        final Image scaledImage = imgCart.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / 12), (int) (FrameSize.HEIGHT.getValue() / 10), Image.SCALE_DEFAULT);
        imgCart.setImage(scaledImage);
        btnCart.setOpaque(false);
        btnCart.setContentAreaFilled(false);
        btnCart.setBorderPainted(false);
        ps.add(btnCart);
        final Color bluette = new Color(C1, C2, C3);
        this.setBackground(bluette);
        this.pn.setBackground(bluette);
        x.setBackground(bluette);
        ps.setBackground(bluette);
        this.p1.setBackground(bluette);
        this.p2.setBackground(bluette);
        this.setVisible(false);

    }
    @Override
    public void addDetails() {
        final ImageIcon img = new ImageIcon(getClass().getResource(obj.get().getImage()));
        final Image scaledImage = img.getImage().getScaledInstance(FrameSize.WIDTH.getValue() / 3, FrameSize.HEIGHT.getValue() / 2, Image.SCALE_DEFAULT);
        img.setImage(scaledImage);
        this.labImg = new JLabel(img);
        this.p1.add(labImg);
        //TITOLO
        this.labTitle = new JLabel(obj.get().getDescription());
        this.labTitle.setFont(new Font(MYFONT, Font.PLAIN, LAB2));
        this.labTitle.setForeground(Color.RED);
        this.pn.add(labTitle);
        //TAGLIA
        this.labSize = new JLabel("Seleziona la taglia");
        this.p2.add(labSize);
        this.comboSize = new JComboBox<String>(size);
        this.p2.add(comboSize);
        //STAGIONE
        this.labSeason = new JLabel("Seleziona il periodo");
        this.p2.add(labSeason);
        this.comboSeason = new JComboBox<String>();
        for (final Season elem : ci.getSeasons()) {
            this.comboSeason.addItem(elem.getPeriod());
        }
        this.p2.add(comboSeason);
        //QUANTITA'
        this.labQuantity = new JLabel("Numero di articoli (1-10)");
        this.p2.add(labQuantity);
        this.quantity = new JTextField("1");
        this.p2.add(quantity);
        //DURATA
        this.labDays = new JLabel("Inserisci la durata (1-20)");
        this.p2.add(labDays);
        this.days = new JTextField("1");
        this.p2.add(days);
        //PREZZO
        this.emp = new JLabel("");
        this.p2.add(emp);
        this.emp1 = new JLabel("");
        this.p2.add(emp1);
        this.labPrice = new JLabel();
        this.labPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB2));
        this.p2.add(labPrice);
    }
    @Override
    public void removeDetails() {
        this.p1.remove(labImg);
        this.p2.remove(comboSize);
        this.p2.remove(labTitle);
        this.p2.remove(labSize);
        this.p2.remove(labQuantity);
        this.p2.remove(quantity);
        this.p2.remove(labPrice);
        this.p2.remove(labDays);
        this.p2.remove(comboSeason);
        this.p2.remove(labSeason);
        this.p2.remove(days);
        this.p2.remove(emp);
        this.p2.remove(emp1);
    }
    @Override
    public void setObject(final Object2 ob) {
        this.obj = Optional.of(ob);
    }
    @Override
    public void resetObject() {
        this.obj = Optional.empty();
    }
    @Override
    public JPanel getPanelRentDetails() {
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
