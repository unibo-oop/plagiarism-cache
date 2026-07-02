package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.Classrooms;
import model.DailyTime;
import model.Days;
import model.interfaces.IDailyTime;
import model.interfaces.ISubject;

/**
 * Dialog for inserting the data needed to add a subject in the timetable .
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class AddForm extends AbstractForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREF_HEIGHT = 200;
	
	private final JComboBox<ISubject> sub = new JComboBox<>();
	private final JComboBox<Days> days = new JComboBox<>(Days.values());
	private final JComboBox<Classrooms> cls = new JComboBox<>(Classrooms.values());
	private final JComboBox<String> hour = new JComboBox<>();
	private final JSpinner spin = new JSpinner(new SpinnerNumberModel(1, 1, DailyTime.HOURS, 1));
	
	/**
	 * 
	 * @param v the Frame from which the dialog is displayed.
	 */
	public AddForm(final Frame v) {
		super(v);
		for (int i = IDailyTime.FIRST_HOUR; i < (IDailyTime.FIRST_HOUR + IDailyTime.HOURS); i++) {
			hour.addItem(i + "-" + (i + 1));
		}
		final JPanel mainPanel = new JPanel(new GridLayout(5, 2));
		mainPanel.add(sub);
		mainPanel.add(new JLabel("Subject"));
		mainPanel.add(days);
		mainPanel.add(new JLabel("Day"));
		mainPanel.add(cls);
		mainPanel.add(new JLabel("Classroom"));
		mainPanel.add(hour);
		mainPanel.add(new JLabel("Hour"));
		mainPanel.add(spin);
		mainPanel.add(new JLabel("N. Hours"));
		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Method to add the list of available subjects to JComboBox, so the user can select which add.
	 * 
	 * @param s Set of available subjects.
	 */
	public void setList(final Set<ISubject> s) {
		sub.removeAllItems();
		for (final ISubject su : s) {
			sub.addItem(su);
		}
		
		sub.setSelectedIndex(0);
		days.setSelectedIndex(0);
		cls.setSelectedIndex(0);
		hour.setSelectedIndex(0);
		spin.setValue(1);
		setSize(sub.getPreferredSize().width * 2, PREF_HEIGHT);
	}
	
	/**
	 * Method to retrieve the selected subject.
	 * 
	 * @return Selected subject.
	 */
	public ISubject getSubject() {
		return (ISubject) sub.getSelectedItem();
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
	 * Method to retrieve the selected classroom.
	 * 
	 * @return Selected classroom.
	 */
	public Classrooms getClassroom() {
		return (Classrooms) cls.getSelectedItem();
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
	 * Method to retrieve the number of consecutive hours.
	 * 
	 * @return Number of consecutive hours.
	 */
	public int getNumberHours() {
		return (int) spin.getValue();
	}
}
