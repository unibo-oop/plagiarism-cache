package hollowmen.enumerators;

import hollowmen.model.Actor;

/**
 * Contains the list of possible command from the player
 * 
 * @author Giordo
 *
 */
public enum InputCommand {
	
	ABILITY1(Actor.Action.ABILITY1.toString()),
	ABILITY2(Actor.Action.ABILITY2.toString()),
	ABILITY3(Actor.Action.ABILITY3.toString()),
	ATTACK(Actor.Action.ATTACK.toString()),
	JUMP(Actor.Action.JUMP.toString()),
	LEFT(Actor.Direction.LEFT.toString()),
	RIGHT(Actor.Direction.RIGHT.toString()),
	CONSUMABLE(Actor.Action.CONSUMABLE.toString()),
	INTERACT(Actor.Action.INTERACT.toString()),
	EQUIP("equip"),
	UNEQUIP("unequip"),
	BUY("buy"),
	SELL("sell"),
	BACKHERO("back");
	
	private String s;
	
	private InputCommand(String s){
		this.s=s;
	}
	
	/**
	 * @return String that represent the command
	 */
	public String getString(){
		return this.s;
	}
}
