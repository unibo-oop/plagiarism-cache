package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import view.panels.interfaces.PanelLoginAdmin;
/**
 * 
 * panel login admin class.
 *
 */
public class PanelLoginAdminImpl extends JPanel implements PanelLoginAdmin {
    private static final long serialVersionUID = 1L;
    private final JTextField password;
    private final JTextField username;
    private final JButton login;
    private final JButton btnReturnMenu;

    /**
     * panel login admin constructor.
     */
    public PanelLoginAdminImpl() {
        super();
        //SFONDO
        final ImageIcon img = new ImageIcon(getClass().getResource("/sfondo.jpg"));
        final Image scaledImage = img.getImage().getScaledInstance(FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue(), Image.SCALE_DEFAULT);
        img.setImage(scaledImage);
        final JLabel labelImg = new JLabel(img);
        labelImg.setBounds(0, 0, FrameSize.WIDTH.getValue(), FrameSize.HEIGHT.getValue());
        //TITOLO
        final JLabel labLoginAdmin = new JLabel("LOGIN ADMIN");
        labLoginAdmin.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C20.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C24.getDimension()), FrameSize.WIDTH.getValue(), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C22.getDimension()));
        labLoginAdmin.setFont(new Font("Tahoma", Font.PLAIN, (int) (FrameSize.WIDTH.getValue() / Dimension.FONT_LAB.getDimension())));
        labLoginAdmin.setForeground(Color.RED);
        labelImg.add(labLoginAdmin);
        //USER
        final JLabel labusername = new JLabel("Username");
        labusername.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C16.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C18.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()));
        labusername.setFont(new Font("Tahoma", Font.PLAIN, (int) (FrameSize.WIDTH.getValue() / Dimension.FONT_LAB1.getDimension())));
        labusername.setForeground(Color.RED);
        labelImg.add(labusername);
        this.username = new JTextField();
        this.username.setBounds((int) (FrameSize.WIDTH.getValue() / 2), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C18.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()));
        labelImg.add(username);
        //PASSWORD
        final JLabel labpassword = new JLabel("Password");
        labpassword.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C16.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C13.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()));
        labpassword.setFont(new Font("Tahoma", Font.PLAIN, (int) (FrameSize.WIDTH.getValue() / Dimension.FONT_LAB1.getDimension())));
        labpassword.setForeground(Color.RED);
        labelImg.add(labpassword);
        this.password = new JPasswordField();
        this.password.setBounds((int) (FrameSize.WIDTH.getValue() / 2), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C13.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C23.getDimension()));
        labelImg.add(password);
        //BOTTONE LOGIN
        this.login = new JButton("Login");
        this.login.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C15.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C1.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(login);
        //BOTTONE MENU
        this.btnReturnMenu = new JButton("Torna al menu'");
        this.btnReturnMenu.setBounds((int) (FrameSize.WIDTH.getValue() / Dimension.W_C3.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C1.getDimension()), (int) (FrameSize.WIDTH.getValue() / Dimension.W_C12.getDimension()), (int) (FrameSize.HEIGHT.getValue() / Dimension.H_C21.getDimension()));
        labelImg.add(btnReturnMenu);
        this.add(labelImg);
        this.setLayout(null);
        this.setVisible(false);
    }
    @Override
    public JPanel getLoginAdminPanel() {
        return this;
    }
    @Override
    public JTextField getUsername() {
        return this.username;
    }
    @Override
    public JTextField getPassword() {
        return this.password;
    }
    @Override
    public JButton getBtnLogin() {
        return this.login;
    }
    @Override
    public JButton getBtnPrev() {
        return this.btnReturnMenu;
    }

}
