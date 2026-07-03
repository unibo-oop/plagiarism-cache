package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.firstturn.alwaysactive.MarvelScale;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.ExtremeSpeed;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Outrage;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.Twister;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.amount.DragonRage;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dragon;
import types.Type;

public class Dratini extends Pokemon{

    public Dratini(int level) {
        super(  level,                                                                                          //level
                41,                                                                                             //baseHP 
                64,                                                                                            	//baseAtk 
                45,                                                                                             //baseDef 
                50,                                                                                            	//baseSpe
                50,                                                                                             //baseSpA 
                50,                                                                                             //baseSpD 
                new Type[]{new Dragon(), null},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new ShedSkin(), new MarvelScale()}),  			//ability
                3.2,                                                                                           	//weight (Kg)
                1.1,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),
                                        new ThunderWave(),
                                        new Twister(),
                                        new DragonRage(),
                                        new Slam(),
                                        new Agility(),
                                        new AquaTail(),
                                        new DragonRush(),
                                        new DragonDance(),
                                        new Outrage(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new DragonTail(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new AquaJet(),
                                        new DragonBreath(),
                                        new DragonPulse(),
                                        new ExtremeSpeed(),
                                        new Haze(),
                                        new IronTail(),
                                        new Supersonic(),
                                        new WaterPulse(),
                                        new Pound(),
                                        new FuryAttack()
                                        
                                }
                                )
                )
        );
    }

}
