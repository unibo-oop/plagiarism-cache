package status_condition;

import pokemon.Pokemon;

public class BadPoison extends Poison{
    
    private static final String BADPOISON = "is badly poisoned";

    public BadPoison() {
        super();
    }
    
    @Override
    public void getDotDamage(Pokemon ill) {
        this.setPoisonDamage();
        damage *= ill.getMaxHp();
        ill.takeDamage(damage, false);
        //messages
        if(ill.isFainted()){
            //messages
        }    
    }
    
    public void setPoisonDamage(){
        this.damage = (this.getTurnCounter()+1)*Poison.DOTPERCENTAGE/2;
    }
    
    @Override
    public String getStatusString() {
        return BADPOISON;
    }

}
