package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.statusalterationcondition.Oblivious;
import abilities.weathercondition.Hydration;
import moves.Move;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Magnitude;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Type;
import types.Water;

public class Barboach extends Pokemon {

    public Barboach(int level) {
        super(level,
                50,		                                                                              	//hp
                48,		                                                                              	//atk
                43,		                                                                              	//def
                60,		                                                                              	//speed
                46,		                                                                              	//spa
                41,		                                                                              	//spd
                new Type[]{new Water(), new Ground()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Oblivious(), new Hydration()}),  			//ability
                2,	                                                                                    	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	//gender
                new HashSet<Move>(                                                                          	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterGun(),
                                        new Amnesia(),
                                        new Magnitude(),
                                        new AquaTail(),
                                        new Fissure(),
                                        new Earthquake(),
                                        new Bulldoze(),
                                        new RockTomb(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Scald(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Surf(),
                                        new Flail(),
                                        new MudShot(),
                                        new DragonDance(),
                                        new EarthPower(),
                                        new Flail(),
                                        new HydroPump(),
                                        new Spark(),
                                        new TakeDown(),

                                }
                                )
                        )
                );
    }

}
