package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.firstturn.Intimidate;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.PoisonTail;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Thief;
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

public class Ekans extends Pokemon{

    public Ekans(int level) {
        super(  level,                                                                                          //level
                35,                                                                                             //baseHP 
                60,                                                                                             //baseAtk 
                44,                                                                                             //baseDef 
                55,                                                                                             //baseSpe
                40,                                                                                             //baseSpA 
                54,                                                                                             //baseSpD 
                new Type[]{new Poison(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new Intimidate(), new ShedSkin()}),                  
                7,                                                                                           	//weight (Kg)
                1.3,                                                                                            //miniFrontSizeScale
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
                                }
                                )
                        )
                );
    }

}
