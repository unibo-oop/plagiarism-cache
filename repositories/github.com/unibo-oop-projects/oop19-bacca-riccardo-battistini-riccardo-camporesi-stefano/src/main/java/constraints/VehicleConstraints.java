package constraints;

public final class VehicleConstraints {

    /**
     * 
     */
    public static final int AREA_OF_CONTROL = 15;

    /**
     * 
     */
    public static final int CONST_ACCELERATION = 1;

    /**
     * 
     */
    public static final int MIN_VELOCITY = 0;


    public enum Status {
        /**
         * 
         */
        RUNNING, 

        /**
         * 
         */
        IDLE, 

        /**
         * 
         */
        ENTERING, 

        /**
         * 
         */
        EXITING
      }

    private VehicleConstraints() { }



}
