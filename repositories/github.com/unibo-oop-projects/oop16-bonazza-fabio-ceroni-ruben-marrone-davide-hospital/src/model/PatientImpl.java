package model;

import java.io.Serializable;

import util.Enums.Priorities;
import util.Enums.Sexes;

/**
 * 
 * A patient to be cured.
 *
 */
public final class PatientImpl implements Patient, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8833036873397943289L;
    private final String name;
    private final String surname;
    private final String codfis;
    private final Sexes sex;
    private final String disease;
    private String log;
    private final int age;
    private final Priorities priority;
    private boolean isoperated;
    private Doctor doctor;
    private Ward ward;

    private PatientImpl(final String name, final String surname, final String codfis, final Sexes sex, final String disease,
            final String log, final int age, final Priorities priority, final boolean isoperated, final Doctor doctor, final Ward ward) {
        super();
        this.name = name;
        this.surname = surname;
        this.codfis = codfis;
        this.sex = sex;
        this.disease = disease;
        this.log = log;
        this.age = age;
        this.priority = priority;
        this.isoperated = isoperated;
        this.doctor = doctor;
        this.setWard(ward);
    }
    /**
     * builder class.
     */
    public static class Builder {
        private String name1;
        private String surname1;
        private String codfis1;
        private Sexes sex1;
        private String disease1;
        private String log1 = "";
        private int age1;
        private Priorities priority1;
        private final boolean isoperated1 = false; //NOPMD
        private final Doctor doctor1 = null; //NOPMD
        private final Ward ward1 = null; //NOPMD
        /**
         * adds the name. 
         * @param n name
         * @return patient
         */
        public Builder name(final String n) {
            this.name1 = n;
            return this;
        }
        /**
         * adds the surname.
         * @param c surname
         * @return patient
         */
        public Builder surname(final String c) {
            this.surname1 = c;
            return this;
        }
        /**
         * adds the fiscal code. 
         * @param cf fiscal code
         * @return patient
         */
        public Builder codfis(final String cf) {
            this.codfis1 = cf;
            return this;
        }
        /**
         * adds the sex.
         * @param s sex
         * @return patient
         */
        public Builder sex(final String s) {
            this.sex1 = Sexes.getFromString(s);
            return this;
        }
        /**
         * adds the disease. 
         * @param d disease
         * @return patient
         */
        public Builder disease(final String d) {
            this.disease1 = d;
            return this;
        }
        /**
         * adds log, empty by default.
         * @param l log
         * @return patient
         */
        public Builder log(final String l) {
            this.log1 = l;
            return this;
        }
        /**
         * adds the age. 
         * @param a age
         * @return patient
         */
        public Builder age(final int a) {
            this.age1 = a;
            return this;
        }
        /**
         * adds the priority.
         * @param p priority
         * @return patient
         */
        public Builder priority(final String p) {
            this.priority1 = Priorities.getFromString(p);
            return this;
        }
        /**
         * Builds the patient.
         * @return Patient
         * @throws IllegalArgumentException e
         */
        public Patient build() throws IllegalArgumentException {
            if (this.name1.isEmpty() || this.surname1.isEmpty() || this.codfis1.isEmpty() || disease1.isEmpty() || age1 < 0) { 
                    throw new IllegalArgumentException("Invalid Input");
            }
            return new PatientImpl(this.name1, this.surname1, this.codfis1, this.sex1, this.disease1, this.log1, this.age1, this.priority1, this.isoperated1, //NOPMD
                    this.doctor1, this.ward1);
        }
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public String getSurname() {
        return this.surname;
    }
    @Override
    public String getCodfis() {
        return this.codfis;
    }
    @Override
    public String getSex() {
        return this.sex.getSex();
    }
    @Override
    public String getDisease() {
        return this.disease;
    }
    @Override
    public String getLog() {
        if (!this.log.isEmpty()) {
            return this.log;
        } else {
            return "Log assente";
        }
    }
    @Override
    public int getAge() {
        return this.age;
    }
    @Override
    public String getPriorityName() {
        return this.priority.getPrio();
    }
    @Override
    public int getPriorityLevel() {
        return this.priority.getLevel();
    }
    @Override
    public void setOperated(final boolean status) {
        this.isoperated = status;
    }
    @Override
    public boolean isBeingOperated() {
        return this.isoperated;
    }
    @Override
    public void updateLog(final String l) {
        if (log.isEmpty()) {
            this.log = l;
        } else {
            this.log = log + '\n' + l;
        }
    }
    @Override
    public Doctor getDoctor() {
        return this.doctor;
    }
    @Override
    public void setDoctor(final Doctor d) throws IllegalStateException {
        if (this.doctor != null) {
            throw new IllegalStateException("Doctor already assigned");
        }
        this.doctor = d;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codfis == null) ? 0 : codfis.hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PatientImpl other = (PatientImpl) obj;
        if (codfis == null) {
            if (other.codfis != null) {
                return false;
            }
        } else if (!codfis.equals(other.codfis)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return this.name + " " + this.surname + ", Code: " + this.codfis;
    }
    @Override
    public Ward getWard() {
        return this.ward;
    }
    @Override
    public void setWard(final Ward ward) {
        this.ward = ward;
    }
}
