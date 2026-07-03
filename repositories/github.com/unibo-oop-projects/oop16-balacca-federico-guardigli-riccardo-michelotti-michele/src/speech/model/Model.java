package speech.model;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import speech.utils.Constants;
import view.speech.SpeechController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO nello shopping commenti in verde da cancellare quando è tutto a posto

// Use of the pattern Model/Controller for SpeechRecognition. 
//It will be connected to the principal view of the project
/**
 * The model implements the business logic.
 * Use of the singleton: it provides a single controller
 */

//TODO ogni volta che PULLI vai a recuperare il codice in ClientController()->startVoice() che è il seguente
/*void StartVoice(MouseEvent event) {
// quando viene premuto start voice come deve cambiare l'interfaccia grafica?
//il pulsante start voice o viene disabilitato oppure il pulsante cambia nome e diventa stopVoice 
//dopo che ha finito qui, deve uscire il menu sull'interfaccia grafica con i miei comandi e ogni volta
//che nomino un parola nel menu bisogna creare un metodo che modifichi l'interfaccia grafica
try {
	Model model=Model.getInstance();
	Controller controller= Controller.getInstance();
	controller.setModel(model);
	controller.enableVoiceRecognition();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}*/
public class Model {
	private Configuration configuration;
	private LiveSpeechRecognizer jsgfRecognizer ;
	private LiveSpeechRecognizer lmRecognizer;
	private static Model instance;

	/**
	 * method for creating the singleton at the first call
	 * @return instance
	 * @throws IOException
	 */
	public static Model getInstance() throws IOException{
		if(instance==null){
			instance=new Model();
		}
		return instance;

	}
	/**
	 * private constructor Model(), where all configurations are launched before the start of speechRecognition
	 * @throws IOException
	 */
	private Model() throws IOException{
		configuration = new Configuration();
		configuration.setAcousticModelPath(Constants.ACOUSTIC_MODEL);
		configuration.setDictionaryPath(Constants.DICTIONARY_PATH);
		configuration.setGrammarPath(Constants.GRAMMAR_PATH);
		configuration.setUseGrammar(true);

		configuration.setGrammarName("dialog");
		jsgfRecognizer =
				new LiveSpeechRecognizer(configuration);

		configuration.setUseGrammar(false);
		configuration.setLanguageModelPath(Constants.LANGUAGE_MODEL);
		lmRecognizer =
				new LiveSpeechRecognizer(configuration);
	}
	/**
	 * Start of the recognition, with the view of the principal menu
	 */
	public void startRecognition(){
		jsgfRecognizer.startRecognition(true);
		while (true) {
			System.out.println("Choose menu item:");
			System.out.println("Shopping [with the salesperson]");
			System.out.println("Discount:[new entries]");
			System.out.println("Weather forecast");
			System.out.println("Calculator");
			System.out.println("Exit the program");

			String utterance = jsgfRecognizer.getResult().getHypothesis();

			if (utterance.startsWith("exit")){
				stopRecognition();
				break;
			}

			else if (utterance.equals("discount")) {
				jsgfRecognizer.stopRecognition();
				recognizeDiscount();
				jsgfRecognizer.startRecognition(true);
			}

			else if (utterance.equals("shopping")) {
				jsgfRecognizer.stopRecognition();
				recognizeShopping();
				jsgfRecognizer.startRecognition(true);
			}

			else if (utterance.endsWith("weather forecast")) {
				jsgfRecognizer.stopRecognition();
				recognizeWeather();
				jsgfRecognizer.startRecognition(true);
			}
			else if (utterance.equals("calculator")) {
				jsgfRecognizer.stopRecognition();
				recognizerCalculator();
				jsgfRecognizer.startRecognition(true);
			}
		}

	}
	/**
	 * Stops recognition process until the call of another process
	 */
	public void stopRecognition(){
		jsgfRecognizer.stopRecognition();
	}
	
