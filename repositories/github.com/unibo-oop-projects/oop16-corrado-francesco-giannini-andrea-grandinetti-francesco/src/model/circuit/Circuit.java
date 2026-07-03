package model.circuit;

import java.util.List;

import model.car.Car;

/**
 * Interface to manage the length of every piece of the track,the circuit dimension
 * and if a determinate piece of the track is a turn or not. 
 */
public interface Circuit {
    /**
     * @return the dimension of the Circuit
     */
    int getTotalTracks();
    /**
     * @param trackNumber one of piece of the track
     * @return the length of the parameter 
     */
    int getTrackLengthByTrackNumber(int trackNumber);
    /**
     * 
     * @param trackNumber one of piece of the track
     * @return if the parameter is a turn
     */
    boolean isATurn(int trackNumber);
    /**
     * @param car the car
     * @return the length of the track where the car is
     */
    int getTrackLengthByCar(Car car);
    /**
     * 
     * @param car car
     * @return if the car is the last track
     */
    boolean isBoxTrack(Car car);
    /**
     * Getter.
     * @param car car
     * @return the track's width of the car
     */
    int getTrackWidthByCar(Car car);
    /**
     * Getter.
     * @param num track number
     * @return the track's width
     */
    int getTrackWidthByTrackNumber(int num);
    /**
     * 
     * @param car car
     * @return if the car is at the finishing line
     */
    boolean isAtFinishLine(Car car);
    /**
     * Getter.
     * @return the start line track
     */
    int getStartLine();
    /**
     * 
     * @param car car 
     * @param carList cars in the circuit
     * @return the car in front of the Car car 
     */
    Car prevCarAligned(Car car, List<Car> carList);
}
