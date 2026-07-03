package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.hpcondition.Swarm;
import abilities.movecondition.Rivalry;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SilverWind;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.MorningSun;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.StringShot;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Type;

public class Beautifly extends Pokemon {

	public Beautifly(int level) {
		super(level,
                60,		                                                              			//hp
                70,		                                                              			//atk
                50,		                                                              			//def
                65,		                                                              			//speed
                90,		                                                              			//spa
                50,		                                                              			//spd
                new Type[]{new Bug(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Swarm(), new Rivalry()}),     		        //ability
                28.4,	                                                                      	                //weight(kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Gust(),
                                        new Absorb(),
                                        new StunSpore(),
                                        new MorningSun(),
                                        new AirCutter(),
                                        new MegaDrain(),
                                        new SilverWind(),
                                        new Attract(),
                                        new Whirlwind(),
                                        new GigaDrain(),
                                        new Rage(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Roost(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new Acrobatics(),
                                        new Swagger(),
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
