package view;

import model.Product;
import controller.ProductController;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class ManagementProductViewImpl extends JFrame implements
		ManagementProductView {

	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel = new JPanel(new GridLayout(0, 6, 2, 2));
	private final JPanel componentPanel = new JPanel(new GridLayout(0, 6, 2, 2));
	private final JButton printProduct = new JButton("Prodotti");
	private final JLabel displayModify = new JLabel("Modifica");
	private final JLabel displayDelete = new JLabel("Elimina");
	private final JLabel displayPrice = new JLabel("Prezzo");
	private final JLabel displayTotalPrice = new JLabel("Prezzo totale");
	private final JLabel displayQuantity = new JLabel("Quantità ");
	ProductController controller;

	public ManagementProductViewImpl() {

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(false);
		this.setSize(700, 500);
		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		this.setLayout(new BorderLayout());

		this.add(mainPanel, BorderLayout.NORTH);
		mainPanel.setBackground(new Color(206, 246, 210));
		componentPanel.setBackground(new Color(206, 246, 210));

		mainPanel.add(componentPanel);

		JScrollPane scroll = new JScrollPane(componentPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scroll);

		mainPanel.add(printProduct);

		mainPanel.add(displayPrice);
		displayPrice.setFont(Utility.Utility.fontDisplay);
		mainPanel.add(displayQuantity);
		displayQuantity.setFont(Utility.Utility.fontDisplay);
		mainPanel.add(displayTotalPrice);
		displayTotalPrice.setFont(Utility.Utility.fontDisplay);
		mainPanel.add(displayModify);
		displayModify.setFont(Utility.Utility.fontDisplay);
		mainPanel.add(displayDelete);
		displayDelete.setFont(Utility.Utility.fontDisplay);

		printProduct.setVisible(false);

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(final WindowEvent e) {

				controller.quitModify();

			}

		});

	}

	public void addObserver(ProductController controller) {

		this.controller = controller;
	}

	public void listProduct(ArrayList<Product> product) {

		for (int i = 0; i < product.size(); i++) {

			JLabel nameProduct = new JLabel(product.get(i).getName());
			JLabel priceProduct = new JLabel("€ " + product.get(i).getPrice());
			JLabel quantityProduct = new JLabel(""
					+ product.get(i).getQuantity());
			JLabel totalPriceProduct = new JLabel("€ "
					+ product.get(i).getQuantity() * product.get(i).getPrice());
			JButton deleteProduct = new JButton("Elimina");
			JButton modifyProduct = new JButton("Modifica");

			componentPanel.add(nameProduct);
			componentPanel.add(priceProduct);
			componentPanel.add(quantityProduct);
			componentPanel.add(totalPriceProduct);
			componentPanel.add(modifyProduct);
			componentPanel.add(deleteProduct);

			/*
			 * Qui per il metodo quit ho utilzzato il this nel metodo action
			 * listener
			 */
			actionEvent(deleteProduct, product.get(i), modifyProduct, this);
			validate();

		}

	}

	public void actionEvent(JButton deleteProduct, Product product,
			JButton modifyDepartment, ManagementProductViewImpl view) {

		modifyDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				controller.modifyController(product, view);

			}

		});

		deleteProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				controller.deleteProdcut(product);

			}

		});

	}

	public void setPanel(ArrayList<Product> products) {

		componentPanel.removeAll();
		listProduct(products);

	}

}
