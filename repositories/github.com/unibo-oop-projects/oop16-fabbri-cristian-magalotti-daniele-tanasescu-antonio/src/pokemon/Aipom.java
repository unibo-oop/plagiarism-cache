package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.SkillLink;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.NastyPlot;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Screech;
import moves.status.Spite;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Aipom extends Pokemon{

    public Aipom(int level) {
        super(  level,                                                                                          //level
                55,                                                                                            	//baseHP 
                70,                                                                                             //baseAtk 
                55,                                                                                             //baseDef 
                85,                                                                                             //baseSpe
                40,                                                                                             //baseSpA 
                55,                                                                                            	//baseSpD 
                new Type[]{new Normal(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new RunAway(), new SkillLink()}), 			//ability                                      
                6.5,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new TailWhip(),
                                        new SandAttack(),
                                        new QuickAttack(),
                                        new Astonish(),
                                        new FurySwipes(),
                                        new Swift(),
                                        new Screech(),
                                        new Agility(),
                                        new NastyPlot(),
                                        /*new WorkUp(),*/
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new BrickBreak(),
                                        new AerialAce(),
                                        new LowKick(),
                                        new ThunderWave(),
                                        new FakeOut(),
                                        new DoubleSlap(),
                                        new Pursuit(),
                                        new Revenge(),
                                        new Slam(),
                                        new Spite(),
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
                                }

                                )
                        )
                );
    }

}
