package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Meditate;
import moves.status.Memento;
import moves.status.NastyPlot;
import moves.status.PoisonGas;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dragon;
import types.Type;

public class Latios extends Pokemon {

	public Latios(int level) {
		super(level,
                80,		                                                              	//hp
                90,		                                                              	//atk
                80,		                                                              	//def
                110,		                                                              	//speed
                130,		                                                              	//spa
                110,		                                                              	//spd
                new Type[]{new Dragon(), new types.Psychic()},		                        //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),     			//ability
                60.6,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.MALE,	                                              			//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Memento(),
                                        new DragonBreath(),
                                        new Refresh(),
                                        new Recover(),
                                        new DragonPulse(),
                                        new DragonClaw(),
                                        new IceBeam(),
                                        new Roost(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new SteelWing(),
                                        new Bulldoze(),
                                        new Surf(),
                                        new Waterfall(),
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
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new ThunderWave(),
                                        new Swagger(),
                                }
                        )
                )
        );
	}

}
