package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Normal;

public class Refresh extends StatusMove{

    public Refresh() {
        super(  "Refresh",                                                                                                       //name
                "The user rests to cure itself of poisoning, a burn, or paralysis.",                                             //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.statusCondition!=null && !user.statusCondition.equals(new Sleep())){
            user.statusCondition.exitingStatusAlteration(user);
        }
        else{
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }

    }

}
