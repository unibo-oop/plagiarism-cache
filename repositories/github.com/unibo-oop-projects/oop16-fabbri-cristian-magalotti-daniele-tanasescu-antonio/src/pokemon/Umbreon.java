package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import abilities.otherconditions.MagicGuard;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Swift;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.MeanLook;
import moves.status.Moonlight;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Dark;
import types.Type;

public class Umbreon extends Pokemon{

    public Umbreon(int level) {
        super(  level,                                                                                          //level
                95,                                                                                            	//baseHP 
                65,                                                                                             //baseAtk 
                110,                                                                                            //baseDef 
                65,                                                                                             //baseSpe
                60,                                                                                             //baseSpA 
                130,                                                                                            //baseSpD 
                new Type[]{new Dark(), null},                                                                   //type
                Ability.getRandomAbility(new Ability[]{new MagicGuard(), new InnerFocus()}), 		        //ability                                      
                27,                 	                                                                        //weight (Kg) 
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
                                        new Curse(new Type[]{new Dark(), null}),
                                        new Detect(),
                                        new Pursuit(),
                                        new ConfuseRay(),
                                        new FeintAttack(),
                                        new Moonlight(),
                                        new MeanLook(),
                                        new Screech(),
                                        /*new Snarl(),*/
                                        new DarkPulse(),
                                }

                                )
                        )
                );
    }

}
