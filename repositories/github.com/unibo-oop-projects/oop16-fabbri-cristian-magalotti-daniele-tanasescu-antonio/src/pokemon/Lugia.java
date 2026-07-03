package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.hpcondition.Multiscale;
import abilities.movecondition.WaterAbsorb;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Agility;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Psychic;
import types.Type;

public class Lugia extends Pokemon{

    public Lugia(int level) {
        super(  level,                                                                                          //level
                106,                                                                                            //baseHP 
                90,                                                                                             //baseAtk 
                130,                                                                                            //baseDef 
                110,                                                                                            //baseSpe
                90,                                                                                             //baseSpA 
                154,                                                                                            //baseSpD 
                new Type[]{new Psychic(), new Flying()},                                                        //type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new Multiscale()}),                      //ability                                        
                216,                                                                                            //weight (Kg)
                0.7,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,		                                                                //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Gust(),
                                        new DragonRush(),
                                        new Extrasensory(),
                                        new HydroPump(),
                                        new AncientPower(),
                                        new Recover(),
                                        new CalmMind(),
                                        /*new Aeroblast(),
                                        new PsyShock(),*/
                                        new AerialAce(),
                                        new Agility(),
                                        new Roost(),
                                        new Whirlwind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Hail(),
                                        new Blizzard(),
                                        new IceBeam(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new moves.damagingmove.special.Psychic(),
                                        new ShadowBall(),
                                        new Sandstorm(),
                                        new PsychUp(),
                                        new Bulldoze(),
                                        new DragonTail(),
                                        new Surf(),
                                        new Waterfall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Rest(),
                                        new SteelWing(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
