package streamline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import akka.actor.ActorRef;

public class StreamLine {

	private final int id;
	private ActorRef sender;
	private final List<ActorRef> receivers = new ArrayList<>();
	private Operation operation;

	private StreamLine(int id, ActorRef sender, Operation operation) {
		this.id = id;
		this.operation = operation;
		this.sender = sender;
	}

	private StreamLine(int id, ActorRef sender, ActorRef receiver, Operation operation) {
		this.id = id;
		if (!Objects.isNull(receiver)) {
			this.receivers.add(receiver);
		}
		this.operation = operation;
		this.sender = sender;
	}

	private StreamLine(int id, ActorRef sender, Collection<ActorRef> receivers, Operation operation) {
		this.id = id;
		if (!Objects.isNull(receivers)) {
			this.receivers.addAll(receivers);
		}
		this.operation = operation;
		this.sender = sender;
	}

	public static StreamLine createStream(int id, ActorRef sender, Operation operation) {

		return new StreamLine(id, sender, operation);
	}

	public static StreamLine createStream(int id, ActorRef sender, ActorRef receiver, Operation operation) {

		return new StreamLine(id, sender, receiver, operation);
	}

	public static StreamLine createStream(int id, ActorRef sender, Collection<ActorRef> receivers,
			Operation operation) {

		return new StreamLine(id, sender, receivers, operation);
	}

	public void addReceiver(ActorRef receiver) {

		this.receivers.add(receiver);
	}

	public void AddReceivers(Collection<ActorRef> receivers) {
		this.receivers.addAll(receivers);
	}

	public void doOp(Object msg) {

		Object computedMsg = this.operation.call(msg);

		for (ActorRef r : this.receivers) {
			r.tell(computedMsg, this.sender);
		}
		computedMsg = new StopSelfMessage(((LoopSelfMessage) msg).getStreamLineId(), "");
		this.sender.tell(computedMsg, this.sender);
	}

	public int getId() {
		return this.id;
	}
}
