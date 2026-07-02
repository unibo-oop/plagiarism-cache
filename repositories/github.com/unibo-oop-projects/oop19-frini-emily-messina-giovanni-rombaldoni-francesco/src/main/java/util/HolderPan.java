package util;



import java.awt.Container;

import javax.swing.JPanel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import table.Nofilter;
import util.CategoryPan;
import util.HottestPan;

public class HolderPan extends JPanel {

	private static final long serialVersionUID = 1L;
	
    public HolderPan(TableDiscussion tableDiscussion) {
        initComponents(tableDiscussion);
    }

    private void initComponents(TableDiscussion tableDiscussion) {
    	
    	holder_panel = new JPanel();
    	holder_panel.setLayout(new AbsoluteLayout());
    	
        //Get Table Pan and add it
        table_panel = new Nofilter(tableDiscussion);
        table_panel.setVisible(true);
        holder_panel.add(table_panel, new AbsoluteConstraints(0, 21, -1, -1));
      
        
    	//Get hottest panel and add it to the main frame
        hottest_panel = new HottestPan();
        hottest_panel.setVisible(true);      
        holder_panel.add(hottest_panel, new AbsoluteConstraints(814, 21, 240, -1));
   
        //Get Category Pan and add it       
        category_panel = new CategoryPan(tableDiscussion);
        category_panel.setVisible(true);
        holder_panel.add(category_panel, new AbsoluteConstraints(814, 192, 240, -1));
   

        //GGet Button Pan and add it
        button_panel = new ButtonPan(tableDiscussion);
        button_panel.setVisible(true);     
        holder_panel.add(button_panel, new AbsoluteConstraints(814, 410, -1, -1));
        
        //Draw the whole thingy
        add(holder_panel);
    	
    	
    }
    // Variables declaration - do not modify

    private Nofilter table_panel;
    private HottestPan hottest_panel;
    private CategoryPan category_panel;
    private JPanel holder_panel;
    private ButtonPan button_panel;
    // End of variables declaration
}
