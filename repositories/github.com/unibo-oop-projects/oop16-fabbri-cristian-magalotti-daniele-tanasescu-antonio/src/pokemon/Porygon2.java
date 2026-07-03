package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Download;
import abilities.movecondition.Analytic;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.TriAttack;
import moves.damagingmove.special.ZapCannon;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Conversion2;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.Sharpen;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Porygon2 extends Pokemon{

    public Porygon2(int level) {
        super(  level,                                                                                          //level
                85,                                                                                            	//baseHP 
                80,                                                                                             //baseAtk 
                90,                                                                                             //baseDef 
                60,                                                                                             //baseSpe
                105,                                                                                            //baseSpA 
                95,                                                                                            	//baseSpD 
                new Type[]{new Normal(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new Download(), new Analytic()}),	                //ability                                      
                32.5,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Conversion2(),
                                        /*new Conversion(),*/
                                        new Sharpen(),
                                        new DefenseCurl(),
                                        new Psybeam(),
                                        new Agility(),
                                        new Recover(),
                                        new SignalBeam(),
                                        /*new Discharge(),*/
                                        new TriAttack(),
                                        new ZapCannon(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Psychic(),
                                        new AerialAce(),
                                        new ThunderWave(),
                                        new DreamEater(),
                                        new PsychUp(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Normal(), null}),
                                        new Detect(),
                                }

                                )
                        )
                );
    }

}
