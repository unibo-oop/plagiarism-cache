package model;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import util.FileHelper;
/**
 * 
 * @author rrok
 *
 */
public class LibraryManager {
	/**
	 * There are songQueue and 
	 * @return userPlaylists List<Playlist>
	 */
	private List<Playlist> userPlaylists = new ArrayList<Playlist>();
	private SongQueue songQueue;
	private IPlaylist playlistToShow;
	private IPlaylist reproducingPlaylist;
	private ReproducingSong reproducingSong;
	private boolean canPlayNext= true;
	
	/**
	 * currentPlaylist is teh playlist that i'm playing now
	 */
	
	private String PLAYLISTS_FOLDER = "";
	
	
	
	
	private static final String PLAYLIST_EXTENSION = ".msort";
	private static final String QUEUE_EXTENSION = ".queue";
	/**
	 * set first the song queue as the first selected Playlist
	 */
	public LibraryManager(){
		songQueue = SongQueue.getInstance();
		File fileP=null;
		String path= "";
		try {
			fileP =  new File(main.Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			
			fileP = new File(System.getProperty("java.class.path"));
			File dir = fileP.getAbsoluteFile().getParentFile();
		    path = dir.toString()+"/res/playlists";
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		File directory = new File(String.valueOf(path));
		if(!directory.exists()){
			directory.mkdirs();
			System.out.println("esiste");
		}
		this.PLAYLISTS_FOLDER=directory.toString();
		System.out.println(directory+"this si my path");
		loadTracks();
	}
	/** 
	 * get Playlist Folder
	 * @return String
	 */
	public  String getPlaylistsFolder() {
		return PLAYLISTS_FOLDER;
	}
	
	/**
	 * loads all playlists (and queue) from playlist folder
	 * PLAYLISTS_FOLDER is the path where the user save all palylist
	 */
	private void loadTracks(){

		String playListFolderPath = this.PLAYLISTS_FOLDER;
		
		
		List<IPlaylist> list = FileHelper.loadPlayLists(playListFolderPath, PLAYLIST_EXTENSION, QUEUE_EXTENSION);
		for(IPlaylist p : list){
			if(p instanceof SongQueue){
				songQueue = (SongQueue)p;
			} else {
				userPlaylists.add((Playlist)p);
			}
		}
		reproducingPlaylist = songQueue;
	}
	
	
	/**
	 * 
	 *  Used to save all palylist that were created or were the have ad changes
	 */
	public void persistPlaylists(){
		FileHelper.persistPlaylist(songQueue, PLAYLISTS_FOLDER, QUEUE_EXTENSION);
		for(Playlist p: userPlaylists){
			FileHelper.persistPlaylist(p, PLAYLISTS_FOLDER, PLAYLIST_EXTENSION);
		}
	}
	
	/**
	 * Create a new palylist with name
	 * @param name
	 * @return Playlist
	 */
	public Playlist createNewPlaylist(String name){
		Playlist playlist = new Playlist(name, Playlist.Status.UPDATED);
		this.userPlaylists.add(playlist);
		return playlist;
	}
	
	
	/**
	 * get Song At Position
	 * @param index
	 * @return Song
	 */
	public Song getRepPlaylistSongAt(int index){
		return this.reproducingPlaylist.getSongsList().get(index);
	}
	//--------------------------divider in playlist playing e playlist watching
	/**
	 * (not uset yet) rename a playlist name
	 * @param id
	 * @param name
	 */
	public void renamePlaylist(String id, String name){
		FileHelper.renamePlaylist(getPlaylistById(id).getName(), name, PLAYLISTS_FOLDER, PLAYLIST_EXTENSION);
		getPlaylistById(id).renamePlaylist(name);
	}
	
	/**
	 * sets  queue as current palylist when it needs
	 */
	public void setReproducingQueue(){
		this.reproducingPlaylist = this.songQueue;
	}
	
	/**
	 * Delete a playlist from id
	 * @param id
	 */
	public void deletePlaylist(String id, int index){
		FileHelper.deletePlaylist(getPlaylistById(id), PLAYLISTS_FOLDER, PLAYLIST_EXTENSION);
		this.userPlaylists.remove(index);
		
	}
	
	public void addSongToQueue(Song song){
		this.songQueue.addSong(song);
	}
	
	/**
	 * 
	 * remove song form queue
	 *{not used yet}
	 * @param song
	 */
	public void removeSongFromQueue(Song song){
		
		this.songQueue.removeSong(song);
		if(this.getQueue().getId().equals(reproducingPlaylist.getId())&&reproducingPlaylist.size()==0){
			this.setcanPlayNext(false);
		}
	}
	
	/**
	 * Get the queue 
	 * @return IPlaylist
	 */
	public IPlaylist getQueue(){
		return songQueue;
	}
	
	public void setQueueToSHow(){
		this.playlistToShow=this.songQueue;
	}

	
	/**
	 * Add a song to the playlist by index
	 * @param song
	 * @param playListIndex
	 */
	public void addSongToPlaylist(Song song, int playListIndex){
		this.userPlaylists.get(playListIndex).addSong(song);
	}
	
	public void removeSongFromPlaylist(IPlaylist p, Song s){
		this.getPlaylistById(p.getId()).removeSong(s);
		if(p.getId().equals(reproducingPlaylist.getId())&&reproducingPlaylist.size()==0){
			this.setcanPlayNext(false);
		}
	}
	
	public boolean canPlayNext() {
		return canPlayNext;
	}
	public void setcanPlayNext(boolean noNextPrev) {
		this.canPlayNext = noNextPrev;
	}
	/**
	 *  Get all playlists 
	 * @return List<Playlist>
	 */
	public List<Playlist> getAllPlaylists(){
		return this.userPlaylists;
	}
	

	/**
	 * set Current playlist is playing using id
	 * @param id
	 */
	public void setReproducingPlaylist(String id){
		this.reproducingPlaylist = getPlaylistById(id);
		if(getPlaylistById(id) == null){
			System.out.println("current playlist null");
		}
	}
	
	/**
	 * Get is playling
	 * @return IPlaylist
	 */
	public IPlaylist getReproducingPlaylist(){
		return this.reproducingPlaylist;
	}
	
	
	/**
	 * set Current playlist is to show using id
	 * @param id
	 */
	public void setPlaylistToShow(String id){
		this.playlistToShow = getPlaylistById(id);
		if(getPlaylistById(id) == null){
			System.out.println("current playlist null");
		}
	}
	
	/**
	 * Get is playling
	 * @return IPlaylist
	 */
	public IPlaylist getShowPlaylist(){
		return this.playlistToShow;
	}
	
	
	/**
	 * set current song playing buy index
	 * @param index
	 */
	public void setReproducingSong(int index){
		this.reproducingSong = new ReproducingSong(getRepPlaylistSongAt(index), index);
	}
	
	/**
	 * get A playlist By id
	 * @param id
	 * @return Playlist
	 */
	public Playlist getPlaylistById(String id){
		for(Playlist p : userPlaylists){
			if(p.getId().equals(id)){
				return p;
			}
		}
		return null;
	}
	
	/**
	 * get current song is playing 
	 * @return Song
	 */
	public Song getReproducingSong(){
		return this.reproducingSong.song;
	}
	
	public int reproducingSongPosInPlaylist(){
		return this.reproducingSong.getPositionInPlaylist();
	}

	/**
	 * Metod to print messages
	 * @param mes
	 */
	private void log(String mes){
		System.out.println(mes);
	}
	
	/**
	 * get the next Song
	 * @return Song
	 */
	public Song getNextSong(){
		int playListLength = reproducingPlaylist.size();
		if(reproducingSong == null){
			log("null");
		}
		Song nextSongToBePlayed = reproducingPlaylist.getSongsList().get((reproducingSong.getPositionInPlaylist()+1)%playListLength);
		reproducingSong.update(nextSongToBePlayed, ((reproducingSong.getPositionInPlaylist()+1)%playListLength));
		return nextSongToBePlayed;
	}
	/**
	 * get the previous Song
	 * @return Song
	 */
	public Song getPreviousSong(){
		
		int playListLength = reproducingPlaylist.size();
		int nextSongToBePlayedIndex=reproducingSong.getPositionInPlaylist();
		if(nextSongToBePlayedIndex-1 == -1){
			nextSongToBePlayedIndex=playListLength-1;
		}else
		{
			nextSongToBePlayedIndex=reproducingSong.getPositionInPlaylist()-1;
		}
		Song nextSongToBePlayed = reproducingPlaylist.getSongsList().get((nextSongToBePlayedIndex));
		reproducingSong.update(nextSongToBePlayed,  nextSongToBePlayedIndex);
		return nextSongToBePlayed;
	}
	
	/**
	 * 
	 * @author rrok
	 *
	 */
	private class ReproducingSong{
		private Song song;
		private int positionInPlaylist;

		/**
		 * set the current song parameter
		 * @param song
		 * @param positionInPlaylist
		 */
		public ReproducingSong(Song song, int positionInPlaylist){
			this.song = song;
			this.positionInPlaylist = positionInPlaylist;
		}
		/**
		 * Change the current song
		 * @param song
		 * @param positionInPlaylist
		 */
		public void update(Song song, int positionInPlaylist) {
			this.song = song;
			this.positionInPlaylist = positionInPlaylist;
		}
		/**
		 * get the index of the song in playlist
		 * @return int
		 */
		public int getPositionInPlaylist() {
			return positionInPlaylist;
		}
	}
}
