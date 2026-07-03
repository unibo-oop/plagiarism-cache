package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import model.Implementations.State;
import model.Interfaces.CultivationModel;
import view.dialog.AddCultivationDialog;
import view.dialog.CultivationDetailDialog;

public class CultivationPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private TableModel model;
	private JScrollPane scrollpane;
	private JPanel southpanel;
	private JButton backBtn;
	private JButton addCultivationBtn;
	private View view;
	private JComboBox<State> stateCombo;

	public CultivationPanel(View v) {
		this.setLayout(new BorderLayout());
		this.view = v;
		this.view.setTitle("Farm Management - Coltivazioni");
		
		this.stateCombo = new JComboBox<>();
		this.stateCombo.addItem(null);
		this.stateCombo.addItem(State.GROWING);
		this.stateCombo.addItem(State.HARVESTING);
		this.stateCombo.addItem(State.DONE);
		this.stateCombo.addActionListener(e -> {
			this.setCultivations((State) this.stateCombo.getSelectedItem());
		});
		this.add(this.stateCombo, BorderLayout.NORTH);
		
		this.model = new DefaultTableModel(new Object[][] {}, new String[] { "Campo", "Pianta", "Fornitura",
				"Data Inizio", "Data Fine", "Giorni Rimanenti", "Stato" });
		this.scrollpane = new JScrollPane();
		this.scrollpane.setEnabled(true);
		this.table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		this.setCultivations();
		this.table.setModel(model);
		this.scrollpane.setViewportView(table);
		this.add(scrollpane, BorderLayout.CENTER);
		this.table.setAutoCreateRowSorter(true);
		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				JDialog jd = new CultivationDetailDialog((String) model.getValueAt(row, 0),
						(String) model.getValueAt(row, 2));
				jd.setModal(true);
				jd.setVisible(true);
				/*non mi piace ma non so come fare...*/
				((CultivationPanel) ((JScrollPane) ((JViewport) ((JTable) e.getSource()).getParent()).getParent())
						.getParent()).setCultivations();
			}
		});

		this.southpanel = new JPanel(new FlowLayout());

		this.backBtn = new JButton("INDIETRO");
		this.backBtn.addActionListener(this);
		this.southpanel.add(backBtn);

		this.addCultivationBtn = new JButton("Aggiungi Coltivazione");
		this.addCultivationBtn.addActionListener(this);
		this.southpanel.add(addCultivationBtn);

		this.add(this.southpanel, BorderLayout.SOUTH);
	}

	private void setCultivations() {
		if (this.model.getRowCount() > 0) {
		    for (int i = this.model.getRowCount() - 1; i > -1; i--) {
		        ((DefaultTableModel)this.model).removeRow(i);
		    }
		}
		for (CultivationModel cm : Controller.getController().getCultivations()) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Object[] obj = { cm.getField().getFieldID(), cm.getSupply().getPlant().getName(),
					cm.getSupply().getSupply(), format.format(cm.getStartDate().getTime()),
					format.format(cm.getEndDate().getTime()), cm.getLeftDays(), cm.getState() };
			((DefaultTableModel) this.model).addRow(obj);
		}
	}

	private void setCultivations(State state) {
		if (this.model.getRowCount() > 0) {
		    for (int i = this.model.getRowCount() - 1; i > -1; i--) {
		        ((DefaultTableModel)this.model).removeRow(i);
		    }
		}
		if (state == null) {
			this.setCultivations();
		} else {
			for (CultivationModel cm : Controller.getController().getCultivations()) {
				if (cm.getState().equals(state)) {
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					Object[] obj = { cm.getField().getFieldID(), cm.getSupply().getPlant().getName(),
							cm.getSupply().getSupply(), format.format(cm.getStartDate().getTime()),
							format.format(cm.getEndDate().getTime()), cm.getLeftDays(), cm.getState() };
					((DefaultTableModel) this.model).addRow(obj);
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if (isPressed == this.backBtn) {
			this.view.remove(this);
			this.view.add(new NavigationPanel(this.view));
			this.view.pack();
		}
		if (isPressed == this.addCultivationBtn) {
			JDialog jd = new AddCultivationDialog();
			jd.setModal(true);
			jd.setVisible(true);
			this.setCultivations();
		}
	}

}
