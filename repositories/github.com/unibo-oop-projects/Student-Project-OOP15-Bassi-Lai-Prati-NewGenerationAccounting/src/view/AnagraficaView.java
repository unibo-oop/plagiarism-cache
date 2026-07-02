/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.management.InstanceNotFoundException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.IAnagraficaViewObserver;
import dataModel.IDataTableModel;

/**
 * classe per le view che rispettano il layout di anagrafica, con i 5 tasti nel
 * footer.
 * 
 * @author Pentolo
 *
 */
public class AnagraficaView<E extends IDataTableModel> extends AbstractFrame {

	private final static String DEFAULT_TASTO_0 = "Cerca";
	private final static String DEFAULT_TASTO_1 = "Aggiungi";
	private final static String DEFAULT_TASTO_2 = "Modifica";
	private final static String DEFAULT_TASTO_3 = "Cancella";
	private final static String DEFAULT_TASTO_4 = "Chiudi";

	private static final long serialVersionUID = -1706093338606827050L;

	private final MyTableModel<E> tableModel;
	private IAnagraficaViewObserver observer;
	private final JTable table = new JTable();
	private final JButton tasto0 = new JButton();
	private final JButton tasto1 = new JButton();
	private final JButton tasto2 = new JButton();
	private final JButton tasto3 = new JButton();
	private final JButton tasto4 = new JButton();

	/**
	 * @param lista
	 *            lista degli elementi
	 * @param intestazione
	 *            intestazione delle colonne
	 * @param title
	 *            titolo della finestra
	 */
	public AnagraficaView(final LinkedList<E> lista, final String intestazione[], final String title) {
		this(lista, intestazione, title, DEFAULT_TASTO_0, DEFAULT_TASTO_1, DEFAULT_TASTO_2, DEFAULT_TASTO_3,
				DEFAULT_TASTO_4);
	}

	/**
	 * @param lista
	 *            lista degli elementi
	 * @param intestazione
	 *            intestazione delle colonne
	 * @param title
	 *            titolo della finestra
	 * @param testo0
	 *            stringa tasto
	 * @param testo1
	 *            stringa tasto
	 * @param testo2
	 *            stringa tasto
	 * @param testo3
	 *            stringa tasto
	 * @param testo4
	 *            stringa tasto
	 */
	public AnagraficaView(final LinkedList<E> lista, final String intestazione[], final String title,
			final String testo0, final String testo1, final String testo2, final String testo3, final String testo4) {
		super(title, new Dimension(500, 625));
		tasto0.setText(testo0);
		tasto1.setText(testo1);
		tasto2.setText(testo2);
		tasto3.setText(testo3);
		tasto4.setText(testo4);
		final JPanel footer = new JPanel(new FlowLayout());
		footer.add(tasto0);
		footer.add(tasto1);
		footer.add(tasto2);
		footer.add(tasto3);
		footer.add(tasto4);
		this.tableModel = new MyTableModel<E>(intestazione, lista);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		final JScrollPane scrollPane = new JScrollPane(table);
		table.setModel(tableModel);
		getMyFrame().getContentPane().add(scrollPane, BorderLayout.CENTER);
		getMyFrame().getContentPane().add(footer, BorderLayout.SOUTH);
		addListeners();
	}

	private void addListeners() {
		tasto0.addActionListener(e -> {
			getObserver().tasto0();
		});
		tasto1.addActionListener(e -> {
			getObserver().tasto1();
		});
		tasto2.addActionListener(e -> {
			getObserver().tasto2();
		});
		tasto3.addActionListener(e -> {
			getObserver().tasto3();
		});
		tasto4.addActionListener(e -> {
			getObserver().chiusura();
		});
	}

	@Override
	protected void chiusura() {
		getObserver().chiusura();
	}

	/**
	 * restituisce il controller
	 * 
	 * @return the observer
	 */
	public IAnagraficaViewObserver getObserver() {
		return observer;
	}

	/**
	 * restituisce l'elemento attualmente selezionato nella tabella. se niente è
	 * selezionato solleva una InstanceNotFoundException
	 * 
	 * @return l'elemtno selezioanto
	 * @throws InstanceNotFoundException
	 *             quando nulla è selezionato
	 */
	public IDataTableModel getSelectedItem() throws InstanceNotFoundException {
		final int row = getTable().getSelectedRow();
		if (row != -1) {
			return tableModel.getObjectAt(row);
		} else {
			throw new InstanceNotFoundException("Nessuna riga selezionata.");
		}
	}

	/**
	 * @return the JTable
	 */
	protected JTable getTable() {
		return table;
	}

	/**
	 * @return the table model
	 */
	protected MyTableModel<E> getTableModel() {
		return tableModel;
	}

	/**
	 * setta la lista degli item
	 * 
	 * @param lista
	 */
	public void setList(final LinkedList<E> lista) {
		getTableModel().setList(lista);
	}

	/**
	 * setta l'observer (controller) nella view
	 * 
	 * @param observer
	 *            il controller
	 */
	public void setObserver(final IAnagraficaViewObserver observer) {
		this.observer = observer;
	}
}
