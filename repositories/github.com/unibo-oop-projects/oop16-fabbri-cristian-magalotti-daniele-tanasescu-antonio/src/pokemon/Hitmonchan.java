package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import abilities.statisticsalterationondemand.KeenEye;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.MachPunch;
import moves.damagingmove.physical.MegaPunch;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.SkyUppercut;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.twotofive.CometPunch;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.FocusBlast;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Fight;
import types.Type;

public class Hitmonchan extends Pokemon {

    public Hitmonchan(int level) {
        super(level,
                50,		                                                              					//hp
                105,		                                                              					//atk
                79,		                                                              					//def
                76,		                                                              					//speed
                35,		                                                              					//spa
                110,		                                                              					//spd
                new Type[]{new Fight(), null},		                                      					//tipo
                Ability.getRandomAbility(new Ability[]{new KeenEye(), /*new IronFist(),*/ new InnerFocus()}),	                //ability
                50.2,	                                                                      				        //weight(kg)
                1,                                                                                                              //miniFrontSizeScale
                Gender.MALE,	                                              							//gender
                new HashSet<Move>(                                                            				        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new CometPunch(),
                                        new Agility(),
                                        new Revenge(),
                                        new MachPunch(),
                                        new Pursuit(),
                                        new Counter(),
                                        new ThunderPunch(),
                                        new IcePunch(),
                                        new FirePunch(),
                                        new SkyUppercut(),
                                        new MegaPunch(),
                                        new Detect(),
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
                                        new FocusBlast(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Tackle(),
                                        new FakeOut()
                                }
                                )
                        )
                );
    }

}
