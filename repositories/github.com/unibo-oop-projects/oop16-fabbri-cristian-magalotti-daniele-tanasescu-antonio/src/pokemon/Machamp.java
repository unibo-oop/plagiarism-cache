package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.statusalterationcondition.Guts;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BulletPunch;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.KarateChop;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.VitalThrow;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.selfrecoil.Submission;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.Meditate;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fight;
import types.Type;

public class Machamp extends Pokemon{

    public Machamp(int level){
        super(  level,                                                                                          //level
                90,                                                                                             //baseHP 
                130,                                                                                            //baseAtk 
                80,                                                                                             //baseDef 
                55,                                                                                             //baseSpe
                65,                                                                                             //baseSpA 
                55,                                                                                             //baseSpD 
                new Type[]{new Fight()},                                                            		//type
                Ability.getRandomAbility(new Ability[]{new Guts()}),    	                                //ability
                130,                                                                                            //weight (Kg)
                1,                                                                                              //sizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new LowKick(),
                                        new Leer(),
                                        new KarateChop(),
                                        new Pursuit(),
                                        new Swagger(),
                                        new CrossChop(),
                                        /*new CloseCombat(),*/
                                        new KnockOff(),
                                        new VitalThrow(),
                                        new Submission(),
                                        new ScaryFace(),
                                        new BulletPunch(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Counter(),
                                        new FocusBlast(),
                                        new RockSlide(),
                                        new Meditate(),
                                        new Revenge(),
                                        new FirePunch(),
                                        new IcePunch(),
                                        new ThunderPunch()
                                }
                                )
                        )
                );

    }

}
