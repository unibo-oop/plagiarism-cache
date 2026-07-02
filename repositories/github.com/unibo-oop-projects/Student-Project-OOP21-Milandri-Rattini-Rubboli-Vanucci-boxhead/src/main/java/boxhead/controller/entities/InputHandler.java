package boxhead.controller.entities;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;



public class InputHandler {
	
	private final Set<KeyCode> keyPressed;
	public InputHandler(){	
		 this.keyPressed = new HashSet<>();
	}
	
	 public final EventHandler<KeyEvent> keyboard() {
	        return new EventHandler<KeyEvent>() {
	            public void handle(final KeyEvent event) {
	                if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
	                    keyPressed.add(event.getCode());
	                } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
	                    keyPressed.remove(event.getCode());
	                }
	            }
	        };
	 }
	 
	 public final Set<KeyCode> getKeyPressed() {
	        return Set.copyOf(this.keyPressed);
	    }
}
