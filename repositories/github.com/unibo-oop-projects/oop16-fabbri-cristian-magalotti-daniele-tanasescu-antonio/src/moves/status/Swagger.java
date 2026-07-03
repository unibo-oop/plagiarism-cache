package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;
import types.Normal;

public class Swagger extends StatusMove{

    public Swagger() {
        super(  "Swagger",                                                                                                        //name
                "The user enrages and confuses the target. However, this also sharply raises the target's Attack stat.",          //description                                       
                new Normal(),                                                                                                     //type
                0.85,                                                                                                             //accuracy
                15,                                                                                                               //PP                                                                                                                     
                0);                                                                                                               //priority     
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAtk(+2, false);
        Confusion confusion = new Confusion();
        if(!confusion.isContained(target.volatileStatusConditions)){
            confusion.addVolatile(target, target.volatileStatusConditions);
        }
    }

}
