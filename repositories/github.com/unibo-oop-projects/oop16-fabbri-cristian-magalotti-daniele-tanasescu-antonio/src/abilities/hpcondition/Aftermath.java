package abilities.hpcondition;

import abilities.otherconditions.Damp;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Aftermath extends HPConditionAbility{

    public Aftermath() {
        super(  "Aftermath",                                                                            //name 
                "Damages the attacker if it contacts the Pokémon with a finishing hit.",                //description 
                0);                                                                                     //hpBound
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.equals(battleArena.activeEnemy)){
            if(battleArena.activeEnemy.isAttacking && battleArena.enemyMove.isMakingContact()){
                if(!target.getAbility().equals(new Damp())){
                    BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
                    target.takeDamage(target.getMaxHp()/4, false);
                }
                else{
                    ((Damp)target.getAbility()).activateAbility(user, target, battleArena);             //cast not needed, only for visibility
                }
            }
        }
        else{
            if(battleArena.activePlayer.isAttacking && battleArena.playerMove.isMakingContact()){
                if(!target.getAbility().equals(new Damp())){
                    BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
                    target.takeDamage(target.getMaxHp()/4, false);
                }
                else{
                    ((Damp)target.getAbility()).activateAbility(user, target, battleArena);             //cast not needed, only for visibility
                }
            }
        }  
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
