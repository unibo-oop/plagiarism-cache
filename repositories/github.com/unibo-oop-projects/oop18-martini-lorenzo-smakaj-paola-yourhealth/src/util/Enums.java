package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Enums {
    /**
     * Tipi Prestazione.
     */
    public enum TipoPrestazione {
        /**
         * 
         */
        VISITA("Visita", 0),
        /**
         * 
         */
        ESAME_DIAGNOSTICO("Esame Diagnostico", 1),
        /**
         * 
         */
        CONTROLLO("Controllo", 2);

        private final String tipo;
        private final int codice;

        // Reverse-lookup map
        private static final Map<String, TipoPrestazione> LOOKUP = new HashMap<String, TipoPrestazione>();

        static {
            for (final TipoPrestazione w : TipoPrestazione.values()) {
                LOOKUP.put(w.getTipo(), w);
            }
        }

        TipoPrestazione(final String tipo, final int codice) {
            this.tipo = tipo;
            this.codice = codice;
        }

        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getValoriPrestazioni() {
            final List<String>  arr = new ArrayList<>();
            for (final TipoPrestazione elem : TipoPrestazione.values()) {
                arr.add(elem.tipo);
            }
            return arr.toArray(new String[0]);
        }
        /**
         * type getter.
         * @return tipo
         */
        public String getTipo() {
            return this.tipo;
        }
        /**
         * code getter.
         * @return codice
         */
        public int getCodice() {
            return this.codice;
        }
        /**
         * returns a Performance type from its string value.
         * @param prestazione
         * @return tipoprestazione
         */
        public static TipoPrestazione getFromString(final String prestazione) {
            final TipoPrestazione tipoprestazione = LOOKUP.get(prestazione);
            if (tipoprestazione == null) {
                throw new IllegalArgumentException("Tipo Prestazione non valido: " + prestazione);
            } 
            return tipoprestazione;
        }
    }
    /**
     * Sesso. 
     */
    public enum Sesso {
        /**
         * 
         */
        UOMO("Uomo"),
        /**
         * 
         */
        DONNA("Donna"),
        /**
         * 
         */
        ALTRO("Altro");

        private final String sesso;

        // Reverse-lookup map
        private static final Map<String, Sesso> LOOKUP = new HashMap<String, Sesso>();

        static {
            for (final Sesso s : Sesso.values()) {
                LOOKUP.put(s.getSesso(), s);
            }
        }
        Sesso(final String sesso) {
            this.sesso = sesso;
        }

        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getValoriSesso() {
            final List<String>  arr = new ArrayList<>();
            for (final Sesso elem : Sesso.values()) {
                arr.add(elem.sesso);
            }
            return arr.toArray(new String[0]);
        }
        /**
         * sex getter.
         * @return sesso
         */
        public String getSesso() {
            return sesso;
        }
        /**
         * returns a sex from its string value.
         * @param sesso s
         * @return sex
         */
        public static Sesso getFromString(final String sesso) {
            final Sesso sex = LOOKUP.get(sesso);
            if (sex == null) {
                throw new IllegalArgumentException("Sesso non valido: " + sesso);
            } 
            return sex;
        }
    }
    /**
     * Stato (prestazione).
     */
    public enum Stato {
        /**
         * 
         */
        NON_DISPONIBILE("Non Disponibile", 0),
        /**
         * 
         */
        DISPONIBILE("Disponibile", 1),
        /**
         * 
         */
        PRENOTATA("Prenotata", 2),
        /**
         * 
         */
        ESEGUITA("Eseguita", 3);

        private final String stato;
        private final int codice;

        // Reverse-lookup map
        private static final Map<String, Stato> LOOKUP = new HashMap<String, Stato>();

        static {
            for (final Stato p : Stato.values()) {
                LOOKUP.put(p.getStato(), p);
            }
        }

        Stato(final String stato, final int codice) {
            this.stato = stato;
            this.codice = codice;
        }
        
        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getValoriStato() {
            final List<String>  arr = new ArrayList<>();
            for (final Stato elem : Stato.values()) {
                arr.add(elem.stato);
            }
            return arr.toArray(new String[0]);
        }
        
        /**
         * state getter.
         * @return stato
         */
        public String getStato() {
            return stato;
        }
        
        /**
         * code getter.
         * @return codice
         */
        public int getCodice() {
            return codice;
        }
        
        /**
         * returns a state from its string value.
         * @param stato p
         * @return stato
         */
        public static Stato getFromString(final String stato) {
            final Stato p = LOOKUP.get(stato);
            if (p == null) {
                throw new IllegalArgumentException("Stato non valido: " + stato);
            } 
            return p;
        }
    }
    /**
     * Ruolo Dottore.
     *
     */
    public enum Ruolo {
        /**
         * 
         */
        INFERMIERE("Infermiere"),
        /**
         * 
         */
        ALLERGOLOGO("Allergologo"),
        /**
         * 
         */
        PNEUMOLOGO("Pneumologo"),
        /**
         * 
         */
        ENDOCRINOLOGO("Endocrinologo"),
        /**
         * 
         */
        GINECOLOGO("Ginecologo"),
        /**
         * 
         */
        OCULISTA("Oculista"),
        /**
         * 
         */
        REUMATOLOGO("Reumatologo"),
        /**
         * 
         */
        FISIATRA("Fisiatra"),
        /**
         * 
         */
        UROLOGO("Urologo"),
        /**
         * 
         */
        CARDIOLOGO("Cardiologo"),
        /**
         * 
         */
        DERMATOLOGO("Dermatologo"),
        /**
         * 
         */
        NEUROLOGO("Neurologo"),
        /**
         * 
         */
        ONCOLOGO("Oncologo"),
        /**
         * 
         */
        ORTOPEDICO("Ortopedico"),
        /**
         * 
         */
        OTORINOLARINGOIATRA("Otorinolaringoiatra"),
        /**
         * 
         */
        PEDIATRA("Pediatra"),
        /**
         * 
         */
        PSICHIATRA("Psichiatra");

        private final String ruolo;

        // Reverse-lookup map
        private static final Map<String, Ruolo> LOOKUP = new HashMap<String, Ruolo>();

        static {
            for (final Ruolo s : Ruolo.values()) {
                LOOKUP.put(s.getRuolo(), s);
            }
        }

        Ruolo(final String ruolo) {
            this.ruolo = ruolo;
        }

        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getValoriRuolo() {
            final List<String>  arr = new ArrayList<>();
            for (final Ruolo elem : Ruolo.values()) {
                arr.add(elem.ruolo);
            }
            return arr.toArray(new String[0]);
        }
        /**
         * role getter.
         * @return ruolo
         */
        public String getRuolo() {
            return ruolo;
        }
        /**
         * returns a Role from its string value.
         * @param ruolo
         * @return r
         */
        public static Ruolo getFromString(final String ruolo) {
            final Ruolo r = LOOKUP.get(ruolo);
            if (r == null) {
                throw new IllegalArgumentException("Ruolo non valido: " + ruolo);
            } 
            return r;
        }
    }
    /**
     * Tipo Macchinario.
     */
    public enum TipoMacchinario {
        /**
         * 
         */
        ECOGRAFO("Ecografo"),
        /**
         * 
         */
        TAC("Tac"),
        /**
         * 
         */
        RISONANZA("Risonanza"),
        /**
         * 
         */
        ENDOSCOPIO("Endoscopio");

        private final String tipomacchinario;

        // Reverse-lookup map
        private static final Map<String, TipoMacchinario> LOOKUP = new HashMap<String, TipoMacchinario>();

        static {
            for (final TipoMacchinario p : TipoMacchinario.values()) {
                LOOKUP.put(p.getTipoMacchinario(), p);
            }
        }

        TipoMacchinario(final String tipomacchinario) {
            this.tipomacchinario = tipomacchinario;
        }
        
        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getValoriTipoMacchinario() {
            final List<String>  arr = new ArrayList<>();
            for (final TipoMacchinario elem : TipoMacchinario.values()) {
                arr.add(elem.tipomacchinario);
            }
            return arr.toArray(new String[0]);
        }
        
        /**
         * machine type getter.
         * @return tipomacchinario
         */
        public String getTipoMacchinario() {
            return tipomacchinario;
        }
        
        /**
         * returns a machine type from its string value.
         * @param tipomacchinario p
         * @return tipomacchinario
         */
        public static TipoMacchinario getFromString(final String tipomacchinario) {
            final TipoMacchinario p = LOOKUP.get(tipomacchinario);
            if (p == null) {
                throw new IllegalArgumentException("Tipo Macchinario non valido: " + tipomacchinario);
            } 
            return p;
        }
    }
    /**
     * Tipo Ambulatorio.
     */
    public enum TipoAmbulatorio {
        /**
         * 
         */
        ORTOPEDIA("Ortopedia"),
        /**
         * 
         */
        ECOGRAFIA("Ecografia"),
        /**
         * 
         */
        RISONANZA("Risonanza"),
        /**
         * 
         */
        OCULISTA("Oculista");

        private final String tipoambulatorio;

        // Reverse-lookup map
        private static final Map<String, TipoAmbulatorio> LOOKUP = new HashMap<String, TipoAmbulatorio>();

        static {
            for (final TipoAmbulatorio p : TipoAmbulatorio.values()) {
                LOOKUP.put(p.getTipoAmbulatorio(), p);
            }
        }

        TipoAmbulatorio(final String tipoambulatorio) {
            this.tipoambulatorio = tipoambulatorio;
        }
        
        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getValoriTipoAmbulatorio() {
            final List<String>  arr = new ArrayList<>();
            for (final TipoAmbulatorio elem : TipoAmbulatorio.values()) {
                arr.add(elem.tipoambulatorio);
            }
            return arr.toArray(new String[0]);
        }
        
        /**
         * machine type getter.
         * @return tipoAmbulatorio
         */
        public String getTipoAmbulatorio() {
            return tipoambulatorio;
        }
        
        /**
         * returns a machine type from its string value.
         * @param tipoAmbulatorio p
         * @return tipoAmbulatorio
         */
        public static TipoAmbulatorio getFromString(final String tipoambulatorio) {
            final TipoAmbulatorio p = LOOKUP.get(tipoambulatorio);
            if (p == null) {
                throw new IllegalArgumentException("Tipo Ambulatorio non valido: " + tipoambulatorio);
            } 
            return p;
        }
    }
}

