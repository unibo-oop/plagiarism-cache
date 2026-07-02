package model.users;

public abstract class PeopleImpl implements People {

    private String CF_PIVA;
    private String name;
    private String address;
    private String city;
    private int cap;
    private String tel;
    private String email;

    public PeopleImpl(final String CF_PIVA, final String name, final String address, final String city, final int cap, final String tel, final String email) {
        this.CF_PIVA = CF_PIVA;
        this.name = name;
        this.address = address;
        this.city = city;
        this.cap = cap;
        this.tel = tel;
        this.email = email;
    }

    /**
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return Fiscal Code or P.IVA
     */
    @Override
    public String getCFPIVA() {
        return this.CF_PIVA;
    }

    /**
     * @return address
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * @return city
     */
    @Override
    public String getCity() {
        return this.city;
    }

    /**
     * @return CAP
     */
    @Override
    public int getCAP() {
        return this.cap;
    }

    /**
     * @return telephone
     */
    @Override
    public String getTel() {
        return this.tel;
    }

    /**
     * @return email
     */
    @Override
    public String getEmail() {
        return this.email;
    }
}
