package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.Moxie;
import abilities.statisticsalterationondemand.HyperCutter;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ViceGrip;
import moves.damagingmove.physical.VitalThrow;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.multistrike.two.DoubleHit;
import moves.damagingmove.physical.onehitko.Guillotine;
import moves.damagingmove.physical.selfrecoil.Submission;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.FocusBlast;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Type;

public class Pinsir extends Pokemon{

    public Pinsir(int level){
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                125,                                                                                            //baseAtk 
                100,                                                                                            //baseDef 
                85,                                                                                             //baseSpe
                55,                                                                                             //baseSpA 
                70,                                                                                             //baseSpD 
                new Type[]{new Bug()},                                                            		//type
                Ability.getRandomAbility(new Ability[]{new Moxie(), new HyperCutter()}),    	                //ability
                55,                                                                                            	//weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new ViceGrip(),
                                        new Harden(),
                                        new VitalThrow(),
                                        new DoubleHit(),
                                        new BrickBreak(),
                                        new XScissor(),
                                        new Submission(),
                                        new SwordsDance(),
                                        new Superpower(),
                                        new Guillotine(),
                                        new Pursuit(),
                                        new Swagger(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new RockSlide(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                }
                                )
                        )
                );

    }

}
