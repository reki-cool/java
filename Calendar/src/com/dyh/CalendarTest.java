package com.dyh;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {

	public static void main(String[] args) {

		//construct d as current date
		GregorianCalendar d = new GregorianCalendar();
		
		int today = d.get(Calendar.DAY_OF_MONTH);//current day
		int month = d.get(Calendar.MONTH); //current month
		
		//set d to start date of the month
		d.set(Calendar.DAY_OF_MONTH, 1);//the start day of the month
		
		int weekday = d.get(Calendar.DAY_OF_WEEK); 
		
		//get first day of week (Sunday in the U.S.)
		int firstDayOfWeek = d.getFirstDayOfWeek();
		
		//determine the required indentation for the first line
		int indent = 0; 
		while(weekday != firstDayOfWeek)
		{
			indent++;
			d.add(Calendar.DAY_OF_MONTH, -1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}
		
		//weekday is same as firstDayOfWeek,now!
		
		//print weekday names
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
		
		do {
			System.out.printf("%4s",weekdayNames[weekday]);
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}while(weekday != firstDayOfWeek); //the loop will has 7 times
		
		System.out.println();
		
		for(int i = 1; i <= indent; i++)
			System.out.printf("    "); //every blankwidth is 4 unit
		
		d.set(Calendar.DAY_OF_MONTH, 1);
		
		do {
			//print day
			int day = d.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);
			
			//mark current day with *
			if(day == today) System.out.printf("*");
			else System.out.printf(" ");
			
			//advance d to the next day
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
			
			//start a new line at the start of the week
			if(weekday == firstDayOfWeek) System.out.println();
			
		}while(d.get(Calendar.MONTH) == month);
		//the loop exits when d is day 1 of the next month
		
		
		//print final end of line if necessary
		if(weekday != firstDayOfWeek) System.out.println();
	}

}
