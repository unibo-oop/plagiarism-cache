package moves.status;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class SandAttack extends StatusMove{

    public SandAttack() {
        super(  "Sand Attack",                                                                                                    //name
                "Sand is hurled in the target's face, reducing the target's accuracy.",                                           //description
                new Ground(),                                                                                                     //type
                1,                                                                                                                //accuracy
                15,                                                                                                               //PP                                                                                                                     
                0);                                                                                                               //priority     
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAccuracy(-1, false);                        
    }
}