package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.HugePower;
import abilities.movecondition.SapSipper;
import abilities.movecondition.WaterAbsorb;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.MeteorMash;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.CalmMind;
import moves.status.Camouflage;
import moves.status.CosmicPower;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Minimize;
import moves.status.Moonlight;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Type;
import types.Water;

public class Marill extends Pokemon{

    public Marill(int level) {
        super(  level,                                                                                          //level
                70,                                                                                             //baseHP 
                20,                                                                                             //baseAtk 
                50,                                                                                             //baseDef 
                40,                                                                                             //baseSpe
                20,                                                                                             //baseSpA 
                50,                                                                                             //baseSpD 
                new Type[]{new Water(), new Fairy()},                                                           //type
                Ability.getRandomAbility(new Ability[]{new SapSipper(), new HugePower()}),                      //ability
                8.5,                                                                                            //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new WaterGun(),
                                        new TailWhip(),
                                        new DoubleSlap(),
                                        new Bubble(),
                                        new BubbleBeam(),
                                        new AquaTail(),
                                        new PlayRough(),
                                        new DoubleEdge(),
                                        /*new SuperPower(),*/
                                        new Scald(),
                                        new Surf(),
                                        new Waterfall(),
                                        new Amnesia(),
                                        new AquaJet(),
                                        new BellyDrum(),
                                        new BodySlam(),
                                        new Camouflage(),
                                        new MuddyWater(),
                                        new Refresh(),
                                        new Slam(),
                                        new Supersonic(),
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
                                        new Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
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
