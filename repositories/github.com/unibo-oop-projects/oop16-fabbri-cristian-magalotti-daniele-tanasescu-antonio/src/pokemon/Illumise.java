package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Prankster;
import abilities.statusalterationcondition.Oblivious;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SilverWind;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Growth;
import moves.status.Moonlight;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Type;

public class Illumise extends Pokemon {

	public Illumise(int level) {
		super(level,
                45,		                                                              			//hp
                67,		                                                              			//atk
                55,		                                                              			//def
                85,		                                                              			//speed
                73,		                                                              			//spa
                75,		                                                              			//spd
                new Type[]{new Bug(), null},		                                        		//tipo
                Ability.getRandomAbility(new Ability[]{new Oblivious(), new Prankster()}),                      //ability
                18,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Charm(),
                                        new QuickAttack(),
                                        new Moonlight(),
                                        new ZenHeadbutt(),
                                        //new BugBuzz(),
                                        new PlayRough(),
                                        new StringShot(),
                                        new SilverWind(),
                                        new Growth(),
                                        new ConfuseRay(),
                                        new FakeTears(),
                                        new Toxic(),
                                        new Protect(),
                                        new SunnyDay(),
                                        new RainDance(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new BrickBreak(),
                                        new Roost(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DazzlingGleam(),
                                        new Acrobatics(),
                                        new Swagger()
                                }
                        )
                )
        );
	}

}
