package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.GrassySurge;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.HornLeech;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.selfrecoil.WoodHammer;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.GigaDrain;
import moves.status.CalmMind;
import moves.status.MeanLook;
import moves.status.NaturePower;
import moves.status.PsychUp;
import moves.status.Roar;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Grass;
import types.Type;

public class TapuBulu extends Pokemon{

    public TapuBulu(int level) {
        super(  level,
                70,                                                                                             //hp
                130,                                                                                            //atk
                115,                                                                                            //def
                75,                                                                                             //speed
                85,                                                                                             //spa
                59,                                                                                             //spd
                new Type[]{new Grass(), new Fairy()},                                                           //tipo
                Ability.getRandomAbility(new Ability[]{new GrassySurge()}),                                     //ability               
                45.5,                                                                                           //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                                                                              //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WoodHammer(),
                                        new Superpower(),
                                        new MeanLook(),
                                        new Whirlwind(),
                                        new HornAttack(),
                                        new GigaDrain(),
                                        new ScaryFace(),
                                        new HornLeech(),
                                        new ZenHeadbutt(),
                                        new Megahorn(),
                                        new CalmMind(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new BrickBreak(),
                                        new RockTomb(),
                                        new Facade(),
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new StoneEdge(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new DazzlingGleam()
                                }
                                )
                        )
                );
    }

}