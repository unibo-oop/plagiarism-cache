package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.ShellArmor;
import abilities.movecondition.WeakArmor;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Constrict;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.ShellSmash;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Withdraw;
import moves.status.protecting.Protect;
import types.Rock;
import types.Type;
import types.Water;

public class Omanyte extends Pokemon {

    public Omanyte(int level) {
        super(level,
                35,		                                                                              		//hp
                40,		                                                                              		//atk
                100,		                                                                              	        //def
                35,		                                                                              		//speed
                90,		                                                                              		//spa
                55,		                                                                              		//spd
                new Type[]{new Rock(), new Water()},		                                                        //tipo
                Ability.getRandomAbility(new Ability[]{new ShellArmor(), new WeakArmor(), new SwiftSwim()}),        //ability
                7.4,	                                                                                                //weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                                      	//gender
                new HashSet<Move>(                                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Constrict(),
                                        new Withdraw(),
                                        new Bite(),
                                        new WaterGun(),
                                        new Leer(),
                                        new MudShot(),
                                        new RockBlast(),
                                        new ShellSmash(),
                                        new HydroPump(),
                                        new AncientPower(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Scald(),
                                        new RockPolish(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new AuroraBeam(),
                                        new BubbleBeam(),
                                        new Haze(),
                                        new KnockOff(),
                                        new MuddyWater(),
                                        new Supersonic(),
                                        new WaterPulse(),
                                }
                                )
                        )
                );
    }

}
