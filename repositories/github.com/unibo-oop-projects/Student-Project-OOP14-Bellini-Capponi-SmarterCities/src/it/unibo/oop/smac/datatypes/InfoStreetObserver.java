package it.unibo.oop.smac.datatypes;

import java.util.Optional;

/**
 * Classe che fornisce metodi che restituiscono Optional contenenti i dati su di uno specifico
 * osservatore. Per l'implementazione della classe e' stato utilizzato il pattern Builder.
 * 
 * @author Federico Bellini
 */
public final class InfoStreetObserver implements IInfoStreetObserver {

  /**
   * Campi di tipo Optional<> contenenti le informazioni sull'osservatore.
   */
  private final Optional<IStreetObserver> streetObserver;
  private final Optional<Integer> nOfSightLastHour;
  private final Optional<Integer> nOfSightToday;
  private final Optional<Integer> nOfSightLastWeek;
  private final Optional<Integer> nOfSightLaatMonth;
  private final Optional<Integer> totalNOfSight;
  private final Optional<Float> averageSpeedToday;
  private final Optional<Float> averageSpeedLastWeek;
  private final Optional<Float> averageSpeedLastMonth;
  private final Optional<Float> maxSpeedToday;
  private final Optional<Float> maxCarRateToday;

  /**
   * Costruttore privato della classe.
   */
  private InfoStreetObserver(final IStreetObserver stObserver, final Integer nSightLastHour,
      final Integer nSightToday, final Integer nSightLastWeek, final Integer nSightLaatMonth,
      final Integer totalSightis, final Float avSpeedToday, final Float avSpeedLastWeek,
      final Float avSpeedLastMonth, final Float mSpeedToday, final Float mCarRateToday) {

    super();
    this.streetObserver = Optional.ofNullable(stObserver);
    this.nOfSightLastHour = Optional.ofNullable(nSightLastHour);
    this.nOfSightToday = Optional.ofNullable(nSightToday);
    this.nOfSightLastWeek = Optional.ofNullable(nSightLastWeek);
    this.nOfSightLaatMonth = Optional.ofNullable(nSightLaatMonth);
    this.totalNOfSight = Optional.ofNullable(totalSightis);
    this.averageSpeedToday = Optional.ofNullable(avSpeedToday);
    this.averageSpeedLastWeek = Optional.ofNullable(avSpeedLastWeek);
    this.averageSpeedLastMonth = Optional.ofNullable(avSpeedLastMonth);
    this.maxSpeedToday = Optional.ofNullable(mSpeedToday);
    this.maxCarRateToday = Optional.ofNullable(mCarRateToday);
  }

