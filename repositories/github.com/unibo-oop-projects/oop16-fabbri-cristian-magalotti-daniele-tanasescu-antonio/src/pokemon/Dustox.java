package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.firstturn.CompoundEyes;
import abilities.movecondition.ShieldDust;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SilverWind;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Moonlight;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Bug;
import types.Type;

public class Dustox extends Pokemon {

	public Dustox(int level) {
		super(level,
                60,		                                                              			//hp
                50,		                                                              			//atk
                70,		                                                              			//def
                65,		                                                              			//speed
                50,		                                                              			//spa
                90,		                                                              			//spd
                new Type[]{new Bug(), new types.Poison()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new ShieldDust(), new CompoundEyes()}),                  //ability
                31.6,	                                                                      	                //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new StringShot(),
                                        new PoisonSting(),
                                        new BugBite(),
                                        new Harden(),
                                        new Gust(),
                                        new Confusion(),
                                        new PoisonPowder(),
                                        new Moonlight(),
                                        new Psybeam(),
                                        new SilverWind(),
                                        new Whirlwind(),
                                        new Toxic(),
                                        new Protect(),
                                        new SunnyDay(),
                                        new Roost(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new Acrobatics(),
                                        new Swagger()
                                }
                        )
                )
        );
	}

}
