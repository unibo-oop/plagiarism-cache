package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.hpcondition.Blaze;
import abilities.weathercondition.SolarPower;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Cut;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockSmash;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Strenght;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.amount.DragonRage;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.Growl;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.SmokeScreen;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fire;
import types.Type;

public class Charmeleon extends Pokemon{

    public Charmeleon(int level) {
        super(  level,                                                                                          //level
                58,                                                                                             //baseHP 
                64,                                                                                             //baseAtk 
                80,                                                                                             //baseDef 
                80,                                                                                             //baseSpe
                58,                                                                                             //baseSpA 
                65,                                                                                             //baseSpD 
                new Type[]{new Fire(), null},                                                                  	//type
                Ability.getRandomAbility(new Ability[]{new Blaze(),                                           	//ability
                        new SolarPower()}),                                        
                19,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),                                                        
                                        new Growl(),
                                        new Ember(),
                                        new SmokeScreen(),
                                        new DragonRage(),
                                        new ScaryFace(),
                                        new Flamethrower(),
                                        new DragonClaw(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new BrickBreak(),
                                        new IronTail(),
                                        new Attract(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Overheat(),
                                        new Cut(),
                                        new Strenght(),
                                        new RockSmash(),
                                        new Swagger(),
                                        new Protect(),
                                        new Facade(),
                                        new RockSlide(),
                                        new FireFang(),
                                        new Slash(),
                                        new AirCutter(),
                                        new AncientPower(),
                                        new BellyDrum(),
                                        new Bite(),
                                        new Counter(),
                                        new Crunch(),
                                        new DragonDance(),
                                        new DragonPulse(),
                                        new MetalClaw(),
                                }
                                )
                        )
                );
    }

}
