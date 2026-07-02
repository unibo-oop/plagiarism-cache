package util;

import java.awt.Container;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import main.Loader;
import notwist.database.DBDiscussionImpl;
import topic.Topic;

public class ButtonPan extends JPanel{

	private static final long serialVersionUID = 1L;
	private Loader loader;
	
	public ButtonPan(TableDiscussion tableDiscussion) {
		this.loader = new Loader();
		drawComp(tableDiscussion);
	}
	
	private void drawComp(TableDiscussion tableDiscussion) {
		random_button = new JButton("Scegli una discussione randomica!");
		button_panel = new JPanel();
	        GroupLayout button_panelLayout = new GroupLayout(button_panel);
	        button_panel.setLayout(button_panelLayout);
	        button_panelLayout.setHorizontalGroup(
	            button_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addComponent(random_button, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
	        );
	        button_panelLayout.setVerticalGroup(
	            button_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(button_panelLayout.createSequentialGroup()
	                .addComponent(random_button, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 6, Short.MAX_VALUE))
	        );
	        add(button_panel);
	        
	        random_button.addActionListener(e ->{
	        		loader.start();
	              	new SwingWorker<String,Object>(){

	      				@Override
	      				protected String doInBackground() throws Exception {
	      					Random rand = new Random();
	      					JTable table = tableDiscussion.getTableDiscussion();
	      	        		new DBDiscussionImpl().
	      								getDiscussionFromId(Integer.parseInt(table.
	      													getValueAt(rand.nextInt(table.getRowCount()), 4).toString())).get();
	      	        		//A TODO
	      	        	loader.end();	
	      	        	return "";
	      				}
	              	}.execute();
	              		
	              });
	        
	        
	        
	}
	private JButton random_button;
	private JPanel button_panel;
}
