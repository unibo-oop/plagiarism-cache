package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import abilities.movecondition.Mummy;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Smog;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Haze;
import moves.status.MeanLook;
import moves.status.Memento;
import moves.status.NastyPlot;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.Spite;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Ghost;
import types.Type;

public class Misdreavus extends Pokemon {

    public Misdreavus(int level) {
        super(level,
                60,		                                                              			//hp
                60,		                                                              			//atk
                60,		                                                              			//def
                85,		                                                              			//speed
                85,		                                                              			//spa
                85,		                                                              			//spd
                new Type[]{new Ghost(), null},					                                //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate(), new Mummy()}),     			//ability
                1,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Lick(),
                                        new Astonish(),
                                        new Growl(),
                                        new PowerGem(),
                                        new CalmMind(),
                                        new DazzlingGleam(),
                                        new AerialAce(),
                                        new Spite(),
                                        new MeanLook(),
                                        new Curse(new Type[]{new Ghost(), null}),
                                        new ConfuseRay(),
                                        new ShadowBall(),
                                        new DreamEater(),
                                        new DarkPulse(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new WillOWisp(),
                                        new NastyPlot(),
                                        new Memento(),
                                        /*new ShadowSneak(),*/
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Haze(),
                                        new ScaryFace(),
                                        new Smog(),
                                }
                                )
                        )
                );
    }

}
