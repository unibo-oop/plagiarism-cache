package game;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import minigames.MiniGame;
import minigames.MiniGameFactoryImpl;
import model.Model;
import view.View;

public class Controller {

	// singleton instance
	private static Controller INSTANCE = null;

	private View view;
    private Model model;

	private LinkedList<GameObject> object = new LinkedList<GameObject>();
	private LinkedList<MiniGame> games = new LinkedList<MiniGame>();
	private HashMap<ID, List<Listener>> deletionListeners = new HashMap<ID, List<Listener>>();
	private boolean playing = false;

	private Controller(){
        if (INSTANCE != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        } else {
        	
      view = new View(this);
    		model = new Model(this);
    		//audio = new AudioPlayer(mainTheme);
    		view.hide();
        }
	}
	
	public static Controller getInstance(){
        if(INSTANCE == null){
        	INSTANCE = new Controller();
        }
        return INSTANCE;
  }
	
	public synchronized void addObject(GameObject o){
		this.object.add(o);
	}
	
	public synchronized void removeObject(GameObject o){
		this.object.remove(o);
		//if (o.id == ID.Enemy && playing)deletionListeners.stream().forEach(l -> l.notification());
		if(deletionListeners.containsKey(o.getId())){
			deletionListeners.get(o.getId()).stream().forEach(l -> l.notification());
		}
		
	}
	
	public void clear(){
		object.clear();
	}

	public synchronized List<GameObject> getObject(ID id) {
		return getAll().stream().filter(o -> o.id == id).collect(Collectors.toList());
	}
	
	public synchronized List<GameObject> getAll(){
		return new LinkedList<GameObject>(object.stream().collect(Collectors.toList()));
	}
	
	public synchronized List<GameObject> getCollisionable(){
		return getAll().stream().filter(o -> o.getId() == ID.Player || o.getId() == ID.Enemy || o.getId() == ID.Bullet).collect(Collectors.toList());
	}
	
	public void keyPressed(int key){
		model.keyPressed(key);
	}
	
	public void keyReleased(int key){
		model.keyReleased(key);
	}
	
	public void mouseDragged(MouseEvent e) {
		model.mouseDragged(e);
	}

	public void mouseMoved(MouseEvent e) {
		model.mouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e){
		model.mouseClicked(e);
	}
	
	public View getView(){
		return view;
	}
	
	public Model getModel(){
		return model;
	}
	
	public void addGame(MiniGame game){
		games.add(game);
	}
	
	public void addFromList (List<MiniGame> list){
		list.forEach(g -> addGame(g));
	}
	
	public void playNext()throws NoSuchElementException{
		view.show();
		view.menuHide();
		//System.out.println("Games left : " + games.size());
		view.stop();
		model.stop();
		if(games.isEmpty()){
			view.start();
			view.WinScreen();
			view.stop();
		} else {
			MiniGame next = games.removeFirst();
			next.StartGame();
			playing = true;
			view.start();
			model.start();
		}
	}
	
	public void FailState() {
		playing = false;
		clear();
		games.clear();
		//System.out.println("GAME OVER");
		MiniGameFactoryImpl factory = new MiniGameFactoryImpl();
		addFromList(factory.increasingRecipe(true));
		view.menuShow();
		view.hide();
	}
	
	public void SuccessState() {
		playing = false;
		clear();
		//System.out.println("HAI VINTO!");
		playNext();
	}
	
	public void menuHide(){
		view.menuHide();
	}
	
	public void menuShow(){
		view.menuShow();
	}

	public void listenDeletion(Listener l, ID id){
		if(deletionListeners.get(id) == null){
			deletionListeners.put(id, new LinkedList<Listener>());
		}
		deletionListeners.get(id).add(l);
	}
	
	
}
