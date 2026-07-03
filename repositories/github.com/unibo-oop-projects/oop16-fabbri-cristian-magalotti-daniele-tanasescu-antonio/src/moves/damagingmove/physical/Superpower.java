package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class Superpower extends PhysicalDamagingMove{

    public Superpower() {
        super(  "Superpower",                                                                                   //name
                "The user attacks the target with great power.\n"+                                              //description
                "However, this also lowers the user's Attack and Defense stats.",                    
                120,                                                                                            //base power
                new Fight(),                                                                                    //type
                1,                                                                                              //accuracy
                critRange1,                                                                                     //crit range 
                5,                                                                                              //PP
                0);                                                                                             //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(-1, true);
        user.setAlterationDef(-1, true);
        
    }

}
