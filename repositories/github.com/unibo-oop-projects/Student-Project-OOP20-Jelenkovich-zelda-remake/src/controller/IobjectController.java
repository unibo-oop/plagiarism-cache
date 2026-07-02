package controller;

import javafx.scene.Node;
import view.StaticObjView;
import java.util.List;

public interface IobjectController {
	
	/**
	 * creazione lista di oggetti statici view (alberi)
	 * @return
	 */
    List<StaticObjView> startObjViewTree();
    
    /**
     * creazione lista di oggetti statici view (massi)
     * @return
     */
    List<StaticObjView> startObjViewStone();

    /**
     * creazione node lista a partire dalla view list
     * @return node list
     */
    List<Node> getNodeList();

    /**
     * 
     * @return lista di oggetti statici view (alberi)
     */
    List<StaticObjView> getObjListViewTree();
    
    /**
     * 
     * @return lista oggetti statici view (massi)
     */
    List<StaticObjView> getObjListViewStone();
}
