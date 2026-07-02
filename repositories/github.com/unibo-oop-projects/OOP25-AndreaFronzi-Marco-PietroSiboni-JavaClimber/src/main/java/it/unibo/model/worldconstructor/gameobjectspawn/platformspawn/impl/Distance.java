package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl;

/**
 * Record to encapsulate distance parameters for platform generation.
 * 
 * @param maxDistanceY the maximum vertical distance between platforms
 * @param minDistanceY the minimum vertical distance between platforms
 * @param maxDistanceX the maximum horizontal distance between platforms
 */
public record Distance(double maxDistanceY,
        double minDistanceY,
        double maxDistanceX) {

}
