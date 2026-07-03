package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.statisticsalterationondemand.KeenEye;
import abilities.weathercondition.Hydration;
import abilities.weathercondition.RainDish;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Twister;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.MirrorMove;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Type;
import types.Water;

public class Wingull extends Pokemon {

	public Wingull(int level) {
		super(level,
                40,		                                                              			//hp
                30,		                                                              			//atk
                30,		                                                              			//def
                85,		                                                              			//speed
                55,		                                                              			//spa
                30,		                                                              			//spd
                new Type[]{new Water(), new Flying()},		                                   	        //tipo
                Ability.getRandomAbility(new Ability[]{new KeenEye(), new Hydration(),
                					new RainDish()}),     					//ability
                9.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	               	//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(),
                                        new WaterGun(),
                                        new Supersonic(),
                                        new WingAttack(),
                                        new WaterPulse(),
                                        new QuickAttack(),
                                        new AirCutter(),
                                        new Pursuit(),
                                        new AerialAce(),
                                        new Roost(),
                                        new Agility(),
                                        new AirSlash(),
                                        new Hurricane(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new Scald(),
                                        new Swagger(),
                                        new Gust(),
                                        new KnockOff(),
                                        new Twister(),
                                        new WaterSprout(),
                                }
                        )
                )
        );
	}

}
