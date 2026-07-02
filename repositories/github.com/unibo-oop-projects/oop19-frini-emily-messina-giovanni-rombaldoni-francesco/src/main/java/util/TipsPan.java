package util;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

public class TipsPan extends JPanel {

	public TipsPan() {

		drawPan();
	}

	private void drawPan() {
		markups_panel = new JPanel();
		jScrollPane4 = new JScrollPane();
		tips_panel = new JTextArea();

		markups_panel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 0, 0)),
						"Tips", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14))); // NOI18N

		jScrollPane4.setEnabled(false);

		importTips();
		tips_panel.setColumns(20);
		tips_panel.setRows(5);
		tips_panel.setWrapStyleWord(true);
		tips_panel.setLineWrap(true);
		tips_panel.setEnabled(false);
		jScrollPane4.setViewportView(tips_panel);

		GroupLayout markups_panelLayout = new GroupLayout(markups_panel);
		markups_panel.setLayout(markups_panelLayout);
		markups_panelLayout.setHorizontalGroup(markups_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(markups_panelLayout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		markups_panelLayout.setVerticalGroup(markups_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(markups_panelLayout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		add(markups_panel);

	}
	
	private JScrollPane jScrollPane4;
	private JPanel markups_panel;
	private JTextArea tips_panel;

	private void importTips() {
		BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader("docs/Tips.txt"));
			tips_panel.read(buf, null);
		} catch (IOException e) {
			System.out.println("File Tips.txt not found! ");
			e.printStackTrace();
		}
	}
}
