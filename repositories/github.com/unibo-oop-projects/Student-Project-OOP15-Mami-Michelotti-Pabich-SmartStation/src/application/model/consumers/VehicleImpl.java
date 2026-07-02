package application.model.consumers;

import application.model.services.Fuel;

/**
* Implements the Vehicle interface.
* @author Alessandro Mami
* 
*/
public class VehicleImpl implements Vehicle {
    
    /** 
     * Vehicle's attributes declaration.
     */
    private Fuel type;
    private int request;
    private int received;
    
    /**
     * Constructor for the VehicleImpl that builds avery vehicle.
     * @param Main attributes: type and request.
     */
    public VehicleImpl(final Fuel type, final int request) {
        this.type = type;
        this.request = request;
        this.received = 0;              
    }
    
    //GETTERS AND SETTERS OF MAIN ATTRIBUTES
    @Override
    public Fuel getFuel() {
    	return this.type;
    }
    
    @Override
    public void setFuel(final Fuel fuel) {
    	this.type = fuel;	
    }
    
    @Override
    public int getRequest() {  
    	return this.request;
    }
    
    @Override
    public void setRequest(final int request) {
    	this.request = request;	
    }
    
    //REQUEST ADDER
    @Override
    public void serve() {
    	this.received += request;		
    }
}
