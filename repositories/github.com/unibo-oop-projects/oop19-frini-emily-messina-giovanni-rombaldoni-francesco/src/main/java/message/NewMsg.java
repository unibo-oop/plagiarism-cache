package message;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class NewMsg extends JPanel{
	
	private static final long serialVersionUID = 1L;
	/**
     * Creates new MessagePan to send a msg.
     */
    public NewMsg() {
        initComponents();
    }

    private void initComponents() {
        newmessage_panel = new JPanel();
        username = new JTextField();
        jScrollPane3 = new JScrollPane();
        message_area = new JTextArea();
        Submit = new JButton();
        
        newmessage_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 0, 0)), "New Message", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14))); // NOI18N

        username.setText("Username");

        jScrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        message_area.setColumns(20);
        message_area.setRows(5);
        jScrollPane3.setViewportView(message_area);
        message_area.setWrapStyleWord(true);
        message_area.setLineWrap(true);

        Submit.setText("Send");

        GroupLayout newmessage_panelLayout = new GroupLayout(newmessage_panel);
        newmessage_panel.setLayout(newmessage_panelLayout);
        newmessage_panelLayout.setHorizontalGroup(
            newmessage_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(newmessage_panelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(newmessage_panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(Submit, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                    .addGroup(newmessage_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(username, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        newmessage_panelLayout.setVerticalGroup(
            newmessage_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(newmessage_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Submit)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        
        add(newmessage_panel);
        
    }

	
    private JButton Submit;
    private JScrollPane jScrollPane3;
    private JTextField username;
    private JPanel newmessage_panel;
    private JTextArea message_area;
}
