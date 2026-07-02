package view;

import javax.swing.*;

import Utility.Utility;
import controller.DepartmentController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 * 
 */
public class InsertDepartmentImpl extends JFrame implements InsertDepartment {

	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel = new JPanel();
	private final JButton insertDepartment = new JButton("Inserisci");
	private final JButton close = new JButton("Annulla");
	private final JTextField departName = new JTextField(20);
	private final JTextField departMaxProduct = new JTextField(20);
	private final JTextField departCode = new JTextField(20);
	private final JLabel titlePanel = new JLabel("Inserimento Nuovo Reparto");
	private final JLabel displayName = new JLabel("Inserisci Nome :");
	private final JLabel displayMaxProduct = new JLabel(
			"Inserisci la quantità  max di prodotti :");
	private final JLabel displayCode = new JLabel("Inserisci codice reparto :");
	private DepartmentController departmentController;
	private final JLabel checkString = new JLabel();

	public InsertDepartmentImpl() {

		super();

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(true);
		this.setSize(700, 500);
		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		SpringLayout spring = new SpringLayout();
		mainPanel.setLayout(spring);

		this.add(mainPanel);

		Font fontTitle = new Font("Serif", 20, 40);
		Font fontDisplay = new Font("Times New Roman", 15, 16);

		mainPanel.add(titlePanel);
		titlePanel.setFont(fontTitle);
		mainPanel.setBackground(new Color(206, 246, 210));
		spring.putConstraint(SpringLayout.NORTH, titlePanel, 10,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, titlePanel, 40,
				SpringLayout.WEST, this.getContentPane());

		mainPanel.add(displayName);
		displayName.setFont(fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayName, 100,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayName, 40,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(displayMaxProduct);
		displayMaxProduct.setFont(fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayMaxProduct, 180,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayMaxProduct, 40,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(displayCode);
		displayCode.setFont(fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayCode, 260,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayCode, 40,
				SpringLayout.WEST, this.getContentPane());

		mainPanel.add(departName);
		spring.putConstraint(SpringLayout.NORTH, departName, 100,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, departName, 280,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(departCode);
		spring.putConstraint(SpringLayout.NORTH, departCode, 260,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, departCode, 280,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(departMaxProduct);
		spring.putConstraint(SpringLayout.NORTH, departMaxProduct, 180,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, departMaxProduct, 280,
				SpringLayout.WEST, this.getContentPane());

		mainPanel.add(checkString);
		spring.putConstraint(SpringLayout.NORTH, checkString, 330,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, checkString, 280,
				SpringLayout.WEST, this.getContentPane());

		mainPanel.add(insertDepartment);
		insertDepartment.setFont(fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, insertDepartment, 300,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, insertDepartment, 280,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(close);
		close.setFont(fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, close, 300,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, close, 400, SpringLayout.WEST,
				this.getContentPane());

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				departmentController.quit();

			}

		});

		insertDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (departCode.getText().isEmpty()
						|| departMaxProduct.getText().isEmpty()
						|| departName.getText().isEmpty()) {

					checkString.setText(Utility.ERRORDATA);

				} else if (!departCode.getText().matches("[+]?\\d*\\.?\\d+")
						|| !departMaxProduct.getText().matches(
								"[+]?\\d*\\.?\\d+")) {

					checkString.setText(Utility.ERRORCODEQUANTIY);

				} else if (departmentController.checkCode(Integer
						.parseInt(departCode.getText())) == true) {

					checkString.setText(Utility.ERRORCODE);

				} else if (departmentController.checkName(departName.getText()) == true) {

					checkString.setText(Utility.ERRORNAME);

				} else {

					departmentController.insertDepartement(
							departName.getText(),
							Integer.parseInt(departMaxProduct.getText()),
							Integer.parseInt(departCode.getText()));

					checkString.setText(Utility.SUCCESSINSERT);

					departName.setText("");
					departMaxProduct.setText("");
					departCode.setText("");

				}

			}

		});

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(final WindowEvent e) {

				departmentController.quit();

			}

		});

	}

	public void addObserver(DepartmentController controller) {

		this.departmentController = controller;
	}

}
