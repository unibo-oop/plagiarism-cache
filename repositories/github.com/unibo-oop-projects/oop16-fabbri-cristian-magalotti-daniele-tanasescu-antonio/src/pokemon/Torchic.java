package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.endofturnconditionability.SpeedBoost;
import abilities.hpcondition.Blaze;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.SmellingSalts;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.amount.NightShade;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Growl;
import moves.status.MirrorMove;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Bug;
import types.Fire;
import types.Type;

public class Torchic extends Pokemon {

    public Torchic(int level) {
        super(level,
                45,		                                                              	//hp
                60,		                                                              	//atk
                40,		                                                              	//def
                45,		                                                              	//speed
                70,		                                                              	//spa
                50,		                                                              	//spd
                new Type[]{new Fire(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new Blaze(), new SpeedBoost()}),         //ability
                2.5,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Growl(),
                                        new Ember(),
                                        new SandAttack(),
                                        new Peck(),
                                        new QuickAttack(),
                                        new Slash(),
                                        new MirrorMove(),
                                        new Flamethrower(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        new ShadowClaw(),
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Agility(),
                                        new Counter(),
                                        new CrushClaw(),
                                        new Curse(new Type[]{new Fire(), null}),
                                        new FeatherDance(),
                                        new LowKick(),
                                        new Reversal(),
                                        new SmellingSalts()
                                }
                                )
                        )
                );
    }

}
