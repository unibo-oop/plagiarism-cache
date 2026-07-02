package controller;

import java.util.ArrayList;
import java.util.List;

import model.IPlaylist;
import model.MpegInfo;
import model.Playlist;
import model.Song;
import model.SongQueue;
import model.LibraryManager;
import util.FileHelper;
import util.Mp3Player;
import util.PositionSongThread;
import util.ProgressBarThread;
import view.CentrePanel;
import view.WestPanel;
import view.MainView;
import view.SouthPanel;
import controller.MusicControllerInterface;
/**
 * Controller class.
 * @author Rrok Gjinaj
 *
 */
public class MainController implements MusicControllerInterface {
    
    private MpegInfo mp3Info;
    private boolean paused = true;
    private Mp3Player mp3Player;
    private LibraryManager libraryManager;
    
    @SuppressWarnings("unused")
    private MainView view;
    private CentrePanel centrePanel;
    private SouthPanel southPanel;
    private WestPanel westPanel;
    private ProgressBarThread progressBar;
    private PositionSongThread positionSongThread;
    private boolean started = false;
    
    public boolean isStarted() {
        return started;
    }
    public void setStarted(boolean started) {
        this.started = started;
    }
    /**
     * Controller class.
     * @author Rrok Gjinaj
     * Controller constructor.
     * @param libraryManager
     */
    public MainController(LibraryManager libraryManager) {
        this.mp3Player = Mp3Player.newInstance(this);
        this.mp3Info = MpegInfo.getInstance();
        this.libraryManager =libraryManager ;
        libraryManager.setQueueToSHow();
        
    }
    /**
     * Set the main view with panels
     *
     * @param view MainView
     */
    public void setView(MainView view){
        this.view = view;
        this.centrePanel = view.centrePanel;
        this.westPanel= view.westPanel;
        this.southPanel = view.southPanel;
        updatePlaylistView(libraryManager.getReproducingPlaylist());
        westPanel.setQueSelected();
    }
    
    /**
     * Get Playlist by Id and update the that is playing
     * @param idPlaylist String
     */
    public void onPlaylistSelected(String id){
        libraryManager.setPlaylistToShow(id);
        updatePlaylistView(libraryManager.getShowPlaylist());
        if(libraryManager.getReproducingPlaylist().getId().equals(libraryManager.getShowPlaylist().getId()) && libraryManager.getShowPlaylist().getSongsList().size()>0){
            centrePanel.setTableSongSelected(this.getLibraryManager().reproducingSongPosInPlaylist()%this.getLibraryManager().getReproducingPlaylist().size());
        }
    }
    
    
    public Song getSongPosShowPlaylist(int index){
        
        System.out.println(libraryManager.getShowPlaylist().getName()+" where i'm getting song at index "+ index);
        return libraryManager.getShowPlaylist().getSongsList().get(index);
    }
    
    
    /**
     * Get Playlist Song by Index
     * @param Index Int
     * @return Song Song
     */
    public Song getReproducingPlaylistSong(int index){
        return libraryManager.getRepPlaylistSongAt(index);
    }
    
    /**
     * Update Playlist View changing the model of playlist to show
     * @param IPlaylist playlist
     */
    public void updatePlaylistView(IPlaylist playlist){
        centrePanel.changeModel(playlist);
    }
    
