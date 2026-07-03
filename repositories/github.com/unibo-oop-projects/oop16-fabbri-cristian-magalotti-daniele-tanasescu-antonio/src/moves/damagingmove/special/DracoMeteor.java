package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dragon;

public class DracoMeteor extends SpecialDamagingMove{

    public DracoMeteor() {
        super(  "Draco Meteor",                                                                                               //name
                "Comets are summoned down from the sky onto the target, what else.\n"+                                        //description
                "The attack's recoil harshly lowers the user's Sp. Atk stat.",                           
                130,                                                                                                          //base power
                new Dragon(),                                                                                                 //type
                0.9,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                5,                                                                                                            //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(-2, true);
        
    }

}
