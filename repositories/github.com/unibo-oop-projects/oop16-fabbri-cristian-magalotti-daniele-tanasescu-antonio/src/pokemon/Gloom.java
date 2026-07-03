package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.Growth;
import moves.status.Moonlight;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Poison;
import types.Type;

public class Gloom extends Pokemon {

    public Gloom(int level) {
        super(level,
                60,		                                                           			//hp
                65,		                                                           			//atk
                70,		                                                           			//def
                45,		                                                           			//speed
                85,		                                                           			//spa
                75,		                                                           			//spd
                new Type[]{new Grass(), new Poison()},		                           		        //tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll()/*, new Stench()*/}),  	                //ability
                8.6,	                                                                   	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                           		        //gender
                new HashSet<Move>(                                                         	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Absorb(),
                                        new Growth(),
                                        new SweetScent(),
                                        new Acid(),
                                        new PoisonPowder(),
                                        new StunSpore(),
                                        new SleepPowder(),
                                        new MegaDrain(),
                                        new Moonlight(),
                                        new GigaDrain(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new Charm(),
                                        new Flail(),
                                        new RazorLeaf(),
                                        new Synthesis(),
                                        new Tickle(),
                                        new Moonblast()

                                }
                                )
                        )
                );
    }

}
