package hollowmen.view.ale;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * The LifeBar class ,called to each frame, will show to the player the internal of the expBar
 * 
 * @author Alessia
 *
 */
public class ExpBar extends InternalBar{
    
    private static final long serialVersionUID = -2330717225812465213L;
    private JLabel exp;
    
    public ExpBar(){
        
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.DARK_GRAY);
        
        this.exp=new JLabel();
        this.exp.setOpaque(true);
        this.exp.setBackground(Color.YELLOW);
    }
    
    protected void barFilling(){
    	this.exp.setBounds(0,0,(int)this.width,this.getHeight());
        this.add(this.exp);
    }
}
