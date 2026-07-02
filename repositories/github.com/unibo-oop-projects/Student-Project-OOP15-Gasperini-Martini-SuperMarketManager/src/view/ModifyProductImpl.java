package view;

import javax.swing.*;

import controller.ProductController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class ModifyProductImpl extends JFrame implements ModifyProduct {

	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel = new JPanel();
	private final JLabel displayName = new JLabel("Nome :");
	private final JLabel displayPrice = new JLabel("Prezzo :");
	private final JLabel displayQuantity = new JLabel("Quantità :");
	private final JLabel title = new JLabel("Modifica Prodotto");
	private final JTextField productName = new JTextField(20);
	private final JTextField priceProduct = new JTextField(20);
	private final JTextField quantityProduct = new JTextField(20);
	private int codeProduct;
	private JButton save = new JButton("Salva");
	private JLabel labelCheck = new JLabel();
	ProductController controller;

	public ModifyProductImpl(ManagementProductViewImpl view) {

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.setVisible(true);

		this.setSize(700, 500);
		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		SpringLayout spring = new SpringLayout();

		mainPanel.setBackground(new Color(206, 246, 210));

		mainPanel.setLayout(spring);

		this.add(mainPanel);

		mainPanel.add(title);
		title.setFont(Utility.Utility.fontTitle);
		spring.putConstraint(SpringLayout.NORTH, title, 10, SpringLayout.NORTH,
				this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, title, 40, SpringLayout.WEST,
				this.getContentPane());
		mainPanel.add(displayName);
		displayName.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayName, 100,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayName, 40,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(productName);
		productName.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, productName, 100,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, productName, 280,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(displayPrice);
		displayPrice.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayPrice, 140,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayPrice, 40,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(priceProduct);
		priceProduct.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, priceProduct, 140,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, priceProduct, 280,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(displayQuantity);
		displayQuantity.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayQuantity, 180,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayQuantity, 40,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(quantityProduct);
		quantityProduct.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, quantityProduct, 180,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, quantityProduct, 280,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(labelCheck);
		labelCheck.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, labelCheck, 260,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, labelCheck, 280,
				SpringLayout.WEST, this.getContentPane());
		mainPanel.add(save);
		save.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, save, 220, SpringLayout.NORTH,
				this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, save, 280, SpringLayout.WEST,
				this.getContentPane());

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String check;

				try {
					check = controller.changeProduct(codeProduct,
							productName.getText(),
							Integer.parseInt(priceProduct.getText()),
							Integer.parseInt(quantityProduct.getText()));

					labelCheck.setText(check);
					view.setVisible(false);

				} catch (NumberFormatException e) {

					check = Utility.Utility.ERRORFORMAT;
					labelCheck.setText(check);
				}

			}

		});

		ModifyProductImpl viewProduct = this;

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(final WindowEvent e) {

				controller.quitModifyProduct(viewProduct);

			}

		});

	}

	public void addObserver(ProductController controller) {

		this.controller = controller;
	}

	public void setData(int code, String name, int price, int quantity) {

		productName.setText(name);
		priceProduct.setText(String.valueOf(price));
		quantityProduct.setText(String.valueOf(quantity));
		this.codeProduct = code;

	}

}
