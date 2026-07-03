package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BodySlam;
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
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.Coil;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Glare;
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
import types.Type;

public class Dunsparce extends Pokemon{

    public Dunsparce(int level) {
        super(  level,                                                                                          //level
                100,                                                                                            //baseHP 
                70,                                                                                             //baseAtk 
                70,                                                                                             //baseDef 
                45,                                                                                             //baseSpe
                65,                                                                                             //baseSpA 
                65,                                                                                            	//baseSpD 
                new Type[]{new Normal(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new RunAway(), /*new SereneGrace()*/}), 			//ability                                      
                15,                 	                                                                        //weight (Kg) 
                1.1,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Rage(),
                                        new DefenseCurl(),
                                        new Pursuit(),
                                        new Screech(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new DrillRun(),
                                        new Roost(),
                                        new Coil(),
                                        new Glare(),
                                        new Endeavor(),
                                        new AirSlash(),
                                        new DragonRush(),
                                        new Flail(),
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
                                        new Curse(new Type[]{new Normal(), null}),
                                        new Detect(),
                                        new Agility(),
                                        new Astonish(),
                                        new Headbutt(),
                                        
                                }

                                )
                        )
                );
    }

}
