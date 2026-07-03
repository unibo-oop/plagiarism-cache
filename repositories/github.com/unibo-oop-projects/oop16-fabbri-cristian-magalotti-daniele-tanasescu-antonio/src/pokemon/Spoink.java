package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.OwnTempo;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SkillSwap;
import moves.status.Splash;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Psychic;
import types.Type;

public class Spoink extends Pokemon{

    public Spoink(int level) {
        super(  level,                                                                                          //level
                60,                                                                                            	//baseHP 
                25,                                                                                             //baseAtk 
                35,                                                                                             //baseDef 
                60,                                                                                             //baseSpe
                70,                                                                                             //baseSpA 
                80,                                                                                            	//baseSpD 
                new Type[]{new Psychic(), null},                                                                //type
                Ability.getRandomAbility(new Ability[]{new OwnTempo()}),      				        //ability                                      
                30,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Splash(),
                                        new ZenHeadbutt(),
                                        new PowerGem(),
                                        new ThunderWave(),
                                        new DreamEater(),
                                        //new GrassKnot(),
                                        new Amnesia(),
                                        new Extrasensory(),
                                        new SkillSwap(),
                                        new MirrorCoat(),
                                        new Whirlwind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Psychic(), null}),
                                        new Detect(),
                                        new ConfuseRay(),
                                        new Confusion(),
                                        new Psybeam(),
                                        new moves.damagingmove.special.Psychic(),
                                        new ShadowBall(),
                                        new DazzlingGleam()
                                }

                                )
                        )
                );
    }

}
