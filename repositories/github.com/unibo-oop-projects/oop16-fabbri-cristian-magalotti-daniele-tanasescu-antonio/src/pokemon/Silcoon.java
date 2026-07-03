package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.status.Harden;
import moves.status.StringShot;
import types.Bug;
import types.Type;

public class Silcoon extends Pokemon {

	public Silcoon(int level) {
		super(level,
                50,		                                                              			//hp
                35,		                                                              			//atk
                55,		                                                              			//def
                15,		                                                              			//speed
                25,		                                                              			//spa
                25,		                                                              			//spd
                new Type[]{new Bug(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new ShedSkin()}),     					//ability
                10,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new StringShot(),
                                        new PoisonSting(),
                                        new BugBite(),
                                        new Harden()
                                }
                        )
                )
        );
	}

}
