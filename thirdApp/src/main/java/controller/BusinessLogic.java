package controller;

import database.SqLiteDB;

public class BusinessLogic {
	public static String getGreeting() {
		String name = SqLiteDB.getData();
		return ("Hallo " + name +"!");
	}
}