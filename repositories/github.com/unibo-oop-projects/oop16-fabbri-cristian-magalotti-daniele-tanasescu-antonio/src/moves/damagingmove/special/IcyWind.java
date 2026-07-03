package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ice;

public class IcyWind extends SpecialDamagingMove{

    public IcyWind() {
        super(  "Icy Wind",                                                                                                   //name
                "The user attacks with a gust of chilled air. This also lowers the opposing Pokémon's Speed stats.",          //description
                55,                                                                                                           //base power
                new Ice(),                                                                                                    //type
                0.95,                                                                                                         //accuracy
                critRange1,                                                                                                   //crit range 
                15,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpe(-1, false);
        
    }

}
