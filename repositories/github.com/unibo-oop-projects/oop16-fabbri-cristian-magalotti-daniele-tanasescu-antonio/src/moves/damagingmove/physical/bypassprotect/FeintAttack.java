package moves.damagingmove.physical.bypassprotect;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dark;

public class FeintAttack extends BypassProtectPhysicalDamagingMove{

    public FeintAttack() {
        super(  "Feint Attack",                                                                                  //name
                "The user approaches the target disarmingly, then throws a sucker punch.\n"+                     //description
                "This attack never misses.",
                60,                                                                                              //base power
                new Dark(),                                                                                      //type
                999,                                                                                             //accuracy
                critRange1,                                                                                      //crit range 
                20,                                                                                              //PP
                0);                                                                                              //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
