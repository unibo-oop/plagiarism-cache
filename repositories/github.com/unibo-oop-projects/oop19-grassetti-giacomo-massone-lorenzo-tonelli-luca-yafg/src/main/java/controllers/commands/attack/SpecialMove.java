package controllers.commands.attack;

public class SpecialMove extends AttackCommand {

    private int damage = 15;
    
    /**
     * Triggers the attack:
     * @return 0 if the player doesn't manage to attack, else if returns the damage.
     */
    @Override
    public int hit() {
        return 0;
    }

}
