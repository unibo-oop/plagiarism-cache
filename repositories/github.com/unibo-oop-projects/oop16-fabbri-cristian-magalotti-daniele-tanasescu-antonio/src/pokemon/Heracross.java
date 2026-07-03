package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.hpcondition.Swarm;
import abilities.movecondition.Moxie;
import abilities.statusalterationcondition.Guts;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.physical.onehitko.Guillotine;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.FocusBlast;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Type;

public class Heracross extends Pokemon{

    public Heracross(int level){
        super(  level,                                                                                          //level
                80,                                                                                             //baseHP 
                125,                                                                                            //baseAtk 
                75,                                                                                             //baseDef 
                85,                                                                                             //baseSpe
                40,                                                                                             //baseSpA 
                95,                                                                                             //baseSpD 
                new Type[]{new Bug()},                                                            		//type
                Ability.getRandomAbility(new Ability[]{new Moxie(), new Guts(), new Swarm()}),    		//ability
                55,                                                                                            	//weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new BulletSeed(),
                                        /*new NightSlash(),*/
                                        new Tackle(),
                                        new Leer(),
                                        new HornAttack(),
                                        new FuryAttack(),
                                        new PinMissile(),
                                        new TakeDown(),
                                        new Megahorn(),
                                        /*new CloseCombat(),*/
                                        new Reversal(),
                                        new BrickBreak(),
                                        new SwordsDance(),
                                        new Superpower(),
                                        new Guillotine(),
                                        new Pursuit(),
                                        new Swagger(),
                                        new Toxic(),
                                        /*new WorkUp(),*/
                                        new ShadowClaw(),
                                        new DoubleEdge(),
                                        new Harden(),
                                        new Pursuit(),
                                        new RockBlast(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new RockSlide(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                }
                                )
                        )
                );

    }

}
