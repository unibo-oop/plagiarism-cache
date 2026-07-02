package hollowmen.view.ale;

import javax.swing.JLabel;

/** 
 * The abstract class is used to draw bars with variable width.
 * 
 * @author Alessia
 */
public abstract class InternalBar extends JLabel{
    
    private static final long serialVersionUID = -3814942016648716113L;
    protected double width;
    protected int value;
    protected int maxValue;
    
    public InternalBar(){}
    
    /**
     * The {@code proportion} method used to calculate the percentage of the bar filling.
     * 
     * @param value
     * @param maxValue
     */
    public void proportion(int value,int maxValue){
        this.value=value;
        this.maxValue=maxValue;
        this.width=(this.getWidth()*value)/maxValue;
        barFilling();
    }
    
    protected abstract void barFilling();
}