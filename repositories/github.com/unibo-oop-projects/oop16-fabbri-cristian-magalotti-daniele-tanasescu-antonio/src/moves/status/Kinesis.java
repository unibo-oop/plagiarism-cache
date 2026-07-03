package moves.status;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class Kinesis extends StatusMove{

    public Kinesis() {
        super(  "Kinesis",                                                                                                     	 //name
                "The user distracts the target by bending a spoon. This lowers the target's accuracy.",          				 //description
                new Psychic(),                                                                                                   //type
                0.8,                                                                                                             //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority     
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAccuracy(-1, false);                        
    }
}