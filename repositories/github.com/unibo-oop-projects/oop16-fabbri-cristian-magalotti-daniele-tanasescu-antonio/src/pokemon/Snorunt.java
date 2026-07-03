package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import abilities.weathercondition.IceBody;
import moves.Move;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.PowderSnow;
import moves.damagingmove.special.ShadowBall;
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

public class Snorunt extends Pokemon {

    public Snorunt(int level) {
        super(level,
                50,		                                                                              			//hp
                50,		                                                                              			//atk
                50,		                                                                              			//def
                50,		                                                                              			//speed
                50,		                                                                              			//spa
                50,		                                                                              			//spd
                new Type[]{new Ice(), null},		                                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new InnerFocus(), new IceBody()}),  					//ability
                17,	                                                                                    		        //weight(kg)
                1,                                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              		        //gender
                new HashSet<Move>(                                                                          	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Headbutt(),
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
