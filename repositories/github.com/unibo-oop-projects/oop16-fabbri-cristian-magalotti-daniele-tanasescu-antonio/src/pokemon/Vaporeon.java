package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.weathercondition.Hydration;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Vaporeon extends Pokemon{

    public Vaporeon(int level) {
        super(  level,                                                                                          //level
                130,                                                                                            //baseHP 
                65,                                                                                             //baseAtk 
                60,                                                                                             //baseDef 
                65,                                                                                             //baseSpe
                110,                                                                                            //baseSpA 
                95,                                                                                            	//baseSpD 
                new Type[]{new Water(), null},                                                                  //type
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Hydration()}), 			//ability                                      
                29,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new TailWhip(),
                                        new SandAttack(),
                                        new QuickAttack(),
                                        new Bite(),
                                        new Swift(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Water(), null}),
                                        new Detect(),
                                        new WaterGun(),
                                        new WaterPulse(),
                                        new MuddyWater(),
                                        new HydroPump(),
                                        new Scald(),
                                        new Surf(),
                                        new Waterfall(),
                                }

                                )
                        )
                );
    }

}
