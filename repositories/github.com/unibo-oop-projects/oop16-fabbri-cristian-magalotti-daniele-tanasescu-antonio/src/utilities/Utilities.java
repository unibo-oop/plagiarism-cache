package utilities;

import java.util.Random;

public class Utilities {

    //we don't need Utilities objects! Singleton docet.
    private Utilities() {
        // TODO Auto-generated constructor stub
    }
    
    public static int indexPositionMaxInIntArray(int[] array){
        int maxElement = array[0];
        int maxPosition = 0;
        int currentPosition = 0;
        for(int elem : array){
            if(elem > maxElement){
                maxElement = elem;
                maxPosition = currentPosition;
            }
            currentPosition++;
        }
        return maxPosition;
    }
    
    public static int getAroundInt(int start, double percentage){
        Random random = new Random();
        double max = start + (start*percentage);
        int difference = (int)(max - start);
        return random.nextInt(difference) + start;
    }
    
    //it assumes all the non-null objects are at the end! (next function!)
    public static int numOfElemsNotNull(Object[] array){
        int max = 0;                                           
        for(Object object : array){
            if(object != null){
                max++;
            }
        }
        
        return max;
    }
    
    //if used with an array like VolatileStatus... in Pokemon, maybe it's helpful to have one more element at the end than the needed ones!
    public static void allNullsAtTheEnd(Object[] array){
        for (int j=0; j<array.length; j++){
            if (array[j]==null){
                int i = j+1;
                while(i<array.length-2 && array[i] == null){
                    i++;
                }
                if(i<array.length-1 && array[i] != null){
                    array[j] = array[i];
                    array[i] = null;
                }
            }
            array[array.length-1] = null;

        }
    }
}
