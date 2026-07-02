package model.util.storage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

/**
 * Used to save or load object into file system.
 * 
 * @param <X> object Class to work with
 */
public abstract class ObjectManagerImpl<X> implements ObjectManager<X> {

    private final Gson gson;
    private final String objectFilePath;
    private String tmpJson;
    private X tmpObject;

    /**
     * Have to set in contruction file's absolute path to work with.
     * 
     * @param objFilePath absolute path of object to Save or load
     */
    public ObjectManagerImpl(final String objFilePath) {
        this.gson = new Gson();
        this.objectFilePath = objFilePath;
    }

    /**
     * Save an object instance of Class X in setted absolute path.
     * 
     * @see model.util.storage.ObjectManager#save(java.lang.Object)
     */
    @Override
    public void save(final X args) {

        try {
            this.tmpJson = this.gson.toJson(args);
        } catch (JsonParseException e) {
            System.out.println("Error encountered in make object ( " + args.getClass().toString()
                    + " ) as a Json String" + e.getMessage());
        }

        try {
            FileUtils.writeStringToFile(new File(objectFilePath), this.tmpJson, Charsets.toCharset("UTF-8"));
        } catch (IOException e) {
            System.out.println("Error encountered in writing Json String in to File ( " + this.objectFilePath + " ): "
                    + e.getMessage());
        }
    }

    /**
     * Load an object instance of Class X by setted absolute path.
     * 
     * @see model.util.storage.ObjectManager#load(java.lang.Class)
     */
    @Override
    public X load(final Class<X> classOfX) throws IOException {

        this.tmpJson = FileUtils.readFileToString(new File(this.objectFilePath), Charsets.toCharset("UTF-8"));

        try {
            this.tmpObject = this.gson.fromJson(this.tmpJson, classOfX);
        } catch (JsonParseException e) {
            System.out.println("Error encountered in make String ( " + classOfX.toString() + " ) as a object of Class X"
                    + e.getMessage());
        }

        return returnTmpObject();
    }

    /**
     * Load an object instance of Type X by setted absolute path.
     * 
     * @see model.util.storage.ObjectManager#load(java.lang.reflect.Type)
     */
    @Override
    public X load(final Type typeOfCollection) throws IOException {

        this.tmpJson = FileUtils.readFileToString(new File(objectFilePath), Charsets.toCharset("UTF-8"));

        try {
            this.tmpObject = this.gson.fromJson(this.tmpJson, typeOfCollection);
        } catch (JsonParseException e) {
            System.out.println("Error encountered in make String ( " + typeOfCollection.toString()
                    + " ) as a object of Type X " + e.getMessage());
        }

        return returnTmpObject();
    }

    /**
     * @return setted absolute path of the object File
     */
    protected String getObjectFilePath() {
        return this.objectFilePath;
    }

    /*
     * throw an exception if no object loaded or saved
     */
    private X returnTmpObject() {
        if (this.tmpObject == null) {
            throw new IllegalArgumentException("Failed deserialization with Json, returned null value ");
        }
        return this.tmpObject;
    }

}
