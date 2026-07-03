package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.HugePower;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.MagicGuard;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.DynamicPunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.PowderSnow;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Fight;
import types.Psychic;
import types.Type;

public class Meditite extends Pokemon {

	public Meditite(int level) {
		super(level,
                30,		                                                              			//hp
                40,		                                                              			//atk
                55,		                                                              			//def
                60,		                                                              			//speed
                40,		                                                              			//spa
                55,		                                                              			//spd
                new Type[]{new Fight(), new Psychic()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new MagicGuard(), new HugePower()}),                     //ability
                11.2,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new moves.status.Meditate(),
                                        new Confusion(),
                                        new Detect(),
                                        new CalmMind(),
                                        new PsychUp(),
                                        new Reversal(),
                                        new Recover(),
                                        new Counter(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new moves.damagingmove.special.Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
                                        new RockSlide(),
                                        new PoisonJab(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new DynamicPunch(),
                                        new FakeOut(),
                                        new FirePunch(),
                                        new IcePunch(),
                                        new ThunderPunch()
                                }
                        )
                )
        );
	}

}
