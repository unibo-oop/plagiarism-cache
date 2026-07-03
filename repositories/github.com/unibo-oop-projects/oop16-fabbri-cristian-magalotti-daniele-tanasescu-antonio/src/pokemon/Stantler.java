package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.SapSipper;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.selfrecoil.JumpKick;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hypnosis;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Stantler extends Pokemon{

    public Stantler(int level) {
        super(  level,                                                                                          //level
                73,                                                                                             //baseHP 
                95,                                                                                            	//baseAtk 
                62,                                                                                            	//baseDef 
                85,                                                                                            	//baseSpe
                85,                                                                                             //baseSpA 
                65,                                                                                             //baseSpD 
                new Type[]{new Normal(), null},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new Intimidate(), new SapSipper()}),  	          	//ability
                71.2,                                                                                           //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Leer(),
                                        new Stomp(),
                                        new Pursuit(),
                                        new Rest(),
                                        new Astonish(),
                                        new Hypnosis(),
                                        new SandAttack(),
                                        new ConfuseRay(),
                                        new CalmMind(),
                                        new JumpKick(),
                                       /* new Psyshock(),*/
                                        new Roar(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new ZenHeadbutt(),
                                        new TakeDown(),
                                        new Swagger(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new DreamEater(),
                                        new WildCharge(),
                                        new Attract(),
                                        new Bulldoze(),
                                        new Bite(),
                                        new DoubleKick(),
                                        new Extrasensory(),
                                        new Megahorn(),
                                        new Rage(),
                                }
                                )
                )
        );
    }

}
