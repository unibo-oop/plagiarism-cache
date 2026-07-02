package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class AdminForm extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8666175241640383282L;
	/**
	 * 
	 */
	
    private GUI fac = new GUIFactory();
    private JPasswordField p;
    private final Float font = 20.0f;
    JFrame frame = new JFrame();
    
    JFrame login = LoginForm.frame;//<--
   
    public AdminForm() {
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(600, 900);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("");
        frame.setResizable(false);

        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);

        JLabel label = fac.createLabelRight("Inserisci la password: ", font);
        canvas.add(label);
        this.p = new JPasswordField(25);
        p.setBounds(300, 110, 200, 30);
        canvas2.add(p);
        
        JButton confirm = new JButton("Invio");
        confirm.setFont(new Font("Calibri", Font.PLAIN,18));
        confirm.setBackground(Color.darkGray);
        confirm.setForeground(Color.white);
        confirm.addActionListener(this);
        
        canvas3.add(confirm);
        frame.setVisible(true);
        frame.pack();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if((Arrays.equals(p.getPassword(), new char[]{'p','a','s'}))){
			new Welcome();
			frame.setVisible(false);
			login.dispose();//<-- 
			
		}
		else {
			JOptionPane.showMessageDialog(this,"Password sbagliata",
					"Errore",JOptionPane.ERROR_MESSAGE); 
		}
		
	}
}
