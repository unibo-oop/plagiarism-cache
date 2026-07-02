/**
 *@author Ceccarelli 
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.BookModel;
import view.observer.WarehouseObserver;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class WarehousePanelImpl extends JPanel implements WarehousePanel, ActionListener {

	/**
	 * Create the panel.
	 */
	private WarehouseObserver observer;
	private JTable tblAllBooks;
	private DefaultTableModel modelAllBooks;
	private JScrollPane scpAllBooks;
	private JLabel lblWareHouseTitle;
	private JLabel lblAmountTitle;
	private JButton btnRemoveOne;
	private JButton btnRemoveTen;
	private JButton btnAddOne;
	private JButton btnAddTen;
	private JButton btnAddToBookShop;
	private static final long serialVersionUID = 1L;
	private JLabel lblAmount;
	private JButton btnAddCopyToWarehouse;
	private final int titleCell = 0;
	private final int authorCell = 1;
	private final int genreCell = 2;
	private final int yearCell = 3;
	private final int priceCell = 4;
	private final int amountCell = 5;

	public WarehousePanelImpl() {
		setBackground(SystemColor.activeCaption);
		this.setLayout(null);
		modelAllBooks = new DefaultTableModel(new Object[][] {},
				new String[] { "Titolo", "Autore", "Genere", "Anno P.", "Prezzo", "Rimanenze" });
		scpAllBooks = new JScrollPane();
		scpAllBooks.setEnabled(false);
		scpAllBooks.setBounds(20, 88, 486, 464);
		add(scpAllBooks);

		tblAllBooks = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scpAllBooks.setViewportView(tblAllBooks);
		tblAllBooks.setModel(modelAllBooks);
		tblAllBooks.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblAllBooks.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblAllBooks.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblAllBooks.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblAllBooks.getColumnModel().getColumn(4).setPreferredWidth(50);
		tblAllBooks.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblAllBooks.setFont(new Font("Calibri", Font.PLAIN, 13));
		tblAllBooks.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblAllBooks.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					if ((Integer) modelAllBooks.getValueAt(row, amountCell) < 1) {
						btnAddToBookShop.setEnabled(false);
					} else {
						btnAddToBookShop.setEnabled(true);
					}
				}
			}
		});

		lblWareHouseTitle = new JLabel("Magazzino");
		lblWareHouseTitle.setForeground(new Color(255, 69, 0));
		lblWareHouseTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		lblWareHouseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblWareHouseTitle.setBounds(10, 11, 880, 66);
		add(lblWareHouseTitle);

		lblAmountTitle = new JLabel("Quantità:");
		lblAmountTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblAmountTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmountTitle.setBounds(680, 88, 192, 14);
		add(lblAmountTitle);

		btnRemoveOne = new JButton("-");
		btnRemoveOne.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		btnRemoveOne.setBounds(680, 133, 50, 50);
		btnRemoveOne.addActionListener(this);
		add(btnRemoveOne);

		btnRemoveTen = new JButton("--");
		btnRemoveTen.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		btnRemoveTen.setBounds(714, 187, 50, 50);
		btnRemoveTen.addActionListener(this);
		add(btnRemoveTen);

		btnAddOne = new JButton("+");
		btnAddOne.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnAddOne.setBounds(822, 134, 50, 50);
		btnAddOne.addActionListener(this);
		add(btnAddOne);

		btnAddTen = new JButton("++");
		btnAddTen.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnAddTen.setBounds(793, 188, 50, 50);
		btnAddTen.addActionListener(this);
		add(btnAddTen);

		btnAddToBookShop = new JButton("Aggiungi al negozzio");
		btnAddToBookShop.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnAddToBookShop.setBounds(680, 297, 192, 50);
		btnAddToBookShop.addActionListener(this);
		add(btnAddToBookShop);

		lblAmount = new JLabel("1");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		lblAmount.setBounds(684, 102, 188, 25);
		add(lblAmount);

		btnAddCopyToWarehouse = new JButton("Aggiungi al magazzino");
		btnAddCopyToWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddCopyToWarehouse.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnAddCopyToWarehouse.setBounds(680, 460, 192, 50);
		btnAddCopyToWarehouse.addActionListener(this);
		add(btnAddCopyToWarehouse);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object isPressed = e.getSource();
		if (isPressed == btnRemoveOne) {
			if (Integer.parseInt(lblAmount.getText()) > 1)
				lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) - 1));
		} else if (isPressed == btnRemoveTen) {
			if (Integer.parseInt(lblAmount.getText()) > 10) {
				lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) - 10));
			}
		} else if (isPressed == btnAddOne) {

			lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) + 1));

		} else if (isPressed == btnAddTen) {

			lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) + 10));

		} else if (isPressed == btnAddToBookShop) {
			if ((int) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 5) >= Integer
					.parseInt(lblAmount.getText())) {
				this.observer.addBooksInBookShopClicked(
						modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), titleCell).toString(),
						modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), authorCell).toString(),
						modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), genreCell).toString(),
						(int) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), yearCell),
						(double) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), priceCell),
						Integer.parseInt(lblAmount.getText()));
				lblAmount.setText("1");
			} else
				displayMessage("Non ci sono abbastanza libri nel magazzino");
		} else if (isPressed == btnAddCopyToWarehouse) {
			this.observer.addCopyToWarehouse(
					modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), titleCell).toString(),
					modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), authorCell).toString(),
					(Integer) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), yearCell),
					Integer.parseInt(lblAmount.getText()));
			lblAmount.setText("1");
		}
	}

	@Override
	public void attachObserver(WarehouseObserver observer) {
		this.observer = observer;
		this.setAllBooks();

	}

	@Override
	public void clearPanel() {
		lblAmount.setText("1");

	}

	@Override
	public void setAllBooks() {
		clearTable(modelAllBooks);
		Map<BookModel, Integer> tmp = this.observer.getBooksInWarehouse();
		int i = 0;

		for (BookModel entry : tmp.keySet()) {
			if (entry.getTitle() == null) {
			} else {
				Object[] obj = { entry.getTitle(), entry.getAuthor(), entry.getLiteraryGenre(),
						entry.getyearOfPublication(), entry.getPrice(), tmp.values().toArray()[i] };
				((DefaultTableModel) modelAllBooks).addRow(obj);

				tblAllBooks.repaint();
				i++;
			}
		}
		if (tblAllBooks.getRowCount() > 0) {
			tblAllBooks.setRowSelectionInterval(0, 0);
		}
	}

	@Override
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Attenzione", JOptionPane.PLAIN_MESSAGE);

	}

	private void clearTable(DefaultTableModel model) {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}