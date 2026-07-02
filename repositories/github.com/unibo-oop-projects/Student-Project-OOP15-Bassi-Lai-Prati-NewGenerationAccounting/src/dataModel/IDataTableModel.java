/**
 * 
 */
package dataModel;

import java.io.Serializable;

/**
 * interfaccia per tutte le classi del dataModel che possono essere visualizzate
 * dentro un MyTableModel
 * 
 * @author Pentolo
 *
 */
public interface IDataTableModel extends Serializable {
	Object getValueAt(int column);
}
