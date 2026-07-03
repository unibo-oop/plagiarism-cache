package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dragon;

public class DragonDance extends StatusMove{

    public DragonDance() {
        super(  "Dragon Dance",                                                                                            //name
                "The user vigorously performs a mystic, powerful dance that boosts its Attack and Speed stats.",           //description
                new Dragon(),                                                                                              //type
                999,                                                                                                       //accuracy
                20,                                                                                                        //PP                                                                                                                     
                0);                                                                                                        //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(+1, true);
        user.setAlterationSpe(+1, true);
        
    }

}
