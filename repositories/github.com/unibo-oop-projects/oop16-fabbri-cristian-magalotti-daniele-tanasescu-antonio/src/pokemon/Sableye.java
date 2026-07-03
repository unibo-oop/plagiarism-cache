package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.Prankster;
import abilities.movecondition.ShieldDust;
import abilities.statisticsalterationondemand.KeenEye;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Flatter;
import moves.status.Leer;
import moves.status.MeanLook;
import moves.status.Moonlight;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Bug;
import types.Dark;
import types.Ghost;
import types.Type;

public class Sableye extends Pokemon {

	public Sableye(int level) {
		super(level,
                50,		                                                              			//hp
                75,		                                                              			//atk
                75,		                                                              			//def
                50,		                                                              			//speed
                65,		                                                              			//spa
                65,		                                                              			//spd
                new Type[]{new Dark(), new Ghost()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new KeenEye(), new Prankster()}),     			//ability
                11,	                                                                      		        //weight(kg)
                0.5,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),
                                        new Scratch(),
                                        new Astonish(),
                                        new FurySwipes(),
                                        new Detect(),
                                        new FeintAttack(),
                                        new FakeOut(),
                                        new KnockOff(),
                                        new ShadowClaw(),
                                        new ConfuseRay(),
                                        new ZenHeadbutt(),
                                        new PowerGem(),
                                        new ShadowBall(),
                                        new MeanLook(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new WillOWisp(),
                                        new PsychUp(),
                                        new PoisonJab(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Flatter(),
                                        new Moonlight(),
                                        new Recover()
                                }
                        )
                )
        );
	}

}
