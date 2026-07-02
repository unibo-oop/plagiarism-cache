package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Node;
import model.staticobj.StaticObj;
import view.StaticObjView;

public class ObjectControllerLv2 implements IobjectController {
	
	private static final int WIDTH = 900;
	private static final int HEIGHT = 650;
	private static final String IMG_PATH_TREE = "res/img/tree.png";
	private static final String IMG_PATH_STONE = "res/img/stone.png";
	
	private final List<StaticObj> listObjTree;
	private final List<StaticObj> listObjStone;
    private final List<StaticObjView> listObjViewTree;
    private final List<StaticObjView> listObjViewStone;
    
    public ObjectControllerLv2() {
    	this.listObjTree = new ArrayList<>();
        this.createTrees();
        this.listObjStone = new ArrayList<>();
        this.createStone();
        this.listObjViewTree = new LinkedList<>();
        this.listObjViewStone = new LinkedList<>();
    }

	private void createStone() {
		StaticObj stone;
		//lato sx
		stone = new StaticObj(50, 400, IMG_PATH_STONE);
    	this.listObjStone.add(stone);
		stone = new StaticObj(50, 350, IMG_PATH_STONE);
    	this.listObjStone.add(stone);
    	stone = new StaticObj(50, 300, IMG_PATH_STONE);
    	this.listObjStone.add(stone);
    	stone = new StaticObj(50, 250, IMG_PATH_STONE);
    	this.listObjStone.add(stone);
    	stone = new StaticObj(50, 200, IMG_PATH_STONE);
    	this.listObjStone.add(stone);
    	
    	//seconda linea sx
    	int i=350;
    	while (i < HEIGHT-50) {
    		stone = new StaticObj(200, i, IMG_PATH_STONE);
    		this.listObjStone.add(stone);
    		i=i+stone.getWidth();
    	}
    	
    	//basso
    	i=350;
    	while (i < WIDTH-300) {
    		stone = new StaticObj(i, HEIGHT-100, IMG_PATH_STONE);
    		this.listObjStone.add(stone);
    		i=i+stone.getWidth();
    	}
    	
    	//blocco v
    	stone = new StaticObj(400, 300, IMG_PATH_STONE);
		this.listObjStone.add(stone);
    	stone = new StaticObj(450, 250, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(500, 200, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(550, 150, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(600, 200, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(650, 250, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(700, 300, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		
	}
	

	private void createTrees() {
		StaticObj tree;
    	//bordo alberi alto
    	int i=0;
    	while (i < WIDTH) {
    		tree = new StaticObj(i, 0, IMG_PATH_TREE);
    		this.listObjTree.add(tree);
    		i=i+tree.getWidth();
    	}
    	
    	//bordo alberi basso
    	i=0;
    	while (i<WIDTH) {
    		tree = new StaticObj(i, HEIGHT-50, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
    	
    	//bordo alberi sx
    	i=50;
    	while (i<HEIGHT-200) {
    		tree = new StaticObj(0, i, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
    	tree = new StaticObj(0, HEIGHT-100, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	
    	//bordo alberi dx
    	i=50;
    	while (i<HEIGHT/2) {
    		tree = new StaticObj(WIDTH-50, i, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
    	tree = new StaticObj(WIDTH-50, HEIGHT-100, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(WIDTH-50, HEIGHT-150, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(WIDTH-50, HEIGHT-200, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
	}

	@Override
	public List<StaticObjView> startObjViewTree() {
		return this.listObjTree.stream().map((element) -> {
            StaticObjView img = new StaticObjView();
            img.setImg(IMG_PATH_TREE);
            img.setObjDim(element.getWidth(), element.getHeight());
            img.setObjPos(element.getX(), element.getY());
            return img;
        }).collect(Collectors.toList());
	}

	@Override
	public List<StaticObjView> startObjViewStone() {
		return this.listObjStone.stream().map((element) -> {
            StaticObjView img = new StaticObjView();
            img.setImg(IMG_PATH_STONE);
            img.setObjDim(element.getWidth(), element.getHeight());
            img.setObjPos(element.getX(), element.getY());
            return img;
        }).collect(Collectors.toList());
	}

	@Override
	public List<Node> getNodeList() {
		this.listObjViewTree.addAll(this.startObjViewTree());
    	List<Node> list = new LinkedList<>();
        for (StaticObjView o : listObjViewTree) {
            list.add(o.getObj());
        }
        this.listObjViewStone.addAll(this.startObjViewStone());
        for (StaticObjView o : listObjViewStone) {
            list.add(o.getObj());
        }
        return list;
	}

	@Override
	public List<StaticObjView> getObjListViewTree() {
		return this.listObjViewTree;
	}

	@Override
	public List<StaticObjView> getObjListViewStone() {
		return this.listObjViewStone;
	}

}
