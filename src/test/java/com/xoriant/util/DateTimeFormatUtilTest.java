package com.xoriant.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.xoriant.bankingapplication.exception.IllegalArgumentException;
import com.xoriant.bankingapplication.util.DateTimeFormatUtil;

class DateTimeFormatUtilTest {

	/*
	 * It should give exception when it is not in expected format
	 */
	@Test
	public void wrongformatInput() {
		try {
			String date = "2021-06 ";
			new DateTimeFormatUtil().dateFormattrUtil(date);
		} catch (IllegalArgumentException e) {
			assertEquals("Give correct format of date!", e.getMessage());
		}
	}

	/*
	 * DateOfBirth can be null, it will accept that
	 */
	@Test
	public void nullInput() throws IllegalArgumentException {
		assertEquals(new DateTimeFormatUtil().dateFormattrUtil(null), null);
	}
	
	/*
	 * To check whether given date before today's date means true
	 */
	@Test
	public void afterTodaysDate () throws IllegalArgumentException{
		DateTimeFormatUtil dt=new DateTimeFormatUtil();
		LocalDate date= dt.dateFormattrUtil("2022-01-22");
		assertTrue(dt.checkDate(date));
	}
	
	/*
	 * To check whether given date after today's date means false
	 */
	@Test
	public void afterTodaysDateWrongTestCase () throws IllegalArgumentException{
		DateTimeFormatUtil dt=new DateTimeFormatUtil();
		LocalDate date= dt.dateFormattrUtil("2022-04-22");
		assertFalse(dt.checkDate(date));
	}

}
