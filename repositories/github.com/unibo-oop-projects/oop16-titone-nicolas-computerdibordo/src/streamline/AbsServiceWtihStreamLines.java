package streamline;


import java.util.Collection;
import java.util.HashMap;

import java.util.Map;
import java.util.Objects;

import akka.actor.*;
import akka.actor.ActorRef;


public abstract class AbsServiceWtihStreamLines extends UntypedActor{

	private final IdGenerator idFactory = new IdGenerator();
	private final Map<Integer, StreamLine> streams = new HashMap<>(); //mappe o liste+stream()?
	
	public AbsServiceWtihStreamLines() {
		this.init();
	}
	
	protected abstract void init();
	
	protected int addStream(Operation op) {
		int id = idFactory.get();
		
		this.streams.put(id, StreamLine.createStream(id, this.getSelf(), op));
		
		return id;
	}
	
	protected int addStream(ActorRef receiver, Operation op) {
		int id = idFactory.get();
		
		this.streams.put(id, StreamLine.createStream(id, this.getSelf(), receiver, op));
		
		return id;
	}
	
	protected int addStream(Collection<ActorRef> receivers, Operation op) {
		int id = idFactory.get();
		
		this.streams.put(id, StreamLine.createStream(id, this.getSelf(), receivers, op));
		
		return id;
	}
	
	protected void addReceiver(int id, ActorRef receiver) {
		this.streams.get(id).addReceiver(receiver);
	}
	
	protected void AddReceivers(int id, Collection<ActorRef> receivers) {
		this.streams.get(id).AddReceivers(receivers);
	}
	
	private class IdGenerator {

		private int incrementalId = 0;

		public int get() {
			this.incrementalId++;
			return incrementalId;
		}
	}
}
