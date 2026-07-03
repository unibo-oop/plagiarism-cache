package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.MagicBounce;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.Charm;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hypnosis;
import moves.status.MeanLook;
import moves.status.Memento;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SkillSwap;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Bug;
import types.Fairy;
import types.Psychic;
import types.Type;

public class Ralts extends Pokemon {

	public Ralts(int level) {
		super(level,
                28,		                                                              			//hp
                25,		                                                              			//atk
                25,		                                                              			//def
                40,		                                                              			//speed
                45,		                                                              			//spa
                35,		                                                              			//spd
                new Type[]{new Psychic(), new Fairy()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new MagicBounce()}),     	           	        //ability
                6.6,	                                                                                      	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                      		//gender
                new HashSet<Move>(                                                                      	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(),
                                        new Confusion(),
                                        new DoubleTeam(),
                                        new DisarmingVoice(),
                                        new MagicalLeaf(),
                                        new CalmMind(),
                                        new moves.damagingmove.special.Psychic(),
                                        new Charm(),
                                        new Hypnosis(),
                                        new DreamEater(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new WillOWisp(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new ConfuseRay(),
                                        new MeanLook(),
                                        new Memento(),
                                        new SkillSwap()
                                }
                        )
                )
        );
	}

}
