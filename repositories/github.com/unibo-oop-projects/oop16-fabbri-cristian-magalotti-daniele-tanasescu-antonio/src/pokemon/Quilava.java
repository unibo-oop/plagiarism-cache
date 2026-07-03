package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.hpcondition.Blaze;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FlameWheel;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
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
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Howl;
import moves.status.Leer;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SmokeScreen;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Type;

public class Quilava extends Pokemon {

	public Quilava(int level) {
		super(level,
                58,		                                                              			//hp
                64,		                                                              			//atk
                58,		                                                              			//def
                80,		                                                              			//speed
                80,		                                                              			//spa
                65,		                                                              			//spd
                new Type[]{new Fire(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Blaze()}),     		                        //ability
                19,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
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
                                        new DefenseCurl(),
                                        new Swift(),
                                        new Flamethrower(),
                                        new DoubleEdge(),
                                        new Eruption(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new BrickBreak(),
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
