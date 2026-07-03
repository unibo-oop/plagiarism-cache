package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.AngerPoint;
import abilities.statisticsalterationondemand.Defiant;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KarateChop;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.SmellingSalts;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.Meditate;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fight;
import types.Type;

public class Mankey extends Pokemon {

	public Mankey(int level) {
		super(level,
                40,		                                                              			//hp
                80,		                                                              			//atk
                35,		                                                              			//def
                70,		                                                              			//speed
                35,		                                                              			//spa
                45,		                                                              			//spd
                new Type[]{new Fight(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{/*new VitalSpirit(),*/ new AngerPoint(),
							 new Defiant()}),     					//ability
                28,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	               	//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new LowKick(),
                                        new Leer(),
                                        new FurySwipes(),
                                        new KarateChop(),
                                        new Pursuit(),
                                        new Swagger(),
                                        new CrossChop(),
                                        new Screech(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Overheat(),
                                        new FocusBlast(),
                                        new Acrobatics(),
                                        new RockSlide(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Counter(),
                                        new Meditate(),
                                        new Revenge(),
                                        new Reversal(),
                                        new SmellingSalts()
                                }
                        )
                )
        );
	}

}
