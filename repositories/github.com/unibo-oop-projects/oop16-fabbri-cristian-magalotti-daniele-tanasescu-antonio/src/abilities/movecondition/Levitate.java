package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.Move;
import moves.status.StatusMove;
import pokemon.Pokemon;
import types.Ground;

public class Levitate extends MoveConditionAbility{

    private static final String LEVITATEPROTECTS = "protects it from damage.";
    private boolean groundAvoided;                                                                           

    public Levitate() {
        super(  "Levitate",                                                                                         //name, 
                "By floating in the air, the Pokémon receives full immunity to all Ground-type moves.");           //description
        this.groundAvoided = false;
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
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        boolean answer = false;
        if(target.isAttacking && !this.groundAvoided){
            if(target.equals(battleArena.activeEnemy)){
                answer = this.isGround(battleArena.enemyMove);
                if(battleArena.enemyMove instanceof StatusMove){
                    if(((StatusMove)battleArena.enemyMove).isSelfEffect()){
                        answer = false;
                    }
                }
            }
            else{
                answer = this.isGround(battleArena.playerMove);
                if(battleArena.playerMove instanceof StatusMove){
                    if(((StatusMove)battleArena.playerMove).isSelfEffect()){
                        answer = false;
                    }
                }
            }
        }
        else{
            this.groundAvoided = false;
        }
        return answer;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!groundAvoided){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            target.effectiveness = 0;
            this.groundAvoided = true;
        }
        else{
            this.groundAvoided = false;
        }
    }

    private boolean isGround(Move move){
        return move.getMoveType().equals(new Ground());
    }


    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return LEVITATEPROTECTS;
    }

}
