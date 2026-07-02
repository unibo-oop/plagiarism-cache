package it.unibo.infomanager.infomng.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import it.unibo.infomanager.infomng.model.modelReceiptsI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.toolbar.MyToolbar;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Classe che definisce viewScontrini.
 * 
 * @author Alessandro
 *
 */
public class ScontriniGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3626871331020606696L;
	private static final String TITOLO = "Scontrini";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(664, 413);
	private ScontriniGUI frame = this;
	private JTextField txtProdotto;
	private JTextField txtPrezzoUnitario;
	private JTextField txtQuantita;
	private JTextField txtIva;
	private JTextField txtSconto;
	private JTextField txtClienteFornitore;
	private JTextArea textArea = new JTextArea();
	private JLabel lblProdotto = new JLabel("Prodotto");
	private JLabel lblPrezzoUnitario = new JLabel("Prezzo Unitario");
	private JLabel lblQuantita = new JLabel("Quanti\u00E0");
	private JLabel lblIva = new JLabel("IVA");
	private JLabel lblSconto = new JLabel("Sconto");
	private JButton btnStampa = new JButton("Stampa");
	private JLabel lblClienteFornitore = new JLabel("Cliente/Fornitore");
	private JLabel lblNewLabel = new JLabel("\u20AC");
	private JLabel label = new JLabel("%");
	private JLabel labelPercento = new JLabel("%");
	private MyToolbar toolbar;
	private JPanel panelTool = new JPanel();
	private JPanel panelText = new JPanel();
	private GroupLayout gcontentPane = new GroupLayout(panelText);
	private double imponibile;
	private double totale;
	private double totiva;
	private double totsconto;
	private double pperq;
	private JScrollPane scrollArea = new JScrollPane();
	private final JButton btnAggiungi = new JButton("Aggiungi");
	private final JLabel lblAnteprimaDiStampa = new JLabel("Anteprima di Stampa");
	private LinkedList<String> lista = new LinkedList<>();
	private String prodotti = new String();
	private final JPanel buttonPane = new JPanel();
	private final JButton btnPrecedente = new JButton("<<");
	private final JButton btnSuccessivo = new JButton(">>");

	/**
	 * Metodo che resetta i TextField del frame.
	 */
	public void resetCampi() {
		this.txtIva.setText("");
		this.txtPrezzoUnitario.setText("");
		this.txtProdotto.setText("");
		this.txtQuantita.setText("");
		this.txtSconto.setText("");
		this.txtClienteFornitore.setText("");
	}
	/**
	 * Metodo per settare i textfield.
	 * @param o
	 * 			oggetto Object
	 */			
	public void setTextField(final Object o) {
		modelReceiptsI scontrino = (modelReceiptsI) o;
		this.txtIva.setText(String.valueOf(scontrino.getIVA()));
		this.txtProdotto.setText(String.valueOf(scontrino.getID()));
		this.txtClienteFornitore.setText(String.valueOf(scontrino.getNumberReceipt()));	
	}

	/**
	 * Costruttore del  ScontriniGUI frame.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	// CHECKSTYLE:OFF: checkstyle:magicnumber
	ScontriniGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ScontriniGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.getMainPanel().setLayout(new BorderLayout(0, 0));
		this.getMainPanel().add(panelTool, BorderLayout.CENTER);
		this.panelTool.setLayout(new BorderLayout(0, 0));
		this.toolbar = new MyToolbar(o, frame);
		this.panelTool.add(toolbar, BorderLayout.NORTH);
		this.panelTool.add(panelText);
		this.txtProdotto = new JTextField();
		this.txtProdotto.setColumns(10);

		this.txtPrezzoUnitario = new JTextField();
		this.txtPrezzoUnitario.setColumns(10);

		this.txtQuantita = new JTextField();
		this.txtQuantita.setColumns(10);

		this.txtIva = new JTextField();
		this.txtIva.setColumns(10);

		this.txtSconto = new JTextField();
		this.txtSconto.setColumns(10);
		this.scrollArea.setViewportView(textArea);
		this.textArea.setEditable(false);

		this.txtClienteFornitore = new JTextField();
		this.txtClienteFornitore.setColumns(10);

		this.gcontentPane
				.setHorizontalGroup(
						this.gcontentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gcontentPane.createSequentialGroup()
												.addGroup(gcontentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gcontentPane.createSequentialGroup().addContainerGap()
																		.addGroup(
																				gcontentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(lblProdotto)
																						.addComponent(lblPrezzoUnitario)
																						.addComponent(lblQuantita)
																						.addComponent(lblIva)
																						.addComponent(lblSconto)
																						.addComponent(
																								lblClienteFornitore))
								.addGap(40)
								.addGroup(
										gcontentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtClienteFornitore)
												.addComponent(txtProdotto, GroupLayout.DEFAULT_SIZE, 160,
														Short.MAX_VALUE)
												.addComponent(txtPrezzoUnitario).addComponent(txtQuantita)
												.addComponent(txtIva).addComponent(txtSconto))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
								.addGroup(gcontentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label).addComponent(labelPercento)))
						.addGroup(gcontentPane.createSequentialGroup().addGap(54)
								.addComponent(btnStampa, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGap(40).addComponent(btnAggiungi, GroupLayout.PREFERRED_SIZE, 86,
										GroupLayout.PREFERRED_SIZE))).addGroup(
												gcontentPane.createParallelGroup(Alignment.TRAILING)
														.addGroup(gcontentPane.createSequentialGroup().addGap(31)
																.addComponent(scrollArea, GroupLayout.DEFAULT_SIZE, 293,
																		Short.MAX_VALUE)
																.addContainerGap())
										.addGroup(gcontentPane.createSequentialGroup().addGap(131)
												.addComponent(lblAnteprimaDiStampa, GroupLayout.PREFERRED_SIZE, 99,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap(104, Short.MAX_VALUE)))));
		this.gcontentPane.setVerticalGroup(this.gcontentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gcontentPane.createSequentialGroup().addGroup(gcontentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gcontentPane.createSequentialGroup().addGap(58)
								.addGroup(gcontentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblProdotto)
										.addComponent(txtProdotto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(33)
								.addGroup(gcontentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPrezzoUnitario)
										.addComponent(txtPrezzoUnitario, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
								.addGap(30)
								.addGroup(gcontentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblQuantita)
										.addComponent(txtQuantita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(29)
								.addGroup(gcontentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblIva)
										.addComponent(txtIva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label))
								.addGap(29)
								.addGroup(gcontentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblSconto)
										.addComponent(txtSconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelPercento)))
						.addGroup(
								gcontentPane.createSequentialGroup().addContainerGap()
										.addGroup(gcontentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblClienteFornitore).addComponent(txtClienteFornitore,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
						.addGroup(gcontentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnStampa)
								.addComponent(btnAggiungi))
						.addGap(28))
				.addGroup(gcontentPane.createSequentialGroup().addContainerGap().addComponent(lblAnteprimaDiStampa)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollArea, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE).addContainerGap()));
		this.panelText.setLayout(gcontentPane);
		
		this.getMainPanel().add(buttonPane, BorderLayout.SOUTH);
		
		this.buttonPane.add(btnPrecedente);
		this.btnPrecedente.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					o.setAttuale(frame);
					getNavigator().indietro();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Eseguire una ricerca per scorrere tra gli scontrini");
				}
				
			}
		});
		
		this.buttonPane.add(btnSuccessivo);
		this.btnSuccessivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					o.setAttuale(frame);
					getNavigator().avanti();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(o.getAttuale().get(), "Eseguire una ricerca per scorrere tra gli scontrini");
				}
				
			}
		});
		this.btnStampa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					imponibile = 0;
					totale = 0;
					totiva = 0;
					totsconto = 0;
					lista.add(txtQuantita.getText());
					lista.add(txtProdotto.getText());
					lista.add(txtPrezzoUnitario.getText());
					textArea.setText("");
					for (Integer i=0; i<lista.size()-2; i=i+3){
						pperq = pperq + (Integer.parseInt(lista.get(i+2)) * Integer.parseInt(lista.get(i)));
						totale = totale + calcola();
						prodotti = prodotti + "Quantita': " + lista.get(i) + " " + lista.get(i+1)
						+ "     Prezzo: " + lista.get(i+2) + "\u20AC \n";
					}
					textArea.setText(prodotti + "\nSconto: "
							+ txtSconto.getText() + "\n\n =====================================\n\n" + "Sconto: "
							+ txtSconto.getText() + "%" + "\t\t" + String.valueOf(totsconto) + "\nImponibile:\t\t"
							+ String.valueOf(imponibile) + "\u20AC\nIva: " + txtIva.getText() + "%" + "\t\t"
							+ String.valueOf(totiva) + "\u20AC" + "\n\n =====================================\n\n"
							+ "Totale:\t\t\t" + String.valueOf(totale) + "\u20AC");
					o.salvaScontrini(getTextField());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame, "Inserisci i campi correttamente", "Campi scorretti",
							JOptionPane.ERROR_MESSAGE);
					
				}
				;
			}
		});
		this.btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				lista.add(txtQuantita.getText());
				lista.add(txtProdotto.getText());
				lista.add(txtPrezzoUnitario.getText());
				txtQuantita.setText("");
				txtPrezzoUnitario.setText("");
				txtProdotto.setText("");
			}
		});

	}

	/**
	 * Metodo per ottenere i dati dai TextField
	 * 
	 * @return Map (String,Object)
	 */
	public Map<String, Object> getTextField() {
		Map<String, Object> mappa = new HashMap<>();
		mappa.put("Prodotti", lista);
		mappa.put("Iva", txtIva.getText());
		mappa.put("Sconto", txtSconto.getText());
		mappa.put("Scontrino", txtClienteFornitore.getText());
		mappa.put("Cliente/Fornitore", txtClienteFornitore.getText());
		return mappa;
	}

	/**
	 * Metodo che calcola il prezzo in tempo reale
	 * 
	 * @param prezzo
	 *            Oggetto Integer
	 * @param quantita
	 *            Oggetto Integer
	 * @return Total
	 */
	private double calcola() {
		if (txtSconto.getText().equals("")) {
			this.totiva = (this.imponibile * Integer.parseInt(txtIva.getText())) / 100;
			this.totale = this.totiva + this.imponibile;
		} else {
			this.totsconto = (this.pperq * Integer.parseInt(txtSconto.getText()) / 100);
			this.imponibile = this.pperq - this.totsconto;
			this.totiva = (this.imponibile * Integer.parseInt(txtIva.getText())) / 100;
			this.totale = this.totiva + this.imponibile;
		}
		return this.totale;

	}
	
}
