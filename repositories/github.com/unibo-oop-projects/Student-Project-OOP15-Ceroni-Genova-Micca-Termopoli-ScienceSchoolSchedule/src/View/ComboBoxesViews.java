package View;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JComboBox;

import Controller.SaveController;
import Controller.SaveControllerInterface;
import Model.Courses;
import Model.CoursesImpl;
import Model.Days;
import Model.Hours;
import Model.Professor;
import Model.Room;

/**
 * The class ComboBoxViews contains instances of JComboBox that are called in
 * PanelButton() and FrameInsert()
 * 
 * @author Anna Termopoli
 * 
 *         Modify by Galya Genova
 *
 */
public class ComboBoxesViews {

	private SaveControllerInterface controller = new SaveController();
	private final JComboBox<String> cDays;
	private final JComboBox<String> cRooms;
	private final JComboBox<String> cHours;
	private final JComboBox<String> cProfessor;
	private final JComboBox<String> cCorses;
	private final Set<JComboBox<String>> setCombo;

	private final static String EMPTYSTR = " ";

	public ComboBoxesViews() {

		this.cDays = new JComboBox<String>();
		this.cRooms = new JComboBox<String>();
		this.cHours = new JComboBox<String>();
		this.cProfessor = new JComboBox<String>();
		this.cCorses = new JComboBox<String>();

		this.setCombo = new HashSet<>();
		this.setCombo.add(cDays);
		this.setCombo.add(cRooms);
		this.setCombo.add(cHours);
		this.setCombo.add(cProfessor);
		this.setCombo.add(cCorses);

		cDays.addItem(EMPTYSTR);
		for (Model.Days d : Days.values()) {
			cDays.addItem(d.getString());
		}
		cRooms.addItem(EMPTYSTR);
		for (Room r : controller.getObjToSave().getListRoom()) {
			cRooms.addItem(r.getNameRoom());
		}
		cHours.addItem(EMPTYSTR);
		for (Model.Hours h : Hours.values()) {
			cHours.addItem(h.getValue());
		}
		cProfessor.addItem(EMPTYSTR);
		for (Professor p : controller.getObjToSave().getListProfessor()) {
			cProfessor.addItem(p.getPerson().toString());
		}

	}

	/**
	 * this method implements listener for the comboBoxes that contains the name
	 * of professors and the name of courses it is called in class FrameInsert()
	 * 
	 * @param comboProf
	 * @param comboCorses
	 */

	public void LisenerCombo(JComboBox<String> comboProf, JComboBox<String> comboCorses) {
		comboProf.addActionListener(l -> {

			for (Professor p : controller.getObjToSave().getListProfessor()) {
				if (comboProf.getSelectedItem() == null) {
					comboCorses.setEnabled(false);
				} else {

					comboCorses.setEnabled(true);

					if (p.getPerson().toString().equals(comboProf.getSelectedItem())) {
						comboCorses.removeAllItems();
						for (CoursesImpl cors : p.getCourses()) {
							comboCorses.addItem(cors.getName());
						}
					}

				}
			}
		});
	}

	/**
	 * the method fill the all the comboBoxes in PanelButton()
	 * 
	 * @param comboCorses
	 */

	public void FillCombobox(JComboBox<String> comboCorses) {

		comboCorses.addItem(EMPTYSTR);

		for (Professor p : controller.getObjToSave().getListProfessor()) {
			for (Courses c : p.getCourses()) {
				cCorses.addItem(c.getName());
			}

		}
	}

	/**
	 * Returns the set whit all of the comboBoxes
	 * 
	 * @return Set<JComboBox<String>>
	 */
	public Set<JComboBox<String>> getSetCombo() {
		return this.setCombo;
	}

	public JComboBox<String> getcDays() {
		return cDays;
	}

	public JComboBox<String> getcRooms() {
		return cRooms;
	}

	public JComboBox<String> getcHours() {
		return cHours;
	}

	public JComboBox<String> getcProfessor() {
		return cProfessor;
	}

	public JComboBox<String> getcCorses() {
		return cCorses;
	}
}