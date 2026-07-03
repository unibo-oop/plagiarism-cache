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

public class Staryu extends Pokemon {

    public Staryu(int level) {
        super(level,
                30,		                                                                              		//hp
                45,		                                                                              		//atk
                55,		                                                                              		//def
                85,		                                                                              		//speed
                70,		                                                                              		//spa
                55,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Damp(), new SwiftSwim()}),                //ability
                34.4,	                                                                                                //weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	        //gender
                new HashSet<Move>(                                                                                      //learnable moves
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
                                }
                                )
                        )
                );
    }

}
