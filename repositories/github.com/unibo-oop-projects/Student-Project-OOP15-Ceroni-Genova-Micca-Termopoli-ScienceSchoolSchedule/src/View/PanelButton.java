package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This class contains the panel whit the buttons and comboBoxes that allows to
 * see the different views of table or perform options like insert new item,
 * export file in Excel, reset the table, cancel specific information, modify
 * name of professor or classroom.
 * 
 * The panel that contains all of these components use the GridGagLayout().
 * There are the instances of the classes : Labels ComboBoxesViews PanelLegend
 * 
 * 
 * @author Anna Termopoli
 * 
 *         Modify by Galya Genova
 *
 */
public class PanelButton {

	private MyComboListeners comboListeners = new MyComboListeners();
	private final JPanel panelButton;
	private final Labels labels = new Labels();
	private final ComboBoxesViews comboBoxes = new ComboBoxesViews();
	private final GridBagConstraints cnst = new GridBagConstraints();
	private final PanelLegend panelLegend = new PanelLegend();

	public PanelButton(MainGUI mainGUI) {
		Buttons buttons = new Buttons(mainGUI);
		this.panelButton = new JPanel(new GridBagLayout());
		this.panelButton.setBorder(new TitledBorder(" Buon lavoro con SSS :) "));
		this.panelButton.setBackground(new Color(171, 205, 239));

		this.cnst.gridy = 1;
		this.cnst.fill = GridBagConstraints.BOTH;

		this.setCnstXOne();
		this.panelButton.add(buttons.getInsert(), cnst);
		this.incrementCnstY();
		this.incrementCnstY();
		this.incrementCnstY();
		this.panelButton.add(buttons.getGeneralView(), cnst);
		buttons.getGeneralView().addActionListener(l -> {
			buttons.getInsert().setEnabled(true);
		});
		this.incrementCnstY();
		this.panelButton.add(new JLabel(" "), cnst);
		this.setCnstXZero();
		this.incrementCnstY();
		this.panelButton.add(this.labels.getViews(), cnst);
		this.incrementCnstY();
		this.setCnstXZero();
		this.panelButton.add(this.labels.getlDays(), cnst);
		this.setCnstXOne();
		this.panelButton.add(this.comboBoxes.getcDays(), cnst);
		this.incrementCnstY();
		this.setCnstXZero();
		this.panelButton.add(this.labels.getlRooms(), cnst);
		this.setCnstXOne();
		this.panelButton.add(this.comboBoxes.getcRooms(), cnst);
		this.incrementCnstY();
		this.setCnstXZero();
		this.panelButton.add(this.labels.getlHours(), cnst);
		this.setCnstXOne();
		this.panelButton.add(this.comboBoxes.getcHours(), cnst);
		this.incrementCnstY();
		this.setCnstXZero();
		this.panelButton.add(this.labels.getlProfessor(), cnst);
		this.setCnstXOne();
		this.panelButton.add(this.comboBoxes.getcProfessor(), cnst);
		this.incrementCnstY();
		this.setCnstXZero();
		this.panelButton.add(this.labels.getlCorses(), cnst);
		this.setCnstXOne();
		this.panelButton.add(this.comboBoxes.getcCorses(), cnst);
		this.incrementCnstY();
		this.panelButton.add(new JLabel(" "), cnst);
		this.incrementCnstY();
		this.panelButton.add(buttons.getInsertNew(), cnst);
		this.incrementCnstY();
		this.panelButton.add(buttons.getSaveInExel(), cnst);
		this.incrementCnstY();
		this.panelButton.add(buttons.getResetTable(), cnst);
		this.incrementCnstY();
		this.panelButton.add(buttons.getCancel(), cnst);
		this.incrementCnstY();
		this.panelButton.add(buttons.getExit(), cnst);
		this.incrementCnstY();
		this.panelButton.add(panelLegend.getPanelLegend(), cnst);

		this.comboBoxes.FillCombobox(this.comboBoxes.getcCorses());

		this.ClearAllCombo(this.comboBoxes.getcDays());
		this.ClearAllCombo(this.comboBoxes.getcHours());
		this.ClearAllCombo(this.comboBoxes.getcCorses());
		this.ClearAllCombo(this.comboBoxes.getcProfessor());
		this.ClearAllCombo(this.comboBoxes.getcRooms());

		this.comboBoxes.getcDays().addActionListener(this.comboListeners.getListenerDays());
		this.comboBoxes.getcRooms().addActionListener(this.comboListeners.getListenerRoom());
		this.comboBoxes.getcHours().addActionListener(this.comboListeners.getListenerHour());
		this.comboBoxes.getcProfessor().addActionListener(this.comboListeners.getListenerProf());
		this.comboBoxes.getcCorses().addActionListener(this.comboListeners.getListenerCourse());
	}

	/**
	 * the method allow to modify the selectItem of the comboBoxes that are not
	 * selected
	 * 
	 * @param combo
	 */

	private void ClearAllCombo(JComboBox<String> combo) {
		combo.addActionListener(l -> {
			for (JComboBox<String> jComboBox : this.comboBoxes.getSetCombo()) {
				if (!jComboBox.equals(combo)) {
					if (!jComboBox.getSelectedItem().equals(" ")) {
						jComboBox.setSelectedIndex(0);
					}
				}
			}
		});
	}

	public JPanel getPanelButton() {
		return this.panelButton;
	}

	/**
	 * the method increment the constant gridy for the GridBagLayout() it is
	 * uses local
	 */

	private void incrementCnstY() {
		this.cnst.gridy++;
	}

	/**
	 * the method set the value of grigx to zero it is use local
	 */
	private void setCnstXZero() {
		this.cnst.gridx = 0;
	}

	/**
	 * the method set the value of grigx to one it is use local
	 */
	private void setCnstXOne() {
		this.cnst.gridx = 1;
	}
}