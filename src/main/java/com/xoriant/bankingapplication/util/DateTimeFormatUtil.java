package com.xoriant.bankingapplication.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.xoriant.bankingapplication.exception.IllegalArgumentException;

public class DateTimeFormatUtil {
	public LocalDate dateFormattrUtil(String date) throws IllegalArgumentException {

		/*
		 * Convert String to date
		 */
		LocalDate localDate = null;
		try {
			if (date != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				localDate = LocalDate.parse(date, formatter);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Give correct format of date!");
		}
		return localDate;
	}

	public boolean checkDate(LocalDate date) {
		boolean flag = true;
		if (date.isAfter(LocalDate.now())) {
			flag = false;
		}
		return flag;
	}

}
