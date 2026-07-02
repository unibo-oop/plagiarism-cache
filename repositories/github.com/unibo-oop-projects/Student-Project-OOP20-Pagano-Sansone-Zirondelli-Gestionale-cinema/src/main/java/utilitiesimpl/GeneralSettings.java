package utilitiesimpl;

public final class GeneralSettings {
    /**Describes file separator depends on operative system where application will be run.*/
    public static final String FS = System.getProperty("file.separator"); 

    /**Describes root name directory where application will be run.*/
    public static final String ROOTNAME = ".application";

    /**Describes images directory where application will save images.*/
    public static final String IMAGESDIRNAME = "images";

    /**Describes images directory where application will save images choosed by the users.*/
    public static final String IMAGESSELECTEDDIRNAME = "selected";

    /**Describes data directory where application will save file for storing data.*/
    public static final String DATADIRNAME = "data";

    /**Describes temp directory for temporary operations.*/
    public static final String TEMPDIRNAME = "temp";

    /**Describes start path where application will be initilize. */
    private static  String STARTPATH =  System.getProperty("user.home");

    /**Describes working directory path  where application will run.*/
    private static  String WORKINGDIR = STARTPATH + FS + ROOTNAME; // Working directory where application store dates

    /**Describes images directory path.*/
    public static final String IMAGESDIR = WORKINGDIR +  FS + IMAGESDIRNAME;

    /**Describes data directory path.*/
    public static final String DATADIR = WORKINGDIR +  FS  + DATADIRNAME;

    /**Describes images choosed path by users directory path.*/
    public static final String IMAGESSELECTEDDIR =  WORKINGDIR +  FS  + IMAGESDIRNAME + FS + IMAGESSELECTEDDIRNAME + FS;

    //File options

    /**Describes film file name where films data will be stored.*/
    public static final String FILMSFILE = "FILMS.json";

    /**Describes film file path.*/
    public static final String FILMSPATH = DATADIR + FS + FILMSFILE;

    /**Describes manager ids films file name where managerFilmIds data will be stored.*/
    public static final String MANAGERIDSFILMSFILE = "MANAGERIDSFILMS.json";

    /**Describes manager ids films path where managerFilmIds data will be stored.*/
    public static final String MANAGERIDSFILMSPATH = DATADIR + FS + MANAGERIDSFILMSFILE;

    /**Describes programmed films file name where programmed films data will be stored.*/
    public static final String PROGRAMMEDFILMSFILE = "PROGRAMMEDFILMS.json";

    /**Describes programmed films file path where programmed films data will be stored.*/
    public static final String PROGRAMMEDFILMSPATH = DATADIR + FS + PROGRAMMEDFILMSFILE;


    /**Describes standard cover image name for film if user doesn't choose any images. */
    //Res options
    public static final String IMAGEFILMSTANDARD = "images/filmStandardIco.png";
    /**
     * Standard account.
     */
    public static final String ACCOUNTSTANDARD = "defaultData/Account.json";

    /**
     * Standard films.
     */
    public static final String FILMSSTANDARD = "defaultData/FILMS.json";

    /**
     * Standard programmed films.
     */
    public static final String PROGRAMMEDFILMSTANDARD = "defaultData/PROGRAMMEDFILMS.json";

    /**
     * Standard manager ids films.
     */
    public static final String MANAGERIDSFILMSSTANDARD = "defaultData/MANAGERIDSFILMS.json";

    /**
     * Standard ticket films.
     */
    public static final String TICKETSSTANDARD = "defaultData/Ticket.json";

    /**
     * Ticket file name.
     */
    public static final String TICKET_FILE = "Ticket.json";
    /**
     * Ticket file path.
     */
    public static final String TICKET_FILE_PATH = DATADIR + FS + TICKET_FILE;
    /**
     * Image seat free path.
     */
    public static final String IMAGE_SEAT_FREE = "images/imageSeatFree.png";
    /**
     * Image seat selected path.
     */
    public static final String IMAGE_SEAT_SELECTED = "images/imageSeatSelected.png";
    /**
     * Image seat taken path.
     */
    public static final String IMAGE_SEAT_TAKEN = "images/imageSeatTaken.png";
    /**
     * Image legend path.
     */
    public static final String IMAGE_LEGEND = "images/legend.png";
    /**
     * Account file name.
     */
    public static final String ACCOUNT_FILE = "Account.json";

    /**
     * Account file path.
     */
    public static final String ACCOUNT_FILE_PATH = DATADIR + FS + ACCOUNT_FILE;

    /**Private constructor. This class can't be instantiated. It's used to access paths where application will work.*/
    private GeneralSettings() { }; 

    public static void setStartPath(final String path) {
        STARTPATH = path;
    }

    public static String getWorkingDIR() {
        return WORKINGDIR;
    }
}
