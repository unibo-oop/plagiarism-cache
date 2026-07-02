package exception;
/**
 * 
 * @author Helena Zaccarelli
 * 
 *    Evita che vengano registrate prenotazioni per un quantitativo di merce superiore a quella depositabile nel piazzale o imbarcabile sulla nave
 */

public class ExceptionNegativeQty extends Throwable {
	public static final long serialVersionUID = 1L;
}
