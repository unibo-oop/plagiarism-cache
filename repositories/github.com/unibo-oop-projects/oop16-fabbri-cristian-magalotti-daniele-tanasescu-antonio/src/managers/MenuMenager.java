package managers;

public class MenuMenager {
    
    private GameManager gameManager;

    public MenuMenager() {
        this.setReferences();
    }
    
    private void setReferences(){
        if(gameManager == null){
            if(GameManager.getInstance() != null){
                this.gameManager = GameManager.getInstance();
            }
        }
    }
    
    public GameManager gameManagerInstance(){
        return this.gameManager;
    }

}
