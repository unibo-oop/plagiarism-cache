package it.unibo.infomanager.infomng.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import it.unibo.infomanager.infomng.model.modelClientsI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.toolbar.MyToolbar;

import javax.swing.JButton;

/**
 * Classe che definisce viewClient.
 * @author Alessandro
 *
 */

public class ClientiGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113641769924370743L;
	private static final String TITOLO = "Clienti";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(480, 290);
	private ClientiGUI frame = this;
	private MyToolbar toolbar;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	//CHECKSTYLE:OFF:
	protected JTextField txtNome;
	protected JTextField txtCognome;
	protected JTextField txtEmail;
	protected JTextField txtTelefono;
	//CHECKSTYLE:ON:
	private GroupLayout gpanelText = new GroupLayout(panelText);
	private JLabel lblNome = new JLabel("Nome");
	private JLabel lblCognome = new JLabel("Cognome");
	private JLabel lblEmail = new JLabel("Email");
	private JLabel lblTelefono = new JLabel("Telefono");
	private final JPanel panelButton = new JPanel();
	private final JButton btnPrecendete = new JButton("<<");
	private final JButton btnProssimo = new JButton(">>");

	
	

/**
 * 	Costruttore del ClientiGUI frame.
 * @param o
 * 			Oggetto ObserverInterface
 */
	//CHECKSTYLE:OFF: checkstyle:magicnumber    
	public ClientiGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ClientiGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.setLayout(new BorderLayout(0, 0));
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText, BorderLayout.CENTER);
		this.txtNome = new JTextField();
		this.txtNome.setColumns(10);
		
		this.txtCognome = new JTextField();
		this.txtCognome.setColumns(10);
		
		this.txtEmail = new JTextField();
		this.txtEmail.setText("");
		this.txtEmail.setColumns(10);
		
		this.txtTelefono = new JTextField();
		this.txtTelefono.setColumns(10);

		this.gpanelText.setHorizontalGroup(
			this.gpanelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gpanelText.createSequentialGroup()
					.addGap(45)
					.addGroup(gpanelText.createParallelGroup(Alignment.LEADING)
						.addGroup(gpanelText.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gpanelText.createSequentialGroup()
							.addGroup(gpanelText.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTelefono, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addComponent(lblEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gpanelText.createParallelGroup(Alignment.LEADING)
						.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
						.addComponent(txtCognome, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
						.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
					.addGap(76))
		);
		this.gpanelText.setVerticalGroup(
			this.gpanelText.createParallelGroup(Alignment.LEADING)
				.addGroup(gpanelText.createSequentialGroup()
					.addGap(52)
					.addGroup(gpanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gpanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCognome))
					.addGap(13)
					.addGroup(gpanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gpanelText.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		this.panelText.setLayout(gpanelText);
		
		this.panelTool.add(panelButton, BorderLayout.SOUTH);
		this.panelButton.add(btnPrecendete);
		
		this.panelButton.add(btnProssimo);
		this.btnPrecendete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					o.setAttuale(frame);
					getNavigator().indietro();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Eseguire una ricerca per scorrere tra i clienti e fornitori");
				}
				
				
			}
		});
		this.btnProssimo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {			
				try {
					o.setAttuale(frame);
					getNavigator().avanti();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Eseguire una ricerca per scorrere tra i clienti e fornitori");
				}
			}
		});
		
	}
	/**
	 * 	Metodo per ottendere i dati dai TextFields.
	 * @return
	 * 			Map (String,String)
	 */			
	public Map<String, String> getTextfield() {
		Map<String, String> mappa = new HashMap<>();
		mappa.put("Nome", txtNome.getText());
		mappa.put("Cognome", txtCognome.getText());
		mappa.put("Email", txtEmail.getText());
		mappa.put("Telefono", txtTelefono.getText());
		return mappa;
	}
	/**
	 * Metodo per settare i dati nei TextFields.
	 * @param congome
	 * Cognome cliente
	 * @param nome 
	 * Nome cliente
	 * @param email
	 * Email cliente
	 * @param telefono
	 * Telefono cliente
	 * 
	 * 
	 */
	public void setTextFields(Object c){
		modelClientsI cliente = (modelClientsI) c;
		this.txtCognome.setText(cliente.getLastName());
		this.txtNome.setText(cliente.getName());
		this.txtEmail.setText(cliente.getMail());
		this.txtTelefono.setText(cliente.getPhone());
	}
	
	
}	
