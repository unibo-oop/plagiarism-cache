package it.unibo.infomanager.infomng.view.tabelle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * Classe che definisce PannelloTabelle JPanel.
 * 
 * @author Alessandro
 *
 */
public class PannelloTabelle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6987439122200376290L;
	private TableModel dataModel = new BaseTabelle();
	private JTable table = new JTable(dataModel);;
	private JScrollPane scrollTabella = new JScrollPane(table);

	/**
	 * Costruttore PannelloTabelle panel.
	 */
	public PannelloTabelle() {
		this.add(scrollTabella);
	}
}
