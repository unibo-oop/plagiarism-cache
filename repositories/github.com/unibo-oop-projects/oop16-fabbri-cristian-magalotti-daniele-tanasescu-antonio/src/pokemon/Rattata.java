package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.statusalterationcondition.Guts;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FlameWheel;
import moves.damagingmove.physical.HyperFang;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Rattata extends Pokemon {

    public Rattata(int level) {

        super(level,
                30,		                                                                   				//hp
                56,		                                                                   				//atk
                35,		                                                                   				//def
                72,		                                                                   				//speed
                25,		                                                                   				//spa
                35,		                                                                   				//spd
                new Type[]{new Normal(), null},			                                   				//tipo
                Ability.getRandomAbility(new Ability[]{new Guts(), new RunAway(), new Hustle()}),  		                //ability
                3.5,	                                                                           		                //weight(kg)
                1,                                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                   			        //gender
                new HashSet<Move>(                                                                 		                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new TailWhip(),
                                        new QuickAttack(),
                                        new Bite(),
                                        new Pursuit(),
                                        new HyperFang(),
                                        new Crunch(),
                                        new DoubleEdge(),
                                        new Endeavor(),
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
                                        new Thief(),
                                        new ThunderWave(),
                                        new Swagger(),
                                        new Bite(),
                                        new Counter(),
                                        new FlameWheel(),
                                        new FurySwipes(),
                                        new Revenge(),
                                        new Reversal(),
                                        new Screech(),
                                }
                                )
                        )
                );
    }
}
