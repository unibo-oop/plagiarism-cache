package view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.IModel;
import controller.LoginController;
import controller.Utils;
/**
 * The home View of the app
 * @author lucadalseno
 *
 */
public class MainView extends JFrame implements CallBackInterface {
    /**
     * 
     */
    private static final long serialVersionUID = 9199009276340778940L;
    private JPanel contentPane;
    public static enum LoginType{adm,user};
    private IModel model;
    private JButton championshipBtn;
    private JButton matchBtn;

    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 692, 549);
        this.setResizable(false);
        model = Utils.loading();
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        championshipBtn = new JButton("Championship");
        championshipBtn.setBounds(101, 439, 172, 29);
        contentPane.add(championshipBtn);
        championshipBtn.addActionListener(e->{
            LoginDialog lD = new LoginDialog(model);
            LoginController contr = new LoginController(LoginType.adm, model,this);
            contr.setView(lD);
            lD.setLocation(250, 250);
            lD.setVisible(true); 
        });
        
        matchBtn = new JButton("Match");
        matchBtn.setBounds(424, 439, 172, 29);
        contentPane.add(matchBtn);
        matchBtn.addActionListener(e->{
            LoginDialog lD = new LoginDialog(model);
            LoginController contr = new LoginController(LoginType.user,model,this);
            contr.setView(lD);
            lD.setLocation(250, 250);
            lD.setVisible(true); 
        });

        ClassLoader cl = getClass().getClassLoader();
        URL url = cl.getResource("logone.png");
        ImageIcon image = new ImageIcon(url);
        JLabel logoLbl = new JLabel();
        logoLbl.setIcon(image);
        logoLbl.setBounds(86, 51, 522, 334);
        contentPane.add(logoLbl);
    }

    @Override
    public void onClose() {
        this.setVisible(true);
    }

    @Override
    public void setVisibility(boolean b) {
       this.setVisible(b);
    }
}