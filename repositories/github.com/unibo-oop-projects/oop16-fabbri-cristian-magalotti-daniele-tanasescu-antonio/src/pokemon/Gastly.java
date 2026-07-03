package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Smog;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Haze;
import moves.status.Hypnosis;
import moves.status.MeanLook;
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
import types.Poison;
import types.Type;

public class Gastly extends Pokemon {

	public Gastly(int level) {
		super(level,
                30,		                                                              			//hp
                35,		                                                              			//atk
                30,		                                                              			//def
                80,		                                                              			//speed
                100,		                                                              		        //spa
                35,		                                                              			//spd
                new Type[]{new Ghost(), new Poison()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),     					//ability
                0.1,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Hypnosis(),
                                        new Lick(),
                                        new Spite(),
                                        new MeanLook(),
                                        new Curse(new Type[]{new Ghost(), new Poison()}),
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
                                        new Explosion(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Astonish(),
                                        new FirePunch(),
                                        new Haze(),
                                        new IcePunch(),
                                        new ScaryFace(),
                                        new Smog(),
                                        new ThunderPunch()
                                }
                        )
                )
        );
	}

}
