package model;

import java.rmi.server.UID;

public class Song {
	
	private String id;
	private String path;
	private String title;
	private String album;
	private String artist;
	private String genre;
	private Duration duration;

	public Song(String title, String artist, String album, String genre, String path, Duration duration) {
		this.id = new UID().toString();
		this.duration = duration;
		this.title= title;
		this.artist = artist;
		this.album= album;
		this.genre= genre;
		this.path= path;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtist() {
		return artist;
	}

	public String getGenre() {
		return genre;
	}

	public Duration getDuration() {
		return duration;
	}

	public String getPath() {
		return path;
	}
	
	@Override
	public String toString(){
		return "artist: " + artist + " - " + title;
	}
}
