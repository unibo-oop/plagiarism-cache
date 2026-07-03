package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import abilities.weathercondition.IceBody;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.PowderSnow;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.onehitko.SheerCold;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ice;
import types.Type;

public class Glalie extends Pokemon {

    public Glalie(int level) {
        super(level,
                80,		                                                                              	//hp
                80,		                                                                              	//atk
                80,		                                                                              	//def
                80,		                                                                              	//speed
                80,		                                                                              	//spa
                80,		                                                                              	//spd
                new Type[]{new Ice(), null},		                                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new InnerFocus(), new IceBody()}),  			//ability
                255,	                                                                                    	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	//gender
                new HashSet<Move>(                                                                          	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new SheerCold(),
                                        new Bite(),
                                        new Earthquake(),
                                        new Explosion(),
                                        //new GyroBall(),
                                        new Headbutt(),
                                        new Bulldoze(),
                                        new DarkPulse(),
                                        new PowderSnow(),
                                        new Leer(),
                                        new IcyWind(),
                                        //new IceShard(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new IceFang(),
                                        new Crunch(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Haze(),

                                }
                                )
                        )
                );
    }

}
