package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.CuteCharm;
import abilities.weathercondition.SnowCloak;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.PowderSnow;
import moves.damagingmove.special.onehitko.SheerCold;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Ice;
import types.Type;

public class Articuno extends Pokemon{

    public Articuno(int level) {
        super(  level,                                                                                          //level
                90,                                                                                             //baseHP 
                85,                                                                                             //baseAtk 
                100,                                                                                            //baseDef 
                85,                                                                                            	//baseSpe
                95,                                                                                             //baseSpA 
                125,                                                                                            //baseSpD 
                new Type[]{new Ice(), new Flying()},                                                         	//type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new SnowCloak()}),                       //ability             
                55,                                                                                             //weight (Kg)
                0.6,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,		                                                                //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Gust(),
                                        new PowderSnow(),
                                       /* new IceShard(),*/
                                        new Agility(),
                                        new AncientPower(),
                                        new AerialAce(),
                                        new Roost(),
                                        new Hurricane(),
                                        new IceBeam(),
                                        new Facade(),
                                        new Blizzard(),
                                        new Hurricane(),
                                        new SheerCold(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new FalseSwipe(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
