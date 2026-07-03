package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import model.Astronauta.Astronauta;
import model.Stanza.Generatore;
import model.Stanza.Giardino;
import model.Stanza.Filtratore;

public class TempoImpl implements Tempo, Serializable {
    private static final long serialVersionUID = -8976023480567730812L;
    private Calendar cal = Calendar.getInstance();
    private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    final Astronauta astronauta;
    
    private CondizioneAtmosferica condizioneAtmosferica = CondizioneAtmosferica.CALMO;
    private static final Integer MOLTIPLICATORE = 5;
    private Integer oreDaUltimoCambiamentoAtmosferico = 0;

    private Integer ora = MINORE;
    
    public TempoImpl(final Astronauta astronauta) {
        this.astronauta = astronauta;
        try {
            Date myDate = format1.parse(STARTINGDAY);
            cal.setTime(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public TempoImpl(final Astronauta astronauta, final String startingDay, final CondizioneAtmosferica condizioneAtmosferica, final Integer oreDaUltimoCambiamentoAtmosferico, final Integer ora) {
        this.astronauta = astronauta;
        try {
            Date myDate = format1.parse(startingDay);
            cal.setTime(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.condizioneAtmosferica = condizioneAtmosferica;
        this.oreDaUltimoCambiamentoAtmosferico = oreDaUltimoCambiamentoAtmosferico;
        this.ora = ora;
    }
    /**
     * Getter method for the Date
     * @return the String value of the Date
     */
    public String getDate() {
        return cal.toInstant().toString().substring(0, 10);
    }

    /**
     * Method to increase the hours in the game world
     * @param ore hours passed
     */
    @Override
    public void passaOre(final Integer ore){
        Integer incrementoGiorno;
        this.ora += ore;
        incrementoGiorno = fixOra();
        fixGiorno(incrementoGiorno);
        Filtratore.getLog().passanoOre(ore, this.condizioneAtmosferica);
        this.astronauta.getParametri().incrementaOre(ore);
        for (int i = 0 ; i < ore; i++) {
            Generatore.getLog().riduciCarica();
        }
        Giardino.getLog().passanoOre(ore);
        influenzaCondizioneAtmosferica(ore);
    }
    
    /**
     * Algorithm for the management of atmospheric conditions
     * @param incremento amount of hours passed
     */
    public Optional<CondizioneAtmosferica> influenzaCondizioneAtmosferica(final Integer incremento) {
        oreDaUltimoCambiamentoAtmosferico += incremento;
        if (RangeRandom.getSuccesso(oreDaUltimoCambiamentoAtmosferico * MOLTIPLICATORE) == 1) {
            RangeRandom<CondizioneAtmosferica> rr2 = new RangeRandom<CondizioneAtmosferica>(CondizioneAtmosferica.values(), CondizioneAtmosferica.getProbabilitaAssociate());
            CondizioneAtmosferica nuovaCondizione = rr2.getARandom();
            if (!nuovaCondizione.getNome().equals(condizioneAtmosferica.getNome())) {
                oreDaUltimoCambiamentoAtmosferico = 0;
                this.condizioneAtmosferica = nuovaCondizione;
                Filtratore.getLog().cambiaStati(this.condizioneAtmosferica);
                return Optional.of(this.condizioneAtmosferica);
            }
        }
        return Optional.empty();
    }

    /**
     * Method to fix the hour
     */
    @Override
    public Integer fixOra() {
        Integer incrementoGiorno = 0;
        while (ora >= MAXORE) {
            ora -= MAXORE;
            incrementoGiorno ++;
        }
        return incrementoGiorno;
    }
    /**
     * Method to fix the day
     */
    @Override
    public void fixGiorno(final Integer aumento) {
        cal.add(Calendar.DATE, aumento);
    }
    /**
     * Getter method for the current hour
     */
    @Override
    public Integer getOra() {
        return ora;
    }

    @Override
    public String toString() {
        return "Ore: " + ora + " del: " + format1.format(cal.getTime()) + " e: " + this.condizioneAtmosferica.getNome();
    }
    /**
     * Method used to increase astronaut oxygen spending one hour
     */
    public void ristoro() {
        Random r = new Random();
        astronauta.getParametri().modificaOssigeno(r.nextInt(20) + 80);
    }
    
    /**
     * Getter Method for Atmospheric conditions
     * @return the Atmospheric conditions
     */
    public CondizioneAtmosferica getCondizioneAtmosferica() {
        return this.condizioneAtmosferica;
    }
}
