package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.PoisonSting;
import moves.status.StringShot;
import moves.status.Harden;
import types.Bug;
import types.Poison;
import types.Type;

public class Kakuna extends Pokemon{

    public Kakuna(int level) {
        super(  level,                                                                                          //level
                45,                                                                                             //baseHP 
                25,                                                                                             //baseAtk 
                50,                                                                                             //baseDef 
                35,                                                                                             //baseSpe
                25,                                                                                             //baseSpA 
                25,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Poison()},                                                            //type
                Ability.getRandomAbility(new Ability[]{new ShedSkin()}),                                        
                10,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PoisonSting(),
                                        new StringShot(),
                                        new BugBite(),
                                        new Harden(),
                                }
                                )
                        )
                );
    }

}
