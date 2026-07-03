package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.FlameBody;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.DrillRun;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.Overheat;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Flying;
import types.Type;

public class Moltres extends Pokemon{

    public Moltres(int level) {
        super(  level,                                                                                          //level
                90,                                                                                             //baseHP 
                100,                                                                                            //baseAtk 
                90,                                                                                             //baseDef 
                90,                                                                                             //baseSpe
                125,                                                                                            //baseSpA 
                85,                                                                                             //baseSpD 
                new Type[]{new Fire(), new Flying()},                                                         	//type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new FlameBody()}),                       //ability             
                60,                                                                                             //weight (Kg)
                0.6,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,		                                                                //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WingAttack(),
                                        new Ember(),
                                        new Leer(),
                                        new Agility(),
                                        new AncientPower(),
                                        new AerialAce(),
                                        new Roost(),
                                        new AirSlash(),
                                        new DrillRun(),
                                        new Facade(),
                                        new Flamethrower(),
                                        new HeatWave(),
                                        new Hurricane(),
                                        new Sandstorm(),
                                        new BraveBird(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        /*new FlameCharge(),*/
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new FalseSwipe(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
