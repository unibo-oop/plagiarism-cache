package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Sludge;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Smog;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Haze;
import moves.status.Memento;
import moves.status.PainSplit;
import moves.status.PoisonGas;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SmokeScreen;
import moves.status.Spite;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Poison;
import types.Type;

public class Koffing extends Pokemon {

	public Koffing(int level) {
		super(level,
                40,		                                                              			//hp
                65,		                                                              			//atk
                95,		                                                              			//def
                35,		                                                              			//speed
                60,		                                                              			//spa
                45,		                                                              			//spd
                new Type[]{new Poison(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),     					//ability
                1,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PoisonGas(),
                                        new Tackle(),
                                        new Smog(),
                                        new SmokeScreen(),
                                        new Sludge(),
                                        new SelfDestruct(),
                                        new Haze(),
                                        new SludgeBomb(),
                                        new Explosion(),
                                        new Memento(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new WillOWisp(),
                                        new Explosion(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Curse(new Type[]{new Poison(), null}),
                                        new PainSplit(),
                                        new Psybeam(),
                                        new Screech(),
                                        new Spite()
                                }
                        )
                )
        );
	}

}
