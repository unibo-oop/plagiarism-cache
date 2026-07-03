package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.LightningRod;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Seaking extends Pokemon {

    public Seaking(int level) {
        super(level,
                80,		                                                                              		//hp
                92,		                                                                              		//atk
                65,		                                                                              		//def
                68,		                                                                              		//speed
                65,		                                                                              		//spa
                80,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new LightningRod(), new SwiftSwim()}),                           //ability
                40,	                                                                                    		//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              		//gender
                new HashSet<Move>(                                                                              	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new TailWhip(),
                                        new Supersonic(),
                                        new HornAttack(),
                                        new WaterPulse(),
                                        new FuryAttack(),
                                        new HornDrill(),
                                        new Megahorn(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Scald(),
                                        new PoisonJab(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
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
