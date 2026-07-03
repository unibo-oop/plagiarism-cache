package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.Sniper;
import abilities.statisticsalterationondemand.KeenEye;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.DrillRun;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Growl;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Normal;
import types.Type;

public class Fearow extends Pokemon{

    public Fearow(int level) {
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                90,                                                                                             //baseAtk 
                65,                                                                                             //baseDef 
                100,                                                                                            //baseSpe
                61,                                                                                             //baseSpA 
                61,                                                                                             //baseSpD 
                new Type[]{new Normal(), new Flying()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new KeenEye(), new Sniper()}),                     	//ability
                38,                                                                                             //weight (Kg)
                0.7,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new Growl(),
                                        new Leer(),
                                        new Pursuit(),
                                        new FuryAttack(),
                                        new AerialAce(),
                                        new Agility(),
                                        new Roost(),
                                        new DrillPeck(),
                                        new DrillRun(),
                                        new Facade(),
                                        new QuickAttack(),
                                        new Astonish(),
                                        new FeatherDance(),
                                        new FeintAttack(),
                                        new ScaryFace(),
                                        new Whirlwind(),
                                        new BraveBird(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new FalseSwipe(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
