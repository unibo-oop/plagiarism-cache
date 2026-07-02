package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.Classrooms;
import model.DailyTime;
import model.Days;
import model.interfaces.IDailyTime;

/**
 * Dialog for removing subjects from timetable.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class RemoveForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JComboBox<Days> days = new JComboBox<>(Days.values());
	private final JComboBox<String> hour = new JComboBox<>();
	private final JComboBox<Classrooms> cls = new JComboBox<>(Classrooms.values());
	private final JSpinner spin = new JSpinner(new SpinnerNumberModel(1, 1, DailyTime.HOURS, 1));
	
	/**
	 * 
	 * @param v The frame from which the dialog is displayed.
	 */
	public RemoveForm(final Frame v) {
		super(v);
		for (int i = IDailyTime.FIRST_HOUR; i < (IDailyTime.FIRST_HOUR + IDailyTime.HOURS); i++) {
			hour.addItem(i + "-" + (i + 1));
		}
		final JPanel mainPanel = new JPanel(new GridLayout(4, 2));
		mainPanel.add(days);
		mainPanel.add(new JLabel("Day"));
		mainPanel.add(cls);
		mainPanel.add(new JLabel("Classroom"));
		mainPanel.add(hour);
		mainPanel.add(new JLabel("hour"));
		mainPanel.add(spin);
		mainPanel.add(new JLabel("N. Hours"));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		pack();
	}
	
	@Override
	public void setVisible(final boolean b) {
		super.setVisible(b);
		if (b) {
			days.setSelectedIndex(0);
			hour.setSelectedIndex(0);
			cls.setSelectedIndex(0);
			spin.setValue(1);
		}
	}
	
	/**
	 * Method to retrieve the selected day.
	 * 
	 * @return Selected day.
	 */
	public Days getDay() {
		return (Days) days.getSelectedItem();
	}
	
	/**
	 * Method to retrieve the lesson starting time.
	 * 
	 * @return Lesson starting time.
	 */
	public int getHour() {
		return DailyTime.FIRST_HOUR + hour.getSelectedIndex();
	}
	
	/**
	 * Method to retrieve the selected classroom.
	 * 
	 * @return Selected classroom.
	 */
	public Classrooms getClassroom() {
		return (Classrooms) cls.getSelectedItem();
	}
	
	/**
	 * Method to retrieve the number of consecutive hours.
	 * 
	 * @return Number of consecutive hours.
	 */
	public int getNumberHours() {
		return (int) spin.getValue();
	}

}
