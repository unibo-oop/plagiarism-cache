package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fire;

public class Overheat extends SpecialDamagingMove{

    public Overheat() {
        super(  "Overheat",                                                                                                      //name
                "The user attacks the target at full power. The attack's recoil harshly lowers the user's Sp. Atk stat.",        //description
                130,                                                                                                             //base power
                new Fire(),                                                                                                      //type
                0.9,                                                                                                             //accuracy
                critRange1,                                                                                                      //crit range 
                5,                                                                                                               //PP
                0);                                                                                                              //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(-2, true);
        
    }

}
