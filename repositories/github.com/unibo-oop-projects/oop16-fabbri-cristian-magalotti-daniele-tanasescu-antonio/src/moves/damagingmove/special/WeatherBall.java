package moves.damagingmove.special;

import battle_arena.BattleArena;
import battle_arena.weather.*;
import pokemon.Pokemon;
import types.Fire;
import types.Ground;
import types.Ice;
import types.Normal;
import types.Water;

public class WeatherBall extends SpecialDamagingMove{
    
    private static final int STANDARDPOWER = 50;

    //its type will depend on current weather -> special overridden getDamage
    public WeatherBall() {
        super(  "Weather Ball",                                                                                       //name
                "This attack move varies in power and type depending on the weather.",                                //description
                STANDARDPOWER,                                                                                        //base power
                new Normal(),                                                                                         //type
                1,                                                                                                    //accuracy
                critRange1,                                                                                           //crit range 
                10,                                                                                                   //PP
                0);                           
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        this.typeToWeather(user, battleArena.weather);
        super.getDamage(user, target, battleArena);
        this.setBasePower(STANDARDPOWER);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }
    
    private void typeToWeather(Pokemon user,Weather weather){
        if(weather != null){
            this.setBasePower(2d);
            if(weather.equals(new HarshSunlight(5))){
                this.setNewType(new Fire());
            }
            else if(weather.equals(new Rain(5))){
                this.setNewType(new Water());
            }
            else if(weather.equals(new Sandstorm(5))){
                this.setNewType(new Ground());
            }
            else if(weather.equals(new Hail(5))){
                this.setNewType(new Ice());
            }
        }
        else{
            this.setBasePower(STANDARDPOWER);
            this.setNewType(new Normal());                              /*this will make abilities such as Aerialate useless (e.g. in that case it will be desplayed
                                                                         *as a Flying move but without a weather it will always be Normal!*/
        }
    }

}
