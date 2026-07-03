package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.VoltAbsorb;
import abilities.otherconditions.InnerFocus;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
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
import moves.status.ThunderWave;
import moves.status.Toxic;
import types.Electric;
import types.Type;

public class Raikou extends Pokemon{

    public Raikou(int level) {
        super(  level,                                                                                          //level
                90,                                                                                            	//baseHP 
                85,                                                                                            	//baseAtk 
                75,                                                                                             //baseDef 
                115,                                                                                            //baseSpe
                115,                                                                                            //baseSpA 
                100,                                                                                            //baseSpD 
                new Type[]{new Electric(), null},                                                             	//type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new InnerFocus(), new VoltAbsorb()}),    //ability
                180,                                                                                            //weight (Kg)
                1,                                                                                              //sizeScale
                Gender.GENDERLESS,                              	                                         //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        /*new Discharge(),*/
                                        new Extrasensory(),
                                        new Bite(),
                                        new Leer(),
                                        new ThunderShock(),
                                        new Roar(),
                                        new QuickAttack(),
                                        new Spark(),
                                        new CalmMind(),
                                        new ThunderFang(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new DoubleTeam(),
                                        new Thunder(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Thunderbolt(),
                                        new Swagger(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new Sandstorm(),
                                        new ThunderWave(),
                                        new WildCharge(),
                                        new Bulldoze(),
                                        /*new Snarl(),*/

                                }
                                )
                        )
                );
    }

}
