package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.movecondition.PoisonPoint;
import abilities.movecondition.Rivalry;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.PoisonTail;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.Flatter;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;

public class NidoranF extends Pokemon {

	public NidoranF(int level) {
		 super(level,
	                55,		                                                              			//hp
	                47,		                                                              			//atk
	                52,		                                                              			//def
	                41,		                                                              			//speed
	                40,		                                                              			//spa
	                40,		                                                              			//spd
	                new Type[]{new types.Poison(), null},		                                                //tipo
	                Ability.getRandomAbility(new Ability[]{new PoisonPoint(), new Rivalry(),
	                					new Hustle()}),     					//ability
	                7,	                                                                      		        //weight(kg)
	                1,                                                                                              //miniFrontSizeScale
	                Gender.FEMALE,	                                              					//gender
	                new HashSet<Move>(                                                            	                //learnable moves
	                        Arrays.asList(
	                                new Move[]{
	                                        new Growl(),
	                                        new Scratch(),
	                                        new TailWhip(),
	                                        new DoubleKick(),
	                                        new PoisonSting(),
	                                        new FurySwipes(),
	                                        new Bite(),
	                                        new Flatter(),
	                                        new Crunch(),
	                                        new PoisonFang(),
	                                        new Toxic(),
	                                        new SunnyDay(),
	                                        new IceBeam(),
	                                        new Blizzard(),
	                                        new Protect(),
	                                        new RainDance(),
	                                        new Thunderbolt(),
	                                        new Thunder(),
	                                        new DoubleTeam(),
	                                        new SludgeBomb(),
	                                        new AerialAce(),
	                                        new Facade(),
	                                        new Rest(),
	                                        new Attract(),
	                                        new Thief(),
	                                        new ShadowClaw(),
	                                        new PoisonJab(),
	                                        new Swagger(),
	                                        new Charm(),
	                                        new Counter(),
	                                        new IronTail(),
	                                        new PoisonTail(),
	                                        new Pursuit(),
	                                        new Supersonic(),
	                                        new TakeDown()
	                                }
	                        )
	                )
	        );
	}

}
