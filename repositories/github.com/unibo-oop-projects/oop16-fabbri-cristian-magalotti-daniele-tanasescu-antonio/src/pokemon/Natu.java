package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.MagicBounce;
import abilities.statusalterationcondition.EarlyBird;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.amount.NightShade;
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
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Psychic;
import types.Type;

public class Natu extends Pokemon {

	public Natu(int level) {
		super(level,
                40,		                                                              			//hp
                50,		                                                              			//atk
                45,		                                                              			//def
                70,		                                                              			//speed
                70,		                                                              			//spa
                45,		                                                              			//spd
                new Type[]{new Psychic(), new Flying()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new EarlyBird(),
                				       new MagicBounce() }),     				//ability
                2,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
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
