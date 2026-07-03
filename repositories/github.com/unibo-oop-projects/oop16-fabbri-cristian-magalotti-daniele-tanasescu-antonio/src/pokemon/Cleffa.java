package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.CuteCharm;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.MagicGuard;
import abilities.switchcondition.RunAway;
import battle_arena.terrain.MistyTerrain;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Present;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Aromatherapy;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Minimize;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.Splash;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.ThunderWave;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Fairy;
import types.Type;

public class Cleffa extends Pokemon {

	public Cleffa(int level) {
		super(level,
                50,		                                                              			//hp
                25,		                                                              			//atk
                28,		                                                              			//def
                15,		                                                              			//speed
                45,		                                                              			//spa
                55,		                                                              			//spd
                new Type[]{new Fairy(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new CuteCharm(), new MagicGuard()}),    	                //ability
                3,	                                                                      		        //weight(kg)
                1.4,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Charm(),
                                        new Sing(),
                                        new SweetKiss(),
                                        new MagicalLeaf(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Amnesia(),
                                        new Aromatherapy(),
                                        new BellyDrum(),
                                        new FakeTears(),
                                        new Present(),
                                        new Splash(),
                                        new Tickle()
                                }
                        )
                )
        );
	}

}
