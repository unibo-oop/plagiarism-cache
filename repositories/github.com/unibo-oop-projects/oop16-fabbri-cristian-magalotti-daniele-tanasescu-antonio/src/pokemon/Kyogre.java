package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Drizzle;
import abilities.movecondition.WaterAbsorb;
import abilities.weathercondition.Hydration;
import moves.Move;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Kyogre extends Pokemon{

    public Kyogre(int level) {
        super(  level,                                                                                          //level
                100,                                                                                            //baseHP 
                100,                                                                                            //baseAtk 
                90,                                                                                             //baseDef 
                90,                                                                                             //baseSpe
                150,                                                                                            //baseSpA 
                140,                                                                                            //baseSpD 
                new Type[]{new Water(), null},                                                                  //type
                Ability.getRandomAbility(new Ability[]{new Drizzle()}), 										//ability                                      
                350,                 	                                                                        //weight (Kg) 
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new AncientPower(),
                                        new ScaryFace(),
                                        new AquaTail(),
                                        new BodySlam(),
                                        new CalmMind(),
                                        new WaterSprout(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new ThunderWave(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new RockTomb(),
                                        new Bulldoze(),
                                        new PsychUp(),
                                        new RockSlide(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Water(), null}),
                                        new Detect(),
                                        new WaterGun(),
                                        new WaterPulse(),
                                        new MuddyWater(),
                                        new HydroPump(),
                                        new Scald(),
                                        new Surf(),
                                        new Waterfall(),
                                }

                                )
                        )
                );
    }

}
