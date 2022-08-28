package com.xoriant.bankingapplication.model;

import java.util.HashMap;
import java.util.Map;

public class Manager {
	/**
	 * Static details of manager
	 */
	private static Map<String, Integer> manager = new HashMap<>();

	public static Map<String, Integer> getAdmin() {
		return manager;
	}

	/**
	 * Hard coded password, bankId for manager
	 */
	static {
		manager.put("m123", 000);
	}
}
