/**
 * 
 */
package controller.dbController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.UUID;

import dataModel.Company;
import dataModel.Customers_Suppliers;
import dataModel.DBDataModel;
import dataModel.IDataTableModel;
import dataModel.Movement;
import dataModel.Product;
import view.AbstractFrame;

/**
 * classe che gestisce l'output su DataBase
 * 
 * @author Pentolo
 *
 */
public class DBSaver extends AbstractDB {

	public static void addCompany(final UUID uuid) {
		new DBSaver(uuid.toString(), null, new DBDataModel(loadDefaultAcconts(), new LinkedList<Customers_Suppliers>(),
				new LinkedList<Movement>(), new LinkedList<Product>(), uuid.toString())).start();
	}

	private static void deleteDirectory(final File path) {
		if (path.exists()) {
			for (final File file : path.listFiles()) {
				if (file.isDirectory()) {
					deleteDirectory(file);
				} else {
					file.delete();
				}
			}
			path.delete();
		}
	}

	public static void removeCompany(final String path) {
		deleteDirectory(getDBDirectory(path));
	}

	private static void save(final boolean mustbeSaved, final File file,
			final LinkedList<? extends IDataTableModel> linkedList) throws IOException {
		boolean save = mustbeSaved;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IOException("Errore critico di creazione database. " + e.getMessage());
			}
			save = true;
		}
		if (save) {
			try (ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)))) {
				out.writeObject(linkedList);
			} catch (IOException e) {
				throw new IOException("Errore critico di scrittura. " + e.getMessage());
			}
		}
	}

	public static void saveCompanys(final LinkedList<Company> companys) throws IOException {
		save(true, getCompanyFile(), companys);
	}

	public DBSaver(final String path, final AbstractFrame view, final DBDataModel db) {
		super(path, view, db);
	}

	@Override
	public void run() {
		final DBDataModel db = getDb();
		try {
			getDBDirectory(db.getPath()).mkdir();
			save(db.isAccountsModified(), getAccountFile(), db.getAccounts());
			save(db.isCustomersSuppliersModified(), getCustomersupplierFile(), db.getCustomersSuppliers());
			save(db.isMovimentsModified(), getMovementFile(), db.getMoviments());
			save(db.isProductsModified(), getProductFile(), db.getProducts());
		} catch (Exception e) {
			getView().errorDialog("Errore di Scrittura", e.getMessage());
		}
		db.resetBooleans();
	}
}
