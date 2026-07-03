package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Damp;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.Camouflage;
import moves.status.ConfuseRay;
import moves.status.CosmicPower;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Harden;
import moves.status.Minimize;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Starmie extends Pokemon {

    public Starmie(int level) {
        super(level,
                60,		                                                                              	//hp
                75,		                                                                              	//atk
                80,		                                                                              	//def
                115,		                                                                              	//speed
                100,		                                                                              	//spa
                85,		                                                                              	//spd
                new Type[]{new Water(), null},		                                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Damp(), new SwiftSwim()}),        //ability
                80,	                                                                                    	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	//gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Harden(),
                                        new Recover(),
                                        new Swift(),
                                        new Camouflage(),
                                        new WaterGun(),
                                        new RainDance(),
                                        new BubbleBeam(),
                                        new Minimize(),
                                        new PowerGem(),
                                        new ConfuseRay(),
                                        new CosmicPower(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new moves.damagingmove.special.Psychic(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Scald(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Surf(),
                                        new Waterfall(),
                                        new BubbleBeam(),
                                        new Endeavor(),
                                        new DazzlingGleam(),
                                        new FlashCannon(),
                                        /*new GrassKnot(),*/
                                        new DreamEater(),
                                }
                                )
                        )
                );
    }

}
