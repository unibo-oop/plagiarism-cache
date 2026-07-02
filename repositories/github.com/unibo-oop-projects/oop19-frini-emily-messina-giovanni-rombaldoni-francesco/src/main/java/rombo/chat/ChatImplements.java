package rombo.chat;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;


public class ChatImplements extends BaseChatImplement implements Chat {
	
	/*Fields.*/
	private Map<UserImplements,MessageImplements> Chat = new HashMap<>();
	private int CountID1;
	private int CountID2;
	private MessageImplements LastMessage;
	
	/*Builders.*/	
	/*Remember to set null if the UserName don`t exist */
	public ChatImplements(int ID1, int ID2, Optional<String> UserName1,Optional<String> UserName2) {
		
		/*Super call.*/
		super (ID1,ID2,UserName1,UserName2);
		
		/*Initialize part.*/
		this.LastMessage=null;
		this.CountID1=0;
		this.CountID2=0;
	}
	
	/*@Input a string with the new massage to salve.*/
	public void AddMessage1(String Message) {
		
		MessageImplements temp = new MessageImplements(this.GetID(),this.GetUserName(),Message);
		
		this.Chat.put(new UserImplements(this.GetID(),this.GetUserName(),this.CountID1),temp);
		this.LastMessage = temp;
		this.CountID1++;
	}

	/*@Input a string with the new massage to salve.*/
	public void AddMessage2(String Message) {
		
		MessageImplements temp = new MessageImplements(this.GetID2(),this.GetUserName2(), Message);
		
		this.Chat.put(new UserImplements(this.GetID2(),this.GetUserName2(),this.CountID2), temp);
		this.LastMessage = temp;
		this.CountID2++;

	}

	/*@Return the absolute message wrote from all user.*/
	public MessageImplements GetLastMessage() {
		
		return this.LastMessage;
	}

	/*@Input a index with the number of the message
	  @Return a optional, if the message exist return the message, else return Option.*/
	public Optional<MessageImplements> GetMessage1(int indx) {
		
		return this.Chat.entrySet().stream().filter(U->U.getKey().GetNumber() == indx)
				.filter(U->U.getKey().GetID() == this.GetID()).map(U->U.getValue()).findFirst();
	}

	/*@Input a index with the number of the message
	  @Return a optional, if the message exist return the message, else return Option.*/
	public  Optional<MessageImplements> GetMessage2(int indx) {

		return this.Chat.entrySet().stream().filter(U->U.getKey().GetNumber() == indx)
				.filter(U->U.getKey().GetID() == this.GetID2()).map(U->U.getValue()).findFirst();
	}

	/*@Return alle messages write from the first user.*/
	public List<MessageImplements> GetAllMessage1() {
		
		return this.Chat.entrySet().stream().filter(U->U.getKey().GetID() == this.GetID())
				.map(M->M.getValue()).collect(Collectors.toList());
	}

	/*@Return all messages write from the second user.*/
	public List<MessageImplements> GetAllMessage2() {
		return this.Chat.entrySet().stream().filter(U->U.getKey().GetID() == this.GetID2())
				.map(M->M.getValue()).collect(Collectors.toList());
	}

	/*@Return all the messages wrote from all user.*/
	public List<MessageImplements> GetAllCronologicalyMessage() {
		
		return this.Chat.entrySet().stream().map( M->M.getValue()).collect(Collectors.toList());
	}
	
}
