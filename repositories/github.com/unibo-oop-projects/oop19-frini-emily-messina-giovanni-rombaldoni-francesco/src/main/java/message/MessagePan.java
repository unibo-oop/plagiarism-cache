package message;

import javax.swing.JPanel;


public class MessagePan extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
     * Creates new MessagePan to hold messages and chat
     */
    public MessagePan() {
        initComponents();
    }

    private void initComponents() {
    	
    	message_panel = new JPanel();
    	
    	historymsg_panel = new HistoryMsg();
    	newmsg_panel = new NewMsg();
       
    	int i= 1;
    	if (i==1) {
        message_panel.add(historymsg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, 220));

        message_panel.add(newmsg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 310, 220));              
        
        add(message_panel);      
    	}else {
    		
            message_panel.add(historymsg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 670, 220));

            message_panel.add(newmsg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 340, 310, 220));              
            
            add(message_panel);    
    	}
    }
     
    private JPanel message_panel;
    private HistoryMsg historymsg_panel;
    private NewMsg newmsg_panel;
    
}
