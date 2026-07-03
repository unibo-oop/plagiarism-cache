package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.movecondition.CuteCharm;
import abilities.otherconditions.Insomnia;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Ice;
import types.Type;

public class Delibird extends Pokemon{

    public Delibird(int level) {
        super(  level,                                                                                          //level
                45,                                                                                             //baseHP 
                55,                                                                                             //baseAtk 
                45,                                                                                            	//baseDef 
                75,                                                                                            	//baseSpe
                65,                                                                                             //baseSpA 
                45,                                                                                            	//baseSpD 
                new Type[]{new Ice(), new Flying()},                                                         	//type
                Ability.getRandomAbility(new Ability[]{new Hustle(), new Insomnia()}),                          //ability             
                16,                                                                                             //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new DrillPeck(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new AerialAce(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new AuroraBeam(),
                                        new FakeOut(),
                                        new IcePunch(),
                                        /*new IceShard(),*/
                                        new IcyWind(),
                                        new QuickAttack(),
                                        new SteelWing(),
                                        new FalseSwipe(),
                                        new Swagger(),
                                }
                                )
                        )
                );
    }

}
