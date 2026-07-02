package it.unibo.infomanager.infomng.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
/**
 * Classe che definisce viewLogin.
 * @author Alessandro
 *
 */
public class LoginGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2973091805746960466L;
	private static final String TITOLO = "Login";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(462, 315);
	private JTextField tUser = new JTextField(10);
	private JTextField tPass = new JPasswordField(10);
	private JLayeredPane lPane = new JLayeredPane();
	private JLabel lUser = new JLabel("Username  :");
	private JLabel lPass = new JLabel("Password  :");
	private JLayeredPane lPane1 = new JLayeredPane();
	private GroupLayout gLayoutPane = new GroupLayout(lPane);
	private GroupLayout gLayoutPane1 = new GroupLayout(lPane1);
	private JPanel buttonPane = new JPanel();
	private JButton bAccendi = new JButton("Accedi");
	private JButton bRegistrati = new JButton("Registrati");
	
	/**
	 * Metodo che setta il frame usabile o non usabile.
	 * @param a
	 * 			Variabile Boolean, true se il frame è abilitato, false se non è abilitato
	 */			
	public void setUsable(final boolean a) {
		this.setEnabled(a);
	}

	/**
	 * Costruttore del frame LoginGUI.
	 * @param o
	 * 			Oggetto ObserverInterface
	 */
	//CHECKSTYLE:OFF: checkstyle:magicnumber    
	public LoginGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.tUser.setEditable(true);
		this.tPass.setEditable(true);
		this.gLayoutPane.setHorizontalGroup(
				this.gLayoutPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gLayoutPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gLayoutPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lUser, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
						.addComponent(lPass, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lPane1, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addContainerGap())
		);
		this.gLayoutPane.setVerticalGroup(
				this.gLayoutPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gLayoutPane.createSequentialGroup()
					.addGroup(gLayoutPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gLayoutPane.createSequentialGroup()
							.addGap(45)
							.addComponent(lUser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(lPass, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gLayoutPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(86, Short.MAX_VALUE))
		);

		this.gLayoutPane1.setHorizontalGroup(
				this.gLayoutPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gLayoutPane1.createSequentialGroup()
					.addGap(10)
					.addGroup(gLayoutPane1.createParallelGroup(Alignment.LEADING)
						.addComponent(tUser, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
						.addComponent(tPass, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
					.addGap(16))
		);
		this.gLayoutPane1.setVerticalGroup(
				this.gLayoutPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gLayoutPane1.createSequentialGroup()
					.addGap(36)
					.addComponent(tUser, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(tPass, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		this.lPane1.setLayout(gLayoutPane1);
		this.lPane.setLayout(gLayoutPane);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(lPane, BorderLayout.NORTH);
		

		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
		this.buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.bAccendi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				if (o.userLogin(tUser.getText(), tPass.getText())){
					o.mostraMenu();
					dispose();
				} else {
					o.mostraDialogWrongUser();
				}
			}
		});
		this.buttonPane.add(bAccendi);
		
		this.bRegistrati.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				dispose();
				o.mostraDialogRegistrati();
			}
		});
		this.buttonPane.add(bRegistrati);
	}
	/**
	 * 	Metodo per ottenere i dati dai TextField.
	 * @return
	 * 			Map (String,String) 
	 */			
	public Map<String, String> getTextfield() {
		Map<String, String> mappa = new HashMap<>();
		mappa.put("Username", tUser.getText());
		mappa.put("Password", tPass.getText());
		return mappa;
	}
		
}
