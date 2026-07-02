package controller.command;

/**
 * Class where the commands will save in a queue, 
 * in order to take the FIFO command saved
 */
import java.util.LinkedList;
import java.util.Queue;


public class CommandController {
	
	private Queue<Command> cmd;
	
	
	public CommandController(){
		cmd = new LinkedList<Command>();
	}
	
	public void addCommand(Command cmd) {
		this.cmd.add(cmd);
	}
	

	public Command pollCommand() {	
		return this.cmd.poll();
	}
	
	public Queue<Command> getQueue(){
		return this.cmd;
	}

}
