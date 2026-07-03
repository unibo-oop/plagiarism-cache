package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.otherconditions.MagicBounce;
import abilities.otherconditions.MagicGuard;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.Moonblast;
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
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Psychic;
import types.Type;

public class Gardevoir extends Pokemon {

	public Gardevoir(int level) {
		super(level,
                68,		                                                              	//hp
                65,		                                                              	//atk
                65,		                                                              	//def
                80,		                                                              	//speed
                125,		                                                              	//spa
                125,		                                                              	//spd
                new Type[]{new Psychic(), new Fairy()},		                                //tipo
                Ability.getRandomAbility(new Ability[]{new MagicGuard(), new MagicBounce()}),   //ability
                48.4,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                	new Moonblast(),
                                        new Growl(),
                                        new Confusion(),
                                        new DoubleTeam(),
                                        new DisarmingVoice(),
                                        new MagicalLeaf(),
                                        new CalmMind(),
                                        new moves.damagingmove.special.Psychic(),
                                        new Hypnosis(),
                                        new DreamEater(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new WillOWisp(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new ConfuseRay(),
                                        new MeanLook(),
                                        new Memento(),
                                        new SkillSwap(),
                                        new Charm()
                                }
                        )
                )
        );
	}

}
