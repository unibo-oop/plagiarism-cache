package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.statusalterationcondition.EarlyBird;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Smog;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Howl;
import moves.status.Leer;
import moves.status.NastyPlot;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import types.Dark;
import types.Fire;
import types.Type;

public class Houndoom extends Pokemon{

    public Houndoom(int level) {
        super(  level,                                                                                          //level
                75,                                                                                             //baseHP 
                90,                                                                                             //baseAtk 
                60,                                                                                             //baseDef 
                95,                                                                                             //baseSpe
                110,                                                                                            //baseSpA 
                80,                                                                                             //baseSpD 
                new Type[]{new Dark(), new Fire()},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new EarlyBird()}),    				        //ability
                35,                                                                          	                //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),                                                        
                                        new Howl(),
                                        new Ember(),
                                        new Smog(),
                                        new Roar(),
                                        new ScaryFace(),
                                        new Flamethrower(),
                                        new Bite(),
                                        new FireFang(),
                                        new FeintAttack(),
                                        /*new FoulPlay(),*/
                                        new Crunch(),
                                        new NastyPlot(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new ShadowBall(),
                                        new SludgeBomb(),
                                        /*new FlameCharge(),*/
                                        new Attract(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Thief(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        /*new Snarl(),*/
                                        new DarkPulse(),
                                        new Swagger(),
                                        new Counter(),
                                        new Pursuit(),
                                        new Rage(),
                                        new Reversal(),
                                        new ThunderFang(),

                                }
                                )
                        )
                );
    }

}
