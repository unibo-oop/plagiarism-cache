package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.statusalterationcondition.Guts;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.BulletPunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.MachPunch;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fight;
import types.Type;

public class Tyrogue extends Pokemon {

    public Tyrogue(int level) {
        super(level,
                35,		                                                              			//hp
                35,		                                                              			//atk
                35,		                                                              			//def
                35,		                                                              			//speed
                35,		                                                              			//spa
                35,		                                                              			//spd
                new Type[]{new Fight(), null},		                                      			//tipo
                Ability.getRandomAbility(new Ability[]{new Guts()}),	                                        //ability
                50.2,	                                                                      			//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.MALE,	                                              					//gender
                new HashSet<Move>(                                                            			//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new FakeOut(),
                                        /*new WorkUp(),*/
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
                                        new Thief(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new BulletPunch(),
                                        new Counter(),
                                       /* new HighJumpKick(),*/
                                        new MachPunch(),
                                        new Pursuit(),
                                        /*new VacuumWave(),*/
                                }
                                )
                        )
                );
    }

}
