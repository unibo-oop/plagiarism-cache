package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.admin.products.Object2;
import view.panels.interfaces.PanelStorage;
/**
 * 
 * panel storage class.
 *
 */
public class PanelStorageImpl extends JPanel implements PanelStorage {

    private static final long serialVersionUID = 1L;
    private final JButton btnRetMenu;
    private final JButton btnCart;
    private final Map<JButton, Object2> jbMap;
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;
    private static final int BOR = FrameSize.WIDTH.getValue() / 40;
    private static final int LAB = FrameSize.WIDTH.getValue() / 30;
    /**
     * panel storage constructor.
     */
    public PanelStorageImpl() {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(BOR, BOR, BOR, BOR));
        final JPanel pn = new JPanel();
        final JPanel pc = new JPanel();
        final JPanel ps = new JPanel();
        this.add(pn, BorderLayout.NORTH);
        this.add(pc, BorderLayout.CENTER);
        this.add(ps, BorderLayout.SOUTH);
        pc.setLayout(new GridLayout(2, 4));
        final Controller ci = Controller.getController();
        this.jbMap = new HashMap<>();
        final JLabel title = new JLabel("Scegli il prodotto che vuoi depositare");
        title.setFont(new Font("Tahoma", Font.PLAIN, LAB));
        title.setForeground(Color.RED);
        pn.add(title);

        for (final Object2 obj : ci.getRentAndStorageObjects()) {
            final ImageIcon icon = new ImageIcon(getClass().getResource(obj.getImage()));
            final JButton bt = new JButton(icon);
            final JLabel jl = new JLabel(obj.getDescription());
            final Image scaledImage = icon.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / 6), (int) (FrameSize.HEIGHT.getValue() / 4), Image.SCALE_DEFAULT);
            icon.setImage(scaledImage);
            this.jbMap.put(bt, obj);
            bt.setOpaque(false);
            bt.setContentAreaFilled(false);
            bt.setBorderPainted(false);
            pc.add(bt);
            bt.add(jl);
            jl.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
        }
        final ImageIcon imgCart = new ImageIcon(getClass().getResource("/carrello.gif"));
        this.btnCart = new JButton(imgCart);
        final Image scaledImage = imgCart.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / 6), (int) (FrameSize.HEIGHT.getValue() / 4), Image.SCALE_DEFAULT);
        imgCart.setImage(scaledImage);
        pc.add(btnCart);
        final JLabel labCart = new JLabel("Visualizza il carrello");
        labCart.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
        labCart.setForeground(Color.blue);
        btnCart.add(labCart);
        btnCart.setOpaque(false);
        btnCart.setContentAreaFilled(false);
        btnCart.setBorderPainted(false);

        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setFont(new Font("Tahoma", Font.PLAIN, LAB));
        ps.add(btnRetMenu);

        final Color bluette = new Color(C1, C2, C3);
        this.setBackground(bluette);
        pn.setBackground(bluette);
        pc.setBackground(bluette);
        ps.setBackground(bluette);
        this.setVisible(false);
    }
    @Override
    public JPanel getPanelStorage() {
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
    @Override
    public Map<JButton, Object2> getBtnObj() {
        return this.jbMap;
    }
}
