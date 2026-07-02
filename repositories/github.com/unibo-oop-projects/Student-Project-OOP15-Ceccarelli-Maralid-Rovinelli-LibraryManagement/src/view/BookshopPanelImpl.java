/**
 *@author Ceccarelli 
 */
package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.BookImpl;
import model.BookModel;
import view.observer.BookshopObserver;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class BookshopPanelImpl extends JPanel implements BookshopPanel, ActionListener {

	private static final long serialVersionUID = 1L;
	private BookshopObserver observer;
	private DefaultTableModel modelAllBooks;
	private DefaultTableModel modelSelectedBooks;
	private JTable tblAllBooks;
	private JTable tblSelectedBooks;
	private JScrollPane scpAllBooks;
	private JScrollPane scpSelectedBooks;
	private JTextField txtAmount;
	private JLabel lblAmount;
	private JButton btnAddBook;
	private JButton btnRemoveBook;
	private JButton btnPurchaseIt;
	private JLabel lblTitle;
	private JButton btnAdd;
	private JButton btnRemove;
	private JLabel lblTotalPrice;
	private JLabel lblTotalPriceAmount;
	private JLabel lblCurrencies;
	private Map<BookModel, Integer> purchaseList;
	private final int titleCell = 0;
	private final int authorCell = 1;
	private final int yearCell = 3;
	private final int priceCell = 4;
	private final int amountCell = 5;
	private JTextField txtSearchType;
	private JButton btnSearch;
	private JComboBox<String> cmbSearchType;

	public BookshopPanelImpl() {
		super();
		setBackground(SystemColor.inactiveCaption);
		this.setLayout(null);

		modelAllBooks = new DefaultTableModel(new Object[][] {},
				new String[] { "Titolo", "Autore", "Genere", "Anno P.", "Prezzo", "Copie" });
		modelSelectedBooks = new DefaultTableModel(new Object[][] {},
				new String[] { "Titolo", "Autore", "Genere", "Anno P.", "Prezzo", "Copie" });

		scpAllBooks = new JScrollPane();
		scpAllBooks.setEnabled(false);
		scpAllBooks.setBounds(10, 139, 377, 327);
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
		tblAllBooks.getColumnModel().getColumn(0).setPreferredWidth(74);
		tblAllBooks.getColumnModel().getColumn(1).setPreferredWidth(66);
		tblAllBooks.getColumnModel().getColumn(2).setPreferredWidth(126);
		tblAllBooks.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblAllBooks.setFont(new Font("Calibri", Font.PLAIN, 13));
		tblAllBooks.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		scpSelectedBooks = new JScrollPane();
		scpSelectedBooks.setEnabled(false);
		scpSelectedBooks.setBounds(502, 87, 375, 379);
		add(scpSelectedBooks);

		tblSelectedBooks = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scpSelectedBooks.setViewportView(tblSelectedBooks);
		tblSelectedBooks.setModel(modelSelectedBooks);
		tblSelectedBooks.getColumnModel().getColumn(0).setPreferredWidth(74);
		tblSelectedBooks.getColumnModel().getColumn(1).setPreferredWidth(66);
		tblSelectedBooks.getColumnModel().getColumn(2).setPreferredWidth(126);
		tblSelectedBooks.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblSelectedBooks.setFont(new Font("Calibri", Font.PLAIN, 13));
		tblSelectedBooks.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		lblTitle = new JLabel("BookShop");
		lblTitle.setForeground(new Color(255, 69, 0));
		lblTitle.setBackground(SystemColor.inactiveCaption);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		lblTitle.setBounds(10, 11, 880, 65);
		add(lblTitle);

		txtAmount = new JTextField();
		txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmount.setText("1");
		txtAmount.setFont(new Font("Calibri", Font.ITALIC, 16));
		txtAmount.setEnabled(false);
		txtAmount.setEditable(false);
		txtAmount.setBounds(396, 142, 43, 42);
		add(txtAmount);
		txtAmount.setColumns(10);

		btnAdd = new JButton("+");
		btnAdd.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnAdd.setBounds(449, 118, 43, 43);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnRemove = new JButton("-");
		btnRemove.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnRemove.setBounds(449, 172, 43, 43);
		btnRemove.addActionListener(this);
		add(btnRemove);

		lblAmount = new JLabel("Quantità");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblAmount.setBounds(389, 87, 95, 14);
		add(lblAmount);

		btnAddBook = new JButton("Aggiungi");
		btnAddBook.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		btnAddBook.setBounds(396, 240, 95, 49);
		btnAddBook.addActionListener(this);
		add(btnAddBook);

		btnRemoveBook = new JButton("Togli");
		btnRemoveBook.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		btnRemoveBook.setBounds(396, 300, 96, 49);
		btnRemoveBook.addActionListener(this);
		add(btnRemoveBook);

		btnPurchaseIt = new JButton("Procedi con la vendita");
		btnPurchaseIt.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		btnPurchaseIt.setBounds(568, 494, 309, 49);
		btnPurchaseIt.addActionListener(this);
		add(btnPurchaseIt);

		lblTotalPrice = new JLabel("Spesa totale:");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblTotalPrice.setBounds(397, 356, 95, 14);
		add(lblTotalPrice);

		lblTotalPriceAmount = new JLabel("0.0");
		lblTotalPriceAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPriceAmount.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 17));
		lblTotalPriceAmount.setBounds(397, 386, 52, 29);
		add(lblTotalPriceAmount);

		lblCurrencies = new JLabel("Euro");
		lblCurrencies.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrencies.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 17));
		lblCurrencies.setBounds(459, 381, 43, 38);
		add(lblCurrencies);

		txtSearchType = new JTextField();
		txtSearchType.setBounds(152, 108, 136, 20);
		add(txtSearchType);
		txtSearchType.setColumns(10);

		cmbSearchType = new JComboBox<String>();
		cmbSearchType.setBounds(10, 108, 132, 20);
		cmbSearchType.addItem("Titolo");
		cmbSearchType.addItem("Autore");
		cmbSearchType.addItem("Anno");
		add(cmbSearchType);

		btnSearch = new JButton("Cerca libri");
		btnSearch.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		btnSearch.setBounds(298, 107, 89, 23);
		btnSearch.addActionListener(this);
		add(btnSearch);

	}

	@Override
	public void attachObserver(BookshopObserver observer) {
		this.observer = observer;
		this.setAllBooks();
	}

	public void actionPerformed(ActionEvent e) {
		if (modelAllBooks.getRowCount() == 0) {
			this.setAllBooks();
		} else {

			Object isPressed = e.getSource();
			int selectedAmount = (int) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), amountCell);
			int newAmount = Integer.parseInt(txtAmount.getText());
			if (isPressed == btnAddBook) {
				if ((Integer) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), amountCell) != 0)
					this.moveBooks();
				else
					displayMessage("Non ci sono libri del tipo scelto in negozzio!");
			} else {
				if (isPressed == btnAdd) {
					if (selectedAmount > newAmount)
						txtAmount.setText(String
								.valueOf(Integer.parseInt(txtAmount.getText()) + 1));
					else if (selectedAmount == 0) {
						displayMessage("Non ci sono piu libri del tipo scelto");
					} else {
						displayMessage("Quantità massima già raggiunta");
					}
				} else if (isPressed == btnRemove) {
					if (newAmount > 1) {
						txtAmount.setText(String
								.valueOf(Integer.parseInt(txtAmount.getText()) - 1));
					} else {
						displayMessage("Attenzione quantità minima gia' impostata!");
					}
				}
			}
			if (isPressed == btnPurchaseIt) {
				if (modelSelectedBooks.getRowCount() > 0)
					this.observer.shopPurchaseItClicked(purchase());
				else
					displayMessage("Nessun libro presente");

			} else if (isPressed == btnRemoveBook) {
				clearSelectedBooks();
			} else if (isPressed == btnSearch) {
				this.setAllBooks();
			}
		}
	}

	@Override
	public void clearSelectedBooks() {
		if (modelSelectedBooks.getRowCount() > 0) {
			for (int i = 0; i < modelAllBooks.getRowCount(); i++) {
				if (modelAllBooks.getValueAt(i, titleCell)
						.equals(modelSelectedBooks.getValueAt(tblSelectedBooks.getSelectedRow(),
								titleCell))
						&& modelAllBooks.getValueAt(i, authorCell)
								.equals(modelSelectedBooks.getValueAt(
										tblSelectedBooks.getSelectedRow(),
										authorCell))
						&& modelAllBooks.getValueAt(i, yearCell).toString()
								.equals(modelSelectedBooks.getValueAt(
										tblSelectedBooks.getSelectedRow(),
										yearCell).toString())) {

					int oldValue = (Integer) modelAllBooks.getValueAt(i, amountCell);
					int newValue = Integer.parseInt(modelSelectedBooks
							.getValueAt(tblSelectedBooks.getSelectedRow(), amountCell)
							.toString());
					modelAllBooks.setValueAt(newValue + oldValue, i, amountCell);
					modelSelectedBooks.removeRow(tblSelectedBooks.getSelectedRow());
					lblTotalPriceAmount.setText(setTotalPrice(-newValue,
							(Double) modelAllBooks.getValueAt(i, priceCell),
							Double.parseDouble(lblTotalPriceAmount.getText())));
					break;
				}
			}
			if (modelSelectedBooks.getRowCount() > 0)
				tblSelectedBooks.setRowSelectionInterval(0, 0);

		}
	}

	@Override
	public void moveBooks() {
		boolean flag = false;
		if (modelSelectedBooks.getRowCount() == 0) {
			this.uploadBooks();
		} else if (modelSelectedBooks.getRowCount() > 0) {

			for (int i = 0; i < modelSelectedBooks.getRowCount(); i++) {
				if (modelSelectedBooks.getValueAt(i, titleCell).toString().equals(modelAllBooks
						.getValueAt(tblAllBooks.getSelectedRow(), titleCell).toString())

						&& modelSelectedBooks.getValueAt(i, authorCell).equals(modelAllBooks
								.getValueAt(tblAllBooks.getSelectedRow(), authorCell))
						&& modelSelectedBooks.getValueAt(i, yearCell).toString()
								.equals(modelAllBooks.getValueAt(
										tblAllBooks.getSelectedRow(), yearCell)
										.toString())) {

					if (Integer.parseInt(txtAmount.getText()) <= (int) modelAllBooks
							.getValueAt(tblAllBooks.getSelectedRow(), amountCell)) {
						modelSelectedBooks.setValueAt(
								Integer.parseInt(txtAmount.getText())
										+ Integer.parseInt(modelSelectedBooks
												.getValueAt(i, amountCell)
												.toString()),
								i, amountCell);
						modelAllBooks.setValueAt(Integer.parseInt(modelAllBooks
								.getValueAt(tblAllBooks.getSelectedRow(), amountCell)
								.toString()) - Integer.parseInt(txtAmount.getText()),
								tblAllBooks.getSelectedRow(), amountCell);

						lblTotalPriceAmount.setText(setTotalPrice(
								Integer.parseInt(txtAmount.getText()),
								Double.parseDouble((modelAllBooks.getValueAt(
										tblAllBooks.getSelectedRow(), priceCell)
										.toString())),
								Double.parseDouble(lblTotalPriceAmount.getText())));
						txtAmount.setText("1");
						flag = true;
						break;
					}
				}

			}
			if (!flag) {
				if (Integer.parseInt(txtAmount.getText()) <= (int) modelAllBooks
						.getValueAt(tblAllBooks.getSelectedRow(), amountCell)) {
					this.uploadBooks();
				}
			}
		}

	}

	@Override
	public void setAllBooks() {
		clearTable(modelAllBooks);
		Map<BookModel, Integer> tmp = this.observer.getBookInShop(cmbSearchType.getSelectedItem().toString(),
				txtSearchType.getText());

		int i = 0;

		for (BookModel entry : tmp.keySet()) {
			if (entry.getTitle() == null) {

			} else {
				Object[] obj = { entry.getTitle(), entry.getAuthor(), entry.getLiteraryGenre(),
						entry.getyearOfPublication(), entry.getPrice(),
						tmp.values().toArray()[i] };
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

	/**
	 * calculates the total price
	 * 
	 * @param amount
	 * @param price
	 * @param previousTotalPrice
	 * 
	 * @return String totalPrice
	 */
	private String setTotalPrice(int amount, double price, double previousTotalPrice) {
		String totalPrice;
		if (previousTotalPrice > 0) {
			totalPrice = String.valueOf(previousTotalPrice + (amount * price));
		} else {
			totalPrice = String.valueOf(amount * price);
		}
		return totalPrice;
	}

	/**
	 * cleans the table passed as parameter
	 * 
	 * @param model
	 *                (DefaultTableModel)
	 */
	private void clearTable(DefaultTableModel model) {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	/**
	 * create the list of selected books to purchase
	 * 
	 * return Map <BookModel, Integer> the list of selected books
	 */
	private Map<BookModel, Integer> purchase() {
		purchaseList = new HashMap<BookModel, Integer>();
		for (int i = 0; i < tblSelectedBooks.getRowCount(); i++) {
			BookModel book = new BookImpl();
			book.setTitle(tblSelectedBooks.getValueAt(i, 0).toString());
			book.setAuthor(tblSelectedBooks.getValueAt(i, 1).toString());
			book.setLiteraryGenre(tblSelectedBooks.getValueAt(i, 2).toString());
			book.setYearOfPublication(Integer.parseInt(tblSelectedBooks.getValueAt(i, 3).toString()));
			book.setPrice(Double.parseDouble(tblSelectedBooks.getValueAt(i, 4).toString()));
			purchaseList.put(book, Integer.parseInt(tblSelectedBooks.getValueAt(i, 5).toString()));
		}
		return purchaseList;
	}

	/**
	 * Add new book in the table tblselectedbooks
	 * 
	 */
	private void uploadBooks() {
		String title = tblAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 0).toString();
		String author = tblAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 1).toString();
		String literaryGenre = tblAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 2).toString();
		String publicationYear = tblAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 3).toString();
		String price = tblAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 4).toString();
		String ammount = txtAmount.getText();

		String[] str = { title, author, literaryGenre, publicationYear, price, ammount };
		((DefaultTableModel) modelSelectedBooks).addRow(str);

		lblTotalPriceAmount.setText(setTotalPrice(Integer.parseInt(txtAmount.getText()),
				(double) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 4),
				Double.parseDouble(lblTotalPriceAmount.getText())));

		modelAllBooks.setValueAt(
				(Integer) modelAllBooks.getValueAt(tblAllBooks.getSelectedRow(), 5)
						- Integer.parseInt(txtAmount.getText()),
				tblAllBooks.getSelectedRow(), 5);
		tblSelectedBooks.repaint();
		txtAmount.setText("1");
		if (tblSelectedBooks.getRowCount() > 0) {
			tblSelectedBooks.setRowSelectionInterval(0, 0);
		}
		if (tblSelectedBooks.getRowCount() > 0) {
			tblSelectedBooks.setRowSelectionInterval(0, 0);
		}
	}
}
