package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class BulkUp extends StatusMove{

    public BulkUp() {
        super(  "Bulk Up",                                                                                                       //name
                "The user tenses its muscles to bulk up its body, raising both its Attack and Defense stats.",                   //description
                new Fight(),                                                                                                     //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationAtk(+1, true);
        user.setAlterationDef(+1, true);
        
    }

}
