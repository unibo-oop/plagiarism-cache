package moves.status;

import abilities.switchcondition.SwitchConditionAbility;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import team.Team;
import types.Normal;

public class Switch extends StatusMove{

    private Pokemon actualActive;
    private Pokemon newActive;

    public Switch() {
        super(  "Switch", 
                "", 
                new Normal(),   
                999,
                999, 
                5);
        this.setSelfEffect(true);
    }


    public Switch(Pokemon actualActive,Pokemon newActive) {
        this();
        this.actualActive = actualActive;
        this.newActive = newActive;
    }

    @Override
    public void getEffect(Pokemon user, Pokemon target, BattleArena battleArena){
        Team team;
        if(this.actualActive.equals(battleArena.activePlayer)){
            team = battleArena.getPlayer();
        }
        else{
            team = battleArena.getEnemy();
        }

        this.sideEffect(actualActive, newActive, battleArena);

        BattleMenuController.battleLogManager.setSwitchMessage(actualActive, newActive, team, battleArena);
    }


    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.isAttacking = true;
        if(this.actualActive.equals(battleArena.activePlayer)){
            if(battleArena.activePlayer.getAbility() instanceof SwitchConditionAbility){
                battleArena.activePlayer.getAbility().checkForActivation(this.actualActive, this.actualActive, battleArena);
            }
            battleArena.switchActive(battleArena.getPlayer(), this.newActive);
        }
        else{
            if(battleArena.activeEnemy.getAbility() instanceof SwitchConditionAbility){
                battleArena.activeEnemy.getAbility().checkForActivation(this.actualActive, this.actualActive, battleArena);
            }
            battleArena.switchActive(battleArena.getEnemy(), this.newActive);
        }
    }
    
    public Pokemon getActualActive(){
        return this.actualActive;
    }
    
    public Pokemon getNewActive(){
        return this.newActive;
    }
}
