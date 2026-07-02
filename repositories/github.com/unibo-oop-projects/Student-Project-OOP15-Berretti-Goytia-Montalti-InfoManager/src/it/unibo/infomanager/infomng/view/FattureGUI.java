package it.unibo.infomanager.infomng.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.infomanager.infomng.model.modelPurchasesI;
import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.toolbar.MyToolbar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * Classe che definice viewFatture.
 * 
 * @author Alessandro
 *
 */
public class FattureGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497349615019432641L;
	private static final String TITOLO = "Fatture";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(830, 568);
	private FattureGUI frame = this;
	private JTextField textNumeroOrdine;
	private JTextField textDataOrdine;
	private JTextField textFornitore;
	private JTextField textDal;
	private JTextField textAl;
	private JTextField textTipoOrdine;
	private JTextField textNegozio;
	private JTextField textFieldPagamento;
	private JTextField textFieldBanca;
	private JTextField textFieldNote;
	private JTextField textFieldSconto;
	private JTextField textFieldProdotto;
	private JTextField textFieldPrezzo;
	private JTextField textFieldIVA;
	private JTextField textFieldQuantita;
	private JLabel lblFornitore = new JLabel("Fornitore/Cliente");
	private JLabel lblDataOrdine = new JLabel("Data Ordine");
	private LinkedList<String> list = new LinkedList<>();
	private JLabel lblNumeroOrdine = new JLabel("Numero Ordine");
	private JLayeredPane layeredPane = new JLayeredPane();
	private MyToolbar toolBar;
	private JLabel lblConsegna = new JLabel("Consegna Dal");
	private JLabel lblAl = new JLabel("Al");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelTestata = new JPanel();
	private JLabel lblTipoOrdine = new JLabel("Tipo Ordine");
	private JLabel lblNegozio = new JLabel("Negozio");
	private JLabel lblPagamento = new JLabel("Pagamento");
	private JLabel lblBanca = new JLabel("Banca");
	private JLabel lblNote = new JLabel("Note");
	private JLabel lblSconto = new JLabel("Sconto");
	private JLabel lblPercento2 = new JLabel("%");
	private JLabel lblProdotto = new JLabel("Prodotto");
	private JLabel lblPrezzoUnitario = new JLabel("P.U.");
	private JLabel lblEuro = new JLabel("\u20AC");
	private JButton bAggiungi = new JButton("Aggiungi");
	private JLabel lblIva = new JLabel("IVA");
	private JLabel lblPercento1 = new JLabel("%");
	private JLabel lblQuantita = new JLabel("Quantit\u00E0");
	private JScrollPane scrollPaneFattura = new JScrollPane();
	private JTextArea textAreaFattura = new JTextArea("");
	private LinkedList<Map<String, Object>> products = new LinkedList<>();
	private final JPanel buttonPane = new JPanel();
	private final JButton btnSuccessivo = new JButton(">>");
	private final JButton btnPrecedente = new JButton("<<");

	/**
	 * Metodo per pulire i campi del frame.
	 */
	public void resetCampi() {
		this.textNumeroOrdine.setText("");
		this.textDataOrdine.setText("");
		this.textFornitore.setText("");
		this.textDal.setText("");
		this.textAl.setText("");
		this.textTipoOrdine.setText("");
		this.textNegozio.setText("");
		this.textFieldPagamento.setText("");
		this.textFieldBanca.setText("");
		this.textFieldNote.setText("");
		this.textFieldSconto.setText("");
		this.textFieldProdotto.setText("");
		this.textFieldPrezzo.setText("");
		this.textFieldIVA.setText("");
		this.textFieldQuantita.setText("");

	}

	/**
	 * Metodo per ottendere i dati dai TextField.
	 * 
	 * @return Map(String,Object)
	 * 
	 */
	public Map<String, Object> getTextfield() {
		Map<String, Object> mappa = new HashMap<>();
		mappa.put("NumeroOrdine", textNumeroOrdine.getText());
		mappa.put("DataOrdine", textDataOrdine.getText());
		mappa.put("Fornitore/Cliente", textFornitore.getText());
		mappa.put("Dal", textDal.getText());
		mappa.put("Al", textAl.getText());
		mappa.put("Tipo Ordine", textTipoOrdine.getText());
		mappa.put("Negozio", textNegozio.getText());
		mappa.put("Pagamento", textFieldPagamento.getText());
		mappa.put("Banca", textFieldBanca.getText());
		mappa.put("Note", textFieldNote.getText());
		mappa.put("Sconto", textFieldSconto.getText());
		mappa.put("IVA", textFieldIVA.getText());
		mappa.put("Prodotti", products);
		return mappa;
	}

	/**
	 * Costruttore del FattureGUI frame.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 */
	// CHECKSTYLE:OFF: checkstyle:magicnumber
	public FattureGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FattureGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setEnabled(true);
		toolBar = new MyToolbar(o, frame);
		getContentPane().add(toolBar);
		this.lblNumeroOrdine.setBounds(10, 11, 97, 14);
		this.layeredPane.add(lblNumeroOrdine);

		this.lblDataOrdine.setBounds(10, 54, 97, 14);
		this.layeredPane.add(lblDataOrdine);

		this.lblFornitore.setBounds(226, 14, 100, 14);
		this.layeredPane.add(lblFornitore);

		this.textNumeroOrdine = new JTextField();
		this.textNumeroOrdine.setBounds(117, 11, 86, 20);
		this.layeredPane.add(textNumeroOrdine);
		this.textNumeroOrdine.setColumns(10);

		this.textDataOrdine = new JTextField();
		textDataOrdine.setText("gg/mm/aaaa");
		textDataOrdine.setToolTipText("");
		this.textDataOrdine.setBounds(117, 54, 86, 20);
		this.layeredPane.add(textDataOrdine);
		this.textDataOrdine.setColumns(10);

		this.textFornitore = new JTextField();
		this.textFornitore.setBounds(336, 8, 86, 20);
		this.layeredPane.add(textFornitore);
		this.textFornitore.setColumns(10);

		this.lblConsegna.setBounds(432, 11, 86, 14);
		this.layeredPane.add(lblConsegna);

		this.lblAl.setBounds(432, 54, 86, 14);
		this.layeredPane.add(lblAl);

		this.textDal = new JTextField();
		textDal.setText("gg/mm/aaaa");
		this.textDal.setBounds(528, 8, 86, 20);
		this.layeredPane.add(textDal);
		this.textDal.setColumns(10);

		this.textAl = new JTextField();
		textAl.setText("gg/mm/aaaa");
		this.textAl.setBounds(528, 51, 86, 20);
		this.layeredPane.add(textAl);
		this.textAl.setColumns(10);

		this.tabbedPane.setBounds(10, 79, 797, 374);
		this.layeredPane.add(tabbedPane);

		this.tabbedPane.addTab("Testata", null, panelTestata, null);
		this.panelTestata.setLayout(null);

		this.lblTipoOrdine.setBounds(10, 11, 86, 14);
		this.panelTestata.add(lblTipoOrdine);

		this.textTipoOrdine = new JTextField();
		this.textTipoOrdine.setBounds(106, 11, 181, 20);
		this.panelTestata.add(textTipoOrdine);
		this.textTipoOrdine.setColumns(10);

		this.lblNegozio.setBounds(10, 59, 86, 14);
		this.panelTestata.add(lblNegozio);

		this.textNegozio = new JTextField();
		this.textNegozio.setBounds(106, 59, 181, 20);
		this.panelTestata.add(textNegozio);
		this.textNegozio.setColumns(10);

		this.lblPagamento.setBounds(10, 108, 86, 14);
		this.panelTestata.add(lblPagamento);

		this.textFieldPagamento = new JTextField();
		this.textFieldPagamento.setBounds(106, 108, 181, 20);
		this.panelTestata.add(textFieldPagamento);
		this.textFieldPagamento.setColumns(10);

		this.lblBanca.setBounds(10, 158, 86, 14);
		this.panelTestata.add(lblBanca);

		this.textFieldBanca = new JTextField();
		this.textFieldBanca.setBounds(106, 158, 181, 20);
		this.panelTestata.add(textFieldBanca);
		this.textFieldBanca.setColumns(10);

		this.lblNote.setBounds(10, 214, 86, 14);
		this.panelTestata.add(lblNote);

		this.textFieldNote = new JTextField();
		this.textFieldNote.setBounds(106, 214, 181, 124);
		this.panelTestata.add(textFieldNote);
		this.textFieldNote.setColumns(10);

		this.lblSconto.setBounds(315, 11, 72, 14);
		this.panelTestata.add(lblSconto);

		this.textFieldSconto = new JTextField();
		this.textFieldSconto.setBounds(387, 8, 86, 20);
		this.panelTestata.add(textFieldSconto);
		this.textFieldSconto.setColumns(10);

		this.lblPercento2.setBounds(483, 11, 21, 14);
		this.panelTestata.add(lblPercento2);

		this.lblProdotto.setBounds(315, 62, 72, 14);
		this.panelTestata.add(lblProdotto);

		this.textFieldProdotto = new JTextField();
		this.textFieldProdotto.setBounds(387, 59, 141, 20);
		this.panelTestata.add(textFieldProdotto);
		this.textFieldProdotto.setColumns(10);

		this.lblPrezzoUnitario.setBounds(538, 62, 26, 14);
		this.panelTestata.add(lblPrezzoUnitario);

		this.textFieldPrezzo = new JTextField();
		this.textFieldPrezzo.setBounds(574, 59, 86, 20);
		this.panelTestata.add(textFieldPrezzo);
		this.textFieldPrezzo.setColumns(10);

		this.lblEuro.setBounds(662, 62, 21, 14);
		this.panelTestata.add(lblEuro);

		this.bAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				Map<String, Object> temp = new HashMap<>();
				temp.put("Nome", textFieldProdotto.getText());
				temp.put("Quantita", textFieldQuantita.getText());
				temp.put("Prezzo", textFieldPrezzo.getText());
				products.add(temp);
				list.add("Prodotto:" + textFieldProdotto.getText() + "\tQuantita':" + textFieldQuantita.getText()
						+ "\tPrezzo:" + textFieldPrezzo.getText() + "\tSconto:" + textFieldSconto.getText() + "\tIVA:"
						+ textFieldIVA.getText());
				textAreaFattura.setText("");
				for (String elem : list) {
					textAreaFattura.append(elem);
					textAreaFattura.append("\n" + "=============================================" + "\n");
				}
				textFieldProdotto.setText("");
				textFieldQuantita.setText("");
				textFieldPrezzo.setText("");
				textFieldSconto.setText("");
			}
		});
		this.bAggiungi.setBounds(538, 104, 97, 23);
		this.panelTestata.add(bAggiungi);

		this.lblIva.setBounds(538, 11, 26, 14);
		this.panelTestata.add(lblIva);

		this.textFieldIVA = new JTextField();
		this.textFieldIVA.setBounds(574, 8, 86, 20);
		this.panelTestata.add(textFieldIVA);
		this.textFieldIVA.setColumns(10);

		this.lblPercento1.setBounds(670, 11, 46, 14);
		this.panelTestata.add(lblPercento1);

		this.lblQuantita.setBounds(315, 108, 46, 14);
		this.panelTestata.add(lblQuantita);

		this.textFieldQuantita = new JTextField();
		this.textFieldQuantita.setBounds(387, 105, 86, 20);
		this.panelTestata.add(textFieldQuantita);
		this.textFieldQuantita.setColumns(10);
		this.textAreaFattura.setEditable(true);

		this.scrollPaneFattura.setBounds(315, 153, 467, 193);
		this.scrollPaneFattura.setViewportView(textAreaFattura); // = new
																	// JScrollPane(textAreaFattura,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.panelTestata.add(scrollPaneFattura);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.getContentPane().add(layeredPane);

		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
		this.buttonPane.add(btnPrecedente);
		this.btnPrecedente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					o.setAttuale(frame);
					getNavigator().avanti();
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(o.getAttuale().get(),
							"Eseguire una ricerca per scorrere tra le fatture");
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
					JOptionPane.showMessageDialog(o.getAttuale().get(),
							"Eseguire una ricerca per scorrere tra le fatture");
				}

			}
		});
	}

	/**
	 * Metodo per settare i textfield.
	 * 
	 * @param o
	 *            oggetto Object
	 */
	public void setTextField(final Object o) {
		modelPurchasesI fatture = (modelPurchasesI) o;
		this.textAreaFattura.setText(fatture.purchasedProducts().iterator().toString());
		this.textFieldIVA.setText(String.valueOf(fatture.getIva()));
		this.textNumeroOrdine.setText(String.valueOf(fatture.getID()));
		this.textFornitore.setText(String.valueOf(fatture.getProvider()));
		this.textFieldSconto.setText(String.valueOf(fatture.getDiscount()));
		this.textDataOrdine.setText(String.valueOf(fatture.getDate()));
	}

}
