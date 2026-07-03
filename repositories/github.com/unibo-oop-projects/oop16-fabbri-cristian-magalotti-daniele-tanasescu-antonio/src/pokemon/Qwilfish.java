package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.PoisonPoint;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Poison;
import types.Type;
import types.Water;

public class Qwilfish extends Pokemon {

    public Qwilfish(int level) {
        super(level,
                65,		                                                                              			//hp
                95,		                                                                              			//atk
                85,		                                                                              			//def
                85,		                                                                              			//speed
                55,		                                                                              			//spa
                55,		                                                                              			//spd
                new Type[]{new Water(), new Poison()},                                                    		        //tipo
                Ability.getRandomAbility(new Ability[]{new PoisonPoint(), new SwiftSwim(), new Intimidate()}),	                //ability
                4,	                                                                                    		        //weight(kg)
                1,                                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              		        //gender
                new HashSet<Move>(                                                                          	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterGun(),
                                        new BubbleBeam(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new PoisonSting(),
                                        new Tackle(),
                                        new Revenge(),
                                        new PinMissile(),
                                        new AquaTail(),
                                        new PoisonJab(),
                                        /*new VenoShock(),*/
                                        new SludgeBomb(),
                                        new ShadowBall(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Psychic(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Scald(),
                                        new ThunderWave(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Haze(),
                                        new AquaJet(),
                                        new Flail(),
                                        new SignalBeam(),
                                        new Supersonic(),
                                }
                                )
                        )
                );
    }

}

