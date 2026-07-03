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
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.onehitko.SheerCold;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import types.Type;
import types.Water;

public class Suicune extends Pokemon{

    public Suicune(int level) {
        super(  level,                                                                                          //level
                100,                                                                                            //baseHP 
                75,                                                                                            	//baseAtk 
                115,                                                                                            //baseDef 
                85,                                                                                            	//baseSpe
                90,                                                                                            	//baseSpA 
                115,                                                                                            //baseSpD 
                new Type[]{new Water(), null},                                                             	//type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new InnerFocus(), new WaterAbsorb()}),   //ability
                190,                                                                                            //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                              	                                        //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new SheerCold(),
                                        new BubbleBeam(),
                                        new Gust(),
                                        new AuroraBeam(),
                                        new IceFang(),
                                        new HydroPump(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Hail(),
                                        new Scald(),
                                        new Surf(),
                                        new Waterfall(),
                                        new Extrasensory(),
                                        new Bite(),
                                        new Leer(),
                                        new Roar(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Swagger(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new Sandstorm(),
                                        new Bulldoze(),
                                        /*new Snarl()*/
                                }
                                )
                        )
                );
    }

}
