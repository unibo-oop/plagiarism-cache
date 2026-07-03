package sharelist.view.notification;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import sharelist.view.application.ApplicationView;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * Centro Notifiche utente
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class CentroNotifiche extends JDialog {

	private static final long serialVersionUID = 660502698170947650L;
	private Integer posListSelected;
	private final JPanel contentPanel = new JPanel();
	private boolean accetta  = false;
	private Object[][] contenutoList;
	public JList list = new JList();
	public JLabel lblLista;
	private JButton btnAccetta;
	
	/**
	 * Costruttore CentroNotifiche
	 * 
	 * @param view
	 * 				View
	 */
	public CentroNotifiche(ApplicationView view) {
		super(view);
		setResizable(false);
		setTitle("Centro Notifiche");
		setSize(new Dimension(600, 350));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JPanel data = new JPanel();
			contentPanel.add(data);
			GridBagLayout gbl_data = new GridBagLayout();
			gbl_data.columnWidths = new int[] {318, 10};
			gbl_data.rowHeights = new int[] {0, 0, 0, 10};
			gbl_data.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_data.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			data.setLayout(gbl_data);
			{
				lblLista = new JLabel("Notifiche:");
				GridBagConstraints gbc_lblLista = new GridBagConstraints();
				gbc_lblLista.insets = new Insets(0, 0, 5, 0);
				gbc_lblLista.gridx = 0;
				gbc_lblLista.gridy = 0;
				data.add(lblLista, gbc_lblLista);
			}
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 1;
				data.add(panel, gbc_panel);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setPreferredSize(new Dimension(500, 200));
					panel.add(scrollPane);
					{
						this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						scrollPane.setViewportView(this.list);
						list.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								//Attiva i bottoni Accetta e Rifiuta solo se la notifica richiede un'azione e id azione e'=1
								posListSelected = list.getSelectedIndex();
								if((Integer)contenutoList[posListSelected+1][3]==1 && (Integer)contenutoList[posListSelected+1][4]==1){
									btnAccetta.setEnabled(true);
								} else {
									accetta=true;
									btnAccetta.setEnabled(false);
								}
							}
						});
					}
				}
			}
			{
				btnAccetta = new JButton("Accetta");
				btnAccetta.setEnabled(false);
				GridBagConstraints gbc_btnAccetta = new GridBagConstraints();
				gbc_btnAccetta.gridx = 0;
				gbc_btnAccetta.gridy = 2;
				data.add(btnAccetta, gbc_btnAccetta);
				
				btnAccetta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						accetta = true;
						setVisible(false);
					}
				});
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JSeparator separator = new JSeparator();
				buttonPane.add(separator);
			}
			{
				JButton okButton = new JButton("Chiudi");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						accetta = false;
						setVisible(false);
					}
				});
			}
		}	
	}
	

	/**
	 *  Ritorna true se abbiamo premuto accetta
	 */
	public boolean accettaAction(){
		return this.accetta; 
	}
	
	/**
	 *  Reinizializza i componenti
	 */
	public void reinit(){
		accetta = false;
		this.lblLista.setText("");
		btnAccetta.setEnabled(false);
		list.setSelectedIndex(-1);
	}
	
	/**
	 *  Ritorna id notifiche
	 */
	public String getIdNotificationWithAction(){
		return contenutoList[posListSelected+1][0].toString();
	}
	
	/**
	 *  Ritorna id lista
	 */
	public String getIdListNotificationWithAction(){
		return contenutoList[posListSelected+1][5].toString();
	}
	
	/**
	 *  Ritorna username proprietario notifica
	 */
	public String getUsernameOwnerNotificationWithAction(){
		return contenutoList[posListSelected+1][1].toString();
	}
	
	/**
	 *  Carica i dati nel componente list
	 *  
	 * @param list
	 * 				le liste da caricare
	 */
	public void loadList(final Object[][] list){
		this.contenutoList = list;
		this.list.setModel(new AbstractListModel() {
			public int getSize() {
				return list.length-1;
			}
			public Object getElementAt(int index) {
				return list[index+1][6];
			}
		});
	}
}