	/**
	 * a support enum for the methos recognizeShopping(), with all the items in the shop
	 * I use it for programming with lambdas
	 * @param word
	 *
	 */
	public enum Clothes{
		JERSEY("jersey"), TSHIRT("tshirt"), UNDERSHIRT("undershirt"), TROUSERS("trousers"),
		JEANS("jeans"), SKIRT("skirt"), LONGSKIRT("skirt"), UNDERWEAR_WOMAN("underwear"),
		JACKET("jacket"),UNDERWEAR_MAN("underwear"), HAT("hat"), SCARF("scarf"),
		SHOES("shoes"), BOOTS("boots"), SANDALS("sandals"), FLIP_FLOP("flip flop"), NONE(" ");

		private String word;

		Clothes(String word) {
			this.word = word;
		}

		public String word() {
			return word;
		}

		private static Map<String,Enum> map=new HashMap<>();

		static {
			for(Clothes td : Clothes.values()){
				map.put(td.word,td);
			}
		}

		public static Enum getEnum(String word){
			return map.get(word);
		}


	}

	/**
	 * a support enum for the methos recognizeSize(), with all the sizes.
	 * I use it for programming with lambdas
	 * @param word
	 *
	 */
	public enum Size{
		S("S","small"),M("M","medium"),L("L","large"),
		XL("XL","extra large"),XXL("XXL","double extra large");

		private String number;
		private String word;

		Size(String number, String word) {
			this.number= number;
			this.word = word;
		}

		public String word() {
			return word;
		}

		public String number(){
			return number;
		}
	}


	/**
	 * MACRO for the weather forecast
	 * a support for the method recognizeWeather()
	 * I use it for programming with lambdas
	 * @param actualWeather
	 * @param word
	 */
	public enum Weather {

		MONDAY ("Any rain or drizzle will die out in the morning and sunny spells will develop in the afternoon", "monday"),
		TUESDAY ("Rain will spread to the city overnight, heavy and persistent in places. Clouds will "
				+ "increase as the day goes on", "tuesday"),
		WEDNESDAY ("Torrential rain will spread to the city in the morning, a shower will spread in the afternoon ", "wednesday"),
		THURSDAY ("Will remain dry apart from perhaps a little drizzle in the morning ", "thursday"),
		FRIDAY (" Almost sunny with a mild temperature all day ", "friday"),
		SATURDAY ("Sunny and hot every day ", "saturday"),
		SUNDAY ("Sunny and hot every day ", "sunday");

		private final String actualWeather ;
		private String word;

