package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.firstturn.alwaysactive.AirLock;
import abilities.firstturn.alwaysactive.MarvelScale;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.ExtremeSpeed;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Outrage;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DracoMeteor;
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
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dragon;
import types.Flying;
import types.Type;

public class Rayquaza extends Pokemon{

    public Rayquaza(int level) {
        super(  level,                                                                                          //level
                105,                                                                                             //baseHP 
                150,                                                                                            //baseAtk 
                90,                                                                                             //baseDef 
                95,                                                                                            	//baseSpe
                150,                                                                                            //baseSpA 
                90,                                                                                             //baseSpD 
                new Type[]{new Dragon(), new Flying()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new AirLock()}),  					//ability
                206,                                                                                           	//weight (Kg)
                0.6,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,                                                                       	//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),
                                        new ThunderWave(),
                                        new Twister(),
                                        new DragonRage(),
                                        new ScaryFace(),
                                        new AncientPower(),
                                        new Crunch(),
                                        new AirSlash(),
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
                                        new PsychUp(),
                                        new StoneEdge(),
                                        new RockSlide(),
                                        new ThunderWave(),
                                        new Bulldoze(),
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
                                        new FuryAttack(),
                                        new DracoMeteor(),
                                }
                                )
                )
        );
    }

}
