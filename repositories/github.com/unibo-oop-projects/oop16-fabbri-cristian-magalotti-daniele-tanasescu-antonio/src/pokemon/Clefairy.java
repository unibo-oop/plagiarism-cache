package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.CuteCharm;
import abilities.otherconditions.MagicGuard;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.MeteorMash;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.CosmicPower;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Minimize;
import moves.status.Moonlight;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Type;

public class Clefairy extends Pokemon{

    public Clefairy(int level) {
        super(  level,                                                                                          //level
                70,                                                                                             //baseHP 
                45,                                                                                             //baseAtk 
                48,                                                                                             //baseDef 
                35,                                                                                             //baseSpe
                60,                                                                                             //baseSpA 
                65,                                                                                             //baseSpD 
                new Type[]{new Fairy(), null},                                                                  //type
                Ability.getRandomAbility(new Ability[]{new CuteCharm(),                                         //ability
                                                       new MagicGuard()}),                                        
                7.5,                                                                                            //weight (Kg) 
                1.2,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Growl(),
                                        new Sing(),
                                        new DoubleSlap(),
                                        new DefenseCurl(),
                                        new Minimize(),
                                        new CosmicPower(),
                                        new BodySlam(),
                                        new Moonlight(),
                                        new Moonblast(),
                                        new MeteorMash(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new DisarmingVoice(),
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
                                        new Swagger()
                                }

                                )
                        )
                );
    }

}
