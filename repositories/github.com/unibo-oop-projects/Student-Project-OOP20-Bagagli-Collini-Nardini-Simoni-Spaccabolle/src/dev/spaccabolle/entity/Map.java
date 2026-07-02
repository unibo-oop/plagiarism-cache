/**
 *  This class return the map of the game, at the beginnning it load the level from a file .txt
 *  every level have 9 row and 13 coloumn but only 11 couloumn are avaiable. It reads every char from
 *  the .txt file and assign a new ball with the color assigned on the file. 
 *  (1-red, 2-blue, 3-green, 4-yellow) this variable is save in readBobble. With two switch 
 *  the class assigned the coordinate (x, y) to the ball.
 *  @author Elisa Simoni
 */

package dev.spaccabolle.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dev.spaccabolle.Launcher;



// TODO: Auto-generated Javadoc
/**
 * The Class Map.
 */
public class Map {
	
	
    
    /** The Constant RADIUS. */
    private static final int RADIUS = (int)(Ball.BOBBLE_SIZE / 1.25);
    
    /** The Constant NROW. */
    private static final int NROW =  13;
    
    /** The Constant NCOL. */
    private static final int NCOL = 15;
    
    /** The Constant RADIUS_NINE. */
    private static final int RADIUS_NINE = RADIUS + 506; //nine coulumn
    
    /** The Constant SCARTO_Y. */
    public static final double SCARTO_Y = RADIUS + 5;  
    
    /** The ball map. */
    public Ball ballMap;    
    
    /** The index. */
    public static int index=0; //public
    
    /** The map matrix. */
    public static Ball[][] mapMatrix = new Ball[NROW][NCOL];
    
    /** The pos X. */
    public static float[] posX = new float[NROW]; 
       
    /** The collect ball map. */
    public static CollectBall collectBallMap;

    /** The line. */
    private String line;
    
    /** The line dimension X. */
    private int lineDimensionX = Launcher.GAME_WIDTH;
    
    /** The line dimension Y. */
    private int lineDimensionY = Launcher.GAME_HEIGHT;
    
    
    /**
     * Instantiates a new map.
     *
     * @param gameYSize the game Y size
     * @param gameXSize the game X size
     * @param collectBall the collect ball
     * @param level the level
     */
    public Map(int gameYSize, int gameXSize, CollectBall collectBall, File level) {
        Map.collectBallMap=collectBall;
        BufferedReader reader = null;
        /*
         * BufferedReader for reading the level
         */
        reader = loadLevel(reader,level);
        loadMap(reader,gameYSize);
    }
    
    /**
     * Gets the collect ball map.
     *
     * @return the collect ball map
     */
    public static CollectBall getCollectBallMap() {
		return collectBallMap;
	}
	
	/**
	 * Sets the collect ball map.
	 *
	 * @param collectBallMap the new collect ball map
	 */
	public static void setCollectBallMap(CollectBall collectBallMap) {
		Map.collectBallMap = collectBallMap;
	}
  
    /**
     * Gets the mapmatrix.
     *
     * @return the mapmatrix
     */
    public static Ball[][] getMapmatrix() {
		return mapMatrix;
	}
    
    /**
     * Load coordinate.
     *
     * @param xMap the x map
     * @param yMap the y map
     * @param row the row
     * @param col the col
     * @param color the color
     * @param map the map
     * @param index the index
     */
    private void loadCoordinate(int xMap, int yMap, int row, int col, int color, Ball[][] map,int index) {    	 
      	 map[row][col]= new Ball(xMap,yMap,Ball.BOBBLE_SIZE,Ball.BOBBLE_SIZE,color,index); /*caricamento matrice*/
    }
    
    /**
     * Load level.
     *
     * @param reader the reader
     * @param level the level
     * @return the buffered reader
     */
    @SuppressWarnings("unused")
	private BufferedReader loadLevel(BufferedReader reader, File level) {
    	if(level!=null) {       	    
			
		    //String filePath = new File("").getAbsolutePath(); 
		    //System.out.println(filePath+level.getName());
		    InputStream in = getClass().getResourceAsStream("/"+level.getName()); 
		    reader = new BufferedReader(new InputStreamReader(in));
		    if(reader==null) {
		    	
		    }
		    //System.out.println("Creato livello");        	    
		} else {
		   // String filePath = new File("").getAbsolutePath();
		    InputStream in = getClass().getResourceAsStream("/res/map/level1.txt"); 
		    reader = new BufferedReader(new InputStreamReader(in));
		    
		    
		}    	
		return reader;    	
    }
    
