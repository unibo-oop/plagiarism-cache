package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.ElectricSurge;
import abilities.firstturn.PsychicSurge;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Agility;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.MeanLook;
import moves.status.NaturePower;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Roar;
import moves.status.Roost;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Psychic;
import types.Type;

public class TapuKoko extends Pokemon{

    public TapuKoko(int level) {
        super(  level,
                70,                                                                                             //hp
                115,                                                                                            //atk
                85,                                                                                             //def
                130,                                                                                            //speed
                95,                                                                                             //spa
                75,                                                                                             //spd
                new Type[]{new Psychic(), new Fairy()},                                                         //tipo
                Ability.getRandomAbility(new Ability[]{new ElectricSurge()}),                                   //ability               
                20.5,                                                                                           //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                                                                              //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new BraveBird(),
                                        new MeanLook(),
                                        new QuickAttack(),
                                        new ThunderShock(),
                                        new WildCharge(),
                                        new Agility(),
                                        new CalmMind(),
                                        new Roar(),
                                        new Protect(), 
                                        new RainDance(),
                                        new Roost(),
                                        new DoubleTeam(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new AerialAce(),
                                        new Acrobatics(),
                                        new ThunderWave(),
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
