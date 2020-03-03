package com.database;

import java.util.Optional;

/**
 * A Query Task handler for sending various information
 * to the Database, used primarily to record issues,
 * debugging, much more.
 * @author Dennis
 */
public abstract class QueryTask {
	
	/**
	 * The Task that is being submited to the Database
	 * via query task
	 * @param input
	 * @param createNewStmt
	 * @return {@link #submitTask(String)}
	 */
	public abstract Optional<QueryTask> submitTask();
}