package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.FlameBody;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FlameWheel;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.PoisonTail;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.FlareBlitz;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.Glare;
import moves.status.Growl;
import moves.status.Haze;
import moves.status.Hypnosis;
import moves.status.Leer;
import moves.status.MorningSun;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Poison;
import types.Type;

public class Ponyta extends Pokemon {

    public Ponyta(int level) {
        super(  level,                                                                                          //level
                50,                                                                                             //baseHP 
                85,                                                                                            	//baseAtk 
                55,                                                                                             //baseDef 
                90,                                                                                            	//baseSpe
                65,                                                                                             //baseSpA 
                65,                                                                                             //baseSpD 
                new Type[]{new Fire(), null},                                                                   //type
                Ability.getRandomAbility(new Ability[]{new RunAway(), new FlameBody()}),                        //ability           
                30,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(),
                                        new TailWhip(),
                                        new Ember(),
                                        new FlameWheel(),
                                        new Stomp(),
                                        new TakeDown(),
                                        new Agility(),
                                        new FireBlast(),
                                        new Toxic(),
                                        new FlareBlitz(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new Charm(),
                                        new DoubleEdge(),
                                        new DoubleKick(),
                                        new Hypnosis(),
                                        new LowKick(),
                                        new MorningSun(),
                                        new Tackle()
                                }
                                )
                        )
                );
    }

}
