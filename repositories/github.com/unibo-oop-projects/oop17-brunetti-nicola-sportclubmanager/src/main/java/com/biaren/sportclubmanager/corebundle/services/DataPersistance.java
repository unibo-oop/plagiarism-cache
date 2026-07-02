package com.biaren.sportclubmanager.corebundle.services;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Handle data persistance, load and save data from .json file,
 * using com.faster.xml.jackson library.
 * @author nbrunetti
 *
 */
public class DataPersistance {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    
    /**
     * Save data to file.
     * @param filePath of file to save data
     * @param set data to save
     */
    public static void saveData(final String filePath, final Set<?> set) {
        try {
            mapper.writeValue(new File(filePath), set);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Load data from file.
     * @param filePath of file to get data
     * @param typeRef of data to load and recreate
     * @return Set with recreated data or empty new Set
     */
    public static Set<?> loadData(final String filePath, final TypeReference<?> typeRef) {
        try {
            return mapper.readValue(new File(filePath), typeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return new HashSet<>();
        } catch (JsonMappingException e) {
          e.printStackTrace();
          return new HashSet<>();
        } catch (IOException e) {
//          e.printStackTrace();
          //File inesistente: creo un HashSet vuoto, non ci sono dati da caricare
            return new HashSet<>();
        }
    }
}
