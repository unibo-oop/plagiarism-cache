package ala.models;

/**
 * DebugInformations class.
 * 
 */
public class DebugInformations implements DebugFeature {

     //Attributes:
     //Counters for debug label to update on the game loop
     private int frameCount;
     private int fpsCurrent;
     private long prevTime;

     //Constructor:
     public DebugInformations() {
         this.frameCount = 0;
         this.fpsCurrent = 0;
         this.prevTime = -1;
     }

     //getters&Setters:
     public final int getFpsCurrent() {
        return fpsCurrent;
    }

    public final void setFpsCurrent(final int fpsCurrent) {
        this.fpsCurrent = fpsCurrent;
    }

    //Methods:
    // calculate fps:
    /**
     * Method that calcolate FPS.
     * 
     */
    @Override
    public final void calculateFPS() {
        this.frameCount++;
        long currTime = System.currentTimeMillis();
        if (currTime - prevTime >= 1000) {
              // get current fps
              fpsCurrent = frameCount;
              // reset counter every second
              prevTime = currTime;
              frameCount = 0;
        }
    }
}
