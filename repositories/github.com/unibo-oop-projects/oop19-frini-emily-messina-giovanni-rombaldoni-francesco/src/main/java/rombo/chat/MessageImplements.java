package rombo.chat;

import java.util.Optional;

import rombo.discuss.BaseAccountImplements;

public class MessageImplements extends BaseAccountImplements implements Message {

	/*Fields.*/
	private final String Message;
	
	/*Builders.*/	
	public MessageImplements(int ID,Optional<String> UserName, String Message) {
		super(ID, UserName);
		this.Message = Message;
	}
	
	/*@Return the message saved.*/
	public String GetMessage() {
		return this.Message;
	}

}
