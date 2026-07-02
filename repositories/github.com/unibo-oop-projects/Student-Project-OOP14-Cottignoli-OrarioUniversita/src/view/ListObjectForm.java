package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dialog where there is only one JComboBox, used to remove subjects from available subject list or for the management of view types.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class ListObjectForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PREF_HEIGHT = 100;
	
	private final JComboBox<Object> sub = new JComboBox<>();
	private final JLabel lsub = new JLabel();
	
	/**
	 * 
	 * @param v The Frame from which the dialog is displayed.
	 */
	public ListObjectForm(final Frame v) {
		super(v);
		final JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(sub);
		mainPanel.add(lsub);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Method to set a list of selectable objects and a name to represent these objects.
	 * 
	 * @param s List of objects to add in JComboBox.
	 * @param objectName Object's name.
	 */
	public void setList(final Set<? extends Object> s, final String objectName) {
		sub.removeAllItems();
		lsub.setText(objectName);
		for (final Object obj : s) {
			sub.addItem(obj);
		}
		sub.setSelectedIndex(0);
		setSize(sub.getPreferredSize().width + lsub.getPreferredSize().width * 2, PREF_HEIGHT);
	}
	
	/**
	 * Method to retrieve the selected object.
	 * 
	 * @return Selected object.
	 */
	public Object getSelectedObject() {
		return sub.getSelectedItem();
	}

}
