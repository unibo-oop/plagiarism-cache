package view;

import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.BaseModelView;
import model.DataContainer;
import model.ReservationBean;
import util.Utilities;

/**
 * Class which implements the overview page from which the new, modification and
 * cancellation reservation use cases are triggered.
 * 
 * @author Oleksandr Melnychuk
 */

public class BaseView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int INDEX = 0;
	private final transient Utilities util = new Utilities();
	private BaseModelView modelView;

	/**
	 * Builds the main view
	 */
	public BaseView() {
		super();
		this.modelView = new BaseModelView();
		this.buildLayout();
		this.setVisible(true);
	}

	public void buildLayout() {
		List<ReservationBean> reservations = this.modelView.getReservations();
		Object[][] rowData = new Object[reservations.size()][];
		for (int i = 0; i < reservations.size(); i++) {
			ReservationBean rb = reservations.get(i);
			Object row[] = { rb.getId().toString(), rb.getTable().toString(), rb.getNumberOfPersons().toString(),
					Utilities.getFormattedTime(rb.getDate()), Utilities.getFormattedDate(rb.getDate()), rb.getEmail() };
			rowData[i] = row;
		}

		String[] columnNames = { "Numero di prenotazione", "Tavolo", "Numero di persona", "Ora", "Data", "Email" };

		JTable table = new JTable(rowData, columnNames);
		Dimension dimension = new Dimension();
		dimension.setSize(900, 500);
		table.setSize(dimension);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setMinWidth(150);
		table.getColumnModel().getColumn(5).setMinWidth(247);
		if (table.getCellSelectionEnabled()) {
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			int rowIndex = table.getSelectedRow();
			int colIndex = table.getSelectedColumn();
		}

		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Ignore extra messages.
				if (e.getValueIsAdjusting())
					return;

				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (lsm.isSelectionEmpty()) {
					System.out.println("No rows are selected.");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();
					javax.swing.table.TableModel model = table.getModel();
					String id = (String) model.getValueAt(selectedRow, 0);
					DataContainer.setData(DataContainer.SELECTED_RESERVATION_ID, new Integer(id));
				}
			}
		});
		JScrollPane scrp = new JScrollPane(table);
		scrp.setPreferredSize(new Dimension(900, 500));
		this.add(scrp);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
