package javarogue.ui.game.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javarogue.config.ConfigGraphics;

public class GUI {

	private Image gui;
	private GraphicsContext context;
	
	// TODO implement map with stat enum.
	private Map<String, String> overlayText;
	private Map<Integer, String> inventoryText;
	private Font font;
	
	// State booleans
	private boolean isInventoryOpened;
	
	public GUI(GraphicsContext context) {
		this.loadImage();
		this.initText();
		this.context = context;
	}

	public void draw() {
		double width = this.context.getCanvas().getWidth();
		double height = this.context.getCanvas().getHeight();
		if (this.isInventoryOpened) {
			this.context.setFill(Color.BLACK);
			this.context.fillRect(0, 0, width, height);
			this.context.setFill(Color.WHITE);
			// TODO implement when inventory is done
			this.context.fillText(this.inventoryText.get(0), width / 20, height / 18);
		} else {
			this.drawHUD(width, height);
		}
	}
	
	public void changeInventoryState() {
		if(this.isInventoryOpened) {
			this.isInventoryOpened = false;
		} else {
			this.isInventoryOpened = true;
		}
	}
	
	private void drawHUD(double width, double height) {

		double hOffset = width / 100;
		double vOffset = height - height / 10;

		double spacing = ConfigGraphics.resolutionWidth / 60;
		
		this.context.drawImage(this.gui, 0, 0, width, height);
		this.context.setFont(this.font);
		this.context.setFill(Color.WHITE);
		// Health Text
		this.context.fillText(this.overlayText.get("health"), hOffset, vOffset);
		this.context.fillText(this.overlayText.get("healthVal"), hOffset + spacing * 8, vOffset);
		// Hunger Text
		this.context.fillText(this.overlayText.get("hunger"), hOffset, vOffset + spacing * 2);
		this.context.fillText(this.overlayText.get("hungerVal"), hOffset + spacing * 8, vOffset + spacing * 2);
		// Level Text
		this.context.fillText(this.overlayText.get("level"), hOffset + spacing * 12, vOffset);
		this.context.fillText(this.overlayText.get("levelVal"), hOffset + spacing * 20, vOffset);
		// Depth Text
		this.context.fillText(this.overlayText.get("depth"), hOffset + spacing * 12, vOffset + spacing * 2);
		this.context.fillText(this.overlayText.get("depthVal"), hOffset + spacing * 20, vOffset + spacing * 2);
	}

	private void loadImage() {
		try {
			this.gui = new Image(new FileInputStream(new File("res/GUI.png")), ConfigGraphics.resolutionWidth,
					ConfigGraphics.resolutionHeight, true, true);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("GUI.png not found!");
		}
	}
	
	private void initText() {
		try {
			this.font = Font.loadFont(new FileInputStream(new File("font/font.ttf")), ConfigGraphics.resolutionWidth / 60);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("font.ttf file not found!");
		}
		this.overlayText = new HashMap<>();
		this.overlayText.put("health", "Health: ");
		this.overlayText.put("healthVal", "X");
		this.overlayText.put("hunger", "Hunger: ");
		this.overlayText.put("hungerVal", "X");
		this.overlayText.put("level", "Level: ");
		this.overlayText.put("levelVal", "X");
		this.overlayText.put("depth", "Depth: ");
		this.overlayText.put("depthVal", "X");
		
		this.inventoryText = new HashMap<>();
		this.inventoryText.put(0, "Hello!");
	}
	
}