  @Override
  public Optional<ICoordinates> getStreetObserverLocation() {
    if (this.streetObserver.isPresent()) {
      return Optional.ofNullable(this.streetObserver.get().getCoordinates());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<String> getStreetObserverID() {
    if (this.streetObserver.isPresent()) {
      return Optional.ofNullable(this.streetObserver.get().getId());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Integer> getnOfSightLastHour() {
    return this.nOfSightLastHour;
  }

  @Override
  public Optional<Integer> getnOfSightToday() {
    return this.nOfSightToday;
  }

  @Override
  public Optional<Integer> getnOfSightLastWeek() {
    return this.nOfSightLastWeek;
  }

  @Override
  public Optional<Integer> getnOfSightLastMonth() {
    return this.nOfSightLaatMonth;
  }

  @Override
  public Optional<Integer> getTotalNOfSight() {
    return this.totalNOfSight;
  }

  @Override
  public Optional<Float> getAverageSpeedToday() {
    return this.averageSpeedToday;
  }

  @Override
  public Optional<Float> getAverageSpeedLastWeek() {
    return this.averageSpeedLastWeek;
  }

  @Override
  public Optional<Float> getAverageSpeedLastMonth() {
    return this.averageSpeedLastMonth;
  }

  @Override
  public Optional<Float> getMaxSpeedToday() {
    return this.maxSpeedToday;
  }

  @Override
  public Optional<Float> getMaxCarRateToday() {
    return this.maxCarRateToday;
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append(" streetObserverLocation=" + this.getStreetObserverLocation())
        .append(" streetObserverID=" + this.getStreetObserverID())
        .append(" nOfSightLastHour=" + this.getnOfSightLastHour())
        .append(" nOfSightToday=" + this.getnOfSightToday())
        .append(" nOfSightLastWeek=" + this.getnOfSightLastWeek())
        .append(" nOfSightLaatMonth=" + this.getnOfSightLastMonth())
        .append(" totalNOfSight=" + this.getTotalNOfSight())
        .append(" averageSpeedToday=" + this.getAverageSpeedToday())
        .append(" averageSpeedLastWeek=" + this.getAverageSpeedLastWeek())
        .append(" averageSpeedLastMonth=" + this.getAverageSpeedLastMonth())
        .append(" maxSpeedToday=" + this.getMaxSpeedToday())
        .append(" maxCarRateToday=" + this.getMaxCarRateToday()).toString();
  }

  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result
        + ((averageSpeedLastMonth == null) ? 0 : averageSpeedLastMonth.hashCode());
    result = prime * result
        + ((averageSpeedLastWeek == null) ? 0 : averageSpeedLastWeek.hashCode());
    result = prime * result + ((averageSpeedToday == null) ? 0 : averageSpeedToday.hashCode());
    result = prime * result + ((maxCarRateToday == null) ? 0 : maxCarRateToday.hashCode());
    result = prime * result + ((maxSpeedToday == null) ? 0 : maxSpeedToday.hashCode());
    result = prime * result + ((nOfSightLaatMonth == null) ? 0 : nOfSightLaatMonth.hashCode());
    result = prime * result + ((nOfSightLastHour == null) ? 0 : nOfSightLastHour.hashCode());
    result = prime * result + ((nOfSightLastWeek == null) ? 0 : nOfSightLastWeek.hashCode());
    result = prime * result + ((nOfSightToday == null) ? 0 : nOfSightToday.hashCode());
    result = prime * result + ((streetObserver == null) ? 0 : streetObserver.hashCode());
    result = prime * result + ((totalNOfSight == null) ? 0 : totalNOfSight.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || obj instanceof InfoStreetObserver) {
      return false;
    }
    final InfoStreetObserver other = (InfoStreetObserver) obj;
    if (!getAverageSpeedLastMonth().equals(other.getAverageSpeedLastMonth())
        || !getAverageSpeedLastWeek().equals(other.getAverageSpeedLastWeek())
        || !getAverageSpeedToday().equals(other.getAverageSpeedToday())) {
      return false;
    }
    if (!getMaxCarRateToday().equals(other.getMaxCarRateToday())
        || !getMaxSpeedToday().equals(other.getMaxSpeedToday())) {
      return false;
    }
    if (!getnOfSightLastHour().equals(other.getnOfSightLastHour())
        || !getnOfSightLastMonth().equals(other.getnOfSightLastMonth())
        || !getnOfSightLastWeek().equals(other.getnOfSightLastWeek())
        || !getnOfSightToday().equals(other.getnOfSightToday())
        || !getTotalNOfSight().equals(other.getTotalNOfSight())) {
      return false;
    }
    if (streetObserver == null || other.streetObserver != null) {
      return false;
    } else if (!streetObserver.equals(other.streetObserver)) {
      return false;
    }
    return true;
  }

  /**
   * Classe statica innestata Builder della classe InfoStreetObserver. Questa classe svolge il
   * compito di creare un'oggetto della classe InfoStreetObserver.
   * 
   * @author Federico Bellini
   */
  public static class Builder {
    private IStreetObserver stObserver;
    private Integer nSightLastHour;
    private Integer nSightToday;
    private Integer nSightLastWeek;
    private Integer nSightLaatMonth;
    private Integer totalSights;
    private Float avSpeedToday;
    private Float avSpeedLastWeek;
    private Float avSpeedLastMonth;
    private Float mSpeedToday;
    private Float mCarRateToday;

    /**
     * Imposta l'IStreetObserver che ha compiuto l'avvistamento.
     * 
     * @param streetObserver
     *          L'IStreetObserver che ha compiuto l'avvistamento.
     * @return Il builder stesso.
     */
    public Builder streetObserver(final IStreetObserver streetObserver) {
      this.stObserver = streetObserver;
      return this;
    }

    /**
     * Imposta il numero di avvitamenti fatti nell'ultima ora.
     * 
     * @param nOfSightLastHour
     *          Il numero di avvitamenti fatti nell'ultima ora.
     * @return Il builder stesso.
     */
    public Builder nOfSightLastHour(final int nOfSightLastHour) {
      this.nSightLastHour = nOfSightLastHour;
      return this;
    }

    /**
     * Imposta il numero di avvitamenti fatti oggi.
     * 
     * @param nOfSightToday
     *          Imposta il numero di avvitamenti fatti oggi.
     * @return Il builder stesso.
     */
    public Builder nOfSightToday(final int nOfSightToday) {
      this.nSightToday = nOfSightToday;
      return this;
    }

    /**
     * Imposta il numero di avvitamenti fatti nell'ultima settimana.
     * 
     * @param nOfSightLastWeek
     *          Imposta il numero di avvitamenti fatti nell'ultima settimana.
     * @return Il builder stesso.
     */
    public Builder nOfSightLastWeek(final int nOfSightLastWeek) {
      this.nSightLastWeek = nOfSightLastWeek;
      return this;
    }

    /**
     * Imposta il numero di avvitamenti fatti nell'ultimo mese.
     * 
     * @param nOfSightLaatMonth
     *          Il numero di avvitamenti fatti nell'ultimo mese.
     * @return Il builder stesso.
     */
    public Builder nOfSightLastMonth(final int nOfSightLaatMonth) {
      this.nSightLaatMonth = nOfSightLaatMonth;
      return this;
    }

    /**
     * Imposta il numero totale di avvistamenti.
     * 
     * @param totalNOfSight
     *          Il numero totale di avvistamenti
     * @return Il builder stesso.
     */
    public Builder totalNOfSight(final int totalNOfSight) {
      this.totalSights = totalNOfSight;
      return this;
    }

    /**
     * Imposta la velocita' media registrata oggi.
     * 
     * @param averageSpeedToday
     *          La velocita' media registrata oggi.
     * @return Il builder stesso.
     */
    public Builder averageSpeedToday(final float averageSpeedToday) {
      this.avSpeedToday = averageSpeedToday;
      return this;
    }

    /**
     * Imposta la velocita' media registrata nell'ultima settimana.
     * 
     * @param averageSpeedLastWeek
     *          La velocita' media registrata nell'ultima settimana.
     * @return Il builder stesso.
     */
    public Builder averageSpeedLastWeek(final float averageSpeedLastWeek) {
      this.avSpeedLastWeek = averageSpeedLastWeek;
      return this;
    }

    /**
     * Imposta la velocita' media registrata nell'ultimo mese.
     * 
     * @param averageSpeedLastMonth
     *          La velocita' media registrata nell'ultimo mese.
     * @return Il builder stesso.
     */
    public Builder averageSpeedLastMonth(final float averageSpeedLastMonth) {
      this.avSpeedLastMonth = averageSpeedLastMonth;
      return this;
    }

    /**
     * Imposta la velocita' massima registrata oggi.
     * 
     * @param maxSpeedToday
     *          La velocita' massima registrata oggi.
     * @return Il builder stesso.
     */
    public Builder maxSpeedToday(final float maxSpeedToday) {
      this.mSpeedToday = maxSpeedToday;
      return this;
    }

    /**
     * Imposta l'ora di maggior affluenza di mezzi.
     * 
     * @param maxCarRateToday
     *          L'ora in cui si è registrata la maggior affluenza di mezzi.
     * @return Il builder stesso.
     */
    public Builder maxCarRateToday(final float maxCarRateToday) {
      this.mCarRateToday = maxCarRateToday;
      return this;
    }

    /**
     * Costruisce l'oggetto della classe InfoStreetObserver con i valori impostati.
     * 
     * @return L'oggetto della classe InfoStreetObserver appena costruito.
     */
    public InfoStreetObserver build() {
      return new InfoStreetObserver(this.stObserver, this.nSightLastHour, this.nSightToday,
          this.nSightLastWeek, this.nSightLaatMonth, this.totalSights, this.avSpeedToday,
          this.avSpeedLastWeek, this.avSpeedLastMonth, this.mSpeedToday, this.mCarRateToday);
    }

  }

}
