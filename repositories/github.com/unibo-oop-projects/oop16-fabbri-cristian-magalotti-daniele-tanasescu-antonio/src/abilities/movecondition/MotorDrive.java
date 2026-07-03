package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.status.StatusMove;
import pokemon.Pokemon;
import types.Electric;

public class MotorDrive extends MoveConditionAbility{

    private static final String MOTORABSORBS = "absorbs ";
    private static final String MOTORATTACK = "'s attack!";

    private boolean electricityAbsorbed;

    public MotorDrive() {
        super(  "Motor Drive",                                                                                        //name, 
                "Boosts its Speed stat if hit by an Electric-type move, instead of taking damage.");                  //description
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
                    if(battleArena.enemyMove.getMoveType().equals(new Electric())){
                        if(battleArena.enemyMove instanceof StatusMove){
                            if(((StatusMove)battleArena.enemyMove).isSelfEffect()){
                                answer = false;
                            }
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

        return answer;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness = 0;
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationSpe(+1, true);
        this.electricityAbsorbed = true;

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return MOTORABSORBS + target.toString() + MOTORATTACK;
    }

}
