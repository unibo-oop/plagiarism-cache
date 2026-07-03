package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.switchcondition.RunAway;
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

public class Oddish extends Pokemon {

    public Oddish(int level) {
        super(level,
                45,		                                                              //hp
                50,		                                                              //atk
                55,		                                                              //def
                30,		                                                              //speed
                75,		                                                              //spa
                65,		                                                              //spd
                new Type[]{new Grass(), new Poison()},		                              //tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll(), new RunAway()}),    //ability
                5.4,	                                                                      //weight(kg)
                1,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              //gender
                new HashSet<Move>(                                                            //learnable moves
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
                                        new Moonblast(),
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
                                        new Tickle()
                                }
                                )
                        )
                );
    }

}
