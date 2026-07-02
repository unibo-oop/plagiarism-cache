package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Node;
import model.staticobj.StaticObj;
import view.StaticObjView;

public class ObjectControllerLv3 implements IobjectController {
	private static final int WIDTH = 900;
	private static final int HEIGHT = 650;
	private static final String IMG_PATH_TREE = "res/img/tree.png";
	private static final String IMG_PATH_STONE = "res/img/stone.png";
	
	private final List<StaticObj> listObjTree;
	private final List<StaticObj> listObjStone;
    private final List<StaticObjView> listObjViewTree;
    private final List<StaticObjView> listObjViewStone;
    
    public ObjectControllerLv3() {
    	this.listObjTree = new ArrayList<>();
        this.createTrees();
        this.listObjStone = new ArrayList<>();
        this.createStone();
        this.listObjViewTree = new LinkedList<>();
        this.listObjViewStone = new LinkedList<>();
    }

	private void createStone() {
		StaticObj stone;
		int i=50;
    	while (i < 550) {
    		stone = new StaticObj(i, 350, IMG_PATH_STONE);
    		this.listObjStone.add(stone);
    		i=i+stone.getWidth();
    	}
    	i=50;
    	while (i < 550) {
    		stone = new StaticObj(i, 400, IMG_PATH_STONE);
    		this.listObjStone.add(stone);
    		i=i+stone.getWidth();
    	}
		stone = new StaticObj(450, 300, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(500, 300, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(450, 250, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(500, 250, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(450, 200, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(500, 200, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(550, 200, IMG_PATH_STONE);
		this.listObjStone.add(stone);
		stone = new StaticObj(600, 200, IMG_PATH_STONE);
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
    	
    	//bordo alberi dx
    	i=50;
    	while (i<HEIGHT) {
    		tree = new StaticObj(WIDTH-50, i, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
    	
    	//bordo alberi sx
    	tree = new StaticObj(0, 50, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 100, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 150, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 200, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 350, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 400, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 550, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 600, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 650, IMG_PATH_TREE);
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
