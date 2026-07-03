package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.hpcondition.Torrent;
import abilities.movecondition.SheerForce;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.Block;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.FakeTears;
import moves.status.Flatter;
import moves.status.Hail;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.Screech;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Totodile extends Pokemon {

    public Totodile(int level) {
        super(level,
                50,		                                                              	//hp
                65,		                                                              	//atk
                64,		                                                              	//def
                43,		                                                              	//speed
                44,		                                                              	//spa
                48,		                                                              	//spd
                new Type[]{new Water(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new Torrent(), new SheerForce()}),     	//ability
                9.5,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                //gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Leer(),
                                        new WaterGun(),
                                        new Rage(),
                                        new Bite(),
                                        new ScaryFace(),
                                        new IceFang(),
                                        new Flail(),
                                        new Crunch(),
                                        new Slash(),
                                        new Screech(),
                                        new AquaTail(),
                                        new Superpower(),
                                        new HydroPump(),
                                        new DragonClaw(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Scald(),
                                        new ShadowClaw(),
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new AncientPower(),
                                        new AquaJet(),
                                        new Block(),
                                        new DragonDance(),
                                        new FakeTears(),
                                        new Flatter(),
                                        new IcePunch(),
                                        new MetalClaw(),
                                        new WaterPulse(),
                                        new WaterSprout()
                                }
                                )
                        )
                );
    }

}
