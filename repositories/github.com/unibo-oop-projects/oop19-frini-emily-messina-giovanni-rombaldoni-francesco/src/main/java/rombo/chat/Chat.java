package rombo.chat;

import java.util.List;
import java.util.Optional;


public interface Chat extends BaseChat {
	
	/*Method for add a message wrote from the first user.*/
	public void AddMessage1(String Message);
	
	/*Method for add a message wrote from the second user.*/
	public void AddMessage2(String Message);
	
	/*This method return the last message wrote.*/
	public MessageImplements GetLastMessage();
	
	/*This method return the n-messages wrote from first user.*/
	public Optional<MessageImplements> GetMessage1(int indx);
	
	/*This method return the n-messages wrote from second user.*/
	public Optional<MessageImplements> GetMessage2(int indx);
	
	/*This method return a list with all messages wrote from the first user.*/
	public List<MessageImplements> GetAllMessage1();
	
	/*This method return a list with all messages wrote from the second user.*/
	public List<MessageImplements> GetAllMessage2();
	
	/*This method return all messages wrote*/
	public List<MessageImplements> GetAllCronologicalyMessage();
	
	

}
