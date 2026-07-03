package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.status.StatusMove;
import pokemon.Pokemon;
import types.Water;

public class StormDrain extends MoveConditionAbility{

    private static final String STORMABSORBS = "absorbs ";
    private static final String STORMATTACK = "'s attack!";

    private boolean waterAbsorbed;

    public StormDrain() {
        super(  "Storm Drain",                                                                                         //name, 
                "Draws in all Water-type moves. Instead of being hit by Water-type moves, it boosts its Sp. Atk. ");   //description
        this.waterAbsorbed = false;
    }
    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            this.waterAbsorbed = false;
        }
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        boolean answer = false;
        if(target.isAttacking && !this.waterAbsorbed){
            if(target.equals(battleArena.activeEnemy)){
                if(battleArena.enemyMove.getMoveType().equals(new Water())){
                    answer = true;
                    answer = true;
                    if(battleArena.enemyMove instanceof StatusMove){
                        if(((StatusMove)battleArena.enemyMove).isSelfEffect()){
                            answer = false;
                        }
                    }
                }
            }
            else{
                if(battleArena.playerMove.getMoveType().equals(new Water())){
                    answer = true;
                    if(battleArena.playerMove instanceof StatusMove){
                        if(((StatusMove)battleArena.playerMove).isSelfEffect()){
                            answer = false;
                        }
                    }
                }
            }
        }
        
        return answer;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness = 0;
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationAtk(+1, true);
        this.waterAbsorbed = true;

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return STORMABSORBS + target.toString() + STORMATTACK;
    }

}
