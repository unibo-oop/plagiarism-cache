package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.CuteCharm;
import abilities.statisticsalterationondemand.Competitive;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Normal;
import types.Type;

public class Wigglytuff extends Pokemon{

    public Wigglytuff(int level) {
        super(  level,                                                                                          //level
                140,                                                                                            //baseHP 
                70,                                                                                             //baseAtk 
                45,                                                                                             //baseDef 
                45,                                                                                             //baseSpe
                75,                                                                                             //baseSpA 
                50,                                                                                             //baseSpD 
                new Type[]{new Normal(), new Fairy()},                                                          //type
                Ability.getRandomAbility(new Ability[]{new CuteCharm(), new Competitive()}),                    //ability                                       
                12,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Sing(),
                                        new DoubleSlap(),
                                        new DefenseCurl(),
                                        new BodySlam(),
                                        new DisarmingVoice(),
                                        new HyperVoice(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new ThunderWave(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new DazzlingGleam(),
                                        //new MistyTerrain(),
                                        new FeintAttack(),
                                        new FakeTears(),
                                        new DoubleEdge(),
                                        new PlayRough(),
                                }

                               )
                        )
                );
                
    }

}
