package hollowmen.view.ale;

import java.awt.event.KeyEvent;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;

/**
 * The class InputChooser is used to choose the player's input. 
 * 
 * @author Alessia
 *
 */
public class InputChooser {
	ViewObserver observer;
	
	public InputChooser(ViewObserver observer){
		this.observer=observer;
	}
	
	//Switch-case improve performance. It's a flexible instruction used to control.
	/**
	 * The method {@code chooser} is used to select, thanks to a switch-case, the correct player's input.
	 * 
	 * @param key
	 */
	public void chooser(int key){
		switch (key){
		
		case KeyEvent.VK_W:{ this.observer.addInput(InputCommand.JUMP);
							break;}
		case KeyEvent.VK_A:{ this.observer.addInput(InputCommand.LEFT);
							break;}
		case KeyEvent.VK_S:{ this.observer.addInput(InputCommand.BACKHERO);
							break;}
		case KeyEvent.VK_D:{ this.observer.addInput(InputCommand.RIGHT);
							break;}
		case KeyEvent.VK_E:{ this.observer.addInput(InputMenu.INVENTORY);
							break;}
		case KeyEvent.VK_F:{ this.observer.addInput(InputMenu.SKILL_TREE);
							break;}
		case KeyEvent.VK_Q:{ this.observer.addInput(InputCommand.INTERACT);
							break;}
		case KeyEvent.VK_SPACE:{ this.observer.addInput(InputCommand.ATTACK);
							break;}
		case KeyEvent.VK_B:{ this.observer.addInput(InputMenu.POKEDEX);
							break;}
		case KeyEvent.VK_V:{ this.observer.addInput(InputMenu.ACHIEVEMENTS);
							break;}
		case KeyEvent.VK_H:{ this.observer.addInput(InputMenu.HELP);
							break;}
		case KeyEvent.VK_P:{ this.observer.addInput(InputCommand.ABILITY1);
							break;}
		case KeyEvent.VK_O:{ this.observer.addInput(InputCommand.ABILITY2);
							break;}
		case KeyEvent.VK_I:{ this.observer.addInput(InputCommand.ABILITY3);
							break;}
		case KeyEvent.VK_ESCAPE:{ this.observer.addInput(InputMenu.PAUSE);
							break;}
		case KeyEvent.VK_0:{ this.observer.addInput(InputCommand.CONSUMABLE);
							break;}
		default:
			break;
		}
	}
}
