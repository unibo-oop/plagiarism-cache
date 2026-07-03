package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import abilities.otherconditions.MagicGuard;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Kinesis;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
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

public class Alakazam extends Pokemon {

    public Alakazam(int level) {
        super(level,
                55,		                                                              		//hp
                50,		                                                              		//atk
                45,		                                                              		//def
                120,		                                                              		//speed
                135,		                                                              		//spa
                85,		                                                              		//spd
                new Type[]{new Psychic(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new InnerFocus(), new MagicGuard()}),    	//ability
                48,	                                                                      		//weight(kg)
                0.9,                                                                                    //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                            	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Kinesis(),
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
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new FirePunch(),
                                        new IcePunch(),
                                        new KnockOff(),
                                        new SkillSwap(),
                                        new ThunderPunch(),
                                        new RolePlay()
                                }
                                )
                        )
                );
    }

}
