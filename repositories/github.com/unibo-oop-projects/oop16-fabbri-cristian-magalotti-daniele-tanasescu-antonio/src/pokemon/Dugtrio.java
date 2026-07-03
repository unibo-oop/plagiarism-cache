package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.alwaysactive.ArenaTrap;
import abilities.weathercondition.SandForce;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.TriAttack;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Memento;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Type;

public class Dugtrio extends Pokemon {

    public Dugtrio(int level) {
        super(level,
                35,		                                                                           		//hp
                80,		                                                                           		//atk
                50,		                                                                           		//def
                120,		                                                                           		//speed
                50,		                                                                           		//spa
                70,		                                                                           		//spd
                new Type[]{new Ground(), null},		                                                   		//tipo
                Ability.getRandomAbility(new Ability[]{new SandVeil(), new ArenaTrap(), new SandForce()}),	 	//ability
                33.3,	                                                                                   		//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                           		//gender
                new HashSet<Move>(                                                                         		//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new TriAttack(),
                                        new Scratch(),
                                        new SandAttack(),
                                        new Growl(),
                                        new Astonish(),
                                        new MudSlap(),
                                        new Earthquake(),
                                        new EarthPower(),
                                        new Slash(),
                                        new FeintAttack(),
                                        new Fissure(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new AncientPower(),
                                        new FeintAttack(),
                                        new Headbutt(),
                                        new Memento(),
                                        new Pursuit(),
                                        new Reversal(),
                                        new Screech()
                                }
                                )
                        )
                );
    }

}
