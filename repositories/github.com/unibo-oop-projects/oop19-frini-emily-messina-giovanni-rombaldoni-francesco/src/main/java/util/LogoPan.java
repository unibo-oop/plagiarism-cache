package util;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LogoPan extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public LogoPan() {
		
		drawComp();
	}
	
	private void drawComp() {
		
        logo_panel = new JPanel();
        logo = new JLabel();
        
        //Draw logo_panel, get the picture and fix the size to fit the panel itself
        logo_panel.setBackground(new Color(67, 71, 91));
        logo.setIcon(new ImageIcon("img/font_test_1.png")); // NOI18N
        logo.setDoubleBuffered(true);
        logo.setFocusable(false);
        logo.setRequestFocusEnabled(false);
        logo.setVerifyInputWhenFocusTarget(false);

        GroupLayout logo_panelLayout = new GroupLayout(logo_panel);
        logo_panel.setLayout(logo_panelLayout);
        
        logo_panelLayout.setHorizontalGroup(
            logo_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(logo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        logo_panelLayout.setVerticalGroup(
            logo_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, logo_panelLayout.createSequentialGroup()
            		.addComponent(logo, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
            		.addGap(0, 0, Short.MAX_VALUE))
        );

        add(logo_panel);
	}

    private JLabel logo;
    private JPanel logo_panel;
}


