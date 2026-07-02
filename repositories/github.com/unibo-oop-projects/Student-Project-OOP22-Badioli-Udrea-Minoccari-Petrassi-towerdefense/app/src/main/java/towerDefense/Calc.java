package towerDefense;

import java.awt.Toolkit;

public class Calc {

    //Class that has the purpose of calculating the size of the panels
    
    public static double calcWidth (){
        double width=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        while(width>1200){
            width=width/1.01;
        }
        return width;
    }

    public static double calcHeight (){
        double height=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        while(height>670){
            height=height/1.01;
        }
        return height;
    }

}
