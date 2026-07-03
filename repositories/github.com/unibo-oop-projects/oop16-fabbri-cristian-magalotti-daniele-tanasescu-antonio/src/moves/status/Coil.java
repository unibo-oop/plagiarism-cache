package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Poison;

public class Coil extends StatusMove{

    public Coil() {
        super(  "Coil",                                                                                                		 //name
                "The user coils up and concentrates."
                + "This raises its Attack and Defense stats as well as its accuracy.",                          				 //description
                new Poison(),                                                                                                    //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(+1, true);
        user.setAlterationDef(+1, true);
        user.setAlterationAccuracy(+1, true);
    }
}
