package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Dark;

public class Flatter extends StatusMove{

    public Flatter() {
        super(  "Flatter",                                                                                                       //name
                "Flattery is used to confuse the target. However, this also raises the target's Sp. Atk stat.",                  //description
                new Dark(),                                                                                                      //type
                1,                                                                                                               //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpA(+2, false);
        Confusion confusion = new Confusion();
        if(!confusion.isContained(target.volatileStatusConditions)){
            confusion.addVolatile(target, target.volatileStatusConditions);
        }
        else{
            //
        }
        
    }

}
