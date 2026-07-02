package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import notwist.base.Category;
import notwist.base.Discussion;
import notwist.base.User;
import notwist.database.DBDiscussion;
import notwist.database.DBDiscussionImpl;
import notwist.database.DBUserImpl;
import topic_gui.Topic_gui;

public class TableDiscussion {

	private JTable tableDiscussion;
	private DefaultTableModel modelDiscussion;
	private DBDiscussion dbdiscussion = new DBDiscussionImpl();

	public TableDiscussion(final User user) {
		tableDiscussion = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableDiscussion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		modelDiscussion = new DefaultTableModel(
				new Object[] { "Titolo", "Categoria", "Like", "Created By", "Risposte", "ID" }, 0);
		tableDiscussion.setModel(modelDiscussion);

		tableDiscussion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		tableDiscussion.setAutoscrolls(false);
		tableDiscussion.setEnabled(true);
		tableDiscussion.setGridColor(new java.awt.Color(0, 0, 0));
		tableDiscussion.setShowGrid(true);
		if (tableDiscussion.getColumnModel().getColumnCount() > 0) {
			tableDiscussion.getColumnModel().getColumn(0).setResizable(false);
			tableDiscussion.getColumnModel().getColumn(1).setResizable(false);
			tableDiscussion.getColumnModel().getColumn(2).setResizable(false);
			tableDiscussion.getColumnModel().getColumn(3).setResizable(false);
			tableDiscussion.getColumnModel().getColumn(4).setResizable(false);
			tableDiscussion.getColumnModel().getColumn(5).setResizable(false);


		}
		tableDiscussion.getColumnModel().getColumn(0).setPreferredWidth(320);
		tableDiscussion.getColumnModel().getColumn(1).setPreferredWidth(108);
		tableDiscussion.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableDiscussion.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableDiscussion.getColumnModel().getColumn(4).setPreferredWidth(90);
		tableDiscussion.getColumnModel().getColumn(5).setPreferredWidth(0);

	
		tableDiscussion.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if(mouseEvent.getClickCount() == 2) {
					
					Discussion d = dbdiscussion.getDiscussionFromId(
							Integer.parseInt(tableDiscussion.getModel().getValueAt(tableDiscussion.getSelectedRow(), 5).toString())).get();
			new Topic_gui(d, user);
				}
			}
		});

	}
		
		


	public JTable getTableDiscussion() {
		return tableDiscussion;
	}

	public DefaultTableModel getModelDiscussion() {
		return modelDiscussion;
	}

	/*--------------UTIL METHODS?? ----------*/

	public void refreshTableDiscussion() {
		this.getModelDiscussion().getDataVector().removeAllElements();
		loadDiscussion(this.getModelDiscussion());
		tableDiscussion.setModel(this.getModelDiscussion());
		tableDiscussion.revalidate();
	}

	public void refreshTableDiscussion(Category category) {
		this.getModelDiscussion().getDataVector().removeAllElements();
		loadDiscussion(this.getModelDiscussion(), category);
		tableDiscussion.setModel(this.getModelDiscussion());
		tableDiscussion.revalidate();
	}

	public void refreshTableDiscussion(String filter) {
		this.getModelDiscussion().getDataVector().removeAllElements();
		loadDiscussion(this.getModelDiscussion(), filter);
		tableDiscussion.setModel(this.getModelDiscussion());
		tableDiscussion.revalidate();
	}

	private void loadDiscussion(DefaultTableModel model) {
		for (Discussion s : dbdiscussion.getAllDiscussion().get()) {
			model.addRow(new Object[] { s.getTitle(), s.getCategory().getName(), 0,
					new DBUserImpl().getUserFromId(s.getIdUser()).get().getUsername(), 0, s.getIdDiscussion() });
		}
	}

	private void loadDiscussion(DefaultTableModel model, final Category category) {
		for (Discussion s : dbdiscussion.getAllDiscussion(category).get()) {
			model.addRow(new Object[] { s.getTitle(), s.getCategory().getName(), 0,
					new DBUserImpl().getUserFromId(s.getIdUser()).get().getUsername(), 0, s.getIdDiscussion() });
		}
	}

	private void loadDiscussion(DefaultTableModel model, final String filter) {
		for (Discussion s : dbdiscussion.getAllDiscussion(filter).get()) {
			model.addRow(new Object[] { s.getTitle(), s.getCategory().getName(), 0,
					new DBUserImpl().getUserFromId(s.getIdUser()).get().getUsername(), 0, s.getIdDiscussion() });
		}
	}

}
