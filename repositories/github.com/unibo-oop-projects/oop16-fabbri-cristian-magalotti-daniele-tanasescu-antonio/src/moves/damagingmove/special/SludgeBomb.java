package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.BadPoison;
import types.Poison;

public class SludgeBomb extends SpecialDamagingMove{

    public SludgeBomb() {
        super(  "Sludge Bomb",                                                                                         //name
                "Unsanitary sludge is hurled at the target.\n"+                                                        //description
                "This may also poison the target.",              
                90,                                                                                                    //base power
                new Poison(),                                                                                          //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                10,                                                                                                    //PP
                0);                                                                                                    //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        new BadPoison().setPokemonStatusCondition(target, battleArena);
        
    }

}
