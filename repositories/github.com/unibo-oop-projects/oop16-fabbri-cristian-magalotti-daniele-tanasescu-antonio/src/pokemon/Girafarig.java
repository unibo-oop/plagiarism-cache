package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.SapSipper;
import abilities.otherconditions.InnerFocus;
import abilities.statusalterationcondition.EarlyBird;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.DrillRun;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.two.DoubleHit;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Agility;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.Coil;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Glare;
import moves.status.Growl;
import moves.status.NastyPlot;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Normal;
import types.Psychic;
import types.Type;

public class Girafarig extends Pokemon{

    public Girafarig(int level) {
        super(  level,                                                                                          //level
                70,                                                                                             //baseHP 
                80,                                                                                             //baseAtk 
                65,                                                                                             //baseDef 
                85,                                                                                             //baseSpe
                90,                                                                                             //baseSpA 
                65,                                                                                            	//baseSpD 
                new Type[]{new Normal(), new Psychic()},                                                        //type
                Ability.getRandomAbility(new Ability[]{new InnerFocus(), new EarlyBird(), new SapSipper()}),    //ability                                      
                41,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new Stomp(),
                                        new Confusion(),
                                        new Psybeam(),
                                        new DoubleHit(),
                                        new ZenHeadbutt(),
                                        new Crunch(),
                                        new NastyPlot(),
                                        new moves.damagingmove.special.Psychic(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new Bite(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Earthquake(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Bulldoze(),
                                        new EnergyBall(),
                                        new DazzlingGleam(),
                                        new ThunderWave(),
                                        new RockSlide(),
                                        new PoisonJab(),
                                        new DreamEater(),
                                        new WildCharge(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Normal(), new Psychic()}),
                                        new Detect(),
                                        new Agility(),
                                        new Astonish(),
                                        new Headbutt(),
                                        new Amnesia(),
                                        new DoubleKick(),

                                }

                                )
                        )
                );
    }

}
