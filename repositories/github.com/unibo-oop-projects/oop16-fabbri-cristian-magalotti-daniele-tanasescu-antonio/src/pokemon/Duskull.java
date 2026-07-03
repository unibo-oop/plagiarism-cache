package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.MeanLook;
import moves.status.Memento;
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

public class Duskull extends Pokemon {

	public Duskull(int level) {
		super(level,
                20,		                                                              			//hp
                40,		                                                              			//atk
                90,		                                                              			//def
                25,		                                                              			//speed
                30,		                                                              			//spa
                95,		                                                              			//spd
                new Type[]{new Ghost(), null},					                                //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),     					//ability
                15,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Lick(),
                                        new Leer(),
                                        //new ShadowSneak(),
                                        new Astonish(),
                                        new Growl(),
                                        new Pursuit(),
                                        new CalmMind(),
                                        new AerialAce(),
                                        new Spite(),
                                        new MeanLook(),
                                        new Curse(new Type[]{new Ghost(), null}),
                                        new ConfuseRay(),
                                        new ShadowBall(),
                                        new DarkPulse(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new WillOWisp(),
                                        new Memento(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Haze(),
                                        new ScaryFace(),
                                }
                        )
                )
        );
	}

}
