package util;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class RulesPan extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates panel for NewTopic Rules
	 */
	public RulesPan() {
		initComponents();
	}

	private void initComponents() {

		rules_panel = new JPanel();
		jScrollPane3 = new JScrollPane();
		rules_area = new JEditorPane("text/html", "");

		rules_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 0, 0)), "Regole della Community",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14))); // NOI18N

		jScrollPane3.setEnabled(false);
		jScrollPane3.setOpaque(false);
		
		importRules();

		rules_area.setEnabled(false);
		jScrollPane3.setViewportView(rules_area);
		GroupLayout rules_panelLayout = new GroupLayout(rules_panel);
		rules_panel.setLayout(rules_panelLayout);
		rules_panelLayout.setHorizontalGroup(rules_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(rules_panelLayout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		rules_panelLayout.setVerticalGroup(rules_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING,
						rules_panelLayout.createSequentialGroup().addContainerGap()
								.addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		add(rules_panel);
	}

	private JScrollPane jScrollPane3;
	private JEditorPane rules_area;
	private JPanel rules_panel;
	
	private void importRules(){
			BufferedReader buf;
			try {
				buf = new BufferedReader(new FileReader("docs/Rules.txt"));
				rules_area.read(buf, null);
			} catch (IOException e) {
				System.out.println("File Rules.txt not found! ");
				e.printStackTrace();
			}
	}

}
