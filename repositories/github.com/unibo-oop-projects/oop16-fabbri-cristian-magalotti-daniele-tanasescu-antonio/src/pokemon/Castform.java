package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.PowderSnow;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.WaterGun;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Castform extends Pokemon{

    public Castform(int level) {
        super(  level,                                                                                          //level
                70,                                                                                            	//baseHP 
                70,                                                                                            	//baseAtk 
                70,                                                                                            	//baseDef 
                70,                                                                                            	//baseSpe
                70,                                                                                            	//baseSpA 
                70,                                                                                            	//baseSpD 
                new Type[]{new Normal()},                                                            		//type
                Ability.getRandomAbility(new Ability[]{new Levitate()}),                       			//ability
                1,                                                                                              //weight (Kg)
                1,                                                                                              //sizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new WaterGun(),
                                        new Ember(),
                                        new PowderSnow(),
                                        new Headbutt(),
                                        new HydroPump(),
                                        new Hurricane(),
                                        new CalmMind(),
                                        new Roar(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new EnergyBall(),
                                        new Scald(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
