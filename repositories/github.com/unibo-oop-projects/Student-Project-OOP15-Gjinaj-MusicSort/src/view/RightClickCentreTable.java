package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.MainController;
import model.Playlist;
import model.Song;
import model.SongQueue;



public class RightClickCentreTable extends JPopupMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuItem remove;
	JMenu sectionsMenu = new JMenu("Aggiungi Alla Playlist");

	List<JMenuItem> menuPlaylisToAdd = new ArrayList<>();

	public RightClickCentreTable(MainController controller, Song song){
		for(Playlist p: controller.getLibraryManager().getAllPlaylists()){
			menuPlaylisToAdd.add(new JMenuItem(p.getName()));
		}
		for (int i = 0; i < controller.getLibraryManager().getAllPlaylists().size(); i++) {
			sectionsMenu.add(menuPlaylisToAdd.get(i) );
			menuPlaylisToAdd.get(i).addActionListener(new ListenerAddToPlaylist(song,controller,i));
		}
		
		
		remove = new JMenuItem("Rimuovi");
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(controller.getLibraryManager().getShowPlaylist() instanceof SongQueue ){
					controller.getLibraryManager().removeSongFromQueue(song);
				}
				else{
				controller.getLibraryManager().removeSongFromPlaylist(controller.getLibraryManager().getShowPlaylist(), song);
				}
				controller.updatePlaylistView(controller.getLibraryManager().getShowPlaylist());
			}
		});
		this.add(remove);
		this.add(sectionsMenu);
	}
}

class ListenerAddToPlaylist implements ActionListener
{
	int indexOfPlaylist;
	private MainController controller;
	private Song song;

	public ListenerAddToPlaylist(Song song, MainController controller, int indexOfPlaylist) {
		// TODO Auto-generated constructor stub
		this.song=song;
		this.controller=controller;
		this.indexOfPlaylist= indexOfPlaylist;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		controller.getLibraryManager().getAllPlaylists().get(indexOfPlaylist).addSong(song);
		//se aggiungo alla stessa playlist
		controller.updatePlaylistView(controller.getLibraryManager().getShowPlaylist());
		if(controller.getLibraryManager().getAllPlaylists().get(indexOfPlaylist).getId()
				.equals(controller.getLibraryManager().getReproducingPlaylist().getId())){
		controller.setSongSelected(controller.getLibraryManager().reproducingSongPosInPlaylist());
		}
		
	}

}