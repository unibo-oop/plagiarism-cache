package main;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import concretes.ComposerImpl;

	/**
	 * The main class. It starts the Composer. It provides a static method for debugging. To use it is necessary to start the
	 * application with the keyword "debug" as first parameter.
	 */

public class Main {
	
	/**
	 * A boolean value used to start the debug mode.
	 */
	public static boolean DEBUG;
	
	/**
	 * The String keyword to be passe as first parameter to start the debug mode.
	 */
	public final static String DEBUG_MODE = "debug";
	
	/**
	 * This method is used to print a String s as standard output. if {@link Main#DEBUG} is true the string is printed,
	 * otherwise is not printed.
	 * @param s the string to be printed.
	 */
	public static void log(String s) {
		if(DEBUG) {
			System.out.println(s);
		}
	}
	
public static void main(String[] args) {
		
		if(args.length != 0 && args[0].compareTo(DEBUG_MODE) == 0) {
			DEBUG = true;
		} else {
			DEBUG = false;
		}
		
		ActorSystem system = ActorSystem.create("MySystem");
		
		ActorRef master = system.actorOf(Props.create(ComposerImpl.class), "Master");
	}
}
