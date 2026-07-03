package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.CuteCharm;
import abilities.movecondition.ShieldDust;
import abilities.statisticsalterationondemand.Competitive;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Present;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Fairy;
import types.Normal;
import types.Psychic;
import types.Type;

public class Igglybuff extends Pokemon {

	public Igglybuff(int level) {
		super(level,
                90,		                                                              			//hp
                30,		                                                              			//atk
                15,		                                                              			//def
                15,		                                                              			//speed
                40,		                                                              			//spa
                20,		                                                              			//spd
                new Type[]{new Normal(), new Fairy()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new CuteCharm(), new Competitive(),
                					/*new FriendGuard()*/}),     				//ability
                1,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Sing(),
                                        new Charm(),
                                        new DefenseCurl(),
                                        new Pound(),
                                        new SweetKiss(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new moves.damagingmove.special.Psychic(),
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
                                        new WildCharge(),
                                        new FakeTears(),
                                        new FeintAttack(),
                                        new Present()
                                }
                        )
                )
        );
	}

}
