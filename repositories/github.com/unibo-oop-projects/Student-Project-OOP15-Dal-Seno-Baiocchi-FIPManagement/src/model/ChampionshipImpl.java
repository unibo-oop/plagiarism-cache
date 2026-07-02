package model;
/**
 * Model a championship
 * @author lucadalseno
 *
 */
public class ChampionshipImpl implements Championship {
    /**
     * 
     */
    private static final long serialVersionUID = -2985081041574393034L;
    private final Division division;
    private final Zone zone;

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}
    /**
     * redifine equals method
     * a Championship is equals to another Championship 
     * if both division & zone are equal
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChampionshipImpl other = (ChampionshipImpl) obj;
		if (division != other.division)
			return false;
		if (zone != other.zone)
			return false;
		return true;
	}
	public ChampionshipImpl(Division division,Zone zone) {
        this.division = division;
        this.zone = zone;
    }

    @Override
    public String toString() {
	return division.toString() + " " + zone.toString();
    }
	
    @Override
    public Division getDivision() {
        return this.division;
    }
    @Override
    public Zone getZone() {
        return this.zone;
    }
}