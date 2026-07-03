package sharelist.view.application;

import sharelist.controller.ViewObserver;
import sharelist.view.share.CondividiLista;
import sharelist.view.share.AggiungiPersona;
import sharelist.view.share.CancellaMembro;
import sharelist.view.notification.CentroNotifiche;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.*;

/**
 * Main View dell'applicazione
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class ApplicationView extends JFrame implements ApplicationViewInterface {
	
	private static final long serialVersionUID = 7705436275822570577L;
	// Titolo window
	final public static String WINDOW_TITLE = "Sharelist";
	// Controllore
	private ViewObserver controller;
	// JDialog
	final private AggiungiLista aggiungiListaDialog = new AggiungiLista(this);
	final private ModificaLista modificaListaDialog = new ModificaLista(this);
	final private CancellaLista cancellaListaDialog = new CancellaLista(this);
	final private CondividiLista condividiListaDialog = new CondividiLista(this);
	final private CancellaMembro cancellaMembroDialog = new CancellaMembro(this);
	final private AggiungiAttivita aggiungiAttivitaDialog = new AggiungiAttivita(this);
	final private AggiungiPersona aggiungiPersonaDialog = new AggiungiPersona(this);
	final private CancellaAttivita cancellaAttivitaDialog = new CancellaAttivita(this);
	final private CentroNotifiche centroNotificheDialog = new CentroNotifiche(this);
	// JPanel
	private JPanel westPanel;
	private JPanel buttonPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panelModificaAttivita;
	// JTable
	private static String[] PROPS = new String[]{"Descrizione", "Completato"};
	private Object[][] INIT_DATA = new Object[][]{};
	private JTable table = new JTable(new DefaultTableModel(INIT_DATA,PROPS));
	// JList
	private JList list = new JList();
	// Variabili locali
	private Object[][] contenutoList;
	private Object[][] contenutoAttivita;
	private Object[][] contenutoNotifiche;
	private Object[][] attivita;
	private Integer posListSelected;
	private Integer posAttivitaSelected;
	private int n_notificiche;
	// Componenti Centro Notifiche
	private JButton btnCentroNotifiche;
	private JLabel lblUsername;
	// Componenti liste
	private JButton btnAggiungiUnaLista;
	private JButton btnModificaLista;
	private JButton btnCancellaLista;
	private JButton btnCondividi;
	// Componenti Attivita'
	private JButton btnAggiungiAttivita;
	private JButton btnModificaAttivita;
	private JButton btnCancellaAttivita;
	// Componenti Modifica Attivita'
	private JCheckBox chckbxCompletatoAttivita;
	private JLabel lblNoteAttivita;
	private JTextArea textAreaNoteAttivita;
	private JButton btnSalvaAttivita;
	private JLabel lblDescrizioneAttivita;
	private JTextField textFieldDescrizioneAttivita;
	
	/**
	 * Costruttore AggiungiAttivita
	 * 
	 * @param username
	 * 				username utente logato
	 */
	public ApplicationView(String username) {
		super(WINDOW_TITLE);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.buildLayout();
		this.setHandlers();
		this.setResizable(false);
		this.setVisible(true);
		this.lblUsername.setText(username);
	}
	
	/**
	 *  Carica i dati nel componente list
	 *  
	 * @param list
	 * 				le liste da caricare
	 */
	@Override
	public void loadList(final Object[][] list){
		this.contenutoList = list;
		this.list.setModel(new AbstractListModel() {
			public int getSize() {
				return list.length-1;
			}
			public Object getElementAt(int index) {
				return list[index+1][1];
			}
		});
	}
	
	/**
	 *  Carica i dati nel componente table
	 *  
	 * @param attivita
	 * 				le attivita da caricare
	 */
	@Override
	public void loadAttivita(final Object[][] attivita){
		this.attivita = attivita;
		final int n = attivita.length;
		this.contenutoAttivita = new Object[n-1][5];
		for (int i = 1; i < n; i++) {
			this.contenutoAttivita[i-1][0] = attivita[i][2];
			if((int)attivita[i][3] == 1){
				this.contenutoAttivita[i-1][1] = "SI";
			} else {
				this.contenutoAttivita[i-1][1] = "NO";
			}	
		}
		((DefaultTableModel) this.table.getModel()).setDataVector(this.contenutoAttivita, PROPS);
	}
	
	/**
	 *  Carica le notifiche al JDialog centroNotificheDialog
	 *  
	 * @param notifiche
	 * 				Array multidimensionale contenente tutte le notifiche utente
	 */
	@Override
	public void loadNotification(final Object[][] notifiche){
		this.contenutoNotifiche = notifiche;
		n_notificiche = 0;
		for (int i = 1; i < notifiche.length; i++) {
			if((Integer)notifiche[i][2]==0){
				n_notificiche++;
			}
		}
		String numero_notifiche = "Centro Notifiche ("+n_notificiche+")";
		this.btnCentroNotifiche.setText(numero_notifiche);
		this.contenutoNotifiche = new Object[notifiche.length-1][7];
		this.centroNotificheDialog.loadList(notifiche);
	}
	
	/**
	 *  Carica le notifiche al JDialog centroNotificheDialog
	 *  
	 * @param list
	 * 				Array multidimensionale contenente tutte le notifiche utente
	 */
	@Override
	public void loadMember(final Object[][] list){
		this.condividiListaDialog.loadList(list);
	}
	
	/**
	 *  Assegno il controllore a ApplicationView
	 *  
	 * @param controller
	 * 				Controllore
	 */
	@Override
	public void attachViewObserver(ViewObserver controller){
		this.controller = controller;
	}
	
	/**
	 *  Permette di fare visualizzare all'utente messagi d'errore provenienti dal server
	 *  
	 * @param message
	 * 				messaggio da visualizzare
	 */
	@Override
	public void commandFailed(String message) {
		JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);		
	}
	
	/**
	 *  Resetto il componente table
	 */
	@Override
	public void clearData() {
		((DefaultTableModel)this.table.getModel()).setDataVector(INIT_DATA,PROPS);
		
	}
	
	/**
	 *  Costruisco il Layout
	 */
	private void buildLayout(){	
		westPanel = new JPanel();
		resize();
		{
			panel_1 = new JPanel();
			panel_1.setBackground(Color.LIGHT_GRAY);
			getContentPane().add(panel_1, BorderLayout.NORTH);
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER));
			{
				lblUsername = new JLabel("New label");
				panel_1.add(lblUsername);
			}
			{
				panel_2 = new JPanel();
				panel_2.setBackground(Color.LIGHT_GRAY);
				panel_1.add(panel_2);
				GridBagLayout gbl_panel_2 = new GridBagLayout();
				gbl_panel_2.columnWidths = new int[] {232};
				gbl_panel_2.rowHeights = new int[] {0};
				gbl_panel_2.columnWeights = new double[]{0.0};
				gbl_panel_2.rowWeights = new double[]{0.0};
				panel_2.setLayout(gbl_panel_2);
				{
					btnCentroNotifiche = new JButton("Centro Notifiche");
					GridBagConstraints gbc_btnCentroNotifiche = new GridBagConstraints();
					gbc_btnCentroNotifiche.gridx = 0;
					gbc_btnCentroNotifiche.gridy = 0;
					panel_2.add(btnCentroNotifiche, gbc_btnCentroNotifiche);
				}
			}
		}
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		{
			JPanel data = new JPanel();
			westPanel.add(data);
			GridBagLayout gbl_data = new GridBagLayout();
			gbl_data.columnWidths = new int[]{0, 0, 232};
			gbl_data.rowHeights = new int[]{30, 0, 0, 0, 0, 0};
			gbl_data.columnWeights = new double[]{1.0, 0.0, 1.0};
			gbl_data.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
			
			data.setLayout(gbl_data);
			{
				JLabel lblListe_1 = new JLabel("Liste");
				GridBagConstraints gbc_lblListe_1 = new GridBagConstraints();
				gbc_lblListe_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblListe_1.gridx = 0;
				gbc_lblListe_1.gridy = 0;
				data.add(lblListe_1, gbc_lblListe_1);
			}
			{
				JLabel lblAttivit = new JLabel("Attivit\u00E0");
				GridBagConstraints gbc_lblAttivit = new GridBagConstraints();
				gbc_lblAttivit.insets = new Insets(0, 0, 5, 5);
				gbc_lblAttivit.gridx = 1;
				gbc_lblAttivit.gridy = 0;
				data.add(lblAttivit, gbc_lblAttivit);
			}
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 1;
				data.add(panel, gbc_panel);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setPreferredSize(new Dimension(200, 400));
					panel.add(scrollPane);
					{
						list.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								resize();
								posListSelected = list.getSelectedIndex();
								btnModificaLista.setEnabled(true);
								btnCancellaLista.setEnabled(true);
								btnCondividi.setEnabled(true);
								btnAggiungiAttivita.setEnabled(true);
								controller.commandLoadAttivitaFromView(contenutoList[posListSelected+1][0].toString());
								btnModificaAttivita.setEnabled(false);
								btnCancellaAttivita.setEnabled(false);
							}
						});
						this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						scrollPane.setViewportView(this.list);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 5, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 1;
				gbc_panel.gridy = 1;
				data.add(panel, gbc_panel);
				{
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							posAttivitaSelected = table.getSelectedRow();
							btnModificaAttivita.setEnabled(true);
							btnCancellaAttivita.setEnabled(true);
							caricaRestoAttivita();
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					JScrollPane scrollPane = new JScrollPane(table);
					scrollPane.setPreferredSize(new Dimension(400, 400));
					panel.add(scrollPane);
				}
			}
			{
				btnAggiungiUnaLista = new JButton("+ Aggiungi Lista");
				btnAggiungiUnaLista.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				{
					panelModificaAttivita = new JPanel();
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 0);
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 2;
					gbc_panel_1.gridy = 1;
					data.add(panelModificaAttivita, gbc_panel_1);
					GridBagLayout gbl_panel_1 = new GridBagLayout();
					gbl_panel_1.columnWidths = new int[]{205, 0};
					gbl_panel_1.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0};
					gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
					gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
					panelModificaAttivita.setLayout(gbl_panel_1);
					{
						chckbxCompletatoAttivita = new JCheckBox("Completato");
						GridBagConstraints gbc_chckbxCompletatoAttivita = new GridBagConstraints();
						gbc_chckbxCompletatoAttivita.insets = new Insets(0, 0, 5, 0);
						gbc_chckbxCompletatoAttivita.anchor = GridBagConstraints.NORTHWEST;
						gbc_chckbxCompletatoAttivita.gridx = 0;
						gbc_chckbxCompletatoAttivita.gridy = 0;
						panelModificaAttivita.add(chckbxCompletatoAttivita, gbc_chckbxCompletatoAttivita);
					}
					{
						lblDescrizioneAttivita = new JLabel("Descrizione:");
						GridBagConstraints gbc_lblDescrizioneAttivita = new GridBagConstraints();
						gbc_lblDescrizioneAttivita.anchor = GridBagConstraints.WEST;
						gbc_lblDescrizioneAttivita.insets = new Insets(0, 0, 5, 0);
						gbc_lblDescrizioneAttivita.gridx = 0;
						gbc_lblDescrizioneAttivita.gridy = 1;
						panelModificaAttivita.add(lblDescrizioneAttivita, gbc_lblDescrizioneAttivita);
					}
					{
						textFieldDescrizioneAttivita = new JTextField();
						GridBagConstraints gbc_textFieldDescrizioneAttivita = new GridBagConstraints();
						gbc_textFieldDescrizioneAttivita.insets = new Insets(0, 0, 5, 0);
						gbc_textFieldDescrizioneAttivita.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldDescrizioneAttivita.gridx = 0;
						gbc_textFieldDescrizioneAttivita.gridy = 2;
						panelModificaAttivita.add(textFieldDescrizioneAttivita, gbc_textFieldDescrizioneAttivita);
						textFieldDescrizioneAttivita.setColumns(10);
					}
					{
						lblNoteAttivita = new JLabel("Note:");
						GridBagConstraints gbc_lblNoteAttivita = new GridBagConstraints();
						gbc_lblNoteAttivita.anchor = GridBagConstraints.WEST;
						gbc_lblNoteAttivita.insets = new Insets(0, 0, 5, 0);
						gbc_lblNoteAttivita.gridx = 0;
						gbc_lblNoteAttivita.gridy = 3;
						panelModificaAttivita.add(lblNoteAttivita, gbc_lblNoteAttivita);
					}
					{
						textAreaNoteAttivita = new JTextArea();
						GridBagConstraints gbc_textAreaNoteAttivita = new GridBagConstraints();
						gbc_textAreaNoteAttivita.insets = new Insets(0, 0, 5, 0);
						gbc_textAreaNoteAttivita.fill = GridBagConstraints.BOTH;
						gbc_textAreaNoteAttivita.gridx = 0;
						gbc_textAreaNoteAttivita.gridy = 4;
						panelModificaAttivita.add(textAreaNoteAttivita, gbc_textAreaNoteAttivita);
					}
				}
				GridBagConstraints gbc_btnAggiungiUnaLista = new GridBagConstraints();
				gbc_btnAggiungiUnaLista.insets = new Insets(0, 0, 5, 5);
				gbc_btnAggiungiUnaLista.gridx = 0;
				gbc_btnAggiungiUnaLista.gridy = 2;
				data.add(btnAggiungiUnaLista, gbc_btnAggiungiUnaLista);
			}
			{
				btnAggiungiAttivita = new JButton("+ Aggiungi Attivit\u00E0");
				btnAggiungiAttivita.setEnabled(false);
				GridBagConstraints gbc_btnAggiungiAttivita = new GridBagConstraints();
				gbc_btnAggiungiAttivita.insets = new Insets(0, 0, 5, 5);
				gbc_btnAggiungiAttivita.gridx = 1;
				gbc_btnAggiungiAttivita.gridy = 2;
				data.add(btnAggiungiAttivita, gbc_btnAggiungiAttivita);
			}
			{
				btnSalvaAttivita = new JButton("Salva");
				GridBagConstraints gbc_btnSalva = new GridBagConstraints();
				gbc_btnSalva.insets = new Insets(0, 0, 5, 0);
				gbc_btnSalva.gridx = 2;
				gbc_btnSalva.gridy = 2;
				data.add(btnSalvaAttivita, gbc_btnSalva);
			}
			{
				btnModificaLista = new JButton("Modifica Lista");
				btnModificaLista.setEnabled(false);
				GridBagConstraints gbc_btnModificaLista = new GridBagConstraints();
				gbc_btnModificaLista.insets = new Insets(0, 0, 5, 5);
				gbc_btnModificaLista.gridx = 0;
				gbc_btnModificaLista.gridy = 3;
				data.add(btnModificaLista, gbc_btnModificaLista);
			}
			{
				btnModificaAttivita = new JButton("Modifica Attivita'");
				btnModificaAttivita.setEnabled(false);
				GridBagConstraints gbc_btnModificaAttivita = new GridBagConstraints();
				gbc_btnModificaAttivita.insets = new Insets(0, 0, 5, 5);
				gbc_btnModificaAttivita.gridx = 1;
				gbc_btnModificaAttivita.gridy = 3;
				data.add(btnModificaAttivita, gbc_btnModificaAttivita);
			}
			{
				btnCancellaLista = new JButton("Cancella Lista");
				btnCancellaLista.setEnabled(false);
				GridBagConstraints gbc_btnCancellaLista = new GridBagConstraints();
				gbc_btnCancellaLista.insets = new Insets(0, 0, 5, 5);
				gbc_btnCancellaLista.gridx = 0;
				gbc_btnCancellaLista.gridy = 4;
				data.add(btnCancellaLista, gbc_btnCancellaLista);
			}
			{
				btnCancellaAttivita = new JButton("Cancella Attivit\u00E0");
				btnCancellaAttivita.setEnabled(false);
				GridBagConstraints gbc_btnCancellaAttivita = new GridBagConstraints();
				gbc_btnCancellaAttivita.insets = new Insets(0, 0, 5, 5);
				gbc_btnCancellaAttivita.gridx = 1;
				gbc_btnCancellaAttivita.gridy = 4;
				data.add(btnCancellaAttivita, gbc_btnCancellaAttivita);
			}
			{
				btnCondividi = new JButton("+ Condividi Lista");
				btnCondividi.setEnabled(false);
				GridBagConstraints gbc_btnCondividi = new GridBagConstraints();
				gbc_btnCondividi.insets = new Insets(0, 0, 0, 5);
				gbc_btnCondividi.gridx = 0;
				gbc_btnCondividi.gridy = 5;
				data.add(btnCondividi, gbc_btnCondividi);
			}
		}
		{
			{
				buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
			}
		}
	}

	/**
	 *  Setto gli ascoltatori
	 */
	private void setHandlers(){
		this.btnCentroNotifiche.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				centroNotificheDialog.reinit();
				controller.commandLoadNotification();
				centroNotificheDialog.lblLista.setText(n_notificiche+" nuove notifiche.");
				controller.commandReadNotification();
				centroNotificheDialog.setVisible(true);
			}
		});
		this.btnAggiungiUnaLista.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				aggiungiListaDialog.reinit();
				aggiungiListaDialog.setVisible(true);
			}
		});
		this.btnModificaLista.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				modificaListaDialog.reinit();
				modificaListaDialog.textField_ModificaLista.setText((String)contenutoList[posListSelected+1][1]);
				modificaListaDialog.setVisible(true);
			}
		});
		this.btnCancellaLista.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				cancellaListaDialog.reinit();
				cancellaListaDialog.textField_ModificaLista.setText((String)contenutoList[posListSelected+1][1]);
				cancellaListaDialog.setVisible(true);
			}
		});
		this.btnCondividi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				controller.commandLoadMember(contenutoList[posListSelected+1][0].toString());
				condividiListaDialog.lblLista.setText("Membri Lista: "+(String)contenutoList[posListSelected+1][1]);
				condividiListaDialog.setVisible(true);
				condividiListaDialog.reinit();
			}
		});
		this.btnAggiungiAttivita.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				aggiungiAttivitaDialog.reinit();
				aggiungiAttivitaDialog.setVisible(true);
			}
		});
		this.btnModificaAttivita.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				expand();
				caricaRestoAttivita();
			}
		});
		this.btnCancellaAttivita.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				cancellaAttivitaDialog.reinit();
				cancellaAttivitaDialog.textField_CancellaAttivita.setText((String)contenutoAttivita[posAttivitaSelected][0]);
				cancellaAttivitaDialog.setVisible(true);
			}
		});
		this.btnSalvaAttivita.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resize();
				String completato;
				if(chckbxCompletatoAttivita.isSelected()){
					completato = "1";
				} else {
					completato = "0";
				}
				Object[] o = new Object[]{
						contenutoList[posListSelected+1][0].toString(),
						attivita[posAttivitaSelected+1][0].toString(),
						textFieldDescrizioneAttivita.getText(),
						completato,
						textAreaNoteAttivita.getText()
				};
				controller.commandEditAttivita(o);
			}
		});
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				quitHandler();
			}
		});
		this.aggiungiListaDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (aggiungiListaDialog.getOKState()){
					Object[] o = new Object[]{	
							aggiungiListaDialog.getNuovaLista()
					};
					controller.commandAddList(o);
				}
			}
		});
		this.modificaListaDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (modificaListaDialog.getOKState()){
					Object[] o = new Object[]{
							contenutoList[posListSelected+1][0].toString(),
							modificaListaDialog.getModificaLista()
					};
					controller.commandEditList(o);
				}
			}
		});
		this.cancellaListaDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (cancellaListaDialog.getOKState()){
					Object[] o = new Object[]{
							contenutoList[posListSelected+1][0].toString()
					};
					controller.commandDeleteList(o);
				}
			}
		});
		this.aggiungiAttivitaDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (aggiungiAttivitaDialog.getOKState()){
					Object[] o = new Object[]{
							contenutoList[posListSelected+1][0].toString(),
							aggiungiAttivitaDialog.getNuovaAttivita()
					};
					controller.commandAddAttivita(o);
				}
			}
		});
		this.cancellaAttivitaDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (cancellaAttivitaDialog.getOKState()){
					Object[] o = new Object[]{
							contenutoList[posListSelected+1][0].toString(),
							attivita[posAttivitaSelected+1][0].toString()
					};
					controller.commandDeleteAttivita(o);
				}
			}
		});
		this.condividiListaDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (condividiListaDialog.deleteMember()){
					cancellaMembroDialog.textField_CancellaMembro.setText(condividiListaDialog.getMember());
					cancellaMembroDialog.setVisible(true);
				}
				if (condividiListaDialog.invitaPersona()){
					aggiungiPersonaDialog.setVisible(true);
				}
			}
		});
		this.cancellaMembroDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (cancellaMembroDialog.getOKState()){
					controller.commandDeleteMember(condividiListaDialog.getMemberId(), condividiListaDialog.getIdList());
					//Ricarico elenco liste
					controller.commandLoadList();
					btnModificaLista.setEnabled(false);
					btnCancellaLista.setEnabled(false);
					btnCondividi.setEnabled(false);
					btnAggiungiAttivita.setEnabled(false);
					btnModificaAttivita.setEnabled(false);
					btnCancellaAttivita.setEnabled(false);
				} else {
					condividiListaDialog.setVisible(true);
				}
			}
		});
		this.aggiungiPersonaDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (aggiungiPersonaDialog.getOKState()){
					controller.commandAggiungiMember(aggiungiPersonaDialog.getNuovaPersona(), contenutoList[posListSelected+1][0].toString(), contenutoList[posListSelected+1][1].toString());
					condividiListaDialog.setVisible(true);
					condividiListaDialog.reinit();
				} else {
					condividiListaDialog.setVisible(true);
				}
			}
		});
		this.centroNotificheDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (centroNotificheDialog.accettaAction()){
					centroNotificheDialog.setVisible(false);
					controller.commandAccettaListaNotification(centroNotificheDialog.getIdNotificationWithAction(), centroNotificheDialog.getIdListNotificationWithAction());
				}
			}
		});
	}
	
	/**
	 *  Setto i componenti, quando sono nello stato di modifica attivita'
	 */
	private void caricaRestoAttivita(){
		if((int)attivita[posAttivitaSelected+1][3] == 1){
			chckbxCompletatoAttivita.setSelected(true);
		} else {
			chckbxCompletatoAttivita.setSelected(false);
		}
		textFieldDescrizioneAttivita.setText(attivita[posAttivitaSelected+1][2].toString());
		textAreaNoteAttivita.setText(attivita[posAttivitaSelected+1][4].toString());
	}
	
	/**
	 *  Risetto la dimensione della window a dimensione normale
	 */
	private void resize(){
		setSize(new Dimension(630, 650));
		setLocationRelativeTo(null);
	}
	
	/**
	 *  Espande la dimensione della window
	 */
	private void expand(){
		setSize(new Dimension(875, 650));
		setLocationRelativeTo(null);
	}
	
	/**
	 *  Chiedo all'utente conferma per uscire dall'applicazione
	 */
	private void quitHandler(){
		int n = JOptionPane.showConfirmDialog(this,
			    "Vuoi chiudere veramente?",
			    "Sto chiudendo..", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			controller.commandQuit();
		}
	}
}
