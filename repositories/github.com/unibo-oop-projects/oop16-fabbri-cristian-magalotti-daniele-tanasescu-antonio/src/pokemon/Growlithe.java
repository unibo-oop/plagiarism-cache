package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.Justified;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.FlameWheel;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.FlareBlitz;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.Overheat;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.MorningSun;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Type;

public class Growlithe extends Pokemon{

    public Growlithe(int level) {
        super(  level,                                                                                          //level
                55,                                                                                             //baseHP 
                70,                                                                                             //baseAtk 
                45,                                                                                             //baseDef 
                60,                                                                                             //baseSpe
                70,                                                                                             //baseSpA 
                50,                                                                                             //baseSpD 
                new Type[]{new Fire(), null},                                                                  	//type
                Ability.getRandomAbility(new Ability[]{new Intimidate(), new Justified()}),                                        
                19,                                                                                             //weight (Kg)
                1.2,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),  
                                        new Roar(),
                                        new Ember(),
                                        new Flamethrower(),
                                        new Leer(),
                                        new FlameWheel(),
                                        new FireFang(),
                                        new TakeDown(),
                                        new Agility(),
                                        new HeatWave(),
                                        new FlareBlitz(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Attract(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Facade(),
                                        new Thief(),
                                        new WillOWisp(),
                                        new Overheat(),
                                        new Swagger(),
                                        new AirCutter(),
                                        new WildCharge(),
                                        //new Snarl(),
                                        new BodySlam(),
                                        //new CloseCombat(),
                                        new Bite(),
                                        new DoubleEdge(),
                                        new Crunch(),
                                        new DoubleKick(),
                                        new IronTail(),
                                        new MorningSun(),
                                }
                                )
                        )
                );
    }

}
