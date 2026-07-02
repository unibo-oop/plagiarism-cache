package model;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rrok
 * song queue is a special playlist with different extention, here there are not permitted to delete song or add
 * new song
 */
public class SongQueue implements IPlaylist{
	
	private static SongQueue songQueue;
	private List<Song> songsList;
	private String id;
	private Status status;
	
	/**
	 * set the queue parameter
	 */
	private SongQueue(){
		this.songsList = new ArrayList<>();
		this.id = new UID().toString();
		this.status = Status.UPDATED;
	}
	
	/**
	 * get istance is a singleton beacuse there will not be more queue.
	 * @return SongQueue
	 */
	public static SongQueue getInstance(){
		if(songQueue == null){
			return new SongQueue();
		}
		return songQueue;
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
		songsList.add(song);
		this.status = Status.UPDATED;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void removeSong(int index) {
		songsList.remove(index);
		this.status = Status.UPDATED;
	}
	
	/**
	 * Remove Song from Queue using Song
	 * @param song Song
	 */
	public void removeSong(Song song) {
		songsList.remove(song);
		this.status = Status.UPDATED;
	}
	 /**
     * {@inheritDoc}
     */
	@Override
	public int size() {
		return songsList.size();
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return "songQueue";
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
	public void setStatus(Status status){
		this.status = status;
	}
	
}
