package hollowmen.view.ale;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * The Bar class will be the container of the two bars(LifeBar and ExpBar) showing to the player 
 * the actual life and experience.
 * 
 * @author Alessia
 *
 */
public class Bar extends JLabel{
    
    private static final long serialVersionUID = 586335926081711383L;
    private static final int GAP=5;
    private LifeBar life;
    private ExpBar exp;
    
    public Bar (){
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
    }
    
    public void setup(){
    	this.life=new LifeBar();
        this.life.setBounds(GAP, GAP, (int)(this.getWidth()-GAP*2), 
                            (int)((this.getHeight()-GAP*3)/3*2));
        this.exp=new ExpBar();
        this.exp.setBounds(GAP, this.life.getHeight()+GAP*2,
                            (int)(this.getWidth()-GAP*2), (int)((this.getHeight()-GAP*3)/3));
        this.add(this.life);
        this.add(this.exp);
    }
    
    /**
     * The {@code updateBar} method set the right length of the life and experience bars. 
     * @param life
     * @param maxLife
     * @param exp
     * @param expNeeded
     */
    public void updateBar(int life,int maxLife,int exp,int expNeeded){
    	this.life.proportion(life,maxLife);
        this.exp.proportion(exp,expNeeded);
        
    }
}
