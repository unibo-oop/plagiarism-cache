package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Drizzle;
import abilities.statisticsalterationondemand.KeenEye;
import abilities.weathercondition.RainDish;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Twister;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Type;
import types.Water;

public class Pelipper extends Pokemon {

	public Pelipper(int level) {
		super(level,
                60,		                                                              			//hp
                50,		                                                              			//atk
                100,		                                                              		        //def
                65,		                                                              			//speed
                85,		                                                              			//spa
                70,		                                                              			//spd
                new Type[]{new Water(), new Flying()},		                                   	        //tipo
                Ability.getRandomAbility(new Ability[]{new KeenEye(), new Drizzle(),
                				      new RainDish()}),     					//ability
                28,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                      	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Protect(),
                                        new Hurricane(),
                                        new HydroPump(),
                                        new Growl(),
                                        new WaterGun(),
                                        new WaterSprout(),
                                        new WingAttack(),
                                        new WaterPulse(),
                                        new Roost(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
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
                                        new Surf(),
                                        new Agility(),
                                        new Gust(),
                                        new KnockOff(),
                                        new Twister(),
                                        new WaterSprout(),
                                        new QuickAttack(),
                                        new AirCutter(),
                                        new Pursuit(),
                                        new Agility(),
                                        new AirSlash()
                                }
                        )
                )
        );
	}

}
