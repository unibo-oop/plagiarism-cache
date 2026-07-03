package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Sniper;
import abilities.otherconditions.Damp;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Octazooka;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Twister;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.amount.DragonRage;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SmokeScreen;
import moves.status.Splash;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Horsea extends Pokemon {

	public Horsea(int level) {
		super(level,
                30,		                                                              			//hp
                40,		                                                              			//atk
                70,		                                                              			//def
                60,		                                                              			//speed
                70,		                                                              			//spa
                25,		                                                              			//spd
                new Type[]{new Water(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new Sniper(), 
                					new Damp()}),     					//ability
                8,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Bubble(),
                                        new SmokeScreen(),
                                        new Leer(),
                                        new WaterGun(),
                                        new Twister(),
                                        new BubbleBeam(),
                                        new Agility(),
                                        new DragonPulse(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Scald(),
                                        new Swagger(),
                                        new FlashCannon(),
                                        new Surf(),
                                        new Waterfall(),
                                        new AuroraBeam(),
                                        new DragonBreath(),
                                        new DragonRage(),
                                        new Flail(),
                                        new MuddyWater(),
                                        new Octazooka(),
                                        new SignalBeam(),
                                        new Splash(),
                                        new WaterPulse()
                                }
                        )
                )
        );
	}

}
