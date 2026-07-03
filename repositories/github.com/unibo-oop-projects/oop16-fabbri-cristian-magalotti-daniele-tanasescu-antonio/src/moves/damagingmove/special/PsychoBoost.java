package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class PsychoBoost extends SpecialDamagingMove{

    public PsychoBoost() {
        super(  "Psycho Boost",                                                                                               //name
                "The user attacks the target at full power.\n"+                                                               //description
                "The attack's recoil harshly lowers the user's Sp. Atk stat.",                           
                140,                                                                                                          //base power
                new Psychic(),                                                                                                //type
                0.9,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                5,                                                                                                          //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationSpA(-2, true);
        
    }

}