		private Weather ( final String actualWeather, String word ){
			this.actualWeather = actualWeather ;
			this.word=word;
		}
		public String actualWeather (){
			return this.actualWeather ;
		}
		public String word(){
			return this.word;
		}

	}
	/**
	 * method for viewing a shop window with the trendiest clothes and their discounts
	 * @param recognizer
	 * @return void
	 */
	private void recognizeDiscount() {
		System.out.println("Choose menu item:");
		System.out.println("shop window (say 'shop')");   
		System.out.println("[discounts] in new entries (say 'discounts')");
		System.out.println("back");


		jsgfRecognizer.startRecognition(true);
		String utterance="";
		while (true) {
			utterance = jsgfRecognizer.getResult().getHypothesis();
			if (utterance.equals("back"))
				break;
			else if( utterance.equals("shop")){
				System.out.println("The new entries:... End with 'back'");
				//da collegare, fa vedere lo shop window in generale, con tutti i capi (collegamento alla porta principale)
				//oppure ti fa vedere gli ultimi capi aggiunti nel negozio
			}

			else if(utterance.equals("discounts")){
				System.out.println("New entries in discount: ... End with 'back'");
				//da fare collegamento: fa vedere i capi in discount
			}
		}
		jsgfRecognizer.stopRecognition();
	}
	/**
	 * a method utility for recognizeShopping, where they discuss about the size
	 * Sometimes is hard to recognize 'large' , maybe for the pronunciation
	 * @param string that is a specific item I've choosen
	 * @return void
	 */
	private void recognizeSize(String string) {
		System.out.println("What size/number would you want?");
		System.out.println("Choose menu item:");
		System.out.println("say 'small' (if you want a S size)");
		System.out.println("say 'medium' (if you want a M size)");
		System.out.println("say 'large' (if you want a L size");
		System.out.println("say 'extra large' (if you want a XL size");
		System.out.println("say 'double extra large' (if you want a XXL size");
		System.out.println("'Ok' (if the item is ok-> (with ok you continue to talk with me about it) or if you want to return to the principal menu");


		while (true) {
			final String utterance = jsgfRecognizer.getResult().getHypothesis();
			Map<String,String> sizes=new HashMap<>();
			Arrays.asList(Size.values()).stream().forEach(x->sizes.put(x.word(),x.number));
			if(utterance.endsWith("ok")){
				System.out.println("ok");
				System.out.println("Continue to talk with me about this");
				break;
			}else{
				sizes.keySet().stream().forEach(x->{
					if(utterance.equals(x)){
						System.out.println(sizes.get(x));
						//TODO COME FARE COLLEGAMENTO
						//x per esempio è small, sizes.get(x) è il numero di taglia, cioè S.
						//quando richiamo il filtro richiamo il metodo della view 
						//e ci passo come taglia sizes.get(x)
						//altrimenti se non va, cambia COME in clothes e metti getEnum()
					}


				});

			}
		}
	}
	/*if(utterance.startsWith("small")){
				System.out.println("S size");
				//C'è UN METODO NELLA VIEW DI GUARDO, SENTIRE SUL VOCALE
				//fai il collegamento con loro, specificando string, string per esempio puï¿½ essere trousers, cosï¿½ mi fa vedere i pantaloni
				//Quando mi dice di vedere dei pantaloni avevo selezionato UN pantalone, ora mi chiede se la taglia di quel pantalone andava bene, se smaller mi fa vedere la taglia più piccola e così via
				//collegamento per taglie piï¿½ piccole, string ï¿½ il capo specifico
			}if(utterance.startsWith("medium")){
				System.out.println("M size");
				//colegamento con quello degli altri per taglie piï¿½ grandi
				//prevelo con il set il numero di taglia di un capo che ho evidenziato,ad esempio, la taglia ï¿½ un l. Se voglio una taglia minore filtro su taglia m
				//faccio un if, se la taglia del capo che ho lï¿½ davanti ï¿½ tot, con l'if stabilisco la piï¿½ piccola e la piï¿½ grande
				//lo faccio per ogni taglia
				//if xl bigger=xxl smaller=l, stanno mettendo un filtro apposita
			}if(utterance.startsWith("large")){
				System.out.println("L size");
				//collegamento
			}if(utterance.startsWith("extra large")){
				System.out.println("XL size");
				//collegamento
			}if(utterance.startsWith("double extra large")){
				System.out.println("XXL size");
				//collegamento
			}else if(utterance.endsWith("ok")){
				//la taglia va bene
				System.out.println("ok");
				System.out.println("Continue to talk with me about this");
				break;
			}
		}*/

	//collegamento 2 con gli altri sulla taglia

	//recognizer.stopRecognition();

	/**
	 * a method utility for recognizeShopping, when they discuss about the price
	 * @param string that is a specific item I've chosen
	 * @return void
	 */
	private void recognizePrice(String string){
		System.out.println("The price is ok?");
		System.out.println("Choose menu item");
		System.out.println("'cheaper' (if you want to view cheaper clothes of the same type)");
		System.out.println("'more expensive' (if you want to view more expensive clothes of the same type)");
		System.out.println("'discount' (if you want to view clothes in discount of the same type)");
		System.out.println("'yes' (if the item is ok-> (with 'yes' you continue to talk with me about it) or if you want to return to the principal menu");
		//  recognizer.startRecognition(true);
		String utterance= "";
		while (true) {
			utterance = jsgfRecognizer.getResult().getHypothesis();
			if(utterance.startsWith("cheaper")){
				System.out.println("Cheaper");
				//fai il collegamento con loro, specificando string, string per esempio puï¿½ essere trousers, cosï¿½ mi fa vedere i pantaloni piï¿½ cheaper
				//collegamento per costi minori, string ï¿½ il capo specifico
				//TODO FARE COLLEGAMENTO CON VIEW
			}else if(utterance.startsWith("more expensive")){
				System.out.println("more expensive");
				//colegamento con quello degli altri per i capi del genere string che costano di piï¿½
				//la funzione prezzo la devono fare
				//TODO FARE COLLEGAMENTO CON VIEW
			}else if(utterance.startsWith("discount")){
				//collegamento con quello degli altri per i capi del genere string che sono in sconto, esempio filtro sconto+ filtro tipoVestiario
				System.out.println("in discount");
				//TODO FARE COLLEGAMENTO CON VIEW
			}else if(utterance.endsWith("yes")){
				//la taglia va bene
				System.out.println("yes");
				System.out.println("Say 'back' if you want to exit or change clothes,instead continue to talk with me about this");
				break;
			}
			//collegamento 2 con gli altri sulla taglia

			//recognizer.stopRecognition();
		}
	}
	/**
	 * a method utility for recognizeShopping(), when they finish the dialog
	 * @param string that is the specific item I've choosen
	 */

