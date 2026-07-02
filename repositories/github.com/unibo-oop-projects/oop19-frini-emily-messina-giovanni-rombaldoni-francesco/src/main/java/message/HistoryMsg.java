package message;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class HistoryMsg extends JPanel{

	private static final long serialVersionUID = 1L;

	/**
     * Creates a space to manage the incoming/sent msgs.
     */
    public HistoryMsg() {
        initComponents();
    }

    private void initComponents() {
    	
        history_panel = new JPanel(); //incoming msgs

        history_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 0, 0)), "My Messages", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14))); // NOI18N
        int i =1;
        if (i==1) {
            GroupLayout messages_panelLayout = new GroupLayout(history_panel);
            history_panel.setLayout(messages_panelLayout);
            messages_panelLayout.setHorizontalGroup(
                messages_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 350, Short.MAX_VALUE)
            );
            messages_panelLayout.setVerticalGroup(
                messages_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 195, Short.MAX_VALUE)
            );

        }else {
        GroupLayout messages_panelLayout = new GroupLayout(history_panel);
        history_panel.setLayout(messages_panelLayout);
        messages_panelLayout.setHorizontalGroup(
            messages_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
        );
        messages_panelLayout.setVerticalGroup(
            messages_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );
        }
        add(history_panel);
    }
    
    private JPanel history_panel;
}
