package com;

import com.database.DatabaseUtility;
import com.impl.SendInputQueryTask;

/**
 * Send Scanner input directly to a MYSQL Database!
 * @author Dennis
 *
 */
public class InputHandler {

	public static void main(String[] args) {
		try {
			DatabaseUtility.init();
			DatabaseUtility.sendTask(new SendInputQueryTask());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}