    /**
     * Obtain the MpegInfo instance.
     *
     * @return tagInfo
     */
    @Override
    public MpegInfo getMpegInfo() {
        return this.mp3Info;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        try {
            mp3Player.resume();
            southPanel.setPauseIcon();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.paused = false;
        this.pauseThreads(false);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void play() {
        Song song = libraryManager.getReproducingSong();
        this.setSouthData();
        mp3Player.play(song.getPath());
        this.paused = false;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        positionSongThread.resetPositionThread();
        positionSongThread.setPausedThread(false);
        
    }
    
    /**
     * Get Playlist Song by Index of playlist to play and set the playlist is playing and update current song
     * if Playlist is istance og SongQueue, set current playlist SongQueue
     * @param Index int
     *
     */
    public void onSongDoubleClick(int index){
        
        this.paused=false;
        setStarted(true);
        
        if(libraryManager.getShowPlaylist() instanceof SongQueue){
            libraryManager.setReproducingQueue();
        } else {
            libraryManager.setReproducingPlaylist(libraryManager.getShowPlaylist().getId());
        }
        
        libraryManager.setReproducingSong(index);
        Song song = libraryManager.getRepPlaylistSongAt(index);
        mp3Player.play(song.getPath());
        
        
        this.setSouthData();
        
    }
    
    
    /**
     * Show a JFile choser letting the user to open new track and
     * sets audioFilePath of the new track opened
     * sets the south panel data to show new infromation of the new LibraryManager
     */
    @Override
    public void openTrack(String audioFilePath) {
        
        libraryManager.setcanPlayNext(true);
        
        libraryManager.addSongToQueue(FileHelper.loadSong(audioFilePath));
        
        libraryManager.setReproducingQueue();
        
        libraryManager.setReproducingSong(libraryManager.getQueue().size()-1);
        
        centrePanel.changeModel(libraryManager.getQueue());
        
        westPanel.setQueSelected();
        
        mp3Player.play(audioFilePath);
        
        centrePanel.setTableSongSelected(this.getLibraryManager().reproducingSongPosInPlaylist());
        
        
        setSouthData();
        
    }
    
    /**
     * On track finished, controller calls libraryManager set ask the next Song to Play, if Playlist
     * is finisced, it start form the fist palylist song
     * Reset position of song
     *
     */
    public void onTrackFinished(){
        String nextSongPath = libraryManager.getNextSong().getPath();
        mp3Player.play(nextSongPath);
        setSouthData();
        if(libraryManager.getReproducingPlaylist().getId().equals(libraryManager.getShowPlaylist().getId())){
            centrePanel.setTableSongSelected(this.getLibraryManager().reproducingSongPosInPlaylist()%this.getLibraryManager().getReproducingPlaylist().size());
        }
        
        setStarted(true);
    }
    /**
     * If user selcts Queue
     * will be setted as current playlist the queue beacuse Queue is a particular playlist
     * it update model of song to show
     */
    public void onQueueSelected(){
        libraryManager.setcanPlayNext(true);
        libraryManager.setQueueToSHow();
        centrePanel.changeModel(libraryManager.getQueue());
        if(isStarted()){
            if(libraryManager.getReproducingPlaylist().getId().equals(libraryManager.getShowPlaylist().getId())){
                centrePanel.setTableSongSelected(this.getLibraryManager().reproducingSongPosInPlaylist()%this.getLibraryManager().getReproducingPlaylist().size());
            }
        }
    }
    
    /**
     *permit to rename palylist {Not used yet}
     * @param id String
     * @param name String
     */
    public void renamePlaylist(String id, String name){
        libraryManager.renamePlaylist(id, name);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPaused() {
        return this.paused;
    }
    /**
     * Create new playlist by name given from north panel
     * @param name  String
     * @return Playlist Playlist
     */
    public Playlist createPlaylist(String name){
        Playlist p = libraryManager.createNewPlaylist(name);
        westPanel.refreshWestTable();
        return p;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        mp3Player.pause();
        this.paused = true;
        this.pauseThreads(true);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void nextTrack() {
        if(libraryManager.canPlayNext()){
            mp3Player.play(libraryManager.getNextSong().getPath());
            setSouthData();
            if(libraryManager.getReproducingPlaylist().getId().equals(libraryManager.getShowPlaylist().getId())){
                
                centrePanel.setTableSongSelected(this.getLibraryManager().reproducingSongPosInPlaylist());
            }
            System.out.println("posizione in playlist"+this.getLibraryManager().reproducingSongPosInPlaylist());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void previuosTrack() {
        if(libraryManager.canPlayNext()){
            mp3Player.play(libraryManager.getPreviousSong().getPath());
            setSouthData();
            if(libraryManager.getReproducingPlaylist().getId().equals(libraryManager.getShowPlaylist().getId()))
                centrePanel.setTableSongSelected(this.getLibraryManager().reproducingSongPosInPlaylist());
            
        }
    }
    
    public void setSongSelected(int index0){
        
        //centrePanel.setTableSongSelected(index0);
        if(libraryManager.getReproducingPlaylist().getId().equals(libraryManager.getShowPlaylist().getId()))
            centrePanel.setTableSongSelected(this.getLibraryManager().reproducingSongPosInPlaylist());
        
        
        
    }
    
    /**
     * Get a list of name of playlist stream().forEach
     * @return List<String> List<String>
     */
    public List<String> getPlaylistsNames(){
        List<String> names = new ArrayList<>();
        libraryManager.getAllPlaylists().stream().forEach(p -> {
            names.add(p.getName());
        });
        return names;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public LibraryManager getLibraryManager() {
        return libraryManager;
    }
    
    
    /**
     * {not user yet}  remove a playlist from playlists by name but will be done using as param playlistId
     *
     * @param id String
     * @param index int
     */
    public void removePlaylist(String id, int index) {
        if(libraryManager.getReproducingPlaylist().equals(libraryManager.getShowPlaylist())){
            libraryManager.setReproducingQueue();
        }
        libraryManager.deletePlaylist(id, index);
        westPanel.refreshWestTable();
    }
    
    public void stop() {
        
        
    }
    
    
    /**
     * set in pause icon play
     *
     */
    public void setPauseIcon(){
        southPanel.setPauseIcon();
    }
    
    /**
     * set in play icon pause
     *
     */
    public void setPlayIcon(){
        southPanel.setPlayIcon();
    }
    /**
     * set param of south panel when it is called
     *
     */
    public void setSouthData() {
        
        if(positionSongThread==null){
            positionSongThread = new PositionSongThread(this);
            positionSongThread.start();
        }
        
        if(progressBar==null){
            progressBar=this.southPanel.barThread;
            progressBar.start();
        }
        positionSongThread.setPausedThread(false);
        progressBar.setPaused(paused);
        positionSongThread.resetPositionThread();
        progressBar.cleanBarData();
        
        
        southPanel.enableButtons();
        southPanel.setPauseIcon();
        int totSec= this.getReproducingSongSeconds();
        southPanel.setSouthData(this.libraryManager.getReproducingSong().getDuration().getMin()+":"+this.libraryManager.getReproducingSong().getDuration().getSec()+"  ",
                                totSec);
        
    }
    public void pauseThreads(boolean status){
        positionSongThread.setPausedThread(status);
        progressBar.setPaused(status);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getReproducingSongInfo() {
        return libraryManager.getReproducingSong().getTitle()+" - "+libraryManager.getReproducingSong().getArtist(); 
    }
    /**
     * get the progress bar thread
     * @return ProgressBarThread ProgressBarThread
     */
    public ProgressBarThread getProgressBar() {
        return progressBar;
    }
    
    /**
     * give the current song seconds used to set setMaximum of progress bar
     * @return totalSecondSong int
     */
    public int getReproducingSongSeconds(){
        return this.libraryManager.getReproducingSong().getDuration().getMin() * 60
        + this.libraryManager.getReproducingSong().getDuration().getSec();
    }
    
    /**
     * get the current song position using thread
     * @return SongPos int
     */
    public int getSongPos(){
        return positionSongThread.getValue();
    }
    
    /**
     * Converter from seconds to minutes and hours
     * @param int seconds
     * @return
     */
    public int[] splitToComponentTimes(int i)
    {
        int Val = i;
        int hours = (int) Val / 3600;
        int remainder = (int) Val - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        
        int[] ints = {hours , mins , secs};
        return ints;
    }
    
}
