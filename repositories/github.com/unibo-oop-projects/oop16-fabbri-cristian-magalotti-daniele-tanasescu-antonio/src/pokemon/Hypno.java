package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Forewarn;
import abilities.otherconditions.InnerFocus;
import abilities.otherconditions.Insomnia;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Assist;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Flatter;
import moves.status.Hypnosis;
import moves.status.Meditate;
import moves.status.NastyPlot;
import moves.status.PoisonGas;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.RolePlay;
import moves.status.SkillSwap;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;

public class Hypno extends Pokemon {

	public Hypno(int level) {
		super(level,
                85,		                                                              			//hp
                73,		                                                              			//atk
                70,		                                                              			//def
                67,		                                                              			//speed
                73,		                                                              			//spa
                115,		                                                              			//spd
                new Type[]{new types.Psychic(), null},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Insomnia(), new Forewarn(), 
                				      new InnerFocus()}),     				        //ability
                76.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Hypnosis(),
                                        new Confusion(),
                                        new Headbutt(),
                                        new PoisonGas(),
                                        new Meditate(),
                                        new Psybeam(),
                                        new PsychUp(),
                                        new ZenHeadbutt(),
                                        new NastyPlot(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new ThunderWave(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Assist(),
                                        new FirePunch(),
                                        new Flatter(),
                                        new IcePunch(),
                                        new RolePlay(),
                                        new SkillSwap(),
                                        new ThunderPunch()
                                }
                        )
                )
        );
	}

}
