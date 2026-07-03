package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class LeafStorm extends SpecialDamagingMove{

    public LeafStorm() {
        super(  "LeafStorm",                                                                             //name
                "The user whips up a storm of leaves around the target.\n"+                              //description
                "The attack's recoil harshly lowers the user's Sp. Atk stat.",                           
                130,                                                                                     //base power
                new Grass(),                                                                             //type
                0.9,                                                                                     //accuracy
                critRange1,                                                                              //crit range 
                5,                                                                                       //PP
                0);                                                                                      //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(-2, true);

    }

}
