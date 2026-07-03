package moves.damagingmove.special;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class MudSlap extends SpecialDamagingMove{

    public MudSlap() {
        super(  "Mud Slap",                                                                                            //name
                "The user hurls mud in the target's face to inflict damage and lower its accuracy.",                   //description
                20,                                                                                                    //base power
                new Ground(),                                                                                          //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                10,                                                                                                    //PP
                0);                                                                                                    //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAccuracy(-1, false);                        
    }
}