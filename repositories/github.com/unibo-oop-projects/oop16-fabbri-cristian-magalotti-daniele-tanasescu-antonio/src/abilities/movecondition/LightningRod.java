package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.status.StatusMove;
import pokemon.Pokemon;
import types.Electric;

public class LightningRod extends MoveConditionAbility{

    private static final String LIGHTNINGABSORBS = "absorbs ";
    private static final String LIGHTNINGATTACK = "'s attack!";
    private boolean electricityAbsorbed;
    
    public LightningRod() {
        super(  "Lighting Rod",                                                                                        //name, 
                "The Pokemon draws in all Electric-type moves.\n"+                                                     //description
                "Instead of being hit by Electric-type moves, it boosts its Sp.Atk.");           
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
        }
        return answer;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness = 0;
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationSpA(+1, true);
        this.electricityAbsorbed = true;
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return LIGHTNINGABSORBS + target.toString() + LIGHTNINGATTACK;
    }

}