	private void recognizeLike(String string){
		System.out.println("Would you want to try it?");
		System.out.println(" Say ok or no");
		String utterance= "";
		while (true) {
			utterance = jsgfRecognizer.getResult().getHypothesis();
			if(utterance.startsWith("ok")){
				System.out.println("The fitting rooms are at the end of the corridor, on the right");
				System.out.println("You have to pay at the cash desk, they are at the end of the corridor, on the left");
				System.out.println("Say 'back' if you want to view other items or exploring the menu item or exit");
				break;
			}else if(utterance.endsWith("no")){
				System.out.println("Say back if you want to view other items or exit");
				break;
			}

		}
	}


	/**
	 * dialog with the salesperson, with other methods for discussing about the size, the price..
	 * ps: avoid uttering shirt, scarf, jersey,skirt. They are contained in the SpeechRecognition vocabulary but the speechRecognition difficultly recognizes them
	 * Sometimes it can happen that it recognizes them, but it's more rarely. Maybe it could depends also from the pronunciation
	 *
	 */

	private void recognizeShopping() {
		System.out.println("This is a shopping account voice menu");
		System.out.println("Hello! What would you want?");
		System.out.println("-------------------------------");
		System.out.println("JERSEY, TSHIRT, UNDERSHIRT, TROUSERS,");
		System.out.println("JEANS, SKIRT, LONGSKIRT, UNDERWEAR_WOMAN");
		System.out.println("JACKET,UNDERWEAR_MAN, HAT, SCARF");
		System.out.println("SHOES, BOOTS, SANDALS, FLIP_FLOP, DRESS");
		System.out.println("-------------------------------");
		System.out.println("EXAMPLE: Say 'trousers'");
		//Per ognuno di questi implementare dei comandi interni
		//Questi comandi interni sono uguali
		//yes
		//do you have cheaper| another size...? con gli or (guarda jsfg)
		// poi chiede dove sono le fitting rooms

		List<String> clothes=new ArrayList<>();
		/*for(Clothes c: Clothes.values()){
			clothes.add(c.word());
			System.out.println(c.word());
		}*/
		Arrays.asList(Clothes.values()).stream().forEach(x->clothes.add(x.word()));

		/* final List<Clothes> clothes= Arrays.asList(Clothes.values()).forEach((x)->clothes.add(x.toString()));
       final List<String> accessories=Arrays.asList(Accessories.values()).forEach((x)->accessories.add(x.toString()));
        // List<String> clothes=Stream.builder(Clothes.values()).map(Clothes::name).collect(Collectors.toList());*/

		/*  final List<Accessories> accessories=new ArrayList<>();
        for(Clothes c: Clothes.values()){
     	   clothes.add(c.toString());
        }
        for(Accessories a: Accessories.values()){
     	   accessories.add(a);
        }*/
		jsgfRecognizer.startRecognition(true);
		/*String utterance= "";*/
		while (true) {
			final String utterance = jsgfRecognizer.getResult().getHypothesis();
			/*  String cloth= clothes.stream().findAny().toString();
             String accessory=accessories.stream().findAny().toString();*/
			if (utterance.endsWith("back")) {
				break;
				/*  } else{

            	 for(String c: clothes){

            	 if(utterance.startsWith(c) && clothes.contains(utterance)){
            	 System.out.println(utterance);
            	 DialogContinue();
            	 recognizeSize(recognizer,utterance);
            	 recognizePrice(recognizer,utterance);
              }
             }
           }
         }
         recognizer.stopRecognition();
    }*/

				/*			}else if(utterance.startsWith("trousers")){
				System.out.println(utterance);
				//fare il collegamento-> mi fa vedere i pantaloni

				dialog("trousers");

				/*System.out.println("Do you want another size?");

            	System.out.println("Say smaller or bigger or Ok");
            	System.out.println("Say back to exit");

            	 // recognizer.startRecognition(true);
                  while (true) {
                      String utterance2 = recognizer.getResult().getHypothesis();
            	if(utterance2.startsWith("smaller")){
            		System.out.println("Smaller size");
            		//fai il collegamento con loro, specificando string, string per esempio puï¿½ essere pantaloni
            		//collegamento per taglie piï¿½ piccole
            	}else if(utterance2.startsWith("bigger")){
            		System.out.println("bigger size");
            		//colegamento con quello degli altri per taglie piï¿½ grandi
            	}else if(utterance2.startsWith("ok")){
            		//la taglia va bene
            		System.out.println("ok");
            		break;
            		//recognizer.stopRecognition();
            	}
                  }
                  //recognizer.stopRecognition();
            	//collegamento 2 con gli altri sulla taglia



             	//problemi nello stop recognition

             	/*vorrei mettere questo metodo per non riusare codice, ma non mi funziona il vocale
				 * ControlSize(utterance2);
				 */
				/*			}else if(utterance.startsWith("flip flop")){
				System.out.println(utterance);
				dialog("flip flop");

			}else if(utterance.startsWith("shoes")){
				//collegamento che mi fa vedere le scarpe
				System.out.println(utterance);
				dialog("shoes");
			}else if(utterance.startsWith("shirt")){
				System.out.println("T"+utterance);
				dialog("shirt");

			}else if(utterance.startsWith("undershirt")){
				System.out.println(utterance);
				dialog("undershirt");
			}else if(utterance.startsWith("jeans")){
				System.out.println(utterance);
				dialog("jeans");
			}else if(utterance.startsWith("skirt")){
				System.out.println(utterance);
				dialog("skirt");
			}else if(utterance.startsWith("underwear")){
				System.out.println(utterance);
				dialog("underwear");
			}else if(utterance.startsWith("jacket")){
				System.out.println(utterance);
				dialog("jacket");
			}else if(utterance.startsWith("hat")){
				System.out.println(utterance);
				dialog("hat");
			}else if(utterance.startsWith("scarf")){
				System.out.println(utterance);
				dialog("scarf");
			}else if(utterance.startsWith("boots")){
				System.out.println(utterance);
				dialog("boots");
			}else if(utterance.startsWith("sandals")){
				System.out.println(utterance);
				dialog("sandals");
			}else if(utterance.startsWith("jersey")){
				System.out.println(utterance);
				dialog("jersey");

			}else if(utterance.startsWith("dress")){
				System.out.println(utterance);
				dialog("dress");
			}															*/	


			}else{
				clothes.stream().forEach(x->{
					if(utterance.startsWith(x)){
						System.out.println(utterance);
						//collegarlo con il loro. ad esempio la utterance ï¿½ trousers e mi fa vedere i pantaloni
						//poi io dovrò sceglierne cliccandoci uno che mi ispira
						//searchByType deve prendere in ingresso la lista di tutti i garment, come ottenerla?
						// filtra e mi restituisce la lista di tutti i garment del tipo che io ho nominato
						//questa lista deve essere visualizzata sul'interfaccia grafica, mi serve un metodo che me li visualizza
						//la stessa cosa per la taglia e il prezzo

						//l'ho commentata, mi manca la lista dei garment
						//SearchImpl.searchByType(list, Clothes.getEnum(utterance));
						//System.out.println(Clothes.getEnum(utterance));

						//TODO COME FARE collegamento!!!
						//x per esempio è utterance, cioè trousers, Clothes.getEnum(utterance) è TROUSERS in maiuscolo
						//quando richiamo il filtro richiamo il metodo della view 
						//e ci passo come parametro Clothes.getEnum(utterance), cioè in questo caso TROUSERS, perchè mi sa 
						//che il metodo della view passa così penso
						dialog(x);
					}
				});
			}
		}
		jsgfRecognizer.stopRecognition();      


	}

