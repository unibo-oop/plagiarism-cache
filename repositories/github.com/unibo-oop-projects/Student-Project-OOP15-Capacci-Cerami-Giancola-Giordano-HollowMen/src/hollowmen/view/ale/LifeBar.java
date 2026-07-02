package hollowmen.view.ale;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * The LifeBar class ,called to each frame, will show to the player the internal of the lifeBar
 * 
 * @author Alessia
 *
 */
public class LifeBar extends InternalBar{
    
    private static final long serialVersionUID = -5182672357552953075L;
    private Font fontA=new Font("Chiller",Font.BOLD, 22);
    private JLabel life;
    private JLabel lifeText;
    
    public LifeBar(){
        
        this.setLayout(null);
        this.setOpaque(true);//Set true in order to allow to draw the component
        this.setBackground(Color.RED);
        
        this.life=new JLabel();
        this.life.setOpaque(true);
        this.life.setBackground(Color.GREEN);
        this.lifeText=new JLabel();
        this.lifeText.setFont(fontA);
        this.lifeText.setForeground(Color.WHITE);//color of the text
        this.setFont(fontA);
    }
    
    protected void barFilling(){
    	this.lifeText.setBounds(0,0,this.getWidth(),this.getHeight());
    	this.lifeText.setText(this.value+"/"+this.maxValue);//It shows the life value.
        this.lifeText.setHorizontalAlignment(CENTER);//To set the text at the center of the bar.
        this.life.setBounds(0,0,(int)this.width,this.getHeight());
        this.add(this.lifeText);
        this.add(this.life);
        
    }
}
