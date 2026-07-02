package model;

import java.util.List;

/**
 * Interface of playlist
 * 
 * @author rrok
 *
 */
public interface IPlaylist {
	/**
	 * used to know the satatus of palylist
	 * CONSISTANT there wasn't changes
	 * UPDATED palylists o queue were updated with new song, deleted song or renamed(only for playlist not for queue) 
	 */
	public enum Status{
		CONSISTENT, UPDATED
	}
/**
 *  give Playlist Id
 * @return PlaylistId String
 */
	public String getId();
	
	/**
	 * give Playlist Name
	 * @return Playlist Name String
	 */
	public String getName();
	
	/**
	 *  Rerturn Playlist Songs
	 * @return SongsList List<Song>
	 */
	public List<Song> getSongsList();
	
	/**
	 * Add new song To Playlist
	 * @param song
	 */
	public void addSong(Song song);
	
	/**
	 * Remove Song from Playlist using index
	 * @param index
	 */
	public void removeSong(int index);
	
	/**
	 * return Playlist size
	 * @return size int
	 */
	public int size();
	
	/**
	 * get playlist status
	 * @return playlistStatus Status
	 */
	public Status getStatus();
	
	/**
	 * Set if playlist was updated adding o deleting song or created from new
	 * @param status Status
	 */
	public void setStatus(Status status);
	
}
