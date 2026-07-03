package model;

import java.util.Set;

public interface WorkerHistory {

	/**
	 * 
	 * @param p
	 *      add new worker.
	 *
	 */
	void addWorker(final Worker p);
	/**
	 * 
	 * @return all patient.
	 */
	Set<Worker> getAllWorkers();
	/**
	 * remove a worker.
	 * @param P
	 */
	void removeWorker(final Worker p);

}
