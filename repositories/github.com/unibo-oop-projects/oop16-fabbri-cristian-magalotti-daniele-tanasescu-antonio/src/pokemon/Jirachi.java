package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Forewarn;
import abilities.movecondition.CuteCharm;
import abilities.movecondition.LightningRod;
import abilities.otherconditions.InnerFocus;
import abilities.otherconditions.Insomnia;
import abilities.otherconditions.MagicGuard;
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
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Assist;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.CosmicPower;
import moves.status.DoubleTeam;
import moves.status.Flatter;
import moves.status.Hypnosis;
import moves.status.Meditate;
import moves.status.NastyPlot;
import moves.status.PoisonGas;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.RolePlay;
import moves.status.Sandstorm;
import moves.status.SkillSwap;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;

public class Jirachi extends Pokemon {

	public Jirachi(int level) {
		super(level,
                100,		                                                            		//hp
                100,		                                                             		//atk
                100,		                                                              		//def
                100,		                                                              		//speed
                100,		                                                              		//spa
                100,		                                                              		//spd
                new Type[]{new types.Psychic(), null},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new LightningRod(), new CuteCharm(),
                                                       new MagicGuard()}), 				//ability
                1.1,	                                                                      	        //weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.GENDERLESS,	                                              			//gender
                new HashSet<Move>(                                                              	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Confusion(),
                                        new Headbutt(),
                                        new Swift(),
                                        new Meditate(),
                                        new Psybeam(),
                                        new Refresh(),
                                        new DoubleEdge(),
                                        new CosmicPower(),
                                        new PsychUp(),
                                        new ZenHeadbutt(),
                                        new NastyPlot(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new EnergyBall(),
                                        new Sandstorm(),
                                        new FlashCannon(),
                                        new DazzlingGleam(),
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
                                }
                        )
                )
        );
	}

}
