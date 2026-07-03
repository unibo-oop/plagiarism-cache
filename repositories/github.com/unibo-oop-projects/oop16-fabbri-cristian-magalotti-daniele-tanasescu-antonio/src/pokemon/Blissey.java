package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.switchcondition.NaturalCure;
import abilities.switchcondition.Regenerator;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.EggBomb;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.Minimize;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.Sing;
import moves.status.SoftBoiled;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Blissey extends Pokemon{

    public Blissey(int level) {
        super(  level,                                                                                          //level
                255,                                                                                            //baseHP 
                10,                                                                                             //baseAtk 
                10,                                                                                             //baseDef 
                55,                                                                                             //baseSpe
                75,                                                                                             //baseSpA 
                135,                                                                                            //baseSpD 
                new Type[]{new Normal(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new NaturalCure(), new Regenerator()}),                  //ability                                      
                47.5,                                                                                           //weight (Kg) 
                0.9,                                                                                            //miniFrontSizeScale
                Gender.FEMALE,                                                                      		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Growl(),
                                        new TailWhip(),
                                        new DoubleSlap(),
                                        new DoubleEdge(),
                                        new SoftBoiled(),
                                        new TakeDown(),
                                        new Sing(),
                                        new EggBomb(),
                                        new DefenseCurl(),
                                        new Minimize(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new DreamEater(),
                                        /*new GrassKnot(),*/
                                        new Swagger(),
                                        new WildCharge(),
                                        /*new DazzlingGleam(),*/
                                }

                                )
                        )
                );
    }

}
