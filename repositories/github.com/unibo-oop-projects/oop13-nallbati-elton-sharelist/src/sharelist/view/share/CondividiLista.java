package sharelist.view.share;

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
 * Condividi lista con altre persone
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class CondividiLista extends JDialog {

	private static final long serialVersionUID = 660502698170947650L;
	private Integer posListSelected;
	private final JPanel contentPanel = new JPanel();
	private boolean aggiungiPersona  = false;
	private boolean deleteMember = false;
	private Object[][] contenutoList;
	public JList list = new JList();
	public JLabel lblLista;
	private JButton btnEliminaMembro;
	
	/**
	 * Costruttore CondividiLista
	 * 
	 * @param view
	 * 				View
	 */
	public CondividiLista(ApplicationView view) {
		super(view);
		setResizable(false);
		setTitle("Condividi Lista");
		setSize(new Dimension(400, 400));
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
			gbl_data.rowHeights = new int[] {0, 0, 0, 0, 0, 10};
			gbl_data.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_data.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			data.setLayout(gbl_data);
			{
				JButton btnInvitaPersone = new JButton("+ Invita Persona");
				btnInvitaPersone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aggiungiPersona = true;
						setVisible(false);
					}
				});
				GridBagConstraints gbc_btnInvitaPersone = new GridBagConstraints();
				gbc_btnInvitaPersone.insets = new Insets(0, 0, 5, 0);
				gbc_btnInvitaPersone.gridx = 0;
				gbc_btnInvitaPersone.gridy = 0;
				data.add(btnInvitaPersone, gbc_btnInvitaPersone);
			}
			{
				lblLista = new JLabel("Lista");
				GridBagConstraints gbc_lblLista = new GridBagConstraints();
				gbc_lblLista.insets = new Insets(0, 0, 5, 0);
				gbc_lblLista.gridx = 0;
				gbc_lblLista.gridy = 2;
				data.add(lblLista, gbc_lblLista);
			}
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 3;
				data.add(panel, gbc_panel);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setPreferredSize(new Dimension(300, 200));
					panel.add(scrollPane);
					{
						this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						scrollPane.setViewportView(this.list);
						list.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								posListSelected = list.getSelectedIndex();
								btnEliminaMembro.setEnabled(true);
							}
						});
					}
				}
			}
			{
				btnEliminaMembro = new JButton("Elimina");
				btnEliminaMembro.setEnabled(false);
				GridBagConstraints gbc_btnEliminaMembro = new GridBagConstraints();
				gbc_btnEliminaMembro.gridx = 0;
				gbc_btnEliminaMembro.gridy = 4;
				data.add(btnEliminaMembro, gbc_btnEliminaMembro);
				
				btnEliminaMembro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteMember = true;
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
						deleteMember = false;
						aggiungiPersona = false;
						setVisible(false);
					}
				});
			}
		}	
	}
	
	/**
	 *  Ritorna true se abbiamo premuto invita persona
	 */
	public boolean invitaPersona(){
		return this.aggiungiPersona; 
	}
		
	/**
	 *  Ritorna true se abbiamo premuto cancella
	 */
	public boolean deleteMember(){
		return this.deleteMember; 
	}
	
	/**
	 *  Reinizializza i componenti
	 */
	public void reinit(){
		aggiungiPersona = false;
		deleteMember = false;
		this.lblLista.setText("");
		btnEliminaMembro.setEnabled(false);
	}
	
	/**
	 *  Ritorna id membro
	 */
	public String getMemberId(){
		return contenutoList[posListSelected+1][0].toString();
	}
	
	/**
	 *  Ritorna id lista
	 */
	public String getIdList(){
		return contenutoList[posListSelected+1][2].toString();
	}
	
	/**
	 *  Ritorna membro
	 */
	public String getMember(){
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
			/*public Object getElementAt(int index) {
				return list[index+1][1];
			}*/
			public Object getElementAt(int index) {
				return list[index+1][1]+" "+list[index+1][4]+" "+list[index+1][3];
			}
		});
	}
}