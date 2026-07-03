package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.hpcondition.Swarm;
import abilities.movecondition.Sniper;
import abilities.otherconditions.Insomnia;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Constrict;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.multistrike.two.Twineedle;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.amount.SonicBoom;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.SpiderWeb;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Poison;
import types.Type;

public class Ariados extends Pokemon {

    public Ariados(int level) {
        super(level,
                70,		                                                              			//hp
                90,		                                                              			//atk
                70,		                                                              			//def
                40,		                                                              			//speed
                60,		                                                              			//spa
                60,		                                                              			//spd
                new Type[]{new Bug(), new Poison()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Swarm(), new Insomnia(),
                                                       new Sniper()}),     					//ability
                33.5,	                                                                      	                //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new SwordsDance(),
                                        new BugBite(),
                                        new PoisonSting(),
                                        new StringShot(),
                                        new Constrict(),
                                        new Absorb(),
                                        new ScaryFace(),
                                        new FurySwipes(),
                                        new SpiderWeb(),
                                        new Agility(),
                                        new PinMissile(),
                                        new Psychic(),
                                        new PoisonJab(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new LeechLife(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new XScissor(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Megahorn(),
                                        new Psybeam(),
                                        new Pursuit(),
                                        new SignalBeam(),
                                        new SonicBoom(),
                                        new Twineedle()
                                }
                                )
                        )
                );
    }

}
