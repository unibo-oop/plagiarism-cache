package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import util.LogoPan;
import util.UpperPan;
import sign_up.RegisterPan;
import login.LoginPan;

public class BuildPreGui extends JFrame {
 
	private static final long serialVersionUID = 1L;

    public BuildPreGui() {
    	
        initComponents();
    }

    private void initComponents() {

        panelHolder = new JPanel(); //Holder for Login and Register
       
        //JFrame properties
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());
   

        Image icon = Toolkit.getDefaultToolkit().getImage("img/icon.jpg");    
        setIconImage(icon);   
        //Get upper panel and add it to the main frame
        upper_panel = new UpperPan();
        upper_panel.setVisible(true);
        
        getContentPane().add(upper_panel, new AbsoluteConstraints(510, 0, 420, 40));
        
        //Card space
        panelHolder.setLayout(new CardLayout());

        //Add login to card
        login_panel = new LoginPan();
        login_panel.setVisible(true);
        panelHolder.add(login_panel, "login");
     
        //Add register to card
        register_panel = new RegisterPan();
        register_panel.setVisible(true);
        panelHolder.add(register_panel,"register_panel");

        //Adding the card!
        getContentPane().add(panelHolder, new AbsoluteConstraints(510, 40, 370, 480));
        

     
        //Get logo panel and add it to the main frame
        logo = new LogoPan();
        logo.setVisible(true);
        logo.setBackground(new Color(67, 71, 91));
    	
        getContentPane().add(logo, new AbsoluteConstraints(0, 0, -1, -1));
        
        pack();
        
        //<--------------METHODS---------------------------------------->
        
        //Call Drag the frame
		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                FrameMouseDragged(evt);
            }
        });
		getContentPane().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                FrameMousePressed(evt);
            }
        });
    }
		
    	//<------------------------END---------------------------->
    
    //Drag the frame 
    private  int xy;
    private  int xx;
    
    private void FrameMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }
    
    private void FrameMousePressed(MouseEvent evt) {  
        xx = evt.getX();
        xy = evt.getY();
    }
    
   
    
    
    
    
    
   //<------------BUILD THE GUI----------------->
    public void start() {

    	EventQueue.invokeLater(new Runnable() {
            public void run() {
            	  new BuildPreGui().setVisible(true);
            	  
            }
        });
    }
    
  

    // Variables declaration - do not modify
    private JPanel panelHolder;   
    private LoginPan login_panel;
    private LogoPan logo;
    private UpperPan upper_panel;
    private RegisterPan register_panel;
    // End of variables declaration
}