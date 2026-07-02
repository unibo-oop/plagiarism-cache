package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class UpperPan extends JPanel {



		private static final long serialVersionUID = 1L;
		
		public UpperPan() {
			
			drawComp();
		}
		
		private void drawComp() {
	        upper_panel = new JPanel();
	        close_label = new JLabel();
	        switch_label= new JLabel();
	        //Draw upper menu panel
	        upper_panel.setLayout(new AbsoluteLayout());

			//Switch "button"
	        switch_label.setFont(new Font("Tahoma", 0, 18)); // NOI18N
	        switch_label.setForeground(new Color(250, 0, 0));
	        switch_label.setText("S");	      
	        upper_panel.add(switch_label, new AbsoluteConstraints(330, 10, -1, -1));
	        
	        //Close "button"
	        close_label.setFont(new Font("Tahoma", 0, 18)); // NOI18N
	        close_label.setForeground(new Color(250, 0, 0));
	        close_label.setText("X");
	        upper_panel.add(close_label, new AbsoluteConstraints(380, 10, -1, -1));

	        add(upper_panel);
	        
			//Close method
	        close_label.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                close_labelMouseClicked(evt);
	            }
	        });
	        
	        //Switch theme
	        switch_label.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                switch_labelMouseClicked(evt);
	            }
	        });
		}

	    //Close
	    private void close_labelMouseClicked(MouseEvent evt) {
	    	System.exit(0);
	    }


	    //Switch
	    private void switch_labelMouseClicked(MouseEvent evt) {
	    	
	    	if ("S" == switch_label.getText()) {
	    	 switch_label.setText("C");
	    	 
	         try {
	             UIManager.setLookAndFeel(new FlatDarkLaf());
	         } catch ( Exception ex ) {
	      	    System.err.println( "Failed to initialize LaF" );
	         }
	         SwingUtilities.updateComponentTreeUI(getParent());
	    	 
	    	}else {
	    		 switch_label.setText("S");
	        	 
	             try {
	                 UIManager.setLookAndFeel(new FlatLightLaf());
	             } catch ( Exception ex ) {
	          	    System.err.println( "Failed to initialize LaF" );
	             }
	             SwingUtilities.updateComponentTreeUI(getParent());    		
	    	}  	 
	    }
		
		
		
	    private JPanel upper_panel;
		 private JLabel close_label;
		    private JLabel switch_label;
}
