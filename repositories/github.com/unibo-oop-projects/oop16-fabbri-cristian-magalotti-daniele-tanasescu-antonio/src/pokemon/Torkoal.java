package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Drought;
import abilities.movecondition.ShellArmor;
import abilities.statisticsalterationondemand.WhiteSmoke;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HeatWave;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Smog;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.ShellSmash;
import moves.status.SmokeScreen;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.Withdraw;
import types.Fire;
import types.Ground;
import types.Type;

public class Torkoal extends Pokemon{

    public Torkoal(int level) {
        super(  level,                                                                                          //level
                70,                                                                                             //baseHP 
                85,                                                                                             //baseAtk 
                140,                                                                                            //baseDef 
                20,                                                                                             //baseSpe
                85,                                                                                            	//baseSpA 
                70,                                                                                             //baseSpD 
                new Type[]{new Fire(), null},                                                          		//type
                Ability.getRandomAbility(new Ability[]{new WhiteSmoke(), new ShellArmor(), new Drought()}),    	//ability
                80,                                                                          	                //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Withdraw(),
                                        new Amnesia(),
                                        new Earthquake(),
                                        new Curse(new Type[]{new Fire(), new Ground()}),
                                        new TakeDown(),
                                        new SmokeScreen(),
                                        new DoubleEdge(),
                                        new Ember(),
                                        new Smog(),
                                        new Roar(),
                                        new ShellSmash(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new SludgeBomb(),
                                        new Explosion(),
                                        new Bulldoze(),
                                        //new GyroBall(),
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
                                        //new SuperPower(),
                                        new BodySlam(),
                                        new DefenseCurl(),
                                        new Fissure(),
                                        new HeatWave(),
                                }
                                )
                        )
                );
    }

}
