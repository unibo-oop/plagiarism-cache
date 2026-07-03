package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.hpcondition.Aftermath;
import abilities.movecondition.Static;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.amount.SonicBoom;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;

public class Voltorb extends Pokemon {

	public Voltorb(int level) {
		super(level,
                40,		                                                              		//hp
                30,		                                                              		//atk
                50,		                                                              		//def
                100,		                                                              		//speed
                55,		                                                              		//spa
                55,		                                                              		//spd
                new Type[]{new Electric(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new Static(),new Aftermath()}),     		//ability
                10.4,	                                                                          	//weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.GENDERLESS,	                                              			//gender
                new HashSet<Move>(                                                            	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new SonicBoom(),
                                        new Spark(),
                                        new Screech(),
                                        new Swift(),
                                        new SelfDestruct(),
                                        new Explosion(),
                                        new MirrorCoat(),
                                        new Toxic(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Thief(),
                                        new ThunderWave(),
                                        new Swagger(),
                                        new WildCharge()
                                }
                        )
                )
        );
	}

}
