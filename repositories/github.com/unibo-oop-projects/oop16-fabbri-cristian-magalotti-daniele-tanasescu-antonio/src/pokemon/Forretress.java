package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.Overcoat;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.ZapCannon;
import moves.status.Automize;
import moves.status.IronDefense;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Steel;
import types.Type;

public class Forretress extends Pokemon{

    public Forretress(int level) {
        super(  level,                                                                                          //level
                75,                                                                                             //baseHP 
                90,                                                                                             //baseAtk 
                140,                                                                                            //baseDef 
                40,                                                                                             //baseSpe
                60,                                                                                             //baseSpA 
                60,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Steel()},                                                             //type
                Ability.getRandomAbility(new Ability[]{new Overcoat()}),                     	                //ability
                125,                                                                                            //weight (Kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Protect(),
                                        new BugBite(),
                                        new SelfDestruct(),
                                        new TakeDown(),
                                        new Explosion(),
                                        new IronDefense(),
                                        /*new GyroBall(),*/
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new Facade(),
                                        new Rest(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new PinMissile(),
                                        new Flail(),
                                        new Swift(),
                                        new Counter(),
                                        new Automize(),
                                        new ZapCannon(),
                                        new FlashCannon(),
                                        /*new HeavySlam(),*/
                                }
                                )
                )
        );
    }

}
