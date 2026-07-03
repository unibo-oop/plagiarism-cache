package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.LightningRod;
import abilities.otherconditions.RockHead;
import moves.Move;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DrillRun;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Magnitude;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Rock;
import types.Type;

public class Rhyhorn extends Pokemon{

    public Rhyhorn(int level) {
        super(  level,                                                                                          //level
                80,                                                                                             //baseHP 
                85,                                                                                             //baseAtk 
                95,                                                                                             //baseDef 
                25,                                                                                             //baseSpe
                30,                                                                                             //baseSpA 
                30,                                                                                             //baseSpD 
                new Type[]{new Ground(), new Rock()},                                                           //type
                Ability.getRandomAbility(new Ability[]{new LightningRod(), new RockHead()}),                    //ability                                     
                115,                                                                                            //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new HornAttack(),
                                        new TailWhip(),
                                        new FuryAttack(),
                                        new ScaryFace(),
                                        new Stomp(),
                                        new RockBlast(),
                                        new Bulldoze(),
                                        new DrillRun(),
                                        new TakeDown(),
                                        new StoneEdge(),
                                        new HornDrill(),
                                        new Megahorn(),
                                        new Peck(),
                                        new DoubleKick(),
                                        new PoisonSting(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Facade(),
                                        new FocusBlast(),
                                        new RockPolish(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Counter(),
                                        new Crunch(),
                                        new Curse(new Type[]{new Ground(), new Rock()}),
                                        new FireFang(),
                                        new IceFang(),
                                        new IronTail(),
                                        new Magnitude(),
                                        new Reversal(),
                                        new ThunderFang(),

                                }

                                )
                        )
                );
    }

}
