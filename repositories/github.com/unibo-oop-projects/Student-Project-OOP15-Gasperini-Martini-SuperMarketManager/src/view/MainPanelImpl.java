package view;

import javax.swing.*;

import Utility.Utility;
import controller.MainController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class MainPanelImpl extends JFrame implements MainPanel {

	private static final long serialVersionUID = 12L;
	private JPanel mainPanel = new JPanel();
	private JPanel logInPanel = new JPanel();
	private final JLabel titleLabel = new JLabel("Gestionale Supermercato");
	private final JLabel titleLogIn = new JLabel(
			"Benvenuti nel gestionale Supermercato");
	private final JLabel displayLogIn = new JLabel("Inserire Credenziali :");
	private final JLabel displayUsername = new JLabel("Username:");
	private final JLabel selectOption = new JLabel("Selezionare una operazione");
	private final JTextField usernameText = new JTextField(20);
	private final JLabel displayPassword = new JLabel("Password:");
	private final JPasswordField passwordText = new JPasswordField(20);
	private final JButton submit = new JButton("Log-In");
	private final JButton insertProduct = new JButton("Inserisci Prodotto");
	private final JButton directProduct = new JButton(
			"Gestione Prodotti/Reparti");
	private final JButton insertDepartment = new JButton("Inserisci Reparto");
	MainController mainController;

	public MainPanelImpl() {

		super("Gestionale Supermercato");

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(false);
		this.setSize(700, 500);
		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		mainPanel.setBackground(new Color(206, 246, 210));

		this.add(logInPanel);
		
		logInPanel.setBackground(new Color(206, 246, 210));

		SpringLayout spring = new SpringLayout();
		SpringLayout springLoginIn = new SpringLayout();

		mainPanel.setLayout(spring);
		logInPanel.setLayout(springLoginIn);

		Font fontDisplay = new Font("Times New Roman", 15, 15);
		Font fontTitle = new Font("Serif", 20, 40);
		titleLogIn.setFont(fontTitle);
		logInPanel.add(titleLogIn);
		springLoginIn.putConstraint(SpringLayout.NORTH, titleLogIn, 60,
				SpringLayout.NORTH, this.getContentPane());
		springLoginIn.putConstraint(SpringLayout.WEST, titleLogIn, 40,
				SpringLayout.WEST, this.getContentPane());
		Font fontDisplayLogIn = new Font("Serif", 20, 20);
		displayLogIn.setFont(fontDisplayLogIn);
		logInPanel.add(displayLogIn);
		springLoginIn.putConstraint(SpringLayout.NORTH, displayLogIn, 120,
				SpringLayout.NORTH, this.getContentPane());
		springLoginIn.putConstraint(SpringLayout.WEST, displayLogIn, 290,
				SpringLayout.WEST, this.getContentPane());
		displayUsername.setFont(fontDisplay);
		logInPanel.add(displayUsername);
		springLoginIn.putConstraint(SpringLayout.NORTH, displayUsername, 180,
				SpringLayout.NORTH, this.getContentPane());
		springLoginIn.putConstraint(SpringLayout.WEST, displayUsername, 170,
				SpringLayout.WEST, this.getContentPane());
		logInPanel.add(usernameText);
		springLoginIn.putConstraint(SpringLayout.NORTH, usernameText, 180,
				SpringLayout.NORTH, this.getContentPane());
		springLoginIn.putConstraint(SpringLayout.WEST, usernameText, 270,
				SpringLayout.WEST, this.getContentPane());
		displayPassword.setFont(fontDisplay);
		logInPanel.add(displayPassword);
		springLoginIn.putConstraint(SpringLayout.NORTH, displayPassword, 210,
				SpringLayout.NORTH, this.getContentPane());
		springLoginIn.putConstraint(SpringLayout.WEST, displayPassword, 170,
				SpringLayout.WEST, this.getContentPane());
		logInPanel.add(passwordText);
		springLoginIn.putConstraint(SpringLayout.NORTH, passwordText, 210,
				SpringLayout.NORTH, this.getContentPane());
		springLoginIn.putConstraint(SpringLayout.WEST, passwordText, 270,
				SpringLayout.WEST, this.getContentPane());

		submit.setFont(fontDisplay);
		logInPanel.add(submit);
		springLoginIn.putConstraint(SpringLayout.NORTH, submit, 240,
				SpringLayout.NORTH, this.getContentPane());
		springLoginIn.putConstraint(SpringLayout.WEST, submit, 270,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(titleLabel);
		titleLabel.setFont(fontTitle);
		spring.putConstraint(SpringLayout.NORTH, titleLabel, 60,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, titleLabel, 150,
				SpringLayout.WEST, this.getContentPane());
		selectOption.setFont(fontDisplayLogIn);
		mainPanel.add(selectOption);
		spring.putConstraint(SpringLayout.NORTH, selectOption, 120,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, selectOption, 160,
				SpringLayout.WEST, this.getContentPane());
		insertProduct.setFont(fontDisplay);
		mainPanel.add(insertDepartment);
		spring.putConstraint(SpringLayout.NORTH, insertDepartment, 210,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, insertDepartment, 90,
				SpringLayout.WEST, this.getContentPane());
		directProduct.setFont(fontDisplay);
		mainPanel.add(directProduct);
		spring.putConstraint(SpringLayout.NORTH, directProduct, 210,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, directProduct, 270,
				SpringLayout.WEST, this.getContentPane());
		insertDepartment.setFont(fontDisplay);
		mainPanel.add(insertProduct);
		spring.putConstraint(SpringLayout.NORTH, insertProduct, 210,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, insertProduct, 490,
				SpringLayout.WEST, this.getContentPane());

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(final WindowEvent e) {
				quitExit();
			}

		});

		insertDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mainController.insertDepartmentView();

			}

		});

		insertProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				mainController.insertProductView();

			}

		});

		directProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				mainController.directProductDepartment();

			}

		});

		submit.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (mainController.logIn(usernameText.getText(),
						passwordText.getText()) == true) {

					setMainPanel();
					logInPanel.setVisible(false);

					validate();

				} else {

					JOptionPane.showMessageDialog(MainPanelImpl.this,
							Utility.ERRORLOGIN);
					usernameText.setText("");
					passwordText.setText("");

				}

			}

		});

	}

	public void quitExit() {

		final int exit = JOptionPane.showConfirmDialog(this,
				Utility.QUESTIONLEAVE, "Uscita", JOptionPane.YES_NO_OPTION);
		if (exit == JOptionPane.YES_OPTION) {
			mainController.setFile();
			System.exit(0);
		}
	}

	public void addObserver(MainController mainController) {

		this.mainController = mainController;
	}

	public void setMainPanel() {

		this.getContentPane().add(mainPanel);
	}

}
