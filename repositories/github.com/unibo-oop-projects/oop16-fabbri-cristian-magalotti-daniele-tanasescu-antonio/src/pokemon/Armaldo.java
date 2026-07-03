package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.BattleArmor;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.IronDefense;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Rock;
import types.Type;

public class Armaldo extends Pokemon{

    public Armaldo(int level) {
        super(  level,                                                                                          //level
                75,                                                                                             //baseHP 
                125,                                                                                            //baseAtk 
                100,                                                                                            //baseDef 
                45,                                                                                             //baseSpe
                70,                                                                                             //baseSpA 
                80,                                                                                             //baseSpD 
                new Type[]{new Bug(), new Rock()},                                                  	        //type
                Ability.getRandomAbility(new Ability[]{new BattleArmor(), new SwiftSwim()}),        		//ability
                70,                                                                                            	//weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Harden(),
                                        new MudShot(),
                                        new WaterGun(),
                                        new MetalClaw(),
                                        new AncientPower(),
                                        new BugBite(),
                                        new RockBlast(),
                                        new Slash(),
                                        new DoubleTeam(),
                                        new XScissor(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new BrickBreak(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new RockPolish(),
                                        new RockSlide(),
                                        new StoneEdge(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new AquaJet(),
                                        new Curse(new Type[]{new Bug(), new Rock()}),
                                        new WaterPulse(),
                                        new SandAttack(),
                                        new KnockOff(),
                                        new IronDefense()

                                }
                                )
                        )
                );
    }

}
