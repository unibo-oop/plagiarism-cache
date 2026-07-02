package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a list of all the years of various types of engineering and science, 
 * then added the colors to not confuse them 
 * 
 * @author Francesco Ceroni 
 * 
 */

public enum Type {

    FIRST_YEAR("1LT : 1° Anno Laurea Triennale", Color.CYAN), 
    SECOND_YEAR("2LT : 2° Anno Laurea Triennale", Color.YELLOW), 
    SECOND_YEAR_ENG("2LTI : 2° Anno Laurea Triennale Ingegneria", Color.ORANGE), 
    SECOND_YEAR_SCI("2LTS : 2° Anno Laurea Triennale Scienze", Color.ORANGE), 
    THIRD_YEAR("3LT : 3° Anno Laurea Triennale", Color.PINK), 
    THIRD_YEAR_OPT("3LTO : 3° Anno Laurea Triennale Opzionali", Color.RED), 
    THIRD_YEAR_ENG("3LTI : 3° Anno Laurea Triennale Ingegneria", Color.MAGENTA), 
    THIRD_YEAR_SCI("3LTS : 3° Anno Laurea Triennale Scienze", Color.MAGENTA), 
    FOURTH_YEAR("1LM : 1° Anno Laurea Magistrale", Color.LIGHT_GRAY), 
    FIFTH_YEAR("2LM : 2° Anno Laurea Magistrale", new Color(173,223,173)), 
    FIFTH_YEAR_OPT("2LMO : 2° Anno Laurea Magistrale Opzionali", Color.GREEN);

    private final String character;
    private final Color color;

    private Type(final String s, final Color c) {
        this.character = s;
        this.color = c;
    }

    public String getCharacter() {
        return character;
    }

    public Color getColor() {
        return color;
    }

    /**
     * @return second year
     */
    
    public static List<Type> getSecondYears(){
        List<Type> second = new ArrayList<>();
        second.add(Type.SECOND_YEAR);
        second.add(Type.SECOND_YEAR_ENG);
        second.add(Type.SECOND_YEAR_SCI);
        return second;
    }
    
    
    
    /**
     * @return third year
     */
    
    public static List<Type> getThirdYears(){
        List<Type> third = new ArrayList<>();
        third.add(Type.THIRD_YEAR);
        third.add(Type.THIRD_YEAR_ENG);
        third.add(Type.THIRD_YEAR_SCI);
        third.add(Type.THIRD_YEAR_OPT);
        return third;
    }
    
    /**
     * @return fifth year
     */
    
    public static List<Type> getFifthYears(){
        List<Type> fifth = new ArrayList<>();
        fifth.add(Type.FIFTH_YEAR);
        fifth.add(Type.FIFTH_YEAR_OPT);
        return fifth;
    }
}