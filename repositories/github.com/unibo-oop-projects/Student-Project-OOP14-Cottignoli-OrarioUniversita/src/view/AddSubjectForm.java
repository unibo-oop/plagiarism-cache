package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SubjectType;

/**
 * Dialog for inserting data needed to create a new subject .
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class AddSubjectForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField sub = new JTextField(20);
	private final JTextField teach = new JTextField(20);
	private final JComboBox<SubjectType> type = new JComboBox<>(SubjectType.values());

	/**
	 * 
	 * @param v The Frame from which the dialog is displayed.
	 */
	public AddSubjectForm(final Frame v) {
		super(v);
		final JPanel mainPanel = new JPanel(new GridLayout(3, 2));
		mainPanel.add(sub);
		mainPanel.add(new JLabel("Subject Name"));
		mainPanel.add(teach);
		mainPanel.add(new JLabel("Teacher Name"));
		mainPanel.add(type);
		mainPanel.add(new JLabel("Subject Type"));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		pack();
	}
	
	@Override
	public void setVisible(final boolean b) {
		super.setVisible(b);
		if (b) {
			sub.setText("");
			teach.setText("");
			type.setSelectedIndex(0);
		}
	}
	
	/**
	 * Method to retrieve the subject's name.
	 * 
	 * @return Subject's name.
	 */
	public String getSubName() {
		return sub.getText();
	}
	
	/**
	 * Method to retrieve the name of the teacher who gives this lesson.
	 * 
	 * @return Name of the teacher who gives this lesson.
	 */
	public String getTeachName() {
		return teach.getText();
	}
	
	/**
	 * Method to retrieve the subject type.
	 * 
	 * @return Subject type.
	 */
	public SubjectType getSubType() {
		return (SubjectType) type.getSelectedItem();
	}
}
