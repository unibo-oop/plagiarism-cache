package model.users;

public class ClientsImpl extends PeopleImpl implements Clients {

    private int mqStructure;

    public ClientsImpl(final String CF_PIVA, final String name, final String address, final String city, final int cap, final String tel, final String email, final int mqStructure) {
        super(CF_PIVA, name, address, city, cap, tel, email);
        this.mqStructure = mqStructure;
    }

    /**
     * @return dimension of client's structure in square meters
     */
    @Override
    public int getMqStructure() {
        return this.mqStructure;
    }

}
