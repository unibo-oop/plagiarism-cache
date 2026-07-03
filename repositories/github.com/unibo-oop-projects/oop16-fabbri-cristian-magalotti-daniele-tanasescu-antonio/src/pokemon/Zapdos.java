package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.FlameBody;
import abilities.movecondition.LightningRod;
import abilities.movecondition.Static;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.DrillRun;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.ZapCannon;
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
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Fire;
import types.Flying;
import types.Type;

public class Zapdos extends Pokemon{

    public Zapdos(int level) {
        super(  level,                                                                                          //level
                90,                                                                                             //baseHP 
                90,                                                                                             //baseAtk 
                85,                                                                                             //baseDef 
                100,                                                                                            //baseSpe
                125,                                                                                            //baseSpA 
                90,                                                                                             //baseSpD 
                new Type[]{new Fire(), new Flying()},                                                         	//type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new Static(), new LightningRod()}),      //ability             
                52,                                                                                             //weight (Kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,			                                                        //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new ThunderShock(),
                                        new ThunderWave(),
                                        new Detect(),
                                        new Agility(),
                                        new AncientPower(),
                                        new AerialAce(),
                                        new Roost(),
                                        new DrillPeck(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new ZapCannon(),
                                        /*new Discharge(),*/
                                        new Facade(),
                                        new Sandstorm(),
                                        new BraveBird(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new DoubleTeam(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new WildCharge(),
                                        new SteelWing(),
                                        new FalseSwipe(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
