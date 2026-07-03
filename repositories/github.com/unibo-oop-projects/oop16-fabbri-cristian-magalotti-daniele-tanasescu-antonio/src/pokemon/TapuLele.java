package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.PsychicSurge;
import abilities.movecondition.SheerForce;
import abilities.otherconditions.RockHead;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Aromatherapy;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.MeanLook;
import moves.status.NaturePower;
import moves.status.PsychUp;
import moves.status.SkillSwap;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Psychic;
import types.Type;

public class TapuLele extends Pokemon{

    public TapuLele(int level) {
        super(  level,
                70,                                                                                             //hp
                85,                                                                                             //atk
                75,                                                                                             //def
                95,                                                                                             //speed
                130,                                                                                            //spa
                115,                                                                                            //spd
                new Type[]{new Psychic(), new Fairy()},                                                         //tipo
                Ability.getRandomAbility(new Ability[]{new PsychicSurge()}),                                    //ability               
                18.6,                                                                                           //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                                                                              //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new moves.damagingmove.special.Psychic(),
                                        new Aromatherapy(),
                                        new MeanLook(),
                                        new Astonish(),
                                        new Confusion(),
                                        new SweetScent(),
                                        new SkillSwap(),
                                        new Tickle(),
                                        new Extrasensory(),
                                        new Moonblast(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new DazzlingGleam()
                                        
                                }
                                )
                        )
                );
    }

}
