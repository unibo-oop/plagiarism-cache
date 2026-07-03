package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.weathercondition.Hydration;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Luvdisc extends Pokemon {

    public Luvdisc(int level) {
        super(level,
                43,		                                                                              		//hp
                30,		                                                                              		//atk
                55,		                                                                              		//def
                40,		                                                                              		//speed
                65,		                                                                              		//spa
                97,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new Hydration(), new SwiftSwim()}),				//ability
                8,	                                                                                    		//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              		//gender
                new HashSet<Move>(                                                                          	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Charm(),
                                        new WaterGun(),
                                        new Agility(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new SweetKiss(),
                                        new TakeDown(),
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
                                        new Thief(),
                                        new Swagger(),
                                        new AquaJet(),
                                        new Surf(),
                                        new BodySlam(),
                                        new MudShot(),

                                }
                                )
                        )
                );
    }

}
