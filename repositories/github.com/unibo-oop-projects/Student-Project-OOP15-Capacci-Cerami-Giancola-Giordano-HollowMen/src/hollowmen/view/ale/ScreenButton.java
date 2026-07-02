package hollowmen.view.ale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;

/**
 * The ScreenButton class is used to show on screen all the buttons that
 * the player can press during the game.
 * 
 * @author Alessia
 *
 */
public class ScreenButton extends JButton{
	private static final long serialVersionUID = -4490819627872969413L;
	private Border border=BorderFactory.createRaisedBevelBorder();//To set a border to the buttons.
	
	public ScreenButton(ViewObserver observer, InputCommand command , Map<String,JLabel> storage){
		super(storage.get(command.getString()).getIcon());
		this.setBorder(border);
		this.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {
    		        observer.addInput(command);
    		    }
		});
	}
	
	public ScreenButton(ViewObserver observer, InputMenu command , Map<String,JLabel> storage){
		super(storage.get(command.getString()).getIcon());
		this.setBorder(border);
		this.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {
			observer.addInput(command);
		    }
		});
	}
}
