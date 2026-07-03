package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.AngerPoint;
import abilities.statusalterationcondition.MagmaArmor;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.IronHead;
import moves.damagingmove.physical.Magnitude;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Smog;
import moves.damagingmove.special.hpdependent.Eruption;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Growth;
import moves.status.Howl;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import types.Fire;
import types.Ground;
import types.Type;

public class Camerupt extends Pokemon{

    public Camerupt(int level) {
        super(  level,                                                                                          //level
                70,                                                                                             //baseHP 
                100,                                                                                            //baseAtk 
                70,                                                                                             //baseDef 
                40,                                                                                             //baseSpe
                105,                                                                                            //baseSpA 
                75,                                                                                             //baseSpD 
                new Type[]{new Fire(), new Ground(),},                                                          //type
                Ability.getRandomAbility(new Ability[]{new MagmaArmor(), new AngerPoint()}),    		//ability
                25,                                                                          	                //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(), 
                                        new Tackle(),
                                        new Magnitude(),
                                        new Amnesia(),
                                        new EarthPower(),
                                        new Eruption(),
                                        new Earthquake(),
                                        new Curse(new Type[]{new Fire(), new Ground()}),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Ember(),
                                        new Smog(),
                                        new Roar(),
                                        new ScaryFace(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new ShadowBall(),
                                        new Sandstorm(),
                                        /*new FlameCharge(),*/
                                        new RockTomb(),
                                        new RockSlide(),
                                        new Bulldoze(),
                                        new NaturePower(),
                                        new Attract(),
                                        new DoubleTeam(),
                                        new Rest(),
                                        new Thief(),
                                        new Overheat(),
                                        new WillOWisp(),
                                        new Swagger(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new DefenseCurl(),
                                        new Growth(),
                                        new HeatWave(),
                                        new IronHead(),
                                        new Howl(),
                                        new MudShot(),
                                        new ScaryFace(),
                                        new Stomp(),
                                }
                                )
                        )
                );
    }

}
