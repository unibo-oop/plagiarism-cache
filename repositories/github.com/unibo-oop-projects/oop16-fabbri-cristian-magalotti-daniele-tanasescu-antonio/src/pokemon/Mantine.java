package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Twister;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Type;
import types.Water;

public class Mantine extends Pokemon{

    public Mantine(int level) {
        super(  level,                                                                                          //level
                85,                                                                                             //baseHP 
                40,                                                                                             //baseAtk 
                70,                                                                                            	//baseDef 
                70,                                                                                            	//baseSpe
                140,                                                                                            //baseSpA 
                80,                                                                                            	//baseSpD 
                new Type[]{new Water(), new Flying()},                                                         	//type
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new WaterAbsorb()/*,
                                                      new WaterVeil()*/}),                                      //ability             
                220,                                                                                            //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Psybeam(),
                                        new BulletSeed(),
                                        new SignalBeam(),
                                        new Tackle(),
                                        new Bubble(),
                                        new Supersonic(),
                                        new BubbleBeam(),
                                        new Roost(),
                                        new ConfuseRay(),
                                        new WingAttack(),
                                        new Headbutt(),
                                        new WaterPulse(),
                                        new WaterGun(),
                                        new TakeDown(),
                                        new HydroPump(),
                                        new AirCutter(),
                                        new AirSlash(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Earthquake(),
                                        new Scald(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new Surf(),
                                        new Waterfall(),
                                        new Toxic(),
                                        new Roost(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new AerialAce(),
                                        new RockTomb(),
                                        new DoubleTeam(),
                                        new Rest(),
                                        new Attract(),
                                        new Whirlwind(),
                                        new Swagger(),
                                        new Amnesia(),
                                        new Haze(),
                                        new MirrorCoat(),
                                        new Slam(),
                                        new Twister(),
                                }
                                )
                        )
                );
    }

}
