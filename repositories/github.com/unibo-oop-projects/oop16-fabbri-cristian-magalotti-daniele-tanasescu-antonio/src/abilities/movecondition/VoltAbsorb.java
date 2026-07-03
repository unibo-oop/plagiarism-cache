package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.status.StatusMove;
import pokemon.Pokemon;
import types.Electric;

public class VoltAbsorb extends MoveConditionAbility{

    private static final String VOLTABSORBS = "absorbs ";
    private static final String VOLTATTACK = "'s attack!";

    private boolean electricityAbsorbed;

    public VoltAbsorb() {
        super(  "Volt Absorb",                                                                                        //name, 
                "Restores HP if hit by an Electric-type move, instead of taking damage.");        			          //description
        this.electricityAbsorbed = false;
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            this.electricityAbsorbed = false;
        }
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        boolean answer = false;
        if(target.isAttacking && !this.electricityAbsorbed){
            if(target.equals(battleArena.activeEnemy)){
                if(battleArena.enemyMove.getMoveType().equals(new Electric())){
                    answer = true;
                    if(battleArena.enemyMove instanceof StatusMove){
                        if(((StatusMove)battleArena.enemyMove).isSelfEffect()){
                            answer = false;
                        }
                    }
                }
            }
            else{
                if(battleArena.playerMove.getMoveType().equals(new Electric())){
                    answer = true;
                    if(battleArena.playerMove instanceof StatusMove){
                        if(((StatusMove)battleArena.playerMove).isSelfEffect()){
                            answer = false;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness = 0;
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.takeDamage(-user.getMaxHp()*0.25, false);          //heals 1/4
        this.electricityAbsorbed = true;

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return VOLTABSORBS + target.toString() + VOLTATTACK;
    }

}
