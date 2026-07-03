package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.LeafGuard;
import abilities.switchcondition.Regenerator;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.Constrict;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.VineWhip;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growth;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.PsychUp;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;

public class Tangela extends Pokemon {

    public Tangela(int level) {
        super(level,
                65,		                                                              		//hp
                55,		                                                              		//atk
                115,		                                                              		//def
                60,		                                                              		//speed
                100,		                                                              		//spa
                40,		                                                              		//spd
                new Type[]{new Grass(), null},		                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll(), new LeafGuard(), 
                                                       new Regenerator()}),     			//ability
                35,	                                                                      		//weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                            	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Constrict(),
                                        new SleepPowder(),
                                        new VineWhip(),
                                        new Absorb(),
                                        new PoisonPowder(),
                                        new Growth(),
                                        new MegaDrain(),
                                        new KnockOff(),
                                        new StunSpore(),
                                        new GigaDrain(),
                                        new AncientPower(),
                                        new Slam(),
                                        new Tickle(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new Amnesia(),
                                        new Confusion(),
                                        new Endeavor(),
                                        new Flail()
                                }
                                )
                        )
                );
    }

}
