package moves.damagingmove.special.hpdependent;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fire;

public class Eruption extends HPDependentSpecialDamagingMove{

    public Eruption() {
        super(  "Eruption",                                                                                                   //name
                "The user attacks opposing Pokémon with explosive fury.\n" +                                                  //description
                "The lower the user's HP, the lower the move's power.",    
                150,                                                                                                          //base power
                new Fire(),                                                                                                  //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                5,                                                                                                            //PP
                0);                                                                                                           //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
