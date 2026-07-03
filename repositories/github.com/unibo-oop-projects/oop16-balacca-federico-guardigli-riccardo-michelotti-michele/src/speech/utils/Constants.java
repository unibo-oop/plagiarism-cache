package speech.utils;

/**
 * An utility class where there are included important file,
 *  the dictionary, the acoustic model, a grammar(dialog.gram, which was created by me) 
 *  and a language model
 *
 */
public class Constants {
	public static final String ACOUSTIC_MODEL =
			"resource:/edu/cmu/sphinx/models/en-us/en-us";
	public static final String DICTIONARY_PATH =
			"resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
	/*"clothes.dic";*/
	/**
	 * dialo.gram is a grammar file which I created in support at the speehrecognition, where commands are expressed
	 */
	public static final String GRAMMAR_PATH =
			//"file:C:\\Users\\lorella\\Desktop\\progettojava\\sphinx4-5prealpha-src\\sphinx4-samples\\src\\main\\resources\\edu\\cmu\\sphinx\\demo\\dialog";
			//
			"resource:/edu/cmu/sphinx/demo/dialog";
	public static final String LANGUAGE_MODEL =
			/*"clothes.lm";*/
			//"resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin";
			"resource:/edu/cmu/sphinx/demo/dialog/weather.lm";
	//"file:C:/Users/lorella/Desktop/progettojava/sphinx4-5prealpha-src/sphinx4-samples/src/main/resources/edu/cmu/sphinx/demo/dialog/vocabweather.lm";

}
