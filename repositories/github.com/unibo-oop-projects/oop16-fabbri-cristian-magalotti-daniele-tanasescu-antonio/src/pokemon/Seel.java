package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.weathercondition.Hydration;
import abilities.weathercondition.IceBody;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Seel extends Pokemon {

    public Seel(int level) {
        super(level,
                65,		                                                                              		//hp
                45,		                                                                              		//atk
                55,		                                                                              		//def
                45,		                                                                              		//speed
                45,		                                                                              		//spa
                70,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new Hydration(), new IceBody()}),  		                //ability
                90,	                                                                                    		//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              		//gender
                new HashSet<Move>(                                                                          	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Headbutt(),
                                        new Growl(),
                                        new IcyWind(),
                                        /*new IceShard(),*/
                                        new WaterPulse(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new AuroraBeam(),
                                        new AquaJet(),
                                        new TakeDown(),
                                        new AquaTail(),
                                        new IronTail(),
                                        new FakeOut(),
                                        new HornDrill(),
                                        new Lick(),
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
                                        new Surf(),
                                        new Waterfall(),
                                        new Haze(),
                                        new WaterSprout(),
                                        new BodySlam(),
                                        new SignalBeam(),

                                }
                                )
                        )
                );
    }

}
