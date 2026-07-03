package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.firstturn.SandStream;
import abilities.movecondition.WaterAbsorb;
import abilities.statusalterationcondition.Guts;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Outrage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.DarkPulse;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.IronDefense;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.ScaryFace;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Rock;
import types.Type;

public class Tyrannitar extends Pokemon {

    public Tyrannitar(int level) {
        super(level,
                100,		                                                              	//hp
                134,		                                                              	//atk
                110,		                                                                //def
                61,		                                                              	//speed
                95,		                                                              	//spa
                100,		                                                                //spd
                new Type[]{new Rock(), new Ground()},		                                //tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new SandStream()}),	//ability
                200,	                                                                      	//weight(kg)
                0.8,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Bite(),
                                        new Leer(),
                                        new Screech(),
                                        new ScaryFace(),
                                        new DarkPulse(),
                                        new Crunch(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Earthquake(),
                                        new RainDance(),
                                        new Sandstorm(),
                                        new Protect(),
                                        new BrickBreak(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                        new DoubleTeam(),
                                        new RockPolish(),
                                        /*new Snarl(),*/
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new AncientPower(),
                                        new DragonDance(),
                                        new IronDefense(),
                                        /*new IronHead(),*/
                                        new IronTail(),
                                        new Outrage(),
                                        new Pursuit(),
                                        new Stomp(),
                                        new Curse(new Type[]{new Rock(), new Ground()}),
                                        new ThunderFang(),
                                        new IceFang(),
                                        new FireFang(),
                                }
                                )
                        )
                );
    }

}
