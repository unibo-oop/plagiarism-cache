package view;

import javax.swing.*;

import Utility.Utility;
import model.Department;
import controller.DirectProductDepartmentController;

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
public class DirectProductDepartmentViewImpl extends JFrame implements
		DirectProductDepartmentView {

	private static final long serialVersionUID = 1L;
	DirectProductDepartmentController controller;
	private final JPanel departmentPanel = new JPanel(
			new GridLayout(0, 4, 2, 2));
	private final JLabel displayDelete = new JLabel("Elimina");
	private final JLabel displayModify = new JLabel("Modifica");
	private final JLabel displayProdcut = new JLabel("Gestione Prodotti");
	private final JLabel dislayName = new JLabel("Nome Reparto");

	public DirectProductDepartmentViewImpl() {

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(false);

		this.setSize(700, 500);
		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		this.setLayout(new BorderLayout());

		this.add(departmentPanel, BorderLayout.NORTH);

		this.setLocationRelativeTo(null);
		departmentPanel.add(dislayName);
		departmentPanel.add(displayModify);
		departmentPanel.add(displayProdcut);
		departmentPanel.add(displayDelete);

		departmentPanel.setBackground(new Color(206, 246, 210));

		JScrollPane scroll = new JScrollPane(departmentPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(scroll);

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(final WindowEvent e) {

				controller.quit();

			}

		});

	}

	public void addObserver(DirectProductDepartmentController controller) {

		this.controller = controller;

		listDepartment(controller.getListDepartmentView());
	}

	public void listDepartment(ArrayList<Department> departments) {

		for (int i = 0; i < departments.size(); i++) {

			JLabel newLabel = new JLabel(departments.get(i).getName());

			JButton modifyButton = new JButton("Modifica Reparto");
			JButton deleteDepartment = new JButton("Elimina");
			JButton directProduct = new JButton("Gestione Prodotti");

			departmentPanel.add(newLabel);
			departmentPanel.add(modifyButton);
			departmentPanel.add(directProduct);
			departmentPanel.add(deleteDepartment);

			actionEvent(deleteDepartment, departments.get(i), modifyButton,
					newLabel, directProduct, this);

			validate();

		}

	}

	public void actionEvent(JButton deleteDepartment, Department departments,
			JButton modifyDepartment, JLabel nameDepartment,
			JButton directProduct, DirectProductDepartmentViewImpl view) {

		deleteDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final int exit = JOptionPane.showConfirmDialog(view,
						Utility.QUESTIONDELETE, "Cancellazione",
						JOptionPane.YES_NO_OPTION);
				if (exit == JOptionPane.YES_OPTION) {

					controller.deleteDepartment(departments);

				}

			}

		});

		modifyDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controller.setModifyController(departments, nameDepartment);

			}

		});

		directProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controller.setModifyProductController(departments);

			}

		});

	}

	public void setPanel(ArrayList<Department> department) {

		departmentPanel.removeAll();
		departmentPanel.add(dislayName);
		departmentPanel.add(displayModify);
		departmentPanel.add(displayProdcut);
		departmentPanel.add(displayDelete);
		validate();
		listDepartment(department);

	}

}