	private void dialog(String string){
		//System.out.println(utterance);
		//collegamento->mi fa vedere le string
		System.out.println("Here there are!");
		//System.out.println("Click on a specific item to continue the dialog");
		//metodo per fare il collegamento per decidere la taglia
		recognizeSize(string);
		//metodo per fare il collegamento per decidere il prezzo
		recognizePrice(string);
		recognizeLike(string);
	}

	/**
	 * A method that implements a calculator that executes, with voices commands, simple operations
	 * It could be useful for example if a customer wants to check if he/she has got enough money to buy what he/she have chosen
	 */
	private void recognizerCalculator() {
		System.out.println("This is a ' very simple calculator' voice menu");
		System.out.println("-------------------------------");
		System.out.println("Example: balance");
		System.out.println("Example: withdraw zero point five");
		System.out.println("Example: deposit one two three");
		System.out.println("Say 'back' to exit");
		System.out.println("-------------------------------");

		double savings = .0;
		jsgfRecognizer.startRecognition(true);
		String utterance="";
		while (true) {
			utterance = jsgfRecognizer.getResult().getHypothesis();
			if (utterance.endsWith("back")) {
				break;
			} else if (utterance.startsWith("deposit")) {
				double deposit = parseNumber(utterance.split("\\s"));
				savings += deposit;
				System.out.format("Deposited: $%.2f\n", deposit);
			} else if (utterance.startsWith("withdraw")) {
				double withdraw = parseNumber(utterance.split("\\s"));
				savings -= withdraw;
				System.out.format("Withdrawn: $%.2f\n", withdraw);
			} else if (!utterance.endsWith("balance")) {
				System.out.println("Unrecognized command: " + utterance);
			}

			System.out.format("Your savings: $%.2f\n", savings);
		}

		jsgfRecognizer.stopRecognition();
	}
	/**
	 * a method that implements a weather forecast. You can say a day you are interested in for knowing the weather
	 */

