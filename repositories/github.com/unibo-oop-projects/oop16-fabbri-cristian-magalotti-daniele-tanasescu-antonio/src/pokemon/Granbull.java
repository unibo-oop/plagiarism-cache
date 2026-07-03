package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Outrage;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.HealBell;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Type;

public class Granbull extends Pokemon{

    public Granbull(int level) {
        super(  level,                                                                                          //level
                90,                                                                                             //baseHP 
                120,                                                                                             //baseAtk 
                75,                                                                                             //baseDef 
                45,                                                                                             //baseSpe
                60,                                                                                             //baseSpA 
                60,                                                                                             //baseSpD 
                new Type[]{new Fairy(), null},                                                                  //type
                Ability.getRandomAbility(new Ability[]{new Intimidate()}),                	                //ability
                50,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Outrage(),
                                        new TailWhip(),
                                        new FireFang(),
                                        new IceFang(),
                                        new ThunderFang(),
                                        new ScaryFace(),
                                        new Charm(),
                                        new Bite(),
                                        new Lick(),
                                        new Headbutt(),
                                        new Roar(),
                                        new Rage(),
                                        new PlayRough(),
                                        new Crunch(),
                                        /*new WorkUp(),*/
                                        new BulkUp(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Overheat(),
                                        new Thief(),
                                        new Bulldoze(),
                                        new Earthquake(),
                                        //new Snarl(),
                                        new DazzlingGleam(),
                                        new WildCharge(),
                                        //new CloseCombat(),
                                        new DoubleEdge(),
                                        new FakeTears(),
                                        new IceFang(),
                                        new HealBell(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new ThunderWave(),
                                        new Swagger()
                                }

                                )
                        )
                );
    }

}
