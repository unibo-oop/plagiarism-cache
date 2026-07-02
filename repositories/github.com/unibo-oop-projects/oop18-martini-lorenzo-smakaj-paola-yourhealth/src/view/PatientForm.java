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

import controller.AccountPaziente;


public class PatientForm extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8666175241640383282L;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();
	
    private GUI fac = new GUIFactory();
    private JTextField textPatID;
    private final Float font = 20.0f;
    JFrame frame = new JFrame();
    JFrame login = LoginForm.frame;//<--
   
    public PatientForm() {
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.setTitle("");
        frame.setResizable(false);
        frame.setSize(width / 3, height / 3);

        JPanel canvas = fac.createBoxPanel();
        frame.add(canvas, BorderLayout.WEST);
        JPanel canvas2 = fac.createBoxPanel();
        frame.add(canvas2, BorderLayout.CENTER);
        JPanel canvas3 = fac.createFlowPanel();
        frame.add(canvas3,  BorderLayout.EAST);

        JLabel label = fac.createLabelRight("Inserisci il codice fiscale: ", font);
        canvas.add(label);
        this.textPatID = fac.createTextField();
        canvas2.add(textPatID);
        
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
		String uname = textPatID.getText();
		if(AccountPaziente.checkEsistenzaCodiceFiscale(uname)){
			new WelcomePatient(uname);
			frame.setVisible(false);
			login.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this,"Codice fiscale inesistente",
					"Errore",JOptionPane.ERROR_MESSAGE); 
		}
	}
}
