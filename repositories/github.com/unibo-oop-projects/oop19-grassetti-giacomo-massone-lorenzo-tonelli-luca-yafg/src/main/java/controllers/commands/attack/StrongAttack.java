package controllers.commands.attack;

public class StrongAttack extends AttackCommand {

	private int damage = 10;
	
	/**
     * Triggers the attack:
     * @return 0 if the player doesn't manage to attack, else if returns the damage.
     */
	@Override
	public int hit() {
		return damage;
	}

}
