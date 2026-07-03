package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Protean extends MoveConditionAbility{
    
    private boolean changeDone;

    public Protean() {
        super(  "Protean",                                                                                         //name, 
                "Changes the Pokémon's type to the type of the move it's about to use.");                          //description
        this.changeDone = false;
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        return user.isAttacking;
    }
    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
        }
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.changeDone){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            if(user.equals(battleArena.activeEnemy)){
                user.changeTypes(battleArena.enemyMove.getMoveType(), null);
            }
            else{
                user.changeTypes(battleArena.playerMove.getMoveType(), null);
            }
            this.changeDone = true;
        }
        else{
            this.changeDone = false;
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
