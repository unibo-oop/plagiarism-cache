package data;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

/**
 *Describes the possible languages into which the program can be translated.
 *
 */
public enum Languages {

     /**
     *Describes language English.
     */
     ENGLISH("English", 0, Locale.US),
    /**
     *Describes language Italian.
     */
     ITALIAN("Italiano", 1, Locale.ITALY),
    /**
     *Describes language Spanish.
     */
     ESPANOL("Español", 2, new Locale("es", "ES")); 

     private String name;
     private int value;
     private Locale loc;

     Languages(final String name, final int value, final Locale loc) { 
         this.name = name;
         this.value = value;
         this.loc = loc;
     }
     public String getName() {
         return name;
     }
     public int getValue() {
         return value;
     }
     public Locale getLocale() {
         return loc;
     }

     /**
      * Calculates the Languages enum relating to the specified value.
      * @param value the value relative to the enum you want to look for
      * @return enum Languages related to the value entered
      * @throws IllegalArgumentException if the value entered does not belong to any enum Languages
      */
     public static Languages getEnum(final int value) {
         Optional<Languages> language = Arrays.asList(Languages.values()).stream().filter(l -> l.getValue() == value).findFirst();
         if (language.isPresent()) {
             return language.get();
         } else {
             throw new IllegalArgumentException();
         }
     }
     
     /**
      * Calculates the Languages enum relating to the specified value.
      * @param value the value relative to the enum you want to look for
      * @return enum Languages related to the value entered
      * @throws IllegalArgumentException if the value entered does not belong to any enum Languages
      */
     public static Languages getEnum(final String name) {
         Optional<Languages> language = Arrays.asList(Languages.values()).stream().filter(l -> l.getName().equals(name)).findFirst();
         if (language.isPresent()) {
             return language.get();
         } else {
             throw new IllegalArgumentException();
         }
     }

}
