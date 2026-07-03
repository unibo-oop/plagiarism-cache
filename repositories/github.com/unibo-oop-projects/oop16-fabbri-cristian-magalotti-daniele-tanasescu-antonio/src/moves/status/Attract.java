package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import status_condition.volatile_status.Infatuation;
import types.Normal;

public class Attract extends StatusMove{

    public Attract() {
        super(  "Attract",                                                                                                       //name
                "If it is the opposite gender of the user, the target becomes infatuated and less likely to attack.",            //description
                new Normal(),                                                                                                    //type
                1,                                                                                                               //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!user.isGenderless() && !target.isGenderless()){
            if(user.getGender() != target.getGender()){
                Infatuation infatuation = new Infatuation();
                if(!infatuation.isContained(target.volatileStatusConditions)){
                    infatuation.addVolatile(target, target.volatileStatusConditions);
                }
                else{
                    BattleMenuController.battleLogManager.setMoveFailedMassage();
                }
            }
            else{
                BattleMenuController.battleLogManager.setMoveFailedMassage();
            }
        }
        else{
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
        
    }

}
