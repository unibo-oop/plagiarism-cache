package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Technician;
import abilities.hpcondition.Swarm;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.two.DoubleHit;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Type;

public class Scyther extends Pokemon{

    public Scyther(int level) {
        super(  level,                                                                                          //level
                40,                                                                                             //baseHP 
                35,                                                                                             //baseAtk 
                30,                                                                                             //baseDef 
                50,                                                                                             //baseSpe
                20,                                                                                             //baseSpA 
                20,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Flying()},                                                            //type
                Ability.getRandomAbility(new Ability[]{new Swarm(), new Technician()}),                         //ability
                3.2,                                                                                            //weight (Kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new QuickAttack(),
                                        new Leer(),
                                        new BugBite(),
                                        new Pursuit(),
                                        new FalseSwipe(),
                                        new Agility(),
                                        new WingAttack(),
                                        new Slash(),
                                        new DoubleTeam(),
                                        new XScissor(),
                                        /*new NightSlash(),*/
                                        new DoubleHit(),
                                        new AirSlash(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new BrickBreak(),
                                        new SteelWing(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Thief(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        /*new BugBuzz(),*/
                                        new Counter(),
                                        new Reversal(),
                                        
                                }
                                )
                )
        );
    }

}
