package controller;

import model.IModel;
import observer.LoginObserver;
import view.ChampionshipView;
import view.MainView.LoginType;
import view.CallBackInterface;
import view.MatchSelector;
import view.ObserverInterface;

/**
 * Login implementation
 * @author francesco
 *
 */

public class LoginController implements LoginObserver {

    private ObserverInterface<LoginObserver> view;
    private LoginType type;
    private IModel model;
    private CallBackInterface callback;
    
    public LoginController(LoginType type, IModel model,CallBackInterface callback) {
    	this.type = type;
    	this.model = model;
    	this.callback = callback;
    }

    
    public void setView(ObserverInterface<LoginObserver> lD){
        this.view = lD;
        this.view.attachObserver(this);
    }
    
    @Override
    public boolean doLogin(String user, String pwd) {
        if(type == LoginType.adm){
	    	if(user.equals("adm") && pwd.equals("adm")){
	    		new ChampionshipView(model,callback).setVisible(true);
	    		callback.setVisibility(false);
	    		return true;
	        } else {
	        	return false;
	        }
    	}else if(type == LoginType.user){
    		if((user.equals("user") && pwd.equals("user")) || (user.equals("adm") && pwd.equals("adm"))){
    			new MatchSelector(model).setVisible(true);
	            return true;
	        } else {
	        	return false;
	        }
    	}
		return false;
    }
}
