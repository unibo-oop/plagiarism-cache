package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Normal;

public class Whirlwind extends StatusMove{

    public Whirlwind() {
        super(  "Whirlwind",                                                                                                    //name
                "The target is blown away, and a different Pokemon is dragged out.\n" +                                         //description       
                "In the wild,this ends a battle against a single Pokémon. ",                                                            
                new Normal(),                                                                                                   //type
                999,                                                                                                            //accuracy
                20,                                                                                                             //PP                                                                                                                     
                -6);                                                                                                            //priority
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
