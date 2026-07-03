package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.endofturnconditionability.Moody;
import abilities.movecondition.TangledFeet;
import abilities.otherconditions.OwnTempo;
import abilities.statisticsalterationondemand.Contrary;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.ChirpChirpPunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.WaterPulse;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Hypnosis;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Psychic;
import types.Type;

public class Spinda extends Pokemon{

    public Spinda(int level) {
        super(  level,                                                                                          //level
                60,                                                                                            	//baseHP 
                60,                                                                                             //baseAtk 
                60,                                                                                             //baseDef 
                60,                                                                                            	//baseSpe
                60,                                                                                            	//baseSpA 
                60,                                                                                            	//baseSpD 
                new Type[]{new Psychic(), null},                                                                //type
                Ability.getRandomAbility(new Ability[]{new OwnTempo(), new TangledFeet(), new Contrary(),
                                                       new Moody()}),                                           //ability                                      
                70,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new FeintAttack(),
                                        new Hypnosis(),
                                        new ChirpChirpPunch(),
                                        new DoubleEdge(),
                                        new PsychUp(),
                                        new FakeOut(),
                                        new FakeTears(),
                                        new IcyWind(),
                                        new WaterPulse(),
                                        new BrickBreak(),
                                        new RockTomb(),
                                        new WildCharge(),
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
