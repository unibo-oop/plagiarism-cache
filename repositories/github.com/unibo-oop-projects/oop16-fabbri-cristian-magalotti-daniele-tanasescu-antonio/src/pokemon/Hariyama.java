package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.SheerForce;
import abilities.statusalterationcondition.Guts;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BulletPunch;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.DynamicPunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.SmellingSalts;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.VitalThrow;
import moves.damagingmove.physical.amount.SeismicToss;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.ArmThrust;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Surf;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Fight;
import types.Type;

public class Hariyama extends Pokemon {

    public Hariyama(int level) {
        super(level,
                114,		                                                              		//hp
                120,		                                                              		//atk
                60,		                                                              		//def
                50,		                                                              		//speed
                40,		                                                              		//spa
                60,		                                                              		//spd
                new Type[]{new Fight(), null},		                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new Guts(),new SheerForce()}),     		//ability
                253.8,	                                                                      	        //weight(kg)
                0.9,                                                                                    //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new SandAttack(),
                                        new ArmThrust(),
                                        new FakeOut(),
                                        new Whirlwind(),
                                        new KnockOff(),
                                        new VitalThrow(),
                                        new BellyDrum(),
                                        new SmellingSalts(),
                                        new Reversal(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
                                        new StoneEdge(),
                                        new RockSlide(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Surf(),
                                        new BulletPunch(),
                                        new Counter(),
                                        new CrossChop(),
                                        new Detect(),
                                        new DynamicPunch(),
                                        new FeintAttack(),
                                        new Revenge()
                                }
                                )
                        )
                );
    }

}
