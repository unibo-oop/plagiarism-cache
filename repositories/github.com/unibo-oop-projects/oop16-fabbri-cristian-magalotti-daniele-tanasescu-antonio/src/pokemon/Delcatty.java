package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.CuteCharm;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Assist;
import moves.status.Attract;
import moves.status.BulkUp;
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
import moves.status.Roar;
import moves.status.Sing;
import moves.status.SlackOff;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Delcatty extends Pokemon {

    public Delcatty(int level) {
        super(level,
                70,		                                                              			//hp
                65,		                                                              			//atk
                65,		                                                              			//def
                70,		                                                              			//speed
                55,		                                                              			//spa
                55,		                                                              			//spd
                new Type[]{new Normal(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new CuteCharm()}),     				        //ability
                32.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new FakeOut(),
                                        new Sing(),
                                        new Attract(),
                                        new DoubleSlap(),
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
                                        new ZenHeadbutt(),
                                        new Growl(),
                                        new TailWhip(),
                                        new Tackle(),
                                        new DisarmingVoice(),
                                        new FeintAttack(),
                                        new Charm(),
                                        new Assist(),
                                        new HealBell(),
                                        new DoubleEdge(),
                                        new PlayRough()
                                }
                                )
                        )
                );
    }

}
