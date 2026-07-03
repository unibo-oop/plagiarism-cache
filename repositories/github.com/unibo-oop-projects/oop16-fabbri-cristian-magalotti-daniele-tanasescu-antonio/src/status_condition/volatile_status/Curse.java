package status_condition.volatile_status;

import pokemon.Pokemon;

public class Curse extends VolatileStatusCondition {

    private static final double DOTPERCENTAGE = 0.25;
    private static final String CURSED = "is cursed";
    private static final String CURSEDOT = "is hit by its curse!";
    private static final String CURSEEND = "is no more cursed!";
    
	public Curse() {
		super(  null,                                                                   //immunityToThis 
                null,                                                     	             	//avoidThis
                true,                                                                           //hasDot                      
                false,                                                                          //mayPreventAttack
                false,                                                                          //alterStat
                999,                                                                            //turnCount (...)
                5,                                                                              //executionPriority
	        CURSEDOT,                                                                       //statusDot   
	        "",                                                                             //statusPrevent
                CURSEEND);                                                                      //statusEnd      
	        this.setStatusAlready("cursed");
    }

	@Override
	public void getVolatilePreventEffect(Pokemon ill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getVolatileDotEffect(Pokemon ill) {
		ill.takeDamage(DOTPERCENTAGE*ill.getMaxHp(), false);       
		
	}

	@Override
	public void checkNextTurnActive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getStatAlteration(Pokemon ill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitingStatusAlteration(Pokemon ill) {
	    this.getEndMessage(ill);		
	}

    @Override
    public String getStatusString() {
        return CURSED;
    }

}
