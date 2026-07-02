package model;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author rrok
 * Playlist that implements IPlaylist
 */
public class Playlist implements IPlaylist{

	private String id;
	private List<Song> songsList;
	private String playlistName;
	private Status status;

	/**
	 *  Playlist parameter
	 * @param name
	 */
	public Playlist(String name) {
		this.id = new UID().toString();
		songsList = new ArrayList<>();
		this.playlistName = name;
		this.status = Status.CONSISTENT;
	}
	
	/**
	 * other costructor if state was changed
	 * @param name
	 * @param status
	 */
	public Playlist(String name, Status status) {
		this.id = new UID().toString();
		songsList = new ArrayList<>();
		this.playlistName = name;
		this.status = status;
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public String getId(){
		return id;
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public List<Song> getSongsList() {
		return songsList;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void addSong(Song song) {
		this.songsList.add(song);
		this.status = Status.UPDATED;
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public Status getStatus(){
		return this.status;
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void removeSong(int index) {
		this.songsList.remove(index);
		this.status = Status.UPDATED;
	}
	
	public void removeSong(Song song) {
		songsList.remove(song);
		this.status = Status.UPDATED;
	}

	public String getPlaylistTitle() {
		return playlistName;
	}

	/**
	 * Rename playlist
	 * @param palylistTitle
	 */
	public void renamePlaylist(String palylistTitle) {
		this.playlistName = palylistTitle;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public int size() {
		return this.songsList.size();
	}

	/**
	 * Song List to String
	 * @return songToString String
	 */
	@Override
	public String toString() {
		String string = "";
		for (Song song : songsList) {
			string = string + " | " + song.toString();
		}
		return string;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public String getName() {
		return this.playlistName;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void setStatus(Status status){
		this.status = status;
	}
	
}