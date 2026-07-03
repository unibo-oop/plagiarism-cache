package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.MagicBounce;
import abilities.otherconditions.MagicGuard;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Swift;
import moves.status.Attract;
import moves.status.ConfuseRay;
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
import types.Psychic;
import types.Type;

public class Espeon extends Pokemon{

    public Espeon(int level) {
        super(  level,                                                                                          //level
                65,                                                                                            	//baseHP 
                65,                                                                                             //baseAtk 
                60,                                                                                             //baseDef 
                110,                                                                                            //baseSpe
                130,                                                                                            //baseSpA 
                95,                                                                                            	//baseSpD 
                new Type[]{new Psychic(), null},                                                                //type
                Ability.getRandomAbility(new Ability[]{new MagicGuard(), new MagicBounce()}),                   //ability                                      
                26.5,                 	                                                                        //weight (Kg) 
                1,                                                                                              //sizeScale
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
                                        new Curse(new Type[]{new Psychic(), null}),
                                        new Detect(),
                                        new ConfuseRay(),
                                        new Confusion(),
                                        new Psybeam(),
                                        new moves.damagingmove.special.Psychic(),
                                        new ShadowBall(),
                                        new DazzlingGleam()
                                }

                                )
                        )
                );
    }

}
