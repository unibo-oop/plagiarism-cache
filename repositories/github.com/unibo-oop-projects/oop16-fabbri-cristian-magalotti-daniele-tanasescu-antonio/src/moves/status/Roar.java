package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Roar extends StatusMove{

    public Roar() {
        super(  "Roar",                                                                                                          //name
                "The target is scared off, and a different Pokemon is dragged out.\n"+                                           //description
                "In the wild, this ends a battle against a single Pokemon.",                            
                new Normal(),                                                                                                    //type
                1,                                                                                                               //accuracy
                20,                                                                                                              //PP                                                                                                                     
                -6);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.equals(battleArena.activePlayer)){
            if(battleArena.getEnemy().getAliveOnesCount() > 2){         //uno oltre a quello attivo!
                battleArena.switchActive(battleArena.getEnemy(), battleArena.getEnemy().getRandomAlly(battleArena.activeEnemy));
            }
            else{
                //messaggio errore in battle
            }
        }
        else{
            if(battleArena.getPlayer().getAliveOnesCount() > 2){        //uno oltre a quello attivo!
                battleArena.switchActive(battleArena.getPlayer(), battleArena.getPlayer().getRandomAlly(battleArena.activePlayer));
            }
            else{
                //messaggio errore in battle
            }
        }    
        
    }

}
