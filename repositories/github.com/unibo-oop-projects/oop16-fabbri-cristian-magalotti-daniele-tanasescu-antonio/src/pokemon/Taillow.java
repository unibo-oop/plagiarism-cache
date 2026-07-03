package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.Scrappy;
import abilities.statusalterationcondition.Guts;
import abilities.switchcondition.RunAway;
import battle_arena.weather.Rain;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Hurricane;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.MirrorMove;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Normal;
import types.Type;

public class Taillow extends Pokemon {

	public Taillow(int level) {
		super(level,
                40,		                                                              			//hp
                55,		                                                              			//atk
                30,		                                                              			//def
                85,		                                                              			//speed
                30,		                                                              			//spa
                30,		                                                              			//spd
                new Type[]{new Normal(), new Flying()},		                                   	        //tipo
                Ability.getRandomAbility(new Ability[]{new Guts(), new Scrappy()}),     		        //ability
                2.3,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new Growl(),
                                        new QuickAttack(),
                                        new WingAttack(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Agility(),
                                        new AirSlash(),
                                        new Endeavor(),
                                        new BraveBird(),
                                        new Reversal(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new Swagger(),
                                        new Hurricane(),
                                        new MirrorMove(),
                                        new Pursuit(),
                                        new Rage(),
                                        new Refresh(),
                                        new Supersonic(),
                                        new Whirlwind()
                                }
                        )
                )
        );
	}

}
