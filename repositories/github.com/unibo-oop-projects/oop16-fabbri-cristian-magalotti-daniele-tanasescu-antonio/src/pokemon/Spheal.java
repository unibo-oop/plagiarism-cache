package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.statusalterationcondition.Oblivious;
import abilities.weathercondition.IceBody;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.PowderSnow;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.onehitko.SheerCold;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ice;
import types.Type;
import types.Water;

public class Spheal extends Pokemon {

    public Spheal(int level) {
        super(level,
                70,		                                                                              			//hp
                40,		                                                                              			//atk
                50,		                                                                              			//def
                25,		                                                                              			//speed
                55,		                                                                              			//spa
                50,		                                                                              			//spd
                new Type[]{new Water(), new Ice()},		                                                            	//tipo
                Ability.getRandomAbility(new Ability[]{new Oblivious(), new IceBody()}),  		                        //ability
                40,	                                                                                    		        //weight(kg)
                1,                                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	               	//gender
                new HashSet<Move>(                                                                          	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Headbutt(),
                                        new DefenseCurl(),
                                        new PowderSnow(),
                                        new SheerCold(),
                                        new Growl(),
                                        new IcyWind(),
                                        //new IceShard(),
                                        new WaterPulse(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new AuroraBeam(),
                                        new AquaJet(),
                                        new TakeDown(),
                                        new AquaTail(),
                                        new IronTail(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new RockSlide(),
                                        new RockTomb(),
                                        new Earthquake(),
                                        new BellyDrum(),
                                        new Curse(new Type[]{new Water(), new Ice()}),
                                        new Fissure(),
                                        new SignalBeam(),
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
                                        new BodySlam(),

                                }
                                )
                        )
                );
    }

}
