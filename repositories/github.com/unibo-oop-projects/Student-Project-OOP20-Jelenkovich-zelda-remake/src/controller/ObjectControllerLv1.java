package controller;

import javafx.scene.Node;
import model.staticobj.StaticObj;
import view.StaticObjView;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ObjectControllerLv1 implements IobjectController{
	
	private static final int WIDTH = 900;
	private static final int HEIGHT = 650;
	private static final String IMG_PATH_TREE = "res/img/tree.png";
	private static final String IMG_PATH_STONE = "res/img/stone.png";
	
	private final List<StaticObj> listObjTree;
	private final List<StaticObj> listObjStone;
    private final List<StaticObjView> listObjViewTree;
    private final List<StaticObjView> listObjViewStone;
    
    public ObjectControllerLv1() {
        this.listObjTree = new ArrayList<>();
        this.createTrees();
        this.listObjStone = new ArrayList<>();
        this.createStone();
        this.listObjViewTree = new LinkedList<>();
        this.listObjViewStone = new LinkedList<>();
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
    
    private List<StaticObj> createTrees() {
    	StaticObj tree;
    	//bordo alberi alto
    	int i=0;
    	while (i < WIDTH) {
    		tree = new StaticObj(i, 0, IMG_PATH_TREE);
    		this.listObjTree.add(tree);
    		i=i+tree.getWidth();
    	}
    	i=0;
    	while (i < WIDTH) {
    		tree = new StaticObj(i, 50, IMG_PATH_TREE);
    		this.listObjTree.add(tree);
    		i=i+tree.getWidth();
    	}
    	//bordo alberi sx
    	tree = new StaticObj(0, 100, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	tree = new StaticObj(0, 150, IMG_PATH_TREE);
    	this.listObjTree.add(tree);
    	i=300;
    	while (i<HEIGHT) {
    		tree = new StaticObj(0, i, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
    	//bordo alberi basso
    	i=50;
    	while (i<WIDTH) {
    		tree = new StaticObj(i, HEIGHT-50, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
    	i=50;
    	while (i<WIDTH) {
    		tree = new StaticObj(i, HEIGHT-100, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
    	//bordo alberi dx
    	i=100;
    	while (i<HEIGHT-200) {
    		tree = new StaticObj(WIDTH-50, i, IMG_PATH_TREE);
        	this.listObjTree.add(tree);
        	i = i + tree.getHeight();
    	}
		return listObjTree;
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
    
    
    private List<StaticObj> createStone() {
    	StaticObj stone;
    	//primo blocco stone
    	int x;
    	for (int y=200; y<=350; y=y+50) {
    		x=200;
    		while (x<400) {
        		stone = new StaticObj(x, y, IMG_PATH_STONE);
            	this.listObjStone.add(stone);
            	x = x + stone.getHeight();
        	}
    	}
    	
    	//secondo blocco stone
    	for (int y=300; y<=400; y=y+50) {
    		x=550;
    		while (x<750) {
        		stone = new StaticObj(x, y, IMG_PATH_STONE);
            	this.listObjStone.add(stone);
            	x = x + stone.getHeight();
        	}
    	}
    	
    	return listObjStone;
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
