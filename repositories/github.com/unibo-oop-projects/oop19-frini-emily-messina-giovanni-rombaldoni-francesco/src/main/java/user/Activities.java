package user;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class Activities extends JPanel{
	
	private static final long serialVersionUID = 1L;
	/**
     * Shows the activies of a certain user
     */
    public Activities() {
        initComponents();
    }
    
    private void initComponents() {
    	
        jScrollPane1 = new JScrollPane();
        activities_panel = new JPanel();
        jScrollPane2 = new  JScrollPane();
        jTextArea1 = new  JTextArea();
    	
    	
    	  jScrollPane1.setBorder( BorderFactory.createTitledBorder( BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 0, 0)), "My Activities", TitledBorder.DEFAULT_JUSTIFICATION,  TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14))); // NOI18N
          jScrollPane1.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

          jScrollPane2.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
          jScrollPane2.setToolTipText("");
          jScrollPane2.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

          jTextArea1.setColumns(20);
          jTextArea1.setLineWrap(true);
          jTextArea1.setRows(5);
          jTextArea1.setText("Hai posto una questione in * chiamata *");
          jScrollPane2.setViewportView(jTextArea1);

          
           GroupLayout  activities_panelLayout = new  GroupLayout(activities_panel);
          activities_panel.setLayout( activities_panelLayout);
          activities_panelLayout.setHorizontalGroup(
          activities_panelLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
              .addGroup( activities_panelLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jScrollPane2,  GroupLayout.PREFERRED_SIZE, 627,  GroupLayout.PREFERRED_SIZE)
                  .addContainerGap(10, Short.MAX_VALUE))
          );
          activities_panelLayout.setVerticalGroup(
        		  activities_panelLayout.createParallelGroup( GroupLayout.Alignment.LEADING)
              .addGroup( activities_panelLayout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jScrollPane2,  GroupLayout.PREFERRED_SIZE, 46,  GroupLayout.PREFERRED_SIZE)
                  .addContainerGap(20, Short.MAX_VALUE))
          );

          jScrollPane1.setViewportView(activities_panel);
          add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 1));
    }
    private  JScrollPane jScrollPane1;
    private  JScrollPane jScrollPane2;
    private  JPanel activities_panel;

    private  JTextArea jTextArea1;
    
}