    /**
     * Load map.
     *
     * @param reader the reader
     * @param gameYSize the game Y size
     */
    private void loadMap(BufferedReader reader, int gameYSize) {    	
    	int posLine = 0;
    	CollectBall.flyngPoint=0;
    	
    	try {
            line = reader.readLine();
    	} catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
    	}   
    	
    	while(line!=null) {
            for(int i = 0; i < line.length(); i++) {
                     
                    char elem = line.charAt(i);
                    String checkElem = String.valueOf(elem);
                    int posChar = i;
                    if(posChar == 13) {
                            posChar = 0;
                    }
                    
                    int readBobble=0;
                    
                    switch(checkElem) { 
                    case "0":
                            readBobble = 0;
                            break;
                    case "1": 
                            readBobble = 1;
                            break;
                    case "2":
                            readBobble = 2;
                            break;
                    case "3":
                            readBobble = 3;
                            break;
                    case "4":
                            readBobble = 4;
                            break;
                    default:
                            break;
                     
                    }
               
                    switch(posLine) {
                            case 0:                                   
                                    lineDimensionY = gameYSize;                                   
                                    break;
                            case 1:                                  
                                    lineDimensionY = gameYSize+RADIUS;
                                    break;
                            case 2:                                    
                                    lineDimensionY = gameYSize+(2*RADIUS);
                                    break;
                            case 3:                                   
                                    lineDimensionY = gameYSize+(3*RADIUS);
                                    break;
                            case 4:                                    
                                    lineDimensionY = gameYSize+(4*RADIUS);
                                    break;
                            case 5:                                   
                                    lineDimensionY = gameYSize+(5*RADIUS);
                                    break;
                            case 6:
                            		lineDimensionY = gameYSize+(6*RADIUS);
                            		break;
                            case 7:
                        			lineDimensionY = gameYSize+(7*RADIUS);
                        			break;		
                            case 8:
                    				lineDimensionY = gameYSize+(8*RADIUS);
                    				break;	                        	
                            default:
                                    break;
                    }
                    
                    switch(posChar) {
                    case 0:
                            lineDimensionX = RADIUS;                            
                            break;
                    case 1:
                            lineDimensionX = 2*RADIUS;                            
                            break;
                    case 2:
                            lineDimensionX = 3*RADIUS;                            
                            break;
                    case 3:
                            lineDimensionX = 4*RADIUS;                           
                            break;
                    case 4:
                            lineDimensionX = 5*RADIUS;
                            break;
                    case 5:
                            lineDimensionX = 6*RADIUS;                           
                            break;
                    case 6:
                            lineDimensionX = 7*RADIUS;                          
                            break;
                    case 7:
                            lineDimensionX = 8*RADIUS;
                            break;
                    case 8:
                        	lineDimensionX = 9*RADIUS;                       	
                        	break;
                    case 9:
                			lineDimensionX = RADIUS_NINE;                			
                			break;
                    case 10:
                    		lineDimensionX = 11*RADIUS+2;                    		
                    		break;
                    case 11:
            				lineDimensionX = 12*RADIUS+2;
            				break;
                    case 12:
        					lineDimensionX = 13*RADIUS+2;        					
        					break;                   
                    default:
                            break;           
            }
                    
            ballMap = new Ball(lineDimensionX,lineDimensionY,Ball.BOBBLE_SIZE,Ball.BOBBLE_SIZE,readBobble,index);
            loadCoordinate(lineDimensionX,lineDimensionY, posLine, posChar, readBobble, mapMatrix,index++);
            
            posX[posChar] = lineDimensionX;
            collectBallMap.addBall(ballMap);              
            
            }
            
            try {
                    line = reader.readLine();
            } catch (IOException e) {                   
                    e.printStackTrace();
            }
            posLine++;
      }
    }
    
    
    
}
