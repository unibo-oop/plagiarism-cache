package model;

import java.io.Serializable;

public enum RoomType implements Serializable {
	NESSUNA("---"),STANDARD("Standard"), PREMIUM("Premium"), SUITE("Suite");
	
	private final String string;
	
	private RoomType(String string) {
		this.string = string;
	}
	
	public String getType() {
		return this.string;
	}
}
