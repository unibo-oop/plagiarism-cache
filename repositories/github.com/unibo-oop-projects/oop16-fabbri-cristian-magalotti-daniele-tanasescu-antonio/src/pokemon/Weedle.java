package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.status.StringShot;
import types.Bug;
import types.Poison;
import types.Type;

public class Weedle extends Pokemon{

    public Weedle(int level) {
        super(  level,                                                                                          //level
                40,                                                                                             //baseHP 
                35,                                                                                             //baseAtk 
                30,                                                                                             //baseDef 
                50,                                                                                             //baseSpe
                20,                                                                                             //baseSpA 
                20,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Poison()},                                                            //type
                Ability.getRandomAbility(new Ability[]{new ShieldDust(), new RunAway()}),                       //ability
                3.2,                                                                                            //weight (Kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PoisonSting(),
                                        new StringShot(),
                                        new BugBite(),
                                        new FuryAttack()
                                }
                                )
                )
        );
    }

}
