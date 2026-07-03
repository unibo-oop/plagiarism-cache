package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.Adaptability;
import abilities.statusalterationcondition.Oblivious;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.Hypnosis;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Splash;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Feebas extends Pokemon {

    public Feebas(int level) {
        super(level,
                20,		                                                                              	//hp
                15,		                                                                              	//atk
                20,		                                                                              	//def
                80,		                                                                              	//speed
                10,		                                                                              	//spa
                55,		                                                                              	//spd
                new Type[]{new Water(), null},		                                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new Oblivious(), new Adaptability()}),	//ability
                7,	                                                                                    	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	//gender
                new HashSet<Move>(                                                                          	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Splash(),
                                        new Tackle(),
                                        new Flail(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
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
                                        new Haze(),
                                        new Flail(),
                                        new MudShot(),
                                        new ConfuseRay(),
                                        new DragonPulse(),
                                        new DragonBreath(),
                                        new IronTail(),
                                        new Hypnosis(),
                                        new MirrorCoat()
                                }
                                )
                        )
                );
    }

}
