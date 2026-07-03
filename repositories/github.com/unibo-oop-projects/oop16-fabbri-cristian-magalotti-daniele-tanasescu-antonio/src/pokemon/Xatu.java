package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.otherconditions.MagicBounce;
import abilities.statusalterationcondition.EarlyBird;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SkillSwap;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Psychic;
import types.Type;

public class Xatu extends Pokemon {

	public Xatu(int level) {
		super(level,
                65,		                                                              			//hp
                75,		                                                              			//atk
                70,		                                                              			//def
                95,		                                                              			//speed
                95,		                                                              			//spa
                70,		                                                              			//spd
                new Type[]{new Psychic(), new Flying()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{/*new Synchronize(),*/ new EarlyBird(),
                				       new MagicBounce() }),     				//ability
                15,	                                                                      		        //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                		new AirSlash(),
                                        new Peck(),
                                        new Leer(),
                                        new ConfuseRay(),
                                        new moves.damagingmove.special.Psychic(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new DrillPeck(),
                                        new FeatherDance(),
                                        new FeintAttack(),
                                        new Haze(),
                                        new QuickAttack(),
                                        new Refresh(),
                                        new SkillSwap(),
                                        new ZenHeadbutt()
                                }
                        )
                )
        );
	}

}
