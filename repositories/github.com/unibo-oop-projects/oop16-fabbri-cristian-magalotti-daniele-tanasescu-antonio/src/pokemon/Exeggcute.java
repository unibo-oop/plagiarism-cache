package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.twotofive.Barrage;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.LeafStorm;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Hypnosis;
import moves.status.Moonlight;
import moves.status.PoisonPowder;
import moves.status.PsychUp;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Psychic;
import types.Type;

public class Exeggcute extends Pokemon {

    public Exeggcute(int level) {
        super(level,
                60,		                                                                  		//hp
                40,		                                                                  		//atk
                80,		                                                                  		//def
                40,		                                                                  		//speed
                60,		                                                                  		//spa
                45,		                                                                  		//spd
                new Type[]{new Grass(), new Psychic()},		                                 	        //tipo,
                Ability.getRandomAbility(new Ability[]{new Chlorophyll()}),  					//ability
                2.5,	                                                                                        //weight(kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                                  	        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Barrage(),
                                        new Confusion(),
                                        new PoisonPowder(),
                                        new StunSpore(),
                                        new SleepPowder(),
                                        new Extrasensory(),
                                        new Hypnosis(),
                                        new BulletSeed(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new moves.damagingmove.special.Psychic(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new Explosion(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new AncientPower(),
                                        new Curse(new Type[]{new Grass(), new Psychic()}),
                                        new GigaDrain(),
                                        /*new GrassyTerrain(),*/
                                        new LeafStorm(),
                                        new Moonlight(),
                                        new Synthesis()
                                }
                                )
                        )
                );
    }

}
