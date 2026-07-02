package view;

import javax.swing.*;

import controller.DepartmentController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class ModifyDepartmentViewImpl extends JFrame implements
		ModifyDepartmentView {

	private static final long serialVersionUID = 1L;
	private final JPanel insertPanel = new JPanel();
	private final JLabel displayName = new JLabel("Nome :");
	private final JLabel displayMax = new JLabel("Capienza Reparto :");
	private final JLabel displayTitle = new JLabel("Modifica reparto");
	private JLabel displaynameDepartment;
	private final JTextField departName = new JTextField(20);
	private final JTextField departMax = new JTextField(20);
	private final JButton save = new JButton("Salva");
	private JLabel labelCheck = new JLabel();
	private int departCode;
	DepartmentController controller;

	public ModifyDepartmentViewImpl() {

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.setVisible(true);
		this.setResizable(false);

		this.setSize(700, 500);
		this.setBounds(300, 175, this.getWidth(), this.getHeight());

		SpringLayout spring = new SpringLayout();

		insertPanel.setBackground(new Color(206, 246, 210));

		this.add(insertPanel);
		insertPanel.setLayout(spring);

		insertPanel.add(displayTitle);
		displayTitle.setFont(Utility.Utility.fontTitle);
		spring.putConstraint(SpringLayout.NORTH, displayTitle, 10,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayTitle, 40,
				SpringLayout.WEST, this.getContentPane());
		insertPanel.add(displayName);
		displayName.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayName, 100,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayName, 40,
				SpringLayout.WEST, this.getContentPane());
		insertPanel.add(departName);
		departName.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, departName, 100,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, departName, 280,
				SpringLayout.WEST, this.getContentPane());
		insertPanel.add(displayMax);
		displayMax.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, displayMax, 180,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, displayMax, 40,
				SpringLayout.WEST, this.getContentPane());
		insertPanel.add(departMax);
		spring.putConstraint(SpringLayout.NORTH, departMax, 180,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, departMax, 280,
				SpringLayout.WEST, this.getContentPane());
		insertPanel.add(labelCheck);
		labelCheck.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, labelCheck, 220,
				SpringLayout.NORTH, this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, labelCheck, 280,
				SpringLayout.WEST, this.getContentPane());
		insertPanel.add(save);
		save.setFont(Utility.Utility.fontDisplay);
		spring.putConstraint(SpringLayout.NORTH, save, 250, SpringLayout.NORTH,
				this.getContentPane());
		spring.putConstraint(SpringLayout.WEST, save, 400, SpringLayout.WEST,
				this.getContentPane());

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String check = null;

				check = controller.modifyDepartment(departName.getText(),
						Integer.parseInt(departMax.getText()), departCode,
						displaynameDepartment);

				labelCheck.setText(check);

			}

		});

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(final WindowEvent e) {

				controller.quitModify();

			}

		});
	}

	public void addObserver(DepartmentController controller) {

		this.controller = controller;
	}

	public void setData(String name, int maxQuantity, int code,
			JLabel nameDepartment) {

		departName.setText(name);
		departMax.setText(String.valueOf(maxQuantity));
		departCode = code;
		displaynameDepartment = nameDepartment;

	}

}
