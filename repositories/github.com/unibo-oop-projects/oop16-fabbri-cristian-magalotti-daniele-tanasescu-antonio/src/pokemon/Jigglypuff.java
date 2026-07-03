package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.CuteCharm;
import abilities.statisticsalterationondemand.Competitive;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Present;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Normal;
import types.Type;

public class Jigglypuff extends Pokemon {

	public Jigglypuff(int level) {
		super(level,
                115,		                                                              		        //hp
                45,		                                                              			//atk
                20,		                                                              			//def
                20,		                                                              			//speed
                45,		                                                              			//spa
                25,		                                                              			//spd
                new Type[]{new Normal(), new Fairy()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new CuteCharm(), new Competitive()}),  	                //ability
                5.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Sing(),
                                        new DefenseCurl(),
                                        new Pound(),
                                        new DoubleSlap(),
                                        new Rest(),
                                        new BodySlam(),
                                        new HyperVoice(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new DisarmingVoice(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Attract(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new FakeTears(),
                                        new FeintAttack(),
                                        new Present(),
                                        new Charm(),
                                        new SweetKiss()
                                }
                        )
                )
        );
	}

}
