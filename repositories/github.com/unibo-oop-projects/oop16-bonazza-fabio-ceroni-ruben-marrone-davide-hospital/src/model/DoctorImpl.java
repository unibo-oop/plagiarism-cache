package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import util.Enums.Sexes;
import util.Enums.Specializations;

/**
 * 
 * A Doctor.
 *
 */
public final class DoctorImpl implements Doctor, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5869408709054138269L;
    private final String name;
    private final String surname;
    private final String codfis;
    private final Sexes sex;
    private final Specializations specialization;
    private final int age;
    private final List<Patient> assignedPatients;
    private boolean inSurgery;

    private DoctorImpl(final String name, final String surname, final String codfis, final Sexes sex, final Specializations specialization, final int age,
            final List<Patient> assignedPatients, final boolean inSurgery) {
        super();
        this.name = name;
        this.surname = surname;
        this.codfis = codfis;
        this.sex = sex;
        this.specialization = specialization;
        this.age = age;
        this.assignedPatients = assignedPatients;
        this.inSurgery = inSurgery;
    }
    /**
     * 
     * builder class.
     *
     */
    public static class Builder {
        private String name1;
        private String surname1;
        private String codfis1;
        private Sexes sex1;
        private Specializations specialization1;
        private int age1;
        private final List<Patient> assignedPatients1 = new ArrayList<>();
        private final boolean inSurgery = false; //NOPMD
        /**
         * adds the name.
         * 
         * @param n name
         * @return doctor
         */
        public Builder name(final String n) {
            this.name1 = n;
            return this;
        }
        /**
         * adds the surname.
         * 
         * @param c surname
         * @return doctor
         */
        public Builder surname(final String c) {
            this.surname1 = c;
            return this;
        }
        /**
         * adds the fiscal code.
         * 
         * @param cf fiscal code
         * @return doctor
         */
        public Builder codfis(final String cf) {
            this.codfis1 = cf;
            return this;
        }
        /**
         * adds the sex.
         * 
         * @param s sex
         * @return doctor
         */
        public Builder sex(final String s) {
            this.sex1 = Sexes.getFromString(s);
            return this;
        }
        /**
         * adds the age.
         * 
         * @param a age
         * @return doctor
         */
        public Builder age(final int a) {
            this.age1 = a;
            return this;
        }
        /**
         * 
         * @param sp specialization
         * @return doctor
         */
        public Builder specializ(final String sp) {
            this.specialization1 = Specializations.getFromString(sp);
            return this;
        }
        /**
         * Builds the doctor.
         * 
         * @return doctor
         * @throws IllegalStateException e
         */
        public Doctor build() throws IllegalStateException {
            if (this.name1.isEmpty() || this.surname1.isEmpty() || this.codfis1.isEmpty() || age1 < 0) {
                    throw new IllegalArgumentException("Invalid Input");
            }
            return new DoctorImpl(this.name1, this.surname1, this.codfis1, this.sex1, this.specialization1, this.age1, this.assignedPatients1, this.inSurgery); //NOPMD
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
    public String getSpecialization() {
        return this.specialization.getSpec();
    }
    @Override
    public int getAge() {
        return this.age;
    }
    @Override
    public List<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    @Override
    public void assignPatient(final Patient p) throws IllegalStateException {
        this.assignedPatients.forEach(pt -> {
            if (pt.equals(p)) {
                throw new IllegalStateException("Patient already assigned");
            }
        });
        this.assignedPatients.add(p);
    }
    @Override
    public void dismissPatient(final Patient p) throws IllegalStateException {
        if (!this.assignedPatients.contains(p)) {
            throw new IllegalStateException("Patient not assigned");
        }
        this.assignedPatients.remove(p);
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
        final DoctorImpl other = (DoctorImpl) obj;
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
        return this.name + " " + this.surname + ": " + this.getSpecialization() + ", Code: " + this.codfis;
    }
    @Override
    public boolean isInSurgery() {
        return this.inSurgery;
    }
    @Override
    public void setSurgery(final boolean s) {
        this.inSurgery = s;
    }
}
