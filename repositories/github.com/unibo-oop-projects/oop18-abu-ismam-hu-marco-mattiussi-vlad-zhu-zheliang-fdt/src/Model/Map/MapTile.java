package model.map;

import utilityclasses.Pair;

public interface MapTile {
		public enum Status {
			EMPTY,
			PATH,
			WITHTOWER,
			WITHENEMY
		}
		
		public Pair<Integer, Integer> getPosition();
		
		public void setPosition(Pair<Integer, Integer> position);
		
		public Status getStatus();
		
		public void setStatus(Status status);
		
	}
