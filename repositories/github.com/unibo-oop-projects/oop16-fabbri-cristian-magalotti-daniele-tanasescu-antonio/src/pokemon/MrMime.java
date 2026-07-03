package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Technician;
import abilities.movecondition.Filter;
import abilities.movecondition.WaterAbsorb;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hypnosis;
import moves.status.Meditate;
import moves.status.NastyPlot;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;

public class MrMime extends Pokemon {

	public MrMime(int level) {
		super(level,
                40,		                                                              			//hp
                45,		                                                              			//atk
                65,		                                                              			//def
                90,		                                                              			//speed
                100,	                                                              			        //spa
                120,	                                                              			        //spd
                new Type[]{new types.Psychic(), null},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Filter(), new Technician()}),     		        //ability
                54.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Hypnosis(),
                                        new Confusion(),
                                        /*new MistyTerrain(),
                                        new Barrier(),*/
                                        new Meditate(),
                                        new Psybeam(),
                                        new PsychUp(),
                                        new DoubleSlap(),
                                        new MagicalLeaf(),
                                        new NastyPlot(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new PsychUp(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                       /* new PsyShock(),*/
                                        new FocusBlast(),
                                        new ThunderWave(),
                                        new DreamEater(),
                                        new DazzlingGleam(),
                                        new Swagger(),
                                        new FakeOut(),
                                        new ConfuseRay(),
                                        new IcyWind(),
                                        
                                }
                        )
                )
        );
	}

}
