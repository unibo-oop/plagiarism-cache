package pvz.controller.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import pvz.controller.Mode;

public class PlayerImpl implements Player {

    private static final long serialVersionUID = 1L;

    private String playerName;
    private int levelProgress;
    
    public PlayerImpl(final String playerName) {
        this.playerName=playerName;
        this.levelProgress=1;
        
    }
    
    @Override
    public String getName() {
        return this.playerName;
    }


    @Override
    public int getLevelProgress() {
        return this.levelProgress;
    }

    @Override
    public void updateHistoryProgress() {
       if (this.levelProgress<Mode.HISTORY.getTotLevels()){
    	   this.levelProgress++;
       }
    }
    
    @Override
    public boolean equals(Object obj) {
    	
    	if(obj == null){
    		return false;
    	}
    	if(!(obj instanceof PlayerImpl)){
    		return false;
    	}
    	
    	Player player = (PlayerImpl)obj;
    	return this.playerName.equals(player.getName());
    }

}
