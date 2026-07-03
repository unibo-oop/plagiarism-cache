package controller;

/**
 * metodi generali per acquisire path, etc.
 * 
 * @author Francesco
 *
 */
public class PathSeparatorImpl implements PathSeparator {
    private String separator;
    private String javaHome;
    private String dir;
    private String name;
    private String userHome;

    public PathSeparatorImpl() {
        // TODO Auto-generated constructor stub
        // Restituisce / per Windows e \ per Unix
        this.separator = System.getProperty("file.separator");
        // La directory di installazione di Java
        this.javaHome = System.getProperty("java.home");
        // La directory da cui il comando java e stato invocato
        this.dir = System.getProperty("user.dir");
        // Restituisce la home directory dell’utente che ha lanciato java
        this.userHome = System.getProperty("user.home");
        // Restituisce il nome utente
        this.name = System.getProperty("user.name");
    }

    public void test() {
        // GuiControllerImpl gci = new GuiControllerImpl();
        System.out.println("file.separator= " + this.getSeparator() + " Restituisce \\ per Windows e / per Unix");
        System.out.println("java.home= " + this.getJavaHome() + " La directory di installazione di Java");
        System.out.println("user.dir= " + this.getDir() + " La directory da cui il comando java e stato invocato");
        System.out.println(
                "user.home= " + this.getUserHome() + " Restituisce la home directory dell’utente che ha lanciato java");
        System.out.println("user.name= " + this.getName() + " Restituisce il nome utente");
    }

    @Override
    public String getSeparator() {
        // TODO Auto-generated method stub
        return this.separator;
    }

    @Override
    public String getJavaHome() {
        // TODO Auto-generated method stub
        return this.javaHome;
    }

    @Override
    public String getDir() {
        // TODO Auto-generated method stub
        return this.dir;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.name;
    }

    @Override
    public String getUserHome() {
        // TODO Auto-generated method stub
        return this.userHome;
    }

    public static void main(final String[] args) {
        System.out.println("Proprietà di sistema/system properties");
        PathSeparator psController = new PathSeparatorImpl();
        System.out.println("classe da cui è chiamato = " + psController.getClass());
        psController.test();

    }
}