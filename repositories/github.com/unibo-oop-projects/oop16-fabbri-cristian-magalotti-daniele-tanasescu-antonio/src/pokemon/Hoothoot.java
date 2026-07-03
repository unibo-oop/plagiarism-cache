package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.Insomnia;
import abilities.statisticsalterationondemand.KeenEye;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.SkyUppercut;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.amount.NightShade;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Growl;
import moves.status.Hypnosis;
import moves.status.MirrorMove;
import moves.status.PsychUp;
import moves.status.RainDance;
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

public class Hoothoot extends Pokemon {

	public Hoothoot(int level) {
		super(level,
                60,		                                                              			//hp
                30,		                                                              			//atk
                30,		                                                              			//def
                50,		                                                              			//speed
                36,		                                                              			//spa
                56,		                                                              			//spd
                new Type[]{new Normal(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Insomnia(), new KeenEye()/*,
                			 new TintedLens()*/}),     				                //ability
                21.2,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new Hypnosis(),
                                        new Peck(),
                                        new Confusion(),
                                        new ZenHeadbutt(),
                                        new Extrasensory(),
                                        new TakeDown(),
                                        new AirSlash(),
                                        new Roost(),
                                        new Moonblast(),
                                        new DreamEater(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new Agility(),
                                        new FeatherDance(),
                                        new FeintAttack(),
                                        new MirrorMove(),
                                        new Supersonic(),
                                        new Whirlwind(),
                                        new WingAttack()
                                }
                        )
                )
        );
	}

}
