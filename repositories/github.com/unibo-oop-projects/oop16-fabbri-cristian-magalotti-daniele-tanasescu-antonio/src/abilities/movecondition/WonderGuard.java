package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Type;

public class WonderGuard extends MoveConditionAbility{

    private static final String MAGICPROTECT = "protects it from the attack";
    
    private boolean attackNegated;

    public WonderGuard() {
        super(  "Wonder Guard",                                                                                        //name, 
                "Its mysterious power only lets supereffective moves hit the Pokémon");                                //description
        this.attackNegated = false;
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            this.attackNegated = false;
        }
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isAttacking && !this.attackNegated){
            return (target.effectiveness < 2 && target.effectiveness > 0);
        }

        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness = 0;
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        this.attackNegated = true;

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return MAGICPROTECT;
    }

}
