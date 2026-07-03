package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.statisticsalterationondemand.KeenEye;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Amnesia;
import moves.status.Assist;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Sentret extends Pokemon {

	public Sentret(int level) {
		super(level,
                35,		                                                              			//hp
                46,		                                                              			//atk
                34,		                                                              			//def
                20,		                                                              			//speed
                35,		                                                              			//spa
                45,		                                                              			//spd
                new Type[]{new Normal(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new RunAway(), new KeenEye(),
                				      /*new Frisk()*/}),     					//ability
                6,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new DefenseCurl(),
                                        new QuickAttack(),
                                        new FurySwipes(),
                                        new Slam(),
                                        new Rest(),
                                        new Amnesia(),
                                        new HyperVoice(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Facade(),
                                        new Attract(),
                                        new Thief(),
                                        new ShadowClaw(),
                                        new Swagger(),
                                        new Surf(),
                                        new Assist(),
                                        new Charm(),
                                        new DoubleEdge(),
                                        new IronTail(),
                                        new Pursuit(),
                                        new Reversal(),
                                        new Slash()
                                }
                        )
                )
        );
	}

}
