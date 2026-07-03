package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.hpcondition.Blaze;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FlameWheel;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.hpdependent.Eruption;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Howl;
import moves.status.Leer;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.SmokeScreen;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import status_condition.Burn;
import types.Fire;
import types.Type;

public class Cyndaquil extends Pokemon {

	public Cyndaquil(int level) {
		super(level,
                39,		                                                              			//hp
                52,		                                                              			//atk
                43,		                                                              			//def
                65,		                                                              			//speed
                60,		                                                              			//spa
                50,		                                                              			//spd
                new Type[]{new Fire(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Blaze()}),     		                        //ability
                7.9,	                                                                      	                //weight(kg)
                1.1,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Leer(),
                                        new SmokeScreen(),
                                        new Ember(),
                                        new QuickAttack(),
                                        new FlameWheel(),
                                        new Swift(),
                                        new Flamethrower(),
                                        new DoubleEdge(),
                                        new Eruption(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new NaturePower(),
                                        new CrushClaw(),
                                        new DoubleEdge(),
                                        new DoubleKick(),
                                        new Extrasensory(),
                                        new FurySwipes(),
                                        new Howl(),
                                        new QuickAttack(),
                                        new Reversal()
                                }
                        )
                )
        );
	}

}
