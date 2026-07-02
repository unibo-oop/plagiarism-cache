package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import model.DCList;
import model.FoodValues;
import model.Profile;

/**
 * Questa classe implementa l'interfaccia ResourceManager e serve a salvare e caricare dati 
 * da un file
 * 
 */

public class ResourceManagerImpl implements ResourceManager{
    
    private static String path = System.getProperty("user.home") + "/Desktop/DietCreatorSaves";
    
    @Override
    public void save(Serializable data, String fileName) throws Exception {
        File folder = new File(path);
        if (!folder.exists()){
            new File(path).mkdirs();
        }
        File file = new File(path, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getPath()))){
            oos.writeObject(data);
        }
        
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public List<Profile> loadProfiles(String fileName) throws IOException, ClassNotFoundException, EOFException{
        
        DCList<Profile> list = new DCList<>();
        File file = new File(path, fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getPath()))) {
            list = (DCList<Profile>) ois.readObject();
        } 
    
        return list;
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public List<FoodValues> loadFoodValues(String fileName) throws IOException, ClassNotFoundException{
        
        DCList<FoodValues> list = new DCList<>();
        File file = new File(path, fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getPath()))) {
            list = (DCList<FoodValues>) ois.readObject();
        }
    
        return list;
    }
    
}
