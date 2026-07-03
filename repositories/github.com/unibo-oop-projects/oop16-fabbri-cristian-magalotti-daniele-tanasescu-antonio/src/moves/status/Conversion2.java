package moves.status;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.*;

public class Conversion2 extends StatusMove{

    public Conversion2() {
        super(  "Conversion 2",                                                                                                  //name
                "The user changes its type to make itself resistant to the type of the attack the opponent used last.",          //description
                new Normal(),                                                                                                     //type
                999,                                                                                                             //accuracy
                30,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.lastMoveUsed != null){
            this.changeTypeToResistantOrImmune(user, target);
        }
        else{
            //failure message
        }
        
    }
    
    private void changeTypeToResistantOrImmune(Pokemon user, Pokemon target){
        Type type = target.lastMoveUsed.getMoveType();
        Type choice = null;
        Type[] allTypes = new Type[]{new Bug(), new Dark(), new Dragon(), new Electric(), new Fairy(), new Fight(), new Fire(),
                                     new Flying(), new Ghost(), new Grass(), new Ground(), new Ice(), new Normal(), new Poison(),
                                     new Psychic(), new Rock(), new Steel(), new Water()};
        Random random = new Random();
        boolean done = false;
        while(!done){
            int index = random.nextInt(allTypes.length);
            if(allTypes[index].isImmuneTo(type) || allTypes[index].isResistantTo(type)){
                choice = allTypes[index];
                done = true;
            }
        }
        user.changeTypes(choice, null);      
    }

}
