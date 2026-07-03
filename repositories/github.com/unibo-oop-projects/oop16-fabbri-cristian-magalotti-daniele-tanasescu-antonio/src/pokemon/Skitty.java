package pokemon;

import java.beans.SimpleBeanInfo;
import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.CuteCharm;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Assist;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.Charm;
import moves.status.CosmicPower;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Growl;
import moves.status.HealBell;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Normal;
import types.Type;

public class Skitty extends Pokemon {

    public Skitty(int level) {
        super(level,
                50,		                                                              			//hp
                45,		                                                              			//atk
                45,		                                                              			//def
                50,		                                                              			//speed
                35,		                                                              			//spa
                35,		                                                              			//spd
                new Type[]{new Normal(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new CuteCharm()}),     				        //ability
                11,	                                                                      		        //weight(kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	               	//gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new FakeOut(),
                                        new Growl(),
                                        new TailWhip(),
                                        new Tackle(),
                                        new Sing(),
                                        new Attract(),
                                        new DisarmingVoice(),
                                        new DoubleSlap(),
                                        new FeintAttack(),
                                        new Charm(),
                                        new Assist(),
                                        new HealBell(),
                                        new DoubleEdge(),
                                        new PlayRough(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new CosmicPower(),
                                        new FakeOut(),
                                        new FakeTears(),
                                        new Tickle(),
                                        new ZenHeadbutt()
                                }
                                )
                        )
                );
    }

}
