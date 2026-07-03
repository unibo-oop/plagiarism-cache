package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Enums used in the builders.
 *
 */
public class Enums {
    /**
     * Various types of wards.
     */
    public enum WardTypes {
        /**
         * 
         */
        MEDICINE("Medicine"),
        /**
         * 
         */
        GINECOLOGY("Ginecology"),
        /**
         * 
         */
        CARDIOLOGY("Cardiology"),
        /**
         * 
         */
        ORTOPEDICS("Ortopedics"),
        /**
         * 
         */
        PEDIATRICS("Pediatrics");

        private final String nome;

        // Reverse-lookup map
        private static final Map<String, WardTypes> LOOKUP = new HashMap<String, WardTypes>();

        static {
            for (final WardTypes w : WardTypes.values()) {
                LOOKUP.put(w.getNome(), w);
            }
        }

        WardTypes(final String nome) {
            this.nome = nome;
        }

        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getWardNames() {
            final List<String>  arr = new ArrayList<>();
            for (final WardTypes elem : WardTypes.values()) {
                arr.add(elem.nome);
            }
            return arr.toArray(new String[0]);
        }
        /**
         * name getter.
         * @return nome
         */
        public String getNome() {
            return this.nome;
        }
        /**
         * returns a Ward name from its string value using a reverse lookup map.
         * @param ward s
         * @return priority
         */
        public static WardTypes getFromString(final String ward) {
            final WardTypes wardtype = LOOKUP.get(ward);
            if (wardtype == null) {
                throw new IllegalArgumentException("Not a Ward: " + ward);
            } 
            return wardtype;
        }
    }
    /**
     * Sexes. 
     */
    public enum Sexes {
        /**
         * 
         */
        MALE("Male"),
        /**
         * 
         */
        FEMALE("Female"),
        /**
         * 
         */
        OTHER("Other");

        private final String sex;

        // Reverse-lookup map
        private static final Map<String, Sexes> LOOKUP = new HashMap<String, Sexes>();

        static {
            for (final Sexes s : Sexes.values()) {
                LOOKUP.put(s.getSex(), s);
            }
        }
        Sexes(final String sex) {
            this.sex = sex;
        }

        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getSexNames() {
            final List<String>  arr = new ArrayList<>();
            for (final Sexes elem : Sexes.values()) {
                arr.add(elem.sex);
            }
            return arr.toArray(new String[0]);
        }
        /**
         * sex getter.
         * @return sex
         */
        public String getSex() {
            return sex;
        }
        /**
         * returns a Sex from its string value using a reverse lookup map.
         * @param sex s
         * @return priority
         */
        public static Sexes getFromString(final String sex) {
            final Sexes sexx = LOOKUP.get(sex);
            if (sexx == null) {
                throw new IllegalArgumentException("Not a Sex: " + sex);
            } 
            return sexx;
        }
    }
    /**
     * Priorities.
     */
    public enum Priorities {
        /**
         * 
         */
        LOW("Low", 0),
        /**
         * 
         */
        MEDIUM("Medium", 1),
        /**
         * 
         */
        HIGH("High", 2),
        /**
         * 
         */
        EMERGENCY("Emergency", 3);

        private final String prio;
        private final int level;

        // Reverse-lookup map
        private static final Map<String, Priorities> LOOKUP = new HashMap<String, Priorities>();

        static {
            for (final Priorities p : Priorities.values()) {
                LOOKUP.put(p.getPrio(), p);
            }
        }

        Priorities(final String prio, final int level) {
            this.prio = prio;
            this.level = level;
        }
        /**
         * checks if priority is valid.
         * @param prio String
         * @return boolean
         */

        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getPrioNames() {
            final List<String>  arr = new ArrayList<>();
            for (final Priorities elem : Priorities.values()) {
                arr.add(elem.prio);
            }
            return arr.toArray(new String[0]);
        }
        /**
         * priority getter.
         * @return priority
         */
        public String getPrio() {
            return prio;
        }
        /**
         * level getter.
         * @return level
         */
        public int getLevel() {
            return level;
        }
        /**
         * returns a Priority from its string value using a reverse lookup map.
         * @param prio p
         * @return priority
         */
        public static Priorities getFromString(final String prio) {
            final Priorities priority = LOOKUP.get(prio);
            if (priority == null) {
                throw new IllegalArgumentException("Not a Priority: " + prio);
            } 
            return priority;
        }
    }
    /**
     * doctor specializations.
     *
     */
    public enum Specializations {
        /**
         * 
         */
        HEARTSURGEON("Heart Surgeon"),
        /**
         * 
         */
        GINECOLOGYST("Ginecologyst"),
        /**
         * 
         */
        DERMATOLOGIST("Dermatologist"),
        /**
         * 
         */
        NEUROLOGIST("Neurologist"),
        /**
         * 
         */
        ONCOLOGIST("Oncologist"),
        /**
         * 
         */
        ORTHOPEDIC("Orthopedic"),
        /**
         * 
         */
        OTORHINOLARYNGOLOGIST("Otorhinolaryngologist"),
        /**
         * 
         */
        PEDIATRIACIAN("Pediatrician"),
        /**
         * 
         */
        PSYCHIATRIST("Psychiatrist");

        private final String spec;

        // Reverse-lookup map
        private static final Map<String, Specializations> LOOKUP = new HashMap<String, Specializations>();

        static {
            for (final Specializations s : Specializations.values()) {
                LOOKUP.put(s.getSpec(), s);
            }
        }

        Specializations(final String spec) {
            this.spec = spec;
        }

        /**
         * returns all elements of the enum in string form.
         * @return array of strings
         */
        public static String[] getSpecNames() {
            final List<String>  arr = new ArrayList<>();
            for (final Specializations elem : Specializations.values()) {
                arr.add(elem.spec);
            }
            return arr.toArray(new String[0]);
        }
        /**
         * spec getter.
         * @return spec
         */
        public String getSpec() {
            return spec;
        }
        /**
         * returns a Specialization from its string value using a reverse lookup map.
         * @param spec s
         * @return priority
         */
        public static Specializations getFromString(final String spec) {
            final Specializations specializ = LOOKUP.get(spec);
            if (specializ == null) {
                throw new IllegalArgumentException("Not a Specialization: " + spec);
            } 
            return specializ;
        }
    }
}

