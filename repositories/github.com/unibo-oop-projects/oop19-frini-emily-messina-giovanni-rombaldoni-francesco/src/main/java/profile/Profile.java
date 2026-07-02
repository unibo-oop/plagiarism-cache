package profile;

import java.awt.Color;

import javax.swing.JPanel;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import message.MessagePan;
import notwist.base.User;
import notwist.database.DBUserImpl;
import user.Activities;
import user.Infos;
import util.StrikePan;

public class Profile extends JPanel{

	private static final long serialVersionUID = 1L;
	/**
     * Creates new Profile Panel
     */
    public Profile(final User user) {
        initComponents(user);
    }  
 
    private void initComponents(final User user) {

    	//If user is a mod
    	int i=1;
       if (i==1) {
    	   
    	   	profile_panel = new JPanel();
            profile_panel.setLayout(new AbsoluteLayout());   
            
            activities_panel = new Activities();
            profile_panel.add(activities_panel, new AbsoluteConstraints(10, 0, -1, -1));
            

            myinfos_panel = new Infos(user);
            profile_panel.add(myinfos_panel, new  AbsoluteConstraints(730, 0, -1, -1));

            message_panel = new MessagePan();
            profile_panel.add(message_panel, new  AbsoluteConstraints(10, 150, -1, -1)); 	   
    	   
            strike_panel = new StrikePan();
            profile_panel.add(strike_panel, new  AbsoluteConstraints(730, 150, 310, 310));
           add(profile_panel);
    	 
       }
       
       //If user is not a mod
       else{
    	
    	profile_panel = new  JPanel();
        profile_panel.setLayout(new  AbsoluteLayout());   
        
        activities_panel = new Activities();
        profile_panel.add(activities_panel, new  AbsoluteConstraints(10, 0, -1, -1));
        

        myinfos_panel = new Infos(user);
        profile_panel.add(myinfos_panel, new  AbsoluteConstraints(730, 0, -1, -1));

        message_panel = new MessagePan();
        profile_panel.add(message_panel, new  AbsoluteConstraints(10, 230, -1, -1));
        
        add(profile_panel);
       }
    }
    
 
    
    // Variables declaration
    private JPanel profile_panel;
    private Activities activities_panel;
    private Infos myinfos_panel;
    private MessagePan message_panel;
    private StrikePan strike_panel;
    // End of variables declaration
    
   
}
