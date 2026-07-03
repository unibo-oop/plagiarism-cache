package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.status.StringShot;
import types.Bug;
import types.Type;

public class Caterpie extends Pokemon {

    public Caterpie(int level) {
        super(level,
                45,		                                                              	//hp
                30,		                                                              	//atk
                35,		                                                              	//def
                45,		                                                              	//speed
                20,		                                                              	//spa
                20,		                                                              	//spd
                new Type[]{new Bug(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new ShieldDust(), new RunAway()}),     	//ability
                2.9,	                                                                      	//weight(kg)
                1.1,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new StringShot(),
                                        new BugBite(),
                                        new FuryAttack()
                                }
                                )
                        )
                );
    }

}