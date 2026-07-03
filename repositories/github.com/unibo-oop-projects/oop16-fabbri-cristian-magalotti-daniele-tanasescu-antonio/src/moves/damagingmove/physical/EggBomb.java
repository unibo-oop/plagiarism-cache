package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class EggBomb extends PhysicalDamagingMove{

    public EggBomb() {
        super(  "Egg Bomb",                                                                                             //name
                "A large egg is hurled at the target with maximum force to inflict damage.",                            //description
                100,                                                                                                    //base power
                new Normal(),                                                                                           //type
                0.75,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
