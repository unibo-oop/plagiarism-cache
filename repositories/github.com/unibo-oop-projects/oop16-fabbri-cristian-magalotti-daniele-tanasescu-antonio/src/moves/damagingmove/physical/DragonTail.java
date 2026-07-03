package moves.damagingmove.physical;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Dragon;

public class DragonTail extends PhysicalDamagingMove{

    public DragonTail() {
        super(  "Dragon Tail",                                                                                         //name
                "The target is knocked away, and a different Pokémon is dragged out.",                                 //description    
                60,                                                                                                    //base power
                new Dragon(),                                                                                          //type
                0.9,                                                                                                   //accuracy
                critRange1,                                                                                            //crit range 
                10,                                                                                                    //PP
                -6);                                                                                                   //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.equals(battleArena.activePlayer)){
            if(battleArena.getEnemy().getAliveOnesCount() > 2){         
                Pokemon newActive = battleArena.getEnemy().getRandomAlly(battleArena.activeEnemy);
                battleArena.switchActive(battleArena.getEnemy(), newActive);
                BattleMenuController.battleLogManager.setForcedSwitchMessage(newActive, battleArena.getEnemy());
            }
            else{
                BattleMenuController.battleLogManager.setMoveFailedMassage();
            }
        }
        else{
            if(battleArena.getPlayer().getAliveOnesCount() > 2){     
                Pokemon newActive = battleArena.getPlayer().getRandomAlly(battleArena.activePlayer);
                battleArena.switchActive(battleArena.getPlayer(), battleArena.getPlayer().getRandomAlly(battleArena.activePlayer));
                BattleMenuController.battleLogManager.setForcedSwitchMessage(newActive, battleArena.getPlayer());
            }
            else{
                BattleMenuController.battleLogManager.setMoveFailedMassage();
            }
        }    
        
    }

}
