package aoc.controller;

import java.util.Arrays;
import java.util.List;

public final class GameConstants {

    /**
     * Number of Updates per Second.
     */
    public static final int UPDATES_PER_SECOND = 120;
    
    /**
     * 12 Frame_Per_Second.
     */
    public static final  int FPS_12 = 12;
    
    /**
     * 30 Frame_Per_Second.
     */
    public static final int FPS_30 = 30;
    
    /**
     * 60 Frame_Per_Second.
     */
    public static final int FPS_60 = 60;
    
    /**
     * List of the possible settings for the FPS.
     */
    public static final List<Integer> FPS_SETTINGS = Arrays.asList(FPS_12, FPS_30, FPS_60);
    
    /**
     * Number of Levels in Story Mode.
     */
    public static final int N_LEVELS = 5;
    

    

}
