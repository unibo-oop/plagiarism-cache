package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Paralysis;
import types.Electric;

public class ZapCannon extends SpecialDamagingMove{

    public ZapCannon() {
        super(  "Zap Cannon",                                                                                                 //name
                "The user fires an electric blast like a cannon to inflict damage and cause paralysis.",                      //description
                120,                                                                                                          //base power
                new Electric(),                                                                                               //type
                0.5,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                5,                                                                                                            //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        new Paralysis().setPokemonStatusCondition(target, battleArena);        
    }
    
}
