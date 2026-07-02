package view;

import javax.swing.*;

import Utility.Utility;
import model.Department;
import controller.ProductController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 *
 */
public class InsertProductViewImpl extends JFrame implements InsertProductView {

	private static final long serialVersionUID = 1L;
	ProductController controller;
	private JPanel mainPanel = new JPanel(new GridLayout(2, 6, 2, 2));
	private final JPanel insertPanel = new JPanel();
	private final JTextField nameProduct = new JTextField(20);
	private final JLabel displayName = new JLabel("Inserire Nome:");
	private final JTextField codeProduct = new JTextField(20);
	private final JLabel displaycode = new JLabel("Inserire Codice:");
	private final JTextField priceProduct = new JTextField(20);
	private final JLabel displayprice = new JLabel("Inserire Prezzo:");
	private final JTextField quantity = new JTextField(20);
	private final JLabel displayquantity = new JLabel("Inserire Quantità :");
	private final JLabel checkString = new JLabel();
	private final JLabel titlePanel = new JLabel("Selezionare Un Reparto");
	private final JLabel title = new JLabel();
	private final JButton submitProduct = new JButton("invia");
	private final JButton cancel = new JButton("Annulla");

	public InsertProductViewImpl(final MainPanel panel) {

		super();

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(false);

		this.setSize(700, 500);
		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		// this.getContentPane().setLayout(new BorderLayout());

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JScrollPane scroll = new JScrollPane(mainPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scroll, BorderLayout.CENTER);
		this.getContentPane().add(mainPanel);
		mainPanel.add(titlePanel);
		
		mainPanel.setBackground(new Color(206, 246, 210));

		titlePanel.setFont(Utility.fontTitle);

		SpringLayout spring = new SpringLayout();
		insertPanel.setLayout(spring);
		
		insertPanel.setBackground(new Color(206, 246, 210));

		insertPanel.add(nameProduct);
		spring.putConstraint(SpringLayout.NORTH, nameProduct, 80,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, nameProduct, 250,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(codeProduct);
		spring.putConstraint(SpringLayout.NORTH, codeProduct, 120,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, codeProduct, 250,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(priceProduct);
		spring.putConstraint(SpringLayout.NORTH, priceProduct, 160,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, priceProduct, 250,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(quantity);
		spring.putConstraint(SpringLayout.NORTH, quantity, 200,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, quantity, 250,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(submitProduct);
		spring.putConstraint(SpringLayout.NORTH, submitProduct, 240,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, submitProduct, 250,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(checkString);
		spring.putConstraint(SpringLayout.NORTH, checkString, 330,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, checkString, 250,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(cancel);
		spring.putConstraint(SpringLayout.NORTH, cancel, 240,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, cancel, 330, SpringLayout.WEST,
				insertPanel);

		insertPanel.add(title);
		spring.putConstraint(SpringLayout.NORTH, title, 40, SpringLayout.NORTH,
				insertPanel);
		spring.putConstraint(SpringLayout.WEST, title, 200, SpringLayout.WEST,
				insertPanel);

		insertPanel.add(displayName);
		spring.putConstraint(SpringLayout.NORTH, displayName, 80,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, displayName, 100,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(displaycode);
		spring.putConstraint(SpringLayout.NORTH, displaycode, 120,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, displaycode, 100,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(displayprice);
		spring.putConstraint(SpringLayout.NORTH, displayprice, 160,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, displayprice, 100,
				SpringLayout.WEST, insertPanel);
		insertPanel.add(displayquantity);
		spring.putConstraint(SpringLayout.NORTH, displayquantity, 200,
				SpringLayout.NORTH, insertPanel);
		spring.putConstraint(SpringLayout.WEST, displayquantity, 100,
				SpringLayout.WEST, insertPanel);

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(final WindowEvent e) {

				controller.quit();

			}

		});

	}

	public void addObserver(ProductController controller) {

		this.controller = controller;

		listDepartment(controller.getMarket().getListDepartment());

	}

	public void listDepartment(ArrayList<Department> departments) {

		for (int i = 0; i < departments.size(); i++) {

			JButton newButton = new JButton(departments.get(i).getName());

			mainPanel.add(newButton);
			newButton.setMaximumSize(new Dimension(100, 100));
			newButton.setFont(Utility.fontDisplay);
			actionEvent(i, newButton, departments);

		}

	}

	public void actionEvent(int index, JButton newButton,
			ArrayList<Department> departments) {

		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mainPanel.setVisible(false);
				setPanel(insertPanel);
				title.setText("Inserisci elemeto nel Reparto : "
						+ departments.get(index).getName());

				cancel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						controller.quit();

						uneableJtextField();

					}

				});

				submitProduct.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (codeProduct.getText().isEmpty()
								|| nameProduct.getText().isEmpty()
								|| priceProduct.getText().isEmpty()
								|| quantity.getText().isEmpty()) {

							checkString.setText(Utility.ERRORDATA);

						} else if (!codeProduct.getText().matches(
								"[+]?\\d*\\.?\\d+")
								|| !quantity.getText().matches(
										"[+]?\\d*\\.?\\d+")
								|| !priceProduct.getText().matches(
										"[+]?\\d*\\.?\\d+")) {

							checkString.setText(Utility.ERRORCODEQUANTIY);

						} else if (controller.checkCode(Integer
								.parseInt(codeProduct.getText()))) {

							checkString.setText(Utility.ERRORCODE);

						} else if (controller.checkName(nameProduct.getText())) {

							checkString.setText(Utility.ERRORNAME);

						} else {

							checkString.setText(controller.insertProduct(
									departments.get(index),
									nameProduct.getText(),
									Integer.parseInt(codeProduct.getText()),
									index,
									Integer.parseInt(priceProduct.getText()),
									Integer.parseInt(quantity.getText())));

							uneableJtextField();

						}

					}

				});

				uneableJtextField();
				validate();

			}

		});

	}

	private void uneableJtextField() {

		nameProduct.setText("");
		codeProduct.setText("");
		priceProduct.setText("");
		quantity.setText("");

	}

	private void setPanel(JPanel panel) {

		this.getContentPane().add(panel);

	}

}
