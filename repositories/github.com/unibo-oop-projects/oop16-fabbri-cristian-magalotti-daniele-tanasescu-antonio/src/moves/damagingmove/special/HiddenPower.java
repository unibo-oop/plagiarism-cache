package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;
import types.Type;

public class HiddenPower extends SpecialDamagingMove{

    public HiddenPower(Type type) {
            super(  "Hidden Power (" + type.getTypeName() + ")",                                                     //name
                    "A unique attack that varies in type.",                                                          //description
                    60,                                                                                              //base power
                    type,                                                                                            //type
                    1,                                                                                               //accuracy
                    critRange1,                                                                                      //crit range 
                    15,                                                                                              //PP
                    0);                                                                                              //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
