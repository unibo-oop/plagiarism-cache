package table;


import javax.swing.JPanel;

import util.TableDiscussion;


public class Nofilter extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Nofilter(TableDiscussion tableDiscussion) {
		drawComp(tableDiscussion);
	}
	
	private void drawComp(TableDiscussion tableDiscussion){
		table_panel = new JPanel();
        jTextField2 = new javax.swing.JTextField();
        main_table = new javax.swing.JScrollPane();
        
        table_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)), "What's new", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        table_panel.setName(""); // NOI18N
        
        main_table.setViewportView(tableDiscussion.getTableDiscussion());
        tableDiscussion.refreshTableDiscussion();

        jTextField2.setText("Spazio per next - prev che non posso creare ora :C");
        jTextField2.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout table_panelLayout = new javax.swing.GroupLayout(table_panel);
        table_panel.setLayout(table_panelLayout);
        table_panelLayout.setHorizontalGroup(
            table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(table_panelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(table_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(main_table)
                .addContainerGap())
        );
        table_panelLayout.setVerticalGroup(
            table_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(table_panelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(main_table, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        add(table_panel);
   }

    // Variables declaration - do not modify
    private JPanel table_panel;

    private javax.swing.JTextField jTextField2;
    private javax.swing.JScrollPane main_table;

    // End of variables declaration

}
	

