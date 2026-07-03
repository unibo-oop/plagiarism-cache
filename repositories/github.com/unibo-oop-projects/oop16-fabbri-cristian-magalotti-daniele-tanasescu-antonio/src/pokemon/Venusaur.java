package pokemon;

import java.util.Arrays;
import java.util.HashSet;


import abilities.Ability;
import abilities.hpcondition.Overgrow;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.VineWhip;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.Sludge;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.GrassWhistle;
import moves.status.Growl;
import moves.status.Growth;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SleepPowder;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Poison;
import types.Type;

public class Venusaur extends Pokemon {

    public Venusaur(int level) {
        super(level,
                80,		                                                               		//hp
                82,		                                                               		//atk
                83,		                                                               		//def
                80,		                                                               		//speed
                100,	                                                                       		//spa
                100,	                                                                       		//spd
                new Type[]{new Grass(), new Poison()},			                       		//tipo,
                Ability.getRandomAbility(new Ability[]{new Overgrow(), new Chlorophyll()}),    		//ability
                100,	                                                                       		//weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                               	        //gender
                new HashSet<Move>(                                                             		//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new VineWhip(),
                                        new PoisonPowder(),
                                        new SleepPowder(),
                                        new TakeDown(),
                                        new RazorLeaf(),
                                        new SweetScent(),
                                        new Growth(),
                                        new DoubleEdge(),
                                        new Synthesis(),
                                        new SludgeBomb(),
                                        new EnergyBall(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new Amnesia(),
                                        new Charm(),
                                        new Curse(new Type[]{new Grass(), new Poison()}),
                                        new GigaDrain(),
                                        new GrassWhistle(),
                                        new MagicalLeaf(),
                                        new Sludge()
                                }
                                )
                        ));
    }

}
