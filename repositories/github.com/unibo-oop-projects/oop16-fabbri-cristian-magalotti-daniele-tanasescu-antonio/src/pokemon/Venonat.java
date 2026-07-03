package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.CompoundEyes;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.MorningSun;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SkillSwap;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Poison;
import types.Type;

public class Venonat extends Pokemon {

    public Venonat(int level) {
        super(level,
                60,		                                                              			//hp
                55,		                                                              			//atk
                50,		                                                              			//def
                45,		                                                              			//speed
                40,		                                                              			//spa
                55,		                                                              			//spd
                new Type[]{new Bug(), new Poison()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new CompoundEyes(),/* new TintedLens(),*/
                                                        new RunAway()}),     				        //ability
                30,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                 		//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Supersonic(),
                                        new Confusion(),
                                        new PoisonPowder(),
                                        new Psybeam(),
                                        new StunSpore(),
                                        new SignalBeam(),
                                        new SleepPowder(),
                                        new LeechLife(),
                                        new ZenHeadbutt(),
                                        new PoisonFang(),
                                        new Psychic(),
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
                                        new Swagger(),
                                        new Agility(),
                                        new BugBite(),
                                        new GigaDrain(),
                                        new MorningSun(),
                                        new Screech(),
                                        new SkillSwap()
                                }
                                )
                        )
                );
    }

}
