package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.endofturnconditionability.SpeedBoost;
import abilities.movecondition.RoughSkin;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dark;
import types.Type;
import types.Water;

public class Sharpedo extends Pokemon {

    public Sharpedo(int level) {
        super(level,
                70,		                                                                              	//hp
                120,		                                                                              	//atk
                40,		                                                                              	//def
                95,		                                                                              	//speed
                95,		                                                                              	//spa
                40,		                                                                              	//spd
                new Type[]{new Water(), new Dark()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new RoughSkin(), new SpeedBoost()}),  			//ability
                88,	                                                                                    	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	//gender
                new HashSet<Move>(                                                                         	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),
                                        //NightSlash(),
                                        new Bite(),
                                        new Rage(),
                                        new WaterGun(),
                                        new AquaJet(),
                                        new Screech(),
                                        new IceFang(),
                                        new PoisonFang(),
                                        new Crunch(),
                                        new Agility(),
                                        new TakeDown(),
                                        new Waterfall(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Thief(),
                                        //new Snarl(),
                                        new DarkPulse(),
                                        new AncientPower(),
                                        new DoubleEdge(),
                                        //new PsychicFangs(),
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

                                }
                                )
                        )
                );
    }

}
