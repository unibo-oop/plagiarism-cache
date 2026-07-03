package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.alwaysactive.MagnetPull;
import abilities.movecondition.Analytic;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.ZapCannon;
import moves.damagingmove.special.amount.SonicBoom;
import moves.status.DoubleTeam;
import moves.status.MetalSound;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Steel;
import types.Type;

public class Magnemite extends Pokemon {

	public Magnemite(int level) {
		super(level,
                25,		                                                              			//hp
                35,		                                                              			//atk
                70,		                                                              			//def
                45,		                                                              			//speed
                95,		                                                              			//spa
                55,		                                                              			//spd
                new Type[]{new Electric(), new Steel()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new MagnetPull(),new Analytic()}),     			//ability
                6,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,	                                              				//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Supersonic(),
                                        new ThunderShock(),
                                        new ThunderWave(),
                                        new SonicBoom(),
                                        new Spark(),
                                        new MetalSound(),
                                        new FlashCannon(),
                                        new Screech(),
                                        new ZapCannon(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Explosion(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new WildCharge()
                                }
                        )
                )
        );
	}

}
