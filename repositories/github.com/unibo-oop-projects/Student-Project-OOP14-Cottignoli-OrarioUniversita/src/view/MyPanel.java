package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import model.SubjectType;

/**
 * Class that define main panel of class {@link View}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel creation.
	 * 
	 * @param tm Model of {@link JTable} in the panel.
	 * @param bAdd Button used to add a subject to timetable.
	 * @param bRemove Button used to remove subject from timetable.
	 * @param r1 RadioButton used to select first semester.
	 * @param r2 RadioButton used to select second semester.
	 */
	public MyPanel(final TableModel tm, final JButton bAdd, final JButton bRemove, final JRadioButton r1, final JRadioButton r2) {
		final JTable table = new JTable(tm);
		final JScrollPane scroll = new JScrollPane(table);
		table.setDefaultRenderer(Object.class, new MyRenderer());
		table.setTableHeader(null);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		
		final JPanel eastPanel = new JPanel(new GridBagLayout());
		GridBagConstraints cnst = new GridBagConstraints();
		cnst.gridy = 0;
		cnst.fill = GridBagConstraints.BOTH;
		eastPanel.add(bAdd, cnst);
		cnst.gridy++;
		eastPanel.add(bRemove, cnst);
		cnst.gridy++;
		
		final JPanel semPanel = new JPanel(new GridBagLayout());
		semPanel.setBorder(new TitledBorder("SEMESTER"));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		final ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(r1);
		r1.setSelected(true);
		semPanel.add(r1, c);
		c.gridy++;
		buttonGroup.add(r2);
		semPanel.add(r2, c);
		
		eastPanel.add(semPanel, cnst);
		cnst.gridy++;

		final JPanel colorPanel = new JPanel(new GridBagLayout());
		c.gridy = 0;
		colorPanel.setBorder(new TitledBorder("TABLE LEGEND"));
		for (final SubjectType st : SubjectType.values()) {
			c.gridx = 0;
			final JLabel rect = new JLabel("   ");
			rect.setOpaque(true);
			rect.setBackground(st.getColor());
			colorPanel.add(rect, c);
			c.gridx++;
			colorPanel.add(new JLabel(st.getDescription() + " (" + st.toString() + ")"), c);
			c.gridy++;
		}
		eastPanel.add(colorPanel, cnst);
		
		add(eastPanel, BorderLayout.EAST);
	}
}
