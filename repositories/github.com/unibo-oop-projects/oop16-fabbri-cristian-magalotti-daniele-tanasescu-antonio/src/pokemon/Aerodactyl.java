package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.CuteCharm;
import abilities.otherconditions.RockHead;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.IronHead;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.RockPolish;
import moves.status.Roost;
import moves.status.Sandstorm;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Rock;
import types.Type;

public class Aerodactyl extends Pokemon{

    public Aerodactyl(int level) {
        super(  level,                                                                                          //level
                80,                                                                                             //baseHP 
                105,                                                                                            //baseAtk 
                65,                                                                                             //baseDef 
                130,                                                                                            //baseSpe
                60,                                                                                             //baseSpA 
                75,                                                                                             //baseSpD 
                new Type[]{new Rock(), new Flying()},                                                           //type
                Ability.getRandomAbility(new Ability[]{new RockHead(), new Pressure()}),                        //ability
                59,                                                                                            	//weight (Kg)
                0.6,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new IronHead(),
                                        new IceFang(),
                                        new FireFang(),
                                        new ThunderFang(),
                                        new WingAttack(),
                                        new Supersonic(),
                                        new Bite(),
                                        new ScaryFace(),
                                        new Roar(),
                                        new Agility(),
                                        new AncientPower(),
                                        new Crunch(),
                                        new TakeDown(),
                                        new IronTail(),
                                        new RockSlide(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new Earthquake(),
                                        new AerialAce(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new SteelWing(),
                                        new RockPolish(),
                                        new Swagger(),
                                        new Bulldoze(),
                                        new Curse(new Type[]{new Rock(), new Flying()}),
                                        new DragonBreath(),
                                        new Pursuit(),
                                        new Whirlwind(),
                                }
                                )
                        )
                );
    }

}
