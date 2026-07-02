package controller.Client;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.client.Client;
import model.client.ClientImpl;
import model.file.MyFile;
import model.file.MyFileImpl;

public class ControllerClientImpl implements ControllerClient {
    private static final String SEP = System.getProperty("file.separator");
    private static final String DIRUSER = System.getProperty("user.home");
    private static final String NAMEFILE = ("Clients.txt");
    private static final String PATH = (DIRUSER + SEP + NAMEFILE);

    private final List<Client> list = new LinkedList<>();
    private Set<String> lists = new TreeSet<>();
    private MyFile myfile;

    public ControllerClientImpl() {
        this.myfile = new MyFileImpl(PATH);
        this.lists = myfile.fileReader();
        for (var i: lists) {
            this.list.add(new ClientImpl(i));
        }
    }
 
    @Override
    public final Set<String> getAllClient() {
        return lists;
    }

    @Override
    public final void insertClient(final String name, final String surname, final String id) {
        lists.add(name + "." + surname + "." + id);
        list.add(new ClientImpl(name, surname, id));
        myfile.fileWriter(name + "." + surname + "." + id);
    }

    @Override
    public final Client getClient(final String id) {
        for (var i: list) {
            if ((i.getId()).equals(id)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public final Boolean searchClient(final String id) {
       if (myfile.fileSearch(id) != null) {
           return true;
       }
        return false;
    }

    @Override
    public final Boolean deleteLine(final String id) {
        if (this.searchClient(id)) {
            if (myfile.deleteline(id)) {
                return true;
            }
        }
        return false;
    }

}
