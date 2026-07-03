package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.InnerFocus;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.SacredFire;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.hpdependent.Eruption;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import types.Fire;
import types.Type;

public class Entei extends Pokemon{

    public Entei(int level) {
        super(  level,                                                                                          //level
                115,                                                                                            //baseHP 
                115,                                                                                            //baseAtk 
                85,                                                                                             //baseDef 
                100,                                                                                            //baseSpe
                90,                                                                                             //baseSpA 
                75,                                                                                             //baseSpD 
                new Type[]{new Fire(), null},                                                                  	//type
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Pressure(), new InnerFocus()}),   //ability
                200,                                                                                            //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                              	                                        //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new SacredFire(),
                                        new Eruption(),
                                        new Extrasensory(),
                                        new Bite(),
                                        new Leer(),
                                        new Ember(),
                                        new Roar(),
                                        new Stomp(),
                                        new Flamethrower(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IronTail(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Rest(),
                                        new FireFang(),
                                        new Overheat(),
                                        new Swagger(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new Sandstorm(),
                                        /*new FlameCharge(),*/
                                        new WillOWisp(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                        /*new Snarl(),*/

                                }
                                )
                        )
                );
    }

}
