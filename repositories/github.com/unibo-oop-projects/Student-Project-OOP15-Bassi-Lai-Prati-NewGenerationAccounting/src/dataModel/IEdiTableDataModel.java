/**
 * 
 */
package dataModel;

/**
 * @author Pentolo
 *
 */
public interface IEdiTableDataModel extends IDataTableModel {
	void setValueAt(Object value, int column) throws IllegalArgumentException;
}
