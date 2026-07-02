package ViewBy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class implements ActionListener and override the actionPerformed method
 * that calls the frame ViewGeneral
 * 
 * @author Galya Genova
 *
 */
public class MyListenerView implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		new ViewGeneral("Tabella principale");
	}

}
