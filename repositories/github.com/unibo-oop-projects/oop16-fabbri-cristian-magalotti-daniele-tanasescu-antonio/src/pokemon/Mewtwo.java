package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.WaterAbsorb;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.AuraSphere;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.NastyPlot;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Psychic;
import types.Type;

public class Mewtwo extends Pokemon{

    public Mewtwo(int level) {
        super(  level,                                                                                          //level
                106,                                                                                            //baseHP 
                110,                                                                                            //baseAtk 
                90,                                                                                            	//baseDef 
                130,                                                                                            //baseSpe
                154,                                                                                            //baseSpA 
                90,                                                                                            	//baseSpD 
                new Type[]{new Psychic(), null},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new Pressure()}),                  	                //ability
                122,                                                                                            //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                                                                              //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Swift(),
                                        new Recover(),
                                        new moves.damagingmove.special.Psychic(),
                                        /*new Barrier(),*/
                                        new AncientPower(),
                                        new Amnesia(),
                                        new NastyPlot(),
                                        new AuraSphere(),
                                        /*new PsyShock(),*/
                                        new CalmMind(),
                                        new Roar(),
                                        new Toxic(),
                                        new Hail(),
                                        /*new BulkUp(),*/
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new WillOWisp(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new DreamEater(),
                                        /*new GrassKnot(),*/
                                        new Swagger(),
                                        new XScissor(),
                                        new DragonTail(),
                                        new PoisonJab(),
                                        new Confusion(),
                                }
                                )
                        )
                );
    }

}
