package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.statusalterationcondition.Guts;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.FlareBlitz;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Smog;
import moves.damagingmove.special.Swift;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Fire;
import types.Type;

public class Flareon extends Pokemon{

    public Flareon(int level) {
        super(  level,                                                                                          //level
                65,                                                                                            	//baseHP 
                130,                                                                                            //baseAtk 
                60,                                                                                             //baseDef 
                65,                                                                                             //baseSpe
                95,                                                                                             //baseSpA 
                110,                                                                                            //baseSpD 
                new Type[]{new Fire(), null},                                                                 	//type
                Ability.getRandomAbility(new Ability[]{new Guts()}),						//ability                                      
                25,    	             	                                                                        //weight (Kg) 
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                              	        //gender  
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
                                        new Curse(new Type[]{new Fire(), null}),
                                        new Detect(),
                                        new Ember(),
                                        new FireFang(),
                                        new FlareBlitz(),
                                        new ScaryFace(),
                                        new Smog(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        
                                }

                                )
                        )
                );
    }

}
