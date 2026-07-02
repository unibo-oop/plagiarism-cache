package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import model.Song;
import model.SongQueue;
import model.IPlaylist;
import model.MpegInfo;
import model.Playlist;

public class FileHelper {

	/**
	 * Load The song using song Path
	 * @param path String
	 * @return Song
	 */
	public static Song loadSong(String path) {
		File file = null;
		MpegInfo mp3Info = null;
		try{
			file = new File(path);
			mp3Info = MpegInfo.getInstance();
			mp3Info.load(file);
		} catch (Exception e){
			log("File not loaded");
			return null;
		}
		return SongBuilder.buildSong(mp3Info, path);
	}

	
	/**
	 *  Used to save all palylists that were created or were the have ad change
	 * @param playlist IPlaylist
	 * @param path String
	 * @param playListExtension String
	 */
	public static void persistPlaylist(IPlaylist playlist, String path, String playListExtension) {
		System.out.println(playlist.getName());
		if (playlist.getStatus() == Playlist.Status.UPDATED) {
			System.out.println(playlist.getName());
			String filepath = path + "/" + playlist.getName() + playListExtension;
			try {
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(new File(filepath))));
				for (Song s : playlist.getSongsList()) {
					bw.write(s.getPath());
					bw.newLine();
				}
				bw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * delete Playlist passing params
	 * @param playlist Playlist
	 * @param path String
	 * @param playListExtension String
	 */
	public static void deletePlaylist(Playlist playlist, String path, String playListExtension) {
		String playlistPath = path + "/" + playlist.getName() + playListExtension;
		new File(playlistPath).delete();
	}

	/**
	 * Rename Playlist with the new name
	 * @param oldName String
	 * @param newName String
	 * @param path String
	 * @param playListExtension String
	 */
	public static void renamePlaylist(String oldName, String newName, String path, String playListExtension) {
		String oldPath = path + "/" + oldName + playListExtension;
		String newPath = path + "/" + newName + playListExtension;
		File oldFile = new File(oldPath);
		File newFile = new File(newPath);
		if (oldFile.renameTo(newFile)) {
			System.out.println("renamed");
		} else {
			System.out.println("Error renaming");
		}
	}

	/**
	 * Load All playlists 
	 * @param path String
	 * @param playListExtension String
	 * @param queueExtension String
	 * @return List<IPlaylist> List<IPlaylist>
	 */
	public static List<IPlaylist> loadPlayLists(String path, String playListExtension, String queueExtension) {
		//se ce la cartella res/playlist
		//else altrimenit crea 
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		List<IPlaylist> playlists = new ArrayList<>();
		log("Loading playlsts..");
		
		if(listOfFiles ==null){
			//torno solo la queue vuota
			IPlaylist queue;
			queue = SongQueue.getInstance();
			playlists.add(queue);
			return playlists;
		}
		else{
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && (file.getName().endsWith(playListExtension) || file.getName().endsWith(queueExtension))) {
				Scanner s;
				IPlaylist playlist;
				if(file.getName().endsWith(playListExtension)){
					String playlistName = file.getName().replace(playListExtension, "");
					playlist = new Playlist(playlistName);
				} else {
					playlist = SongQueue.getInstance();
				} 
				try {
					s = new Scanner(file);
					while (s.hasNextLine()) {
						Song song = loadSong(s.nextLine());
						if(song != null){
							playlist.addSong(song);
						}
					}
					playlist.setStatus(Playlist.Status.CONSISTENT);
					playlists.add(playlist);
					s.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (IPlaylist playlist : playlists) {
			log(playlist.toString());
		}
		return playlists;
		}
	}

	/**
	 * Log to print errors there were 
	 * @param message
	 */
	private static void log(String message) {
		System.out.println(message);
	}

}
