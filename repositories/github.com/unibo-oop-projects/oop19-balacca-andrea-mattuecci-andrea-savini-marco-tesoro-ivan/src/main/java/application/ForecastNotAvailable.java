package application;

public class ForecastNotAvailable extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 7460974390189198269L;
    
    public ForecastNotAvailable(String msg) {
        super(msg);
    }
}
