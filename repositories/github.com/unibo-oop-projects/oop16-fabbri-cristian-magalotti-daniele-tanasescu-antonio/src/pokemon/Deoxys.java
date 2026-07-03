package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Pressure;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.ExtremeSpeed;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.RockThrow;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.PsychoBoost;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ZapCannon;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Agility;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.CosmicPower;
import moves.status.DoubleTeam;
import moves.status.IronDefense;
import moves.status.Meditate;
import moves.status.NastyPlot;
import moves.status.PoisonGas;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;

public class Deoxys extends Pokemon {

	public Deoxys(int level) {
		super(level,
                50,		                                                              	//hp
                150,		                                                          	//atk
                50,		                                                              	//def
                150,		                                                                //speed
                150,		                                                              	//spa
                50,		                                                              	//spd
                new Type[]{new types.Psychic(), null},		                                //tipo
                Ability.getRandomAbility(new Ability[]{new Pressure()}),     			//ability
                60.6,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,	                                              		//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Confusion(),
                                        new KnockOff(),
                                        new Pursuit(),
                                        new Recover(),
                                        new PsychoBoost(),
                                        new Headbutt(),
                                        new PoisonGas(),
                                        new ZapCannon(),
                                        new CosmicPower(),
                                        new Amnesia(),
                                        new IronDefense(),
                                        new ExtremeSpeed(),
                                        new Agility(),
                                        new Meditate(),
                                        new Psybeam(),
                                        new PsychUp(),
                                        new ZenHeadbutt(),
                                        new IceBeam(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new RockThrow(),
                                        new RockTomb(),
                                        new EnergyBall(),
                                        new AerialAce(),
                                        new PoisonJab(),
                                        new FlashCannon(),
                                        new DarkPulse(),
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
                                }
                        )
                )
        );
	}

}
