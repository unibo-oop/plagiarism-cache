package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.LightningRod;
import abilities.movecondition.Moxie;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.Twister;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.amount.DragonRage;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.Splash;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Type;
import types.Water;

public class Gyarados extends Pokemon {

    public Gyarados(int level) {
        super(level,
                95,		                                                                              	//hp
                125,		                                                                              	//atk
                79,		                                                                              	//def
                81,		                                                                              	//speed
                60,		                                                                              	//spa
                100,		                                                                              	//spd
                new Type[]{new Water(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Intimidate(), new Moxie()}),  		        //ability
                235,	                                                                                    	//weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	//gender
                new HashSet<Move>(                                                                          	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Bite(),
                                        new Twister(),
                                        new Leer(),
                                        new AquaTail(),
                                        new WaterPulse(),
                                        new ScaryFace(),
                                        new DragonRage(),
                                        new Crunch(),
                                        new DragonDance(),
                                        new Hurricane(),
                                        new IceFang(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Scald(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Earthquake(),
                                        new FireBlast(),
                                        new Flamethrower(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                        new ThunderWave(),
                                        new DragonTail(),
                                        new DarkPulse(),
                                        new Splash(),
                                        new Surf(),
                                        new Haze(),
                                        new WaterSprout(),
                                        new BodySlam(),
                                        new AquaTail(),
                                        new MudShot(),
                                        new MudSlap(),
                                        new Psybeam(),
                                        new SignalBeam(),

                                }
                                )
                        )
                );
    }

}
