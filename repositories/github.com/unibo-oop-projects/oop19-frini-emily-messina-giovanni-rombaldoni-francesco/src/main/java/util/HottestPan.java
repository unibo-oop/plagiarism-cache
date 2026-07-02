package util;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;



public class HottestPan extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public HottestPan() {
		
		drawComp();
	}
	
	private void drawComp() {
		hottest_panel = new JPanel();
	    topdiscussiontable = new JTable();
	    //Panel Title
        hottest_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0)), "Hottest Topics", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14))); // NOI18N
        hottest_panel.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        //Draw Table
        topdiscussiontable.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        topdiscussiontable.setModel(new DefaultTableModel(
            new Object [][] {
                {"test1"},
                {"test2"},
                {"test3"},
                {"test4"},
                {"test5"}
            },
            new String [] {
                "Title "
            }
        ) {
            Class[] types = new Class [] {
                String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        topdiscussiontable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        topdiscussiontable.setAutoscrolls(false);
        topdiscussiontable.setEnabled(false);
        topdiscussiontable.setRowHeight(25);
        topdiscussiontable.getTableHeader().setReorderingAllowed(false);

        GroupLayout hottest_panelLayout = new GroupLayout(hottest_panel);
        hottest_panel.setLayout(hottest_panelLayout);
        hottest_panelLayout.setHorizontalGroup(
            hottest_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(hottest_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topdiscussiontable, 210, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
        hottest_panelLayout.setVerticalGroup(
            hottest_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, hottest_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topdiscussiontable, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );
        add(hottest_panel);
	}
        private javax.swing.JPanel hottest_panel;
        private javax.swing.JTable topdiscussiontable;
    
}