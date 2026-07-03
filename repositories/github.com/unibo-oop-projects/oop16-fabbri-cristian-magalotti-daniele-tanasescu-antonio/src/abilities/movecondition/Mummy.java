package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Mummy extends MoveConditionAbility{

    private static final String MUMMYTRANSFORMS = "transforms ";
    private static final String MUMMYINTO = " into ";

    public Mummy() {
        super(  "Mummy",                                                                                                //name, 
                "Contact with the Pokemon changes the attacker's Ability to Mummy.");                                   //description
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isAttacking){
            if(target.equals(battleArena.enemyMove)){
                return this.contactCheck(battleArena.enemyMove);
            }
            else{
                return this.contactCheck(battleArena.playerMove);
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        target.changeAbility(user, target, battleArena, new Mummy());
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return MUMMYTRANSFORMS + target.getAbility().getName() + MUMMYINTO + this.getName();
    }

}
