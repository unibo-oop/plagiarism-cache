package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.LeafStorm;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.MegaDrain;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.Growth;
import moves.status.Leer;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Grass;
import types.Type;

public class Tropius extends Pokemon {

	public Tropius(int level) {
		super(level,
                99,		                                                              			//hp
                68,		                                                              			//atk
                83,		                                                              			//def
                51,		                                                              			//speed
                72,		                                                              			//spa
                87,		                                                              			//spd
                new Type[]{new Grass(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll()}),    					//ability
                100,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Stomp(),
                                        new Leer(),
                                        new Gust(),
                                        new StunSpore(),
                                        new Growth(),
                                        new MagicalLeaf(),
                                        new RazorLeaf(),
                                        new AirSlash(),
                                        new BodySlam(),
                                        new Synthesis(),
                                        new Whirlwind(),
                                        new LeafStorm(),
                                        new PoisonPowder(),
                                        new SleepPowder(),
                                        new BulletSeed(),
                                        new MegaDrain(),
                                        new GigaDrain(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new AerialAce(),
                                        new Earthquake(),
                                        new Bulldoze(),
                                        new SteelWing(),
                                        new Roost(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new Curse(new Type[]{new Grass(), new Flying()}),
                                        new DoubleEdge(),
                                        new Slam(),
                                        new Headbutt(),
                                        new DragonDance(),
                                        new BulletSeed(),
                                }
                        )
                )
        );
	}

}
