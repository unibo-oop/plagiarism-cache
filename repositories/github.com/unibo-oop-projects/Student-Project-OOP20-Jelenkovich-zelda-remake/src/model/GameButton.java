package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

public class GameButton extends Button{
	
	public GameButton(String text) {
		setText(text);
		setStyle("-fx-background-color:transparent; -fx-background-image: url('/res/img/btMenu.png');");
		setPrefHeight(50);
		setPrefWidth(150);
		btListener();
	}
	
	/**
	 * implementazione effetti bottone
	 */
	private void btListener() {
		setOnMouseEntered(new EventHandler <>(){
			@Override
			public void handle(javafx.scene.input.MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler <>(){
			@Override
			public void handle(javafx.scene.input.MouseEvent event) {
				setEffect(null);
			}
		});
		
		
	}
}
