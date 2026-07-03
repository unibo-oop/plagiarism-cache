package concretes;

import java.util.Map;
import java.util.Objects;
import akka.actor.ActorRef;
import components.SerializedComponent;
import components.SerializedComponentFactory;

/**
 * This class is used to manage the entire {@link SerializedComponent} structure of the user interface. It provides method to add 
 * or change two different service structures in the principal structure. In fact it builds a structure composed of a frame, with two panels;
 * in each panel is added a different service structure (it can be composed by different component collected in an array).
 */
public class StructureBuilder {
	
	/**
	 * A constant representing the width of the principal frame
	 */
	private final static int W_FRAME = 900;
	
	/**
	 * A constant representing the height of the principal frame
	 */
	private final static int H_FRAME = 900;
	
	/**
	 * A constant representing the height of principal panels
	 */
	private final static int H_SERVICE = 400;
	
	/**
	 * A constant representing the width of principal panels
	 */
	private final static int W_SERVICE = 600;
	
	/**
	 * A constant representing the width of the menu panel
	 */
	private final static int W_MENU = 100;
	
	/**
	 * A string id for the principal frame
	 */
	private final String ID_FR;
	
	/**
	 * A string id for the first principal panel
	 */
	private final String ID_S1;
	
	/**
	 * A string id for the second principal panel
	 */
	private final String ID_S2;
	
	/**
	 * A string id for the menu of the structure
	 */
	private final String ID_MENU;
	
	/**
	 * A string reference to the composer {@link ActorRef}
	 */
	private static String COMPOSER_TO_STRING;
	
	/**
	 * An array representing the first structure
	 */
	private SerializedComponent[] s1 = null;
	
	/**
	 * An array representing the second structure
	 */
	private SerializedComponent[] s2 = null;
	
	private SerializedComponent[] menubuttons = null;
	
	public StructureBuilder(ActorRef composer, String s1, String s2, String frame, String menu_id, Map<String, String> buttons) {
		COMPOSER_TO_STRING = composer.toString();
		this.ID_S1 = s1;
		this.ID_S2 = s2;
		this.ID_FR = frame;
		this.ID_MENU = menu_id;
		this.menubuttons = new SerializedComponent[buttons.size()];
		int i = 0;
		for(String elem: buttons.keySet()){
			this.menubuttons[i] = SerializedComponentFactory.tButton(elem, null, buttons.get(elem), COMPOSER_TO_STRING);
			i+=1;
		}
	}
	
	/**
	 * A method to know if both structures are set
	 * @return true if both structures are set, otherwise false.
	 */
	public boolean canBuild() {
		if(Objects.nonNull(this.s1) && Objects.nonNull(this.s2)){
			return true;
		}

		return false;
			
	}
	
	/**
	 * This method is used to get the complete structure of the user interface. It doesn't control if structures are set.
	 * @see #canBuild()
	 * @return a {@link SerializedComponent} representing the user interface structure.
	 */
	public SerializedComponent build() {
		SerializedComponent s1panel = SerializedComponentFactory.tPanel(ID_S1, null, W_SERVICE, H_SERVICE, this.s1, COMPOSER_TO_STRING);
		SerializedComponent s2panel = SerializedComponentFactory.tPanel(ID_S2, null, W_SERVICE, H_SERVICE, this.s2, COMPOSER_TO_STRING);
		SerializedComponent menu = SerializedComponentFactory.tPanel(ID_MENU, null, W_MENU, H_SERVICE, this.menubuttons, COMPOSER_TO_STRING);
		SerializedComponent[] children = new SerializedComponent[]{s1panel, s2panel, menu};
		SerializedComponent panel = SerializedComponentFactory.tPanel("0", null, W_FRAME, H_FRAME, children, COMPOSER_TO_STRING);
		return SerializedComponentFactory.tFrame(ID_FR, W_FRAME, H_FRAME, new SerializedComponent[]{panel}, COMPOSER_TO_STRING);
	}
	
	/**
	 * Setter for the first structure
	 * @param s the structure to set as first structure
	 */
	public void setService1Structure(SerializedComponent s) {
		this.s1 = new SerializedComponent[]{s};
	}
	
	/**
	 * Setter for the second structure
	 * @param s the structure to set as second structure
	 */
	public void setService2Structure(SerializedComponent s) {
		this.s2 = new SerializedComponent[]{s};
	}
}
