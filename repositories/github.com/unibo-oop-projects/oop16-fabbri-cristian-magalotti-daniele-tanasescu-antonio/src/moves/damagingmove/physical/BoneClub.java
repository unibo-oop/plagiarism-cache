package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Ground;

public class BoneClub extends PhysicalDamagingMove{

    public BoneClub() {
        super(  "Bone Club",                                                                                            //name
                "The user clubs the target with a bone. This may also make the target flinch.",                         //description
                65,                                                                                                     //base power
                new Ground(),                                                                                           //type
                0.85,                                                                                                   //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}
