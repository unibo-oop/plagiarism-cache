package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Forewarn;
import abilities.movecondition.DrySkin;
import abilities.otherconditions.InnerFocus;
import abilities.otherconditions.Insomnia;
import abilities.statusalterationcondition.Oblivious;
import abilities.weathercondition.Hydration;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.PowderSnow;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Hail;
import moves.status.Hypnosis;
import moves.status.LovelyKiss;
import moves.status.Meditate;
import moves.status.NastyPlot;
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

public class Smoochum extends Pokemon {

	public Smoochum(int level) {
		super(level,
                45,		                                                              				//hp
                30,		                                                              				//atk
                15,		                                                              				//def
                85,		                                                              				//speed
                65,		                                                              				//spa
                85,		                                                              				//spd
                new Type[]{new types.Psychic(), null},		                                    			//tipo
                Ability.getRandomAbility(new Ability[]{new Oblivious(), new Forewarn(), new Hydration()}),	        //ability
                40.6,	                                                                      				//weight(kg)
                0.9,                                                                                                    //miniFrontSizeScale
                Gender.FEMALE,	                                         			     			//gender
                new HashSet<Move>(                                                            				//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Hypnosis(),
                                        new Lick(),
                                        new LovelyKiss(),
                                        new PowderSnow(),
                                        new DoubleSlap(),
                                        new FakeTears(),
                                        new BodySlam(),
                                        new Blizzard(),
                                        new Confusion(),
                                        new PsychUp(),
                                        new NastyPlot(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new ThunderWave(),
                                        /*new GrassKnot(),*/
                                        new DreamEater(),
                                        new Swagger(),
                                        new FakeOut(),
                                        new Meditate(),
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
