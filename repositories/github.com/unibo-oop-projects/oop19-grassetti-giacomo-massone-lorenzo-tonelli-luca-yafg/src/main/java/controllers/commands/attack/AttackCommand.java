/**
 * A class representing attack commands
 * 
 */
package controllers.commands.attack;

public abstract class AttackCommand {
	
	/**
	 * the type of attack depends on the move ( light, strong, special)
	 */
	public void execute() {
		// the modus operandi for all "commands"
		hit();
	}
	
	/**
	 * the type of move must be implemented differently for every move and character ( theoretically... )
	 * @return 0 if the player doesn't manage to attack, else if returns the damage.
	 */
	public abstract int hit();
	
}
