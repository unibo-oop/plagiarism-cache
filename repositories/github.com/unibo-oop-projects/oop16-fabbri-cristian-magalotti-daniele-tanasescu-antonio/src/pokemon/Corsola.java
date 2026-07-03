package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.switchcondition.NaturalCure;
import abilities.switchcondition.Regenerator;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Harden;
import moves.status.Haze;
import moves.status.IronDefense;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Rock;
import types.Type;
import types.Water;

public class Corsola extends Pokemon {

    public Corsola(int level) {
        super(level,
                65,		                                                                              		//hp
                55,		                                                                              		//atk
                95,		                                                                              		//def
                35,		                                                                              		//speed
                65,		                                                                              		//spa
                95,		                                                                              		//spd
                new Type[]{new Water(), new Rock()},		                                                  	//tipo
                Ability.getRandomAbility(new Ability[]{new Hustle(), new NaturalCure(), new Regenerator()}),    	//ability
                5,	                                                                                    	        //weight(kg)
                1,                                                                                                      //miniFrontSizeScale    
                Gender.getRandomGender(),	                                                              		//gender
                new HashSet<Move>(                                                                          	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Harden(),
                                        new Recover(),
                                        new Bubble(),
                                        new BubbleBeam(),
                                        new IronDefense(),
                                        new PowerGem(),
                                        new MirrorCoat(),
                                        new Counter(),
                                        new EarthPower(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new SunnyDay(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Scald(),
                                        new PoisonJab(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new ShadowBall(),
                                        new RockTomb(),
                                        new RockSlide(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                        new Earthquake(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Haze(),
                                        new WaterSprout(),
                                        new Amnesia(),
                                        new Bite(),
                                        new Curse(new Type[]{new Water(), new Rock()}),
                                        new MudShot(),
                                        new MudSlap(),
                                        new Psybeam(),
                                        new SignalBeam(),

                                }
                                )
                        )
                );
    }

}
