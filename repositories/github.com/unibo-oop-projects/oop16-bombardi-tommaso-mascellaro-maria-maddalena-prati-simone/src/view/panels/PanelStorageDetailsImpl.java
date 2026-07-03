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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.admin.products.Object2;
import view.panels.interfaces.PanelStorageDetails;
/**
 * panel storage details class.
 *
 */
public class PanelStorageDetailsImpl extends JPanel implements PanelStorageDetails {

    private static final long serialVersionUID = 1L;
    private final Controller ci;
    private final JButton btnRetMenu;
    private final JButton btnCart;
    private JLabel labImg;
    private JLabel labQuantity;
    private JLabel labTitle;
    private JLabel labPrice;
    private JLabel labDays;
    private JTextField days;
    private JTextField quantity;
    private Optional<Object2> obj;
    private final JPanel pn;
    private final JPanel p1;
    private final JPanel p2;
    private JLabel emp;
  //COLORE
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;
    private static final int BOR = FrameSize.WIDTH.getValue() / 40;
    private static final int NUM_RIG = 9;
    private static final int LAB = FrameSize.WIDTH.getValue() / 40;
    private static final int LAB2 = FrameSize.WIDTH.getValue() / 30;
    private static final String MYFONT = "Tahoma";
   /**
    * reset component after error or add cart.
    */
    private void resetComp() {
        this.days.setText("1");
        this.quantity.setText("1");
        this.labPrice.setText("");
    }
    /**
     * set price of object.
     * @param s
     *        price
     */
    private void setPriceLabel(final String s) {
        this.labPrice.setText("Prezzo = " + s + " Euro");
    }
    /**
     * panel storage details constructor.
     */
    public PanelStorageDetailsImpl() {
        super();
        this.ci = Controller.getController();
        this.pn = new JPanel();
        this.p1 = new JPanel();
        this.p2 = new JPanel();
        final Container x = new Container();
        final JPanel ps = new JPanel();
        this.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));
        this.setLayout(new BorderLayout());
        this.add(pn, BorderLayout.NORTH);
        this.add(x, BorderLayout.CENTER);
        this.add(ps, BorderLayout.SOUTH);
        x.setLayout(new GridLayout(1, 2));
        x.add(p1);
        x.add(p2);
        this.p2.setLayout(new GridLayout(NUM_RIG, 1));
        this.obj = Optional.empty();
        final JButton btnAddCart = new JButton("Aggiungi al carrello");
        btnAddCart.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnAddCart);
        //AGGIUNGI AL CARRELLO UN DEPOSITO
        btnAddCart.addActionListener(e-> {
            try {
                ci.addStorage(this.obj.get(), this.quantity.getText(), this.days.getText());
                JOptionPane.showMessageDialog(null, "Prodotto aggiunto al carrello");
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(null, "Il numero di oggetti deve essere compreso tra 1 e 10 e il numero di giorni tra 1 e 20");
            }
            resetComp();
        });

        final JButton btnPrice = new JButton("Calcola Prezzo");
        btnPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnPrice);
        //CALCOLA PREZZO
        btnPrice.addActionListener(e-> {
            try {
                setPriceLabel(ci.getStoragePrice(this.obj.get(), this.quantity.getText(), this.days.getText()));
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(null, "Il numero di oggetti deve essere compreso tra 1 e 10 e il numero di giorni tra 1 e 20");
                resetComp();
            }
        });

        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setFont(new Font(MYFONT, Font.PLAIN, LAB));
        ps.add(btnRetMenu);

        final ImageIcon imgCart = new ImageIcon(getClass().getResource("/carrello.gif"));
        this.btnCart = new JButton(imgCart);
        final Image scaledImage = imgCart.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / 12), (int) (FrameSize.HEIGHT.getValue() / 10), Image.SCALE_DEFAULT);

        imgCart.setImage(scaledImage);
        ps.add(btnCart);
        btnCart.setOpaque(false);
        btnCart.setContentAreaFilled(false);
        btnCart.setBorderPainted(false);

        final Color bluette = new Color(C1, C2, C3);
        this.setBackground(bluette);
        this.p1.setBackground(bluette);
        this.p2.setBackground(bluette);
        ps.setBackground(bluette);
        pn.setBackground(bluette);
        this.setVisible(false);

    }
    @Override
    public void addDetails() {
        final ImageIcon img = new ImageIcon(getClass().getResource(obj.get().getImage()));
        final Image scaledImage = img.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / 3), FrameSize.HEIGHT.getValue() / 2, Image.SCALE_DEFAULT);
        img.setImage(scaledImage);
        this.labImg = new JLabel(img);
        this.p1.add(labImg);
        this.labTitle = new JLabel(obj.get().getDescription());
        this.labTitle.setFont(new Font(MYFONT, Font.PLAIN, LAB2));
        this.labTitle.setForeground(Color.RED);
        this.pn.add(labTitle);
        this.labQuantity = new JLabel("Numero di articoli (1-10)");
        this.p2.add(labQuantity);
        this.quantity = new JTextField("1");
        this.p2.add(quantity);
        this.labDays = new JLabel("Numero giorni (1-20)");
        this.p2.add(labDays);
        this.days = new JTextField("1");
        this.p2.add(days);
        this.emp = new JLabel("");
        this.p2.add(emp);
        this.labPrice = new JLabel();
        this.labPrice.setFont(new Font(MYFONT, Font.PLAIN, LAB2));
        this.p2.add(labPrice);
    }
    @Override
    public void removeDetails() {
        this.p1.remove(labImg);
        this.p2.remove(labTitle);
        this.p2.remove(labQuantity);
        this.p2.remove(quantity);
        this.p2.remove(labPrice);
        this.p2.remove(labDays);
        this.p2.remove(days);
        this.p2.remove(emp);
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
    public JButton getBtnPrev() {
        return this.btnRetMenu;
    }
    @Override
    public JButton getBtnCart() {
        return this.btnCart;
    }
    @Override
    public JPanel getPanelStorageDetails() {
        return this;
    }
}
