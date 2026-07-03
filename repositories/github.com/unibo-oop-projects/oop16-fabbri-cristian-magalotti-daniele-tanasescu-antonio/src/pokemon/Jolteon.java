package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Anticipation;
import abilities.movecondition.Adaptability;
import abilities.movecondition.VoltAbsorb;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;

public class Jolteon extends Pokemon{

    public Jolteon(int level) {
        super(  level,                                                                                          //level
                65,                                                                                            	//baseHP 
                65,                                                                                             //baseAtk 
                60,                                                                                             //baseDef 
                130,                                                                                            //baseSpe
                110,                                                                                            //baseSpA 
                95,                                                                                            	//baseSpD 
                new Type[]{new Electric(), null},                                                               //type
                Ability.getRandomAbility(new Ability[]{new VoltAbsorb()/*, new QuickFeet()*/}),                 //ability                                      
                25,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new TailWhip(),
                                        new SandAttack(),
                                        new QuickAttack(),
                                        new Bite(),
                                        new Swift(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Electric(), null}),
                                        new Detect(),
                                        new Thunder(),
                                        new ThunderShock(),
                                        new ThunderFang(),
                                        new Thunderbolt(),
                                        /*new Discharge(),*/
                                        new WildCharge(),
                                }

                                )
                        )
                );
    }

}