	private void recognizeWeather() {
		System.out.println("Say a day you are interested in! End with 'back'");
		Map<String,String> weather=new HashMap<>();
		Arrays.asList(Weather.values()).stream().forEach(x->weather.put(x.word(),x.actualWeather));
		jsgfRecognizer.startRecognition(true);

		while (true) {
			final String utterance= jsgfRecognizer.getResult().getHypothesis();
			if (utterance.equals("back"))
				break;
			else{
				weather.keySet().stream().forEach(x->{
					if(utterance.equals(x)){
						System.out.println(weather.get(x));

					}
				});

			}

		}

		jsgfRecognizer.stopRecognition();
	}
	private static final Map<String, Integer> DIGITS =
			new HashMap<String, Integer>();

	static {
		DIGITS.put("oh", 0);
		DIGITS.put("zero", 0);
		DIGITS.put("one", 1);
		DIGITS.put("two", 2);
		DIGITS.put("three", 3);
		DIGITS.put("four", 4);
		DIGITS.put("five", 5);
		DIGITS.put("six", 6);
		DIGITS.put("seven", 7);
		DIGITS.put("eight", 8);
		DIGITS.put("nine", 9);
	}
	/**
	 * method for using the numbers you say! It is an utility of recognizeCalculator()
	 * */
	private static double parseNumber(String[] tokens) {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < tokens.length; ++i) {
			if (tokens[i].equals("point"))
				sb.append(".");
			else
				sb.append(DIGITS.get(tokens[i]));
		}

		return Double.parseDouble(sb.toString());
	}


}
