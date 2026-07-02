package model;

import model.WaveInfo.Difficulty;

/**
 * Generator used to create Waves and elaborate local parameters and values.
 */
public interface WaveInfoGenerator {

	/**
	 * Returns a new WaveInfo tagged with its own number.
	 * Number is used for parameterization.
	 * 
	 * @param waveNUM = number of the Wave
	 * @return a brand new WaveInfo
	 */
    WaveInfo loadWaveStandard(int waveNUM);

    /**
     * Returns Difficulty used to initialize the generator.
     * 
     * @return WaveGenerator Difficulty
     */
    Difficulty getDifficulty();

}
