package view.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.panels.interfaces.PanelBase;

/**
 * 
 * panel base class.
 *
 */
public class PanelBaseImpl extends JPanel implements PanelBase {
    private static final long serialVersionUID = 1L;
    private final JPanel panelWelcome;
    private final JButton btnUser;
    private final JButton btnAdmin;
    /**
     * panel base constructor.
     */
    public PanelBaseImpl() {
        super();
        //PANNELLO BASE
        final CardLayout cl = new CardLayout();
        this.setLayout(cl);
        //PANNELLO DI INIZIO
        this.panelWelcome = new JPanel();
        //SFONDO
        final ImageIcon img = new ImageIcon(getClass().getResource("/sfondo.jpg"));
        final Image scaledImage = img.getImage().getScaledInstance(FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue(), Image.SCALE_DEFAULT);
        img.setImage(scaledImage);
        final JLabel labelImg = new JLabel(img);
        labelImg.setBounds(0, 0, FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue());
        //TITOLO
        final JLabel benvenuto = new JLabel("Benvenuto in SkiCenter Manager");
        benvenuto.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C18.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()), FrameSize.WIDTH.getValue(), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        benvenuto.setFont(new Font("Tahoma", Font.PLAIN, (int) (FrameSize.WIDTH.getValue() / Dimension.FONT_LAB.getDimension())));
        benvenuto.setForeground(Color.RED);
        this.btnUser = new JButton("Accedi come utente");
        this.btnUser.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C16.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C1.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C11.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        this.btnAdmin = new JButton("Accedi come amministratore");
        this.btnAdmin.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C6.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C1.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C11.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnAdmin);
        labelImg.add(btnUser);
        labelImg.add(benvenuto);
        this.panelWelcome.add(labelImg);
        this.panelWelcome.setLayout(null);
        this.panelWelcome.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public JPanel getPanelBase() {
        return this;
    }

    @Override
    public JPanel getPanelWelcome() {
        return this.panelWelcome;
    }

    @Override
    public JButton getBtnUserPanel() {
        return this.btnUser;
    }

    @Override
    public JButton getBtnAdminPanel() {
        return this.btnAdmin;
    }
}
