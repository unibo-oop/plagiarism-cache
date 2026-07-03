package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.VoltAbsorb;
import abilities.movecondition.WaterAbsorb;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShockWave;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Agility;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;
import types.Water;

public class Lanturn extends Pokemon {

	public Lanturn(int level) {
		super(level,
                125,		                                                              		        //hp
                58,		                                                              			//atk
                58,		                                                              			//def
                67,		                                                              			//speed
                76,		                                                              			//spa
                76,		                                                              			//spd
                new Type[]{new Water(), new Electric()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new VoltAbsorb(),new WaterAbsorb()}),     		//ability
                22.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Bubble(),
                                        new Supersonic(),
                                        new ThunderWave(),
                                        new WaterGun(),
                                        new ConfuseRay(),
                                        new BubbleBeam(),
                                        new Spark(),
                                        new SignalBeam(),
                                        new Flail(),
                                        new TakeDown(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Scald(),
                                        new ThunderWave(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new Surf(),
                                        new Waterfall(),
                                        new Agility(),
                                        new Amnesia(),
                                        new Flail(),
                                        new Psybeam(),
                                        new Screech(),
                                        new ShockWave(),
                                        new WaterPulse()
                                }
                        )
                )
        );
	}

}
