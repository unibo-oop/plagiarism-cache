package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.ViceGrip;
import moves.damagingmove.physical.amount.SeismicToss;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Growl;
import moves.status.IronDefense;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import team.Player;
import types.Bug;
import types.Fairy;
import types.Steel;
import types.Type;

public class Mawile extends Pokemon {

	public Mawile(int level) {
		super(level,
                50,		                                                              	//hp
                85,		                                                              	//atk
                85,		                                                              	//def
                50,		                                                              	//speed
                55,		                                                              	//spa
                55,		                                                              	//spd
                new Type[]{new Steel(), new Fairy()},		                                //tipo
                Ability.getRandomAbility(new Ability[]{new ShieldDust(), new RunAway()}),     	//ability
                11.5,	                                                                      	//weight(kg)
                0.6,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PlayRough(),
                                        new Growl(),
                                        new Astonish(),
                                        new FakeTears(),
                                        new Bite(),
                                        new SweetScent(),
                                        new ViceGrip(),
                                        new FeintAttack(),
                                        new Crunch(),
                                        new IronDefense(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Protect(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new SludgeBomb(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
                                        new FalseSwipe(),
                                        new StoneEdge(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new FlashCannon(),
                                        new DarkPulse(),
                                        new AncientPower(),
                                        new FireFang(),
                                        new IceFang(),
                                        new PoisonFang(),
                                        new Slam(),
                                        new ThunderFang(),
                                        new Tickle()
                                }
                        )
                )
        );
	}

}
