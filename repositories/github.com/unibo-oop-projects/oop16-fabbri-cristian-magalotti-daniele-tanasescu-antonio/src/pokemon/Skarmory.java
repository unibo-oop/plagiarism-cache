package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WeakArmor;
import abilities.statisticsalterationondemand.KeenEye;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.Swift;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Automize;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Steel;
import types.Type;

public class Skarmory extends Pokemon{

    public Skarmory(int level) {
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                80,                                                                                             //baseAtk 
                140,                                                                                            //baseDef 
                70,                                                                                            	//baseSpe
                40,                                                                                             //baseSpA 
                70,                                                                                            	//baseSpD 
                new Type[]{new Steel(), new Flying()},                                                         	//type
                Ability.getRandomAbility(new Ability[]{new KeenEye(), new WeakArmor()}),                        //ability             
                50,                                                                                             //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),
                                        new Peck(),
                                        new SandAttack(),
                                        new MetalClaw(),
                                        new AirCutter(),
                                        new FuryAttack(),
                                        new Swift(),
                                        new Agility(),
                                        new SteelWing(),
                                        new Slash(),
                                        new AirSlash(),
                                        /*new NighSlash(),*/
                                        new Automize(),
                                        new Toxic(),
                                        new Roost(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new AerialAce(),
                                        new RockTomb(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SwordsDance(),
                                        new FlashCannon(),
                                        new XScissor(),
                                        new DarkPulse(),
                                        new BraveBird(),
                                        new DrillPeck(),
                                        new Pursuit(),
                                        new Whirlwind(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Steel(), new Flying()})
                                }
                                )
                        )
                );
    }

}
