package pvz.controller.data;

import java.io.Serializable;


public class ScoreImpl implements Score,Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2L;
    private int score;
    private String playerName;

    public ScoreImpl(final int score, final String playerName) {

        if (score > 0 && !playerName.equals(null)) {
            this.score = score;
            this.playerName = playerName;
        }

    }

    @Override
    public String getPlayer() {
        return this.playerName;
    }

    @Override
    public int getScore() {
        return this.score;
    }
    
    @Override
    public boolean equals(Object obj) {
    	
    	if(obj == null){
    		return false;
    	}
    	if(!(obj instanceof ScoreImpl)){
    		return false;
    	}
    	
    	Score score = (ScoreImpl)obj;
    	return this.getScore() == score.getScore() && this.getPlayer().equals(score.getPlayer());
    }
}