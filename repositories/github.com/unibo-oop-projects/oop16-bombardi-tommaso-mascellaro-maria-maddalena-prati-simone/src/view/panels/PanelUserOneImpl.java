package view.panels;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.panels.interfaces.PanelUserOne;

/**
 * 
 * panel user class.
 *
 */
public class PanelUserOneImpl extends JPanel implements PanelUserOne {

    private static final long serialVersionUID = 1L;
    private final JButton btnRent;
    private final JButton btnBuy;
    private final JButton btnSkipass;
    private final JButton btnInstructor;
    private final JButton btnStorage;
    private final JButton btnTornaMenu;

    private final JButton btnCart;
/**
 * panel user constructor.
 */
    public PanelUserOneImpl() {
        super();
        final ImageIcon img = new ImageIcon(getClass().getResource("/sfondo.jpg"));
        final Image scaledImage = img.getImage().getScaledInstance(FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue(), Image.SCALE_DEFAULT);
        img.setImage(scaledImage);
        final JLabel labelImg = new JLabel(img);
        labelImg.setBounds(0, 0, FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue());
        this.btnBuy = new JButton("ACQUISTO");
        this.btnBuy.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C21.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnBuy);
        this.btnRent = new JButton("NOLEGGIO");
        this.btnRent.setBounds(FrameSize.WIDTH.getValue() / 2, (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnRent);
        this.btnSkipass = new JButton("SKIPASS");
        this.btnSkipass.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C21.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C18.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnSkipass);
        this.btnStorage = new JButton("DEPOSITO");
        this.btnStorage.setBounds(FrameSize.WIDTH.getValue() / 2, (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C18.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnStorage);
        this.btnInstructor = new JButton("MAESTRO");
        this.btnInstructor.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C21.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C10.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnInstructor);
        this.btnTornaMenu = new JButton("Torna al menu'");
        this.btnTornaMenu.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C6.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C1.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnTornaMenu);
        final ImageIcon imgCart = new ImageIcon(getClass().getResource("/carrello.gif"));
        this.btnCart = new JButton(imgCart);
        final Image scaledImages = imgCart.getImage().getScaledInstance((int) (FrameSize.WIDTH.getValue() / Dimension.W_C19.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()), Image.SCALE_DEFAULT);
        imgCart.setImage(scaledImages);
        this.btnCart.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C1.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C1.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C19.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnCart);
        this.add(labelImg);
        this.setLayout(null);
        this.setVisible(false);
    }

    @Override
    public JPanel getPanelUserOne() {
        return this;
    }
    @Override
    public JButton getBtnPrev() {
        return this.btnTornaMenu;
    }
    @Override
    public JButton getBtnSkipass() {
        return this.btnSkipass;
    }
    @Override
    public JButton getBtnBuy() {
        return this.btnBuy;
    }
    @Override
    public JButton getBtnRent() {
        return this.btnRent;
    }
    @Override
    public JButton getBtnStorage() {
        return this.btnStorage;
    }
    @Override
    public JButton getBtnInstructor() {
        return this.btnInstructor;
    }
    @Override
    public JButton getBtnCart() {
        return this.btnCart;
    }
}
