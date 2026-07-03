package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Drought;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Howl;
import moves.status.Hypnosis;
import moves.status.PsychUp;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Spite;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Type;

public class Ninetales extends Pokemon {

	public Ninetales(int level) {
		super(level,
                73,		                                                              	//hp
                76,		                                                              	//atk
                75,		                                                              	//def
                100,		                                                                //speed
                81,		                                                             	//spa
                100,		                                                              	//spd
                new Type[]{new Fire(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new Drought()}),     	                //ability
                19.9,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Ember(),
                                        new TailWhip(),
                                        new Roar(),
                                        new QuickAttack(),
                                        new ConfuseRay(),
                                        new CalmMind(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Overheat(),
                                        new EnergyBall(),
                                        new WillOWisp(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Extrasensory(),
                                        new FeintAttack(),
                                        new Flail(),
                                        new HeatWave(),
                                        new Howl(),
                                        new Hypnosis(),
                                        new Spite(),
                                        new TailWhip()
                                }
                        )
                )
        );
	}

}
