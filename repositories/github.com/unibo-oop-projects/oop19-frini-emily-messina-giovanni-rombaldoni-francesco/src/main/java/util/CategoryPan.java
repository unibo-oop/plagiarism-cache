package util;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;

import main.Loader;
import notwist.base.Category;
import notwist.database.DBCategory;
import notwist.database.DBCategoryImpl;



public class CategoryPan extends JPanel{

	private static final long serialVersionUID = 1L;
	private Loader loader = new Loader();
	private DBCategory dbcategory = new DBCategoryImpl();
	
	public CategoryPan(TableDiscussion tableDiscussion) {
		drawComp(tableDiscussion);
	}
	
	private void drawComp(TableDiscussion tableDiscussion) {
		
		category_panel = new JPanel();
        category_list = new JScrollPane();
        jList = new javax.swing.JList<>();
	    category_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 0)), "category", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14))); // NOI18N
	    category_panel.setFont(new Font("Tahoma", 0, 14)); // NOI18N
	    
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        category_list.setViewportView(jList);   
        
	        jList.setFont(new Font("Tahoma", 0, 14)); // NOI18N

	        GroupLayout category_panelLayout = new GroupLayout(category_panel);
	        category_panel.setLayout(category_panelLayout);
	        category_panelLayout.setHorizontalGroup(
	            category_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(category_panelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(category_list,210, 210,Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        category_panelLayout.setVerticalGroup(
	            category_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(category_panelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(category_list, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
	                .addContainerGap())
	            
	          
	        );
	    
	        jList.setModel(new AbstractListModel<String>() {
	        	List<Category> list = new DBCategoryImpl().getCategories().get();
	        	public int getSize() { return list.size(); }
	            public String getElementAt(int i) { return list.get(i).getName(); }
	        });
	        
	        
	        //Filter results of discussions by choosen category on list on the right
	        jList.addMouseListener(new MouseAdapter() {

	        	public void mouseClicked(MouseEvent e) {
	        		loader.start();
	        		new SwingWorker<String,Object>(){

	        			@Override
	        			protected String doInBackground() throws Exception {
	        			 	Category cat = dbcategory.getCategoryByName(jList.getSelectedValue());
	        			 	tableDiscussion.refreshTableDiscussion(cat);
	        				loader.end();
	        				return "";
	        			}
	 				
	        		}.execute();
	        	}
	        });
	        


	        
	        add(category_panel);
	}
	
	
        
    private JPanel category_panel;
    private JScrollPane category_list;
    private JList<String> jList;

    
}
