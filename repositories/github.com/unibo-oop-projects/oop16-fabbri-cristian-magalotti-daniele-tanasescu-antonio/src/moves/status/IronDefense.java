package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class IronDefense extends StatusMove{

    public IronDefense() {
        super(  "Iron Defense",                                                                                                  //name
                "The user hardens its body's surface like iron, sharply raising its Defense stat.",                              //description
                new Steel(),                                                                                                     //type
                999,                                                                                                             //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority 
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationDef(+2, true);
        
    }
}
