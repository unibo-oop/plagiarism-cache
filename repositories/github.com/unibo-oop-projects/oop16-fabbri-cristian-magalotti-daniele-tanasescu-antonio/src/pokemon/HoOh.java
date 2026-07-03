package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.WaterAbsorb;
import abilities.switchcondition.Regenerator;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.SacredFire;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.selfrecoil.FlareBlitz;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Roost;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Flying;
import types.Type;

public class HoOh extends Pokemon{

    public HoOh(int level) {
        super(  level,                                                                                          //level
                106,                                                                                            //baseHP 
                130,                                                                                            //baseAtk 
                90,                                                                                             //baseDef 
                90,                                                                                            	//baseSpe
                110,                                                                                            //baseSpA 
                154,                                                                                            //baseSpD 
                new Type[]{new Fire(), new Flying()},                                                           //type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new Regenerator()}),                     //ability                                        
                200,                                                                                           	//weight (Kg) 
                0.6,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,                                                                       	//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Whirlwind(),
                                        new Gust(),
                                        new BraveBird(),
                                        new Extrasensory(),
                                        new SacredFire(),
                                        new AncientPower(),
                                        new Recover(),
                                        new CalmMind(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        /*new FlameCharge(),*/
                                        new SteelWing(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Bulldoze(),
                                        new DreamEater(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Overheat(),
                                        new FlareBlitz(),
                                        new Swagger(),
                                        new Facade(),
                                        new WingAttack(),
                                        new HeatWave(),
                                        new Roost(),
                                        new Earthquake(),
                                        new FocusBlast(),
                                        new WillOWisp(),
                                }
                                )
                        )
                );
    }

}
