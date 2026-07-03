package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.LiquidOoze;
import abilities.statisticsalterationondemand.ClearBody;
import abilities.weathercondition.RainDish;
import moves.Move;
import moves.damagingmove.physical.Constrict;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Poison;
import types.Type;
import types.Water;

public class Tentacool extends Pokemon {

	public Tentacool(int level) {
		super(level,
                40,		                                                              			//hp
                40,		                                                              			//atk
                35,		                                                              			//def
                70,		                                                              			//speed
                50,		                                                              			//spa
                100,		                                                              		        //spd
                new Type[]{new Water(), new Poison()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new ClearBody(), new LiquidOoze(),
                				       new RainDish()}),     					//ability
                45.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PoisonSting(),
                                        new Supersonic(),
                                        new Constrict(),
                                        new Acid(),
                                        new WaterPulse(),
                                        new BubbleBeam(),
                                        new PoisonJab(),
                                        new Screech(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Scald(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new AuroraBeam(),
                                        new Bubble(),
                                        new ConfuseRay(),
                                        new Haze(),
                                        new KnockOff(),
                                        new MirrorCoat(),
                                        new MuddyWater(),
                                        new Tickle()
                                }
                        )
                )
        );
	}

}
