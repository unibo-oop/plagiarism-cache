package model;

import dataModel.DBDataModel;

public class ReceivablesPayablesModel {
	// anagrafica crediti e debiti
	private final DBDataModel db;

	public ReceivablesPayablesModel(DBDataModel db) {
		super();
		this.db = db;
	}

	public DBDataModel saveDBAndClose() {
		return db;
	}
}
