package hollowmen.enumerators;

import hollowmen.model.Actor;

/**
 * Contains the list of possible menu 
 * 
 * @author Giordo
 *
 */
public enum InputMenu {
	MAIN("main", "basic"),
	CLASS("class", "basic"),
	DIFFICULTY("difficulty", "basic"),
	HELP("help", "basic"),
	PAUSE("pause", "basic"),
	INVENTORY("inventory", "complex"),
	SKILL_TREE("skillTree", "complex"),
	POKEDEX("pokedex", "complex"),
	SHOP("shop", "complex"),
	ACHIEVEMENTS("achievements", "complex"),
	START("start","basic"),
	LOBBY("lobby","basic"),
	BACK(Actor.Action.BACK.toString(),"basic"),
	RESUME("resume","basic");
	
	private String s, type;
	
	private InputMenu(String s, String type){
		this.s=s;
		this.type = type;
	}
	
	/**
	 * @return String that represent the name of the menu
	 */
	public String getString(){
		return this.s;
	}
	
	/**
	 * @return String representing menu's type
	 */
	public String getType() {
		return this.type;
	}
}
