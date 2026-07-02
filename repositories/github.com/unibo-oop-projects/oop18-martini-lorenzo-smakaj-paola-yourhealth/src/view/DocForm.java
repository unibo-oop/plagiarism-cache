package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AccountDottore;


public class DocForm extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8666175241640383282L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
	
    private GUI fac = new GUIFactory();
    private JTextField textDocID;
    private final Float font = 20.0f;
    JFrame frame = new JFrame();
    JFrame login = LoginForm.frame;//<--
   
    public DocForm() {
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(width / 3, height / 3);
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

        JLabel label = fac.createLabelRight("Inserisci il tesserino: ", font);
        canvas.add(label);
        this.textDocID = fac.createTextField();
        canvas2.add(textDocID);
        
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
		String uname = textDocID.getText();
		if(AccountDottore.checkEsistenzaTesserino(Integer.parseInt(uname))){
			new WelcomeDoc(Integer.parseInt(uname));
			frame.setVisible(false);
			login.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this,"Tesserino inesistente",
					"Errore",JOptionPane.ERROR_MESSAGE); 
		}
	}
}
