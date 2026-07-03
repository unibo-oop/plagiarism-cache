package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.firstturn.Intimidate;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.PoisonTail;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Attract;
import moves.status.Coil;
import moves.status.DoubleTeam;
import moves.status.Glare;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import types.Poison;
import types.Type;

public class Arbok extends Pokemon{

    public Arbok(int level) {
        super(level,                                                                                          	//level
                60,                                                                                             //baseHP 
                95,                                                                                             //baseAtk 
                69,                                                                                             //baseDef 
                80,                                                                                             //baseSpe
                65,                                                                                             //baseSpA 
                79,                                                                                             //baseSpD 
                new Type[]{new Poison(), null},                                                           	//type
                Ability.getRandomAbility(new Ability[]{new Intimidate(), new ShedSkin()}),                  
                65,                                                                                           	//weight (Kg) 
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Glare(),
                                        new Leer(),
                                        new Bite(),
                                        new PoisonSting(),
                                        new Screech(),
                                        new Acid(),
                                        new PoisonJab(),
                                        new Haze(),
                                        new Toxic(),
                                        new Coil(),
                                        new SunnyDay(),
                                        new Earthquake(),
                                        new RainDance(),
                                        new RockTomb(),
                                        new RockSlide(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new DarkPulse(),
                                        new Swagger(),
                                        new IronTail(),
                                        new PoisonTail(),
                                        new PoisonFang(),
                                        new ScaryFace(),
                                        new Slam(),
                                        new Crunch(),
                                        new IceFang(),
                                        new FireFang(),
                                        new ThunderFang(),
                                        new RockSlide(),
                                        new DragonTail(),
                                }
                                )
                        )
                );

    }

}
