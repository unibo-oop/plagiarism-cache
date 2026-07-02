/**
 *@author Ceccarelli 
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.observer.AddBookObserver;

import javax.swing.JButton;

//da aggiungere i controlli sulle textField
public class AddBookPanelImpl extends JPanel implements AddBookPanel, ActionListener {

	/**
	 * Create the panel.
	 */
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtPrice;
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblLiteraryGenre;
	private JLabel lblPrice;
	private JLabel lblYear;
	private JLabel lblPanelTitle;
	private JLabel lblAmountTitle;
	private JComboBox<String> cmbLiteraryGenre;
	private JComboBox<Integer> cmbYear;
	private static final long serialVersionUID = 1L;
	private JLabel lblMessage;
	private JButton btnRemoveOne;
	private JButton btnAddBook;
	private JButton btnRemoveTen;
	private JButton btnAddTen;
	private JButton btnAddOne;
	private AddBookObserver observer;
	private JButton btnBack;
	private JLabel lblAmount;

	public AddBookPanelImpl() {
		this.setLayout(null);
		setBackground(SystemColor.activeCaption);

		lblTitle = new JLabel("Titolo:");
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 77, 154, 17);
		add(lblTitle);

		lblAuthor = new JLabel("Autore:");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		lblAuthor.setBounds(724, 77, 154, 17);
		add(lblAuthor);

		lblLiteraryGenre = new JLabel("Genere:");
		lblLiteraryGenre.setHorizontalAlignment(SwingConstants.CENTER);
		lblLiteraryGenre.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		lblLiteraryGenre.setBounds(380, 77, 154, 14);
		add(lblLiteraryGenre);

		lblYear = new JLabel("Anno:");
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		lblYear.setBounds(10, 207, 154, 14);
		add(lblYear);

		cmbLiteraryGenre = new JComboBox<String>();
		cmbLiteraryGenre.setFont(new Font("Calibri", Font.ITALIC, 13));
		cmbLiteraryGenre.setBounds(380, 102, 154, 20);
		cmbLiteraryGenre.addItem("Fanatscienza");
		cmbLiteraryGenre.addItem("Storico");
		cmbLiteraryGenre.addItem("Noire");
		cmbLiteraryGenre.addItem("Horror");
		add(cmbLiteraryGenre);

		cmbYear = new JComboBox<Integer>();
		cmbYear.setFont(new Font("Calibri", Font.ITALIC, 13));
		cmbYear.setBounds(10, 232, 154, 20);
		add(cmbYear);

		txtTitle = new JTextField();
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtTitle.setBounds(10, 105, 154, 20);
		add(txtTitle);
		txtTitle.setColumns(10);

		txtAuthor = new JTextField();
		txtAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAuthor.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtAuthor.setBounds(724, 105, 154, 20);
		add(txtAuthor);
		txtAuthor.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtPrice.setBounds(380, 232, 154, 20);
		add(txtPrice);
		txtPrice.setColumns(10);

		lblPrice = new JLabel("Prezzo singolo:");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		lblPrice.setBounds(380, 207, 154, 14);
		add(lblPrice);

		lblPanelTitle = new JLabel("Aggiungi libro");
		lblPanelTitle.setForeground(new Color(255, 69, 0));
		lblPanelTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		lblPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelTitle.setBounds(10, 11, 880, 55);
		add(lblPanelTitle);

		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(255, 0, 0));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblMessage.setBounds(53, 435, 837, 144);
		add(lblMessage);

		btnAddBook = new JButton("Aggiungi libri");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddBook.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnAddBook.setBounds(10, 356, 154, 68);
		btnAddBook.addActionListener(this);
		add(btnAddBook);

		lblAmountTitle = new JLabel("Quantità:");
		lblAmountTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmountTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblAmountTitle.setBounds(724, 207, 154, 14);
		add(lblAmountTitle);

		btnRemoveOne = new JButton("-");
		btnRemoveOne.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		btnRemoveOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRemoveOne.setBounds(724, 364, 56, 23);
		btnRemoveOne.addActionListener(this);
		add(btnRemoveOne);

		btnRemoveTen = new JButton("--");
		btnRemoveTen.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		btnRemoveTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveTen.setBounds(724, 398, 56, 23);
		btnRemoveTen.addActionListener(this);
		add(btnRemoveTen);

		btnAddTen = new JButton("++");
		btnAddTen.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		btnAddTen.setBounds(822, 401, 56, 23);
		btnAddTen.addActionListener(this);
		add(btnAddTen);

		btnAddOne = new JButton("+");
		btnAddOne.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		btnAddOne.setBounds(822, 364, 56, 23);
		btnAddOne.addActionListener(this);
		add(btnAddOne);

		btnBack = new JButton("Vai al amgazziono");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 13));
		btnBack.setBounds(380, 390, 154, 34);
		btnBack.addActionListener(this);
		add(btnBack);
		
		lblAmount = new JLabel("1");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblAmount.setBounds(724, 232, 154, 20);
		add(lblAmount);

		setYear();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if (isPressed == btnAddOne) {
			lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) + 1));
		} else if (isPressed == btnAddTen) {
			lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) + 10));
		} else if (isPressed == btnRemoveOne && Integer.parseInt(lblAmount.getText()) > 1) {
			lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) - 1));
		} else if (isPressed == btnRemoveTen && Integer.parseInt(lblAmount.getText()) > 10) {
			lblAmount.setText(String.valueOf(Integer.parseInt(lblAmount.getText()) - 10));
		} else if (isPressed == btnAddBook) {
			try {				
				observer.addBookClicked(txtTitle.getText(), txtAuthor.getText(),
						cmbLiteraryGenre.getSelectedItem().toString(),
						(Integer) cmbYear.getSelectedItem(),
						Double.parseDouble(txtPrice.getText()),
						Integer.parseInt(lblAmount.getText()));
			} catch (IllegalArgumentException iE) {
				this.displayMessage("Valore inserito errato. Controllare i campi inseriti");
			}
			lblAmount.setText("1");
		} else if (isPressed == btnBack) {
			observer.backToWharehouseClicked();
		}
	}

	@Override
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Attenzione", JOptionPane.PLAIN_MESSAGE);

	}

	@Override
	public void attachObserver(AddBookObserver observer) {
		this.observer = observer;

	}

	@Override
	public void clearView() {
		lblAmount.setText("1");
		txtAuthor.setText("");
		txtTitle.setText("");
		txtPrice.setText("");

	}

	public void setYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = year; i >= year - 100; i--) {
			cmbYear.addItem(i);
		}
	}

}
