package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.statusalterationcondition.Guts;
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
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SlackOff;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Slaking extends Pokemon {

    public Slaking(int level) {
        super(level,
                150,		                                                              		//hp
                160,		                                                              		//atk
                100,		                                                              		//def
                100,		                                                              		//speed
                95,		                                                              		//spa
                65,		                                                              		//spd
                new Type[]{new Normal(), null},		                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new Guts()}),     				//ability
                130.5,	                                                                      	        //weight(kg)
                1,                                                                            	        //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Swagger(),
                                        new HammerArm(),
                                        new Scratch(),
                                        new SlackOff(),
                                        new FeintAttack(),
                                        new Amnesia(),
                                        new Counter(),
                                        new Flail(),
                                        new Roar(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
                                        new ShadowClaw(),
                                        new RockSlide(),                                        
                                        new BodySlam(),
                                        new CrushClaw(),                                        
                                        new Pursuit(),
                                        new Slash(),
                                        new Tickle(), 
                                        new PlayRough(),
                                        new Reversal(),
                                        new FurySwipes()
                                }
                                )
                        )
                );
    }

}
