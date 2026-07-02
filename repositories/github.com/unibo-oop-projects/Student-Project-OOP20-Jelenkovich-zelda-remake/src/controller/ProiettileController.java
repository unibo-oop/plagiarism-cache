package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.Node;
import model.Proiettile;
import view.ProiettileView;

public class ProiettileController implements IproiettileController{
	
	private final List<Proiettile> listProiettile;
    private final List<ProiettileView> listProiettileView;
    private static final String IMG_PATH_PROIETTILE = "res/img/proiettile.png";
    
    public ProiettileController() {
    	this.listProiettile = new ArrayList<>();
    	this.listProiettileView = new LinkedList<>();
    }
    
    @Override
    public List<ProiettileView> startProiettileView() {
    	return this.listProiettile.stream().map((element) -> {
            ProiettileView img = new ProiettileView();
            img.setImg(IMG_PATH_PROIETTILE);
            img.setProiettileDim(element.getWidth(), element.getHeight());
            img.updatePos(element.getX(), element.getY());
            return img;
        }).collect(Collectors.toList());
    }

    @Override
	public void createProiettile(double x, double y, double mousex, double mousey) {
		Proiettile p = new Proiettile(x, y, mousex, mousey);
		listProiettile.add(p);
	}
	
    @Override
	public void deleteProiettile(int index) {
		listProiettile.remove(index);
		listProiettileView.remove(index);
	}

    @Override
	public List<Node> getNodeList() {
		this.listProiettileView.clear();
		this.listProiettileView.addAll(this.startProiettileView());
    	List<Node> list = new LinkedList<>();
        for (ProiettileView p : listProiettileView) {
            list.add(p.getRect());
        }
        return list;
	}

    @Override
	public void move() {
		double x, y;
		double nowx, nowy;
		double finalx, finaly;
		double initx;
		double m, q;
		for (ProiettileView p : listProiettileView) {
			x = p.getRect().getX();
			y = p.getRect().getY();
			finalx = this.listProiettile.get(listProiettileView.indexOf(p)).getPosFinalx();
			finaly = this.listProiettile.get(listProiettileView.indexOf(p)).getPosFinaly();
			initx = this.listProiettile.get(listProiettileView.indexOf(p)).getInitx();
			m=(y-finaly)/(x-finalx);
			q=y-(m*x);
			if (initx<finalx) {
				nowx = x+2;
			} else {
				nowx = x-2;
			}
			nowy = (m*nowx) + q;			
			this.listProiettile.get(listProiettileView.indexOf(p)).setX((int)nowx);
			this.listProiettile.get(listProiettileView.indexOf(p)).setY((int)nowy);
			p.updatePos(nowx, nowy);
		}
		
	}

	public List<ProiettileView> getlistProiettileView() {
		return this.listProiettileView;
	}

}
