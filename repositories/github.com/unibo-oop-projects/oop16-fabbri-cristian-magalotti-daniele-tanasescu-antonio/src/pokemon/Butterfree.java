package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.CompoundEyes;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SilverWind;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.PoisonPowder;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.StringShot;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Type;

public class Butterfree extends Pokemon {

    public Butterfree(int level) {
        super(level,
                60,		                                                                  		//hp
                45,		                                                                  		//atk
                50,		                                                                  		//def
                70,		                                                                  		//speed
                80,		                                                                  		//spa
                80,		                                                                  		//spd
                new Type[]{new Bug(), new Flying()},		                                 	        //tipo,
                Ability.getRandomAbility(new Ability[]{new CompoundEyes()}),                                    //ability
                32,	                                                                          	        //weight(kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                                  	        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Gust(),
                                        new Confusion(),
                                        new PoisonPowder(),
                                        new StunSpore(),
                                        new SleepPowder(),
                                        new Psybeam(),
                                        new SilverWind(),
                                        new Supersonic(),
                                        new Whirlwind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new moves.damagingmove.special.Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Tackle(),
                                        new StringShot(),
                                        new Harden(),                                		
                                }
                                )
                        )
                );
    }

}
