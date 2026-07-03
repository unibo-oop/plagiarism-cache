package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import abilities.otherconditions.InnerFocus;
import abilities.otherconditions.MagicGuard;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.CosmicPower;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.HealBell;
import moves.status.Hypnosis;
import moves.status.Kinesis;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.RolePlay;
import moves.status.SkillSwap;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Psychic;
import types.Type;

public class Chimecho extends Pokemon {

    public Chimecho(int level) {
        super(level,
                75,		                                                              			  //hp
                50,		                                                              			  //atk
                80,		                                                              			  //def
                65,		                                                              		  	  //speed
                95,		                                                              		  	  //spa
                90,		                                                              			  //spd
                new Type[]{new Psychic(), null},		                                                  //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),     				          //ability
                1,	                                                                      	  	          //weight(kg)
                1,                                                                                                //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		          //gender
                new HashSet<Move>(                                                            	                  //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(),
                                        new Astonish(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Recover(),
                                        new Refresh(),
                                        new HealBell(),
                                        new Confusion(),
                                        new Psybeam(),
                                        new Recover(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new moves.damagingmove.special.Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        //new GrassKnot(),
                                        new DazzlingGleam(),
                                        new CosmicPower(),
                                        new Curse(new Type[]{new Psychic(), null}),
                                        new Hypnosis(),
                                }
                                )
                        )
                );
    }

}
