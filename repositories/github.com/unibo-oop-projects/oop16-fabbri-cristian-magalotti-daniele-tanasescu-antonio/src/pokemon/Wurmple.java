package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.status.StringShot;
import types.Bug;
import types.Type;

public class Wurmple extends Pokemon {

	public Wurmple(int level) {
		super(level,
                45,		                                                              	//hp
                45,		                                                              	//atk
                35,		                                                              	//def
                20,		                                                              	//speed
                20,		                                                              	//spa
                30,		                                                              	//spd
                new Type[]{new Bug(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new ShieldDust(), new RunAway()}),     	//ability
                3.6,	                                                                      	//weight(kg)
                0.9,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                             	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new StringShot(),
                                        new PoisonSting(),
                                        new BugBite(),
                                        new FuryAttack()
                                }
                        )
                )
        );
	}

}
