package view.panels;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.admin.products.Object1;
import view.panels.interfaces.PanelBuy;

/**
 * 
 * panel buy class.
 *
 */
public class PanelBuyImpl extends JPanel implements PanelBuy {
    private static final long serialVersionUID = 1L;
    private final JButton btnRetMenu;
    private final JButton btnCart;
    private final Map<JButton, Object1> jbMap;
    //COLORE
    private static final int C1 = 171;
    private static final int C2 = 205;
    private static final int C3 = 239;

    /**
     * panel buy constructor.
     */
    public PanelBuyImpl() {
        super();
        final Controller ci = Controller.getController();
        this.jbMap = new HashMap<>();
        int countCol = 0;
        int countRig = 0;
        for (final Object1 obj : ci.getBuyObjects()) {
            final ImageIcon icon = new ImageIcon(getClass().getResource(obj.getImage()));
            final JButton bt = new JButton(icon);
            final JLabel jl = new JLabel(obj.getDescription());
            final Image scaledImage = icon.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / Dimension.W_C17.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C18.getDimension()), Image.SCALE_DEFAULT);
            icon.setImage(scaledImage);
            bt.setBounds((int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()) + (FrameSize.WIDTH.getValue() / 4 * countCol), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C24.getDimension()) + (FrameSize.HEIGHT.getValue() / 4 * countRig), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C17.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C18.getDimension()));
            jl.setBounds((int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()) + (FrameSize.WIDTH.getValue() / 4 * countCol), FrameSize.HEIGHT.getValue() / 4 + (int) ((FrameSize.HEIGHT.getValue() / 4 * countRig)), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C17.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C26.getDimension()));
            this.jbMap.put(bt, obj);
            this.add(bt);
            this.add(jl);
            countCol++;
            if (countCol == 4) {
                countCol = 0;
                countRig++;
            }
        }
        final ImageIcon imgCart = new ImageIcon(getClass().getResource("/carrello.gif"));
        this.btnCart = new JButton(imgCart);
        final Image scaledImage = imgCart.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / Dimension.W_C19.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()), Image.SCALE_DEFAULT);
        imgCart.setImage(scaledImage);
        this.btnCart.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C1.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C0.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C19.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        this.add(btnCart);
        this.btnRetMenu = new JButton("Pagina precedente");
        this.btnRetMenu.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C6.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C0.getDimension()), FrameSize.WIDTH.getValue() / 4, (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        this.add(btnRetMenu);
        final Color bluette = new Color(C1, C2, C3);
        this.setBackground(bluette);
        this.setLayout(null);
        this.setVisible(false);
    }
    @Override
    public JPanel getPanelBuy() {
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
    public Map<JButton, Object1> getBtnObj() {
        return this.jbMap;
    }
}
