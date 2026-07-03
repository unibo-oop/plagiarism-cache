package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.movecondition.Sniper;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.GunkShot;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Octazooka;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Octillery extends Pokemon {

    public Octillery(int level) {
        super(level,
                75,		                                                                              		//hp
                105,		                                                                              		//atk
                75,		                                                                              		//def
                45,		                                                                              		//speed
                105,		                                                                              		//spa
                75,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new Hustle(), new Sniper()}),  					//ability
                28,	                                                                                    		//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              		//gender
                new HashSet<Move>(                                                                          	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterGun(),
                                        new AuroraBeam(),
                                        new BubbleBeam(),
                                        new BulletSeed(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new RainDance(),
                                        new GunkShot(),
                                        new RockBlast(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new SludgeBomb(),
                                        new EnergyBall(),
                                        new FlashCannon(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
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
                                        new WaterSprout(),
                                        new Flail(),
                                        new MudShot(),
                                        new Psybeam(),
                                        new SignalBeam(),
                                        new Octazooka(),
                                        new Screech(),
                                        new Supersonic()
                                }
                                )
                        )
                );
    }

